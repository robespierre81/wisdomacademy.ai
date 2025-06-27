package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.repository.CoursePurchaseRepository;
import com.bodiva.wisdomacademy.backend.model.CoursePurchase;
import com.bodiva.wisdomacademy.blockchain.CourseManager; // this is the web3j-generated wrapper
import com.bodiva.wisdomacademy.config.BlockchainConfig;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.crypto.Credentials;

import java.util.concurrent.Executors;
import org.web3j.protocol.core.DefaultBlockParameterName;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoursePurchaseEventListener {

    private final CoursePurchaseRepository coursePurchaseRepository;
    private final BlockchainConfig blockchainConfig;

    @PostConstruct
    public void listenToCoursePurchases() {
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                Web3j web3 = Web3j.build(new HttpService(blockchainConfig.getRpcUrl()));
                Credentials credentials = Credentials.create(blockchainConfig.getPrivateKey());

                CourseManager contract = CourseManager.load(
                        blockchainConfig.getCourseManagerAddress(),
                        web3,
                        credentials,
                        new DefaultGasProvider()
                );

                log.info("📡 Listening to CoursePurchased events...");

                contract.coursePurchasedEventFlowable(
                        DefaultBlockParameterName.EARLIEST,
                         DefaultBlockParameterName.LATEST)
                        .subscribe(event -> {
                            String user = event.user;
                            int courseId = event.courseId.intValue();

                            log.info("📥 Course Purchased: {} => course {}", user, courseId);

                            // Only insert if not already present (in case of reorgs etc.)
                            boolean exists = coursePurchaseRepository.existsByUserWalletAddressAndCourseId(user, courseId);
                            if (!exists) {
                                CoursePurchase purchase = new CoursePurchase();
                                purchase.setUserWalletAddress(user);
                                purchase.setCourseId(courseId);
                                coursePurchaseRepository.save(purchase);
                                log.info("✅ Saved purchase in DB");
                            } else {
                                log.info("ℹ️ Already recorded in DB.");
                            }
                        });

            } catch (Exception e) {
                log.error("❌ Error listening to CoursePurchased events: ", e);
            }
        });
    }
}
