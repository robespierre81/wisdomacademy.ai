package com.bodiva.wisdomacademy.backend.controller;

import com.bodemer.myarmo.database.Abfrage;
import com.bodiva.wisdomacademy.backend.dto.VisitorLogRequest;
import com.bodiva.wisdomacademy.backend.service.BesucherService;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    @Autowired
    private BesucherService besucherService;

    @Autowired
    private Abfrage abfrage;

    @PostMapping("/log")
    public ResponseEntity<String> logVisitor(@RequestBody VisitorLogRequest visitorLogRequest) {
        logger.info("Received visitor log request, User-Agent: {}, Website: {}", 
            visitorLogRequest.getUserAgent(), visitorLogRequest.getWebsite());
        try {
            String userAgentString = visitorLogRequest.getUserAgent();
            if (userAgentString == null || userAgentString.isEmpty()) {
                logger.warn("User-Agent is missing in request");
                return ResponseEntity.badRequest().body("User-Agent is missing");
            }
            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
            String website = visitorLogRequest.getWebsite() != null ? visitorLogRequest.getWebsite() : "wisdomacademy.ai";
            String ipAddress = visitorLogRequest.getIpAddress(); // May be null if not sent
            besucherService.logVisitor(userAgent, ipAddress, website);
            logger.info("Visitor logged successfully for website: {}", website);
            return ResponseEntity.ok("Visitor logged successfully");
        } catch (Exception e) {
            logger.error("Error logging visitor", e);
            return ResponseEntity.status(500).body("Error logging visitor: " + e.getMessage());
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<ArrayList<ArrayList>> getVisitorStats(@RequestParam String website) {
        logger.info("Fetching visitor stats for website: {}", website);
        try {
            String sql = "SELECT EXTRACT(YEAR FROM datum) AS year, COUNT(*) AS total_visitors " +
                         "FROM besucher WHERE website = ? " +
                         "GROUP BY EXTRACT(YEAR FROM datum) ORDER BY year DESC";
            ArrayList<ArrayList> stats = abfrage.getAbfrageErgebnis(sql, website);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            logger.error("Error fetching visitor stats for website: {}", website, e);
            return ResponseEntity.status(500).body(null);
        }
    }
}