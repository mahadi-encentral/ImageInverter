package com.mamt4real.entities;

import javax.persistence.*;

@Entity
@Table(name = "uploads")
public class JpaUpload {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "filepath", nullable = false)
    private String filepath;

    public JpaUpload() {
    }

    public JpaUpload(String filepath) {
        this.filepath = filepath;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
