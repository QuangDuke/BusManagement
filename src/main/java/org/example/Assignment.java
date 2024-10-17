package org.example;

public class Assignment {

    private BusDriver driver;
    private BusRoute route;
    private int numberOfTrips;

    public Assignment(BusDriver driver, BusRoute route, int numberOfTrips) {
        this.driver = driver;
        this.route = route;
        this.numberOfTrips = numberOfTrips;
    }

    //Getters
    public BusDriver getDriver() {
        return driver;
    }

    public BusRoute getRoute() {
        return route;
    }

    public int getNumberOfTrips() {
        return numberOfTrips;
    }

    @Override
    public String toString() {
        return "Lái xe: " + driver + " - Tuyến phân công: " + route + " - Số lượt trên tuyến này: " + numberOfTrips;
    }

}
