package org.example;

public class BusRoute {
    private static int startingBusRouteCode = 100;
    int busRouteCode;
    double distance;
    int numberOfStops;

    public BusRoute(String distance, String numberOfStops) {
        this.busRouteCode = startingBusRouteCode++;
        setDistance(distance);
        setNumberOfStops(numberOfStops);
    }

    //Getters
    public int getBusRouteCode() {
        return busRouteCode;
    }

    public double getDistance() {
        return distance;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    //Setters
    public void setDistance(String distance) {
        if (distance == null || distance.isEmpty()) {
            throw new IllegalArgumentException("Khoảng cách không được để trống!");
        }

        try {
            Double.parseDouble(distance);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Định dạng khoảng cách không hợp lệ");
        }

        if (Double.parseDouble(distance) > 0) {
            this.distance = Double.parseDouble(distance);
        } else {
            throw new IllegalArgumentException("Khoảng cách phải lớn hơn 0");
        }
    }

    public void setNumberOfStops(String numberOfStops) {
        if (numberOfStops == null || numberOfStops.isEmpty()) {
            throw new IllegalArgumentException("Số điểm dừng không được để trống!");
        }

        try {
            Integer.parseInt(numberOfStops);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Định dạng số điểm dừng không hợp lệ");
        }

        if (Integer.parseInt(numberOfStops) > 0) {
            this.numberOfStops = Integer.parseInt(numberOfStops);
        } else {
            throw new IllegalArgumentException("Số điểm dừng phải lớn hơn 0");
        }

    }

    @Override
    public String toString() {
        return "[Mã tuyến= " + busRouteCode + ", Khoảng cách= " + distance + "km" + ", Số điểm dừng= " + numberOfStops + "]";
    }
}
