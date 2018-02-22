package com.hoteling.hotelApp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Guest implements Serializable {
    private static final long serialVersionUID = 41856156L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id", nullable=false, updatable = false)
    private long id;
    private String name;
    private String address;
    private byte[] idProof;
    private Date checkInTime;
    private Date checkoutTime;
    private double amount;
    private String roomNumber;
    private boolean active;
    private String otherDetails;

    public long getId() {
        return id;
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

    public byte[] getIdProof() {
        return idProof;
    }

    public void setIdProof(byte[] idProof) {
        this.idProof = idProof;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(Date checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }
}
