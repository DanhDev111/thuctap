package com.example.thuctap.entity;


public enum GenderName {
    MALE("Nam"),
    FEMALE("Nữ"),
    OTHER("Khác");

    private  String displayName;
    GenderName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
