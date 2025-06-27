package com.bodiva.wisdomacademy.backend.service;

import com.bodemer.myarmo.database.Abfrage;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Service
public class BesucherService {

    private static final Logger logger = LoggerFactory.getLogger(BesucherService.class);

    @Autowired
    private Abfrage abfrage;

    public void logVisitor(UserAgent userAgent, String ipAddress, String website) {
        try {
            String browser = userAgent != null ? userAgent.getBrowser().getName() + " " + userAgent.getBrowserVersion() : "Unknown";
            String operatingSystem = userAgent != null ? userAgent.getOperatingSystem().getManufacturer() + " " + userAgent.getOperatingSystem().getName() : "Unknown";
            String device = userAgent != null ? userAgent.getOperatingSystem().getDeviceType().getName() : "Unknown";
            String userAgentString = userAgent != null ? userAgent.getUserAgentString() : "Unknown";
            String land = "unknown";
            String abfrageText = "";

            if (ipAddress != null && !ipAddress.trim().isEmpty()) {
                try {
                    String[] geoData = getCountryFromIp(ipAddress).split("###");
                    land = geoData[0];
                    abfrageText = geoData.length > 1 ? geoData[1].replace("\"", "").replace("'", "") : "";
                } catch (Exception e) {
                    logger.error("Error fetching geolocation for IP: {}", ipAddress, e);
                }
            }

            insertBesucher(browser, operatingSystem, userAgentString, ipAddress, land, device, abfrageText, website);
        } catch (Exception e) {
            logger.error("Error logging visitor for website: {}", website, e);
        }
    }

    private String getCountryFromIp(String ipAddress) {
        if (ipAddress == null || ipAddress.trim().equals("127.0.0.1")) {
            return "localhost###Local request";
        }

        try {
            URL api = new URL("http://ip-api.com/json/" + ipAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(api.openStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String json = response.toString();
            String country = json.contains("\"country\":\"") ? json.split("\"country\":\"")[1].split("\"")[0] : "unknown";
            return country + "###" + json;
        } catch (Exception e) {
            logger.error("Error fetching geolocation for IP: {}", ipAddress, e);
            return "unknown###Error fetching location";
        }
    }

    private void insertBesucher(String browser, String operatingSystem, String userAgent, String ipAddress, String land, String device, String ipLocation, String website) {
        String sql = "INSERT INTO besucher(datum, browser, operatingsystem, useragent, ipadresse, land, geraet, iplocation, website) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = abfrage.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            stmt.setString(2, browser);
            stmt.setString(3, operatingSystem);
            stmt.setString(4, userAgent);
            stmt.setString(5, ipAddress);
            stmt.setString(6, land);
            stmt.setString(7, device);
            stmt.setString(8, ipLocation);
            stmt.setString(9, website);
            stmt.executeUpdate();
            logger.info("Inserted visitor record for website: {}", website);
        } catch (SQLException e) {
            logger.error("Error inserting visitor record for website: {}", website, e);
        }
    }
}