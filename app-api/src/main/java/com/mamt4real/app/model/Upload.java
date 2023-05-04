package com.mamt4real.app.model;

public class Upload {
    private long id;
    private String filepath;

    public Upload() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Upload(String filepath) {
        this.filepath = filepath;
    }
}
