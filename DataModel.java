package com.example.myapp.models;

import com.google.gson.annotations.SerializedName;

public class DataModel {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    // Add other fields here matching your API response

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
