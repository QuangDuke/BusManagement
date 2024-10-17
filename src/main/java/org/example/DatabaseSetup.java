package org.example;

import java.sql.*;
import java.util.List;

public class DatabaseSetup {
    private static final String URL = "jdbc:mysql://localhost:3306/bus_management";
    private static final String USER = "root";
    private static final String PASSWORD = "root@1108";

    public static void connectToDatabase(List<BusDriver> drivers, List<BusRoute> routes) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); Statement statement = connection.createStatement();  PreparedStatement driversStatement=connection.prepareStatement("insert into bus_driver values(?,?,?,?,?)"); PreparedStatement routesStatement=connection.prepareStatement("insert into bus_route values(?,?,?)")){
            System.out.println("Connection successful!");
            connection.setAutoCommit(false);

            statement.execute("CREATE DATABASE IF NOT EXISTS bus_management");
            statement.execute("USE bus_management");

            statement.execute("CREATE TABLE IF NOT EXISTS bus_driver(id int(5) PRIMARY KEY NOT NULL,name varchar(40),address varchar(40),phone_number varchar(11),level varchar(3))");
            for (BusDriver driver : drivers) {
                driversStatement.setInt(1, driver.getBusDriverCode());
                driversStatement.setString(2, driver.getBusDriverName());
                driversStatement.setString(3,driver.getAddress());
                driversStatement.setString(4,driver.getPhoneNumber());
                driversStatement.setString(5,driver.getDegree());
                driversStatement.addBatch();
            }
            driversStatement.executeBatch();

            statement.execute("CREATE TABLE IF NOT EXISTS bus_route(id int(3) PRIMARY KEY NOT NULL,distance DOUBLE,number_of_stops int(5))");
            for (BusRoute route : routes) {
                routesStatement.setInt(1, route.getBusRouteCode());
                routesStatement.setDouble(2, route.getDistance());
                routesStatement.setInt(3, route.getNumberOfStops());
                routesStatement.addBatch();
            }
            routesStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }

}

