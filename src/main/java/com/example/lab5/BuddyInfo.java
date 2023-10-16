package com.example.lab5;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BuddyInfo {

    @Id
    private Integer id = null;
    private String number = null;
    private String name = null;

    public BuddyInfo() {}

    public BuddyInfo(String buddyName, String buddyNumber, int id) {
        name = buddyName;
        number = buddyNumber;
        this.id = id;
    }

    public Integer getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
