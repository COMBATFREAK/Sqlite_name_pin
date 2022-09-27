package com.example.sqlite;

import androidx.annotation.NonNull;

public class CustomerModel {
    private int id;
    private String name;
    private int pin;
    private boolean isactive;


    public CustomerModel(int id,String name, int pin, boolean isactive) {
        this.name = name;
        this.pin = pin;
        this.isactive = isactive;
        this.id = id;
    }

    public CustomerModel(){}

    @NonNull
    @Override
    public String toString() {
        return "["+
                "id: "+id+
                " ,name: "+name+
                " ,Pin: "+pin+
                " ,is Active: "+isactive+"]";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean Isactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
