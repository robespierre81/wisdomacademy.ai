package com.bodiva.wisdomacademy.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "blockchain")
@Getter
public class BlockchainConfig {

    @Value("${blockchain.rpc.url}")
    private String rpcUrl;

    @Value("${blockchain.private.key}")
    private String privateKey;

    @Value("${blockchain.coursemanager.address}")
    private String courseManagerAddress;
}
