/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Asus
 */
public class Dealer {
    private String id;
    private String name;
    private String address;
    private String phone;
    private Status status;
    public Dealer() {};
    public Dealer(String id, String name, String address, String phone, Status status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

    public String toString() {
        return String.format("[%s, %s, %s, %s]", this.id, this.name, this.address, this.phone, this.status);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
