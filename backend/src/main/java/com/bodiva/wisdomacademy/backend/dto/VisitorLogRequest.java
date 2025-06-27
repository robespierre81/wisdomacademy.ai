package com.bodiva.wisdomacademy.backend.dto;

public class VisitorLogRequest {
    private String userAgent;
    private String website;
    private String ipAddress; // Optional, if sent from frontend or derived server-side

    // Getters and setters
    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}