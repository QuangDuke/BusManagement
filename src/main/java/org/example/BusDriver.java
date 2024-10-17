package org.example;

import java.util.Arrays;

public class BusDriver {
    private static int startingBusDriverCode = 10000;
    int busDriverCode;
    String busDriverName;
    String address;
    String phoneNumber;
    String degree;
    String[] degrees = {"A", "B", "C", "D", "E", "F"};

    public BusDriver(String busDriverName, String address, String phoneNumber, String degree) {
        this.busDriverCode = startingBusDriverCode++;
        setBusDriverName(busDriverName);
        setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.setDegree(degree);
    }

    //Getters
    public int getBusDriverCode() {
        return busDriverCode;
    }

    public String getBusDriverName() {
        return busDriverName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDegree() {
        return degree;
    }

    //Setters
    public void setBusDriverName(String busDriverName) {
        if (!busDriverName.isEmpty()) {
            this.busDriverName = busDriverName;
        } else {
            throw new IllegalArgumentException("Tên lái xe không được để trống!");
        }
    }

    public void setAddress(String address) {
        if (!address.isEmpty()) {
            this.address = address;
        } else {
            throw new IllegalArgumentException("Địa chỉ không được để trống!");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 9) {
            throw new IllegalArgumentException("Độ dài số điện thoại không hợp lệ! (SĐT dài ít nhất 9 số)");
        }

        try {
            Long.parseLong(phoneNumber);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Định dạng số điện thoại không hợp lệ");
        }

        this.phoneNumber = phoneNumber;
    }


    public void setDegree(String degree) {
        if (Arrays.asList(degrees).contains(degree)) {
            this.degree = degree;
        } else {
            throw new IllegalArgumentException("Trình độ phải từ A đến F.");
        }
    }

    @Override
    public String toString() {
        return "[Mã lái xe= " + busDriverCode + ", Họ tên= " + busDriverName + ", Địa chỉ= " + address + ", SĐT: " + phoneNumber + ", Bằng cấp: " + degree + "]";
    }
}
