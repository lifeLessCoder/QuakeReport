package com.example.android.quakereport;

public class Earthquake {
    private double magnitude;
    private String locationText;
    private long timestamp;
    private String url;

    public Earthquake(double magnitude, String locationText, long timestamp, String url) {
        this.magnitude = magnitude;
        this.locationText = locationText;
        this.timestamp = timestamp;
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocationText() {
        return locationText;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUrl() {
        return url;
    }
}
