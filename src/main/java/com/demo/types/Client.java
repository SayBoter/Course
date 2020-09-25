package com.demo.types;

import java.util.Date;

public class Client {
    private int id;
    private String fio;
    private String birthday;
    private String homeAdress;
    private String sex;
    private String phoneNumber;

    public Client(int id, String fio, String birthday, String home_adress, String sex, String phone_number) {
        this.id = id;
        this.fio = fio;
        this.birthday = birthday;
        this.homeAdress = home_adress;
        this.sex = sex;
        this.phoneNumber = phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHomeAdress() {
        return homeAdress;
    }

    public void setHomeAdress(String homeAdress) {
        this.homeAdress = homeAdress;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
