package org.example;

import java.sql.SQLException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static List<BusDriver> drivers = new ArrayList<>();
    private static List<BusRoute> routes = new ArrayList<>();
    private static AssignmentBoard assignmentBoard = new AssignmentBoard();

    public static void main(String[] args) throws SQLException {
        int choice;
        do {
            showMenu();
            waitForEnter();
            choice = getChoice();
            executeChoice(choice);
        } while (choice != 6);
        scanner.close();

        System.out.println("\nLưu dữ liệu vào database...");
        DatabaseSetup.connectToDatabase(drivers, routes);
    }

    private static void showMenu() {
        System.out.println("\n===== Chương Trình Quản Lý Lái Xe Buýt =====");
        System.out.println("1. Thêm Lái Xe");
        System.out.println("2. Thêm Tuyến");
        System.out.println("3. Phân Công Lái Xe");
        System.out.println("4. Sắp Xếp Danh Sách Phân Công");
        System.out.println("5. Tổng Khoảng Cách Chạy Xe Trong Ngày");
        System.out.println("6. Thoát & Lưu Dữ Liệu Vào Database");
        System.out.print("Hãy chọn chức năng (1-6): ");
    }

    private static int getChoice() {
        int userChoice = 0;
        try {
            userChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\nVui lòng nhập số từ 1 đến 6.");
        }
        return userChoice;
    }

    private static void executeChoice(int choice) {
        switch (choice) {
            case 1:
                addBusDriver();
                break;
            case 2:
                addRoute();
                break;
            case 3:
                assign();
                break;
            case 4:
                sortAssignment();
                break;
            case 5:
                calculateTotalDistance();
                break;
            case 6:
                System.out.println("Chương trình kết thúc.");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                break;
        }
    }

    private static void waitForEnter() {
        System.out.println("\nNhấn Enter để tiếp tục...");
    }

    private static void addBusDriver() {
        System.out.println("\nNhập thông tin lái xe: ");
        System.out.println("\n--- Thêm Lái Xe ---");
        System.out.print("Nhập họ tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Nhập trình độ (A-F): ");
        String degree = scanner.nextLine().toUpperCase();

        try {
            BusDriver busDriver = new BusDriver(name, address, phoneNumber, degree);
            drivers.add(busDriver);
            System.out.println("Thêm lái xe thành công. Mã LX: " + busDriver.getBusDriverCode());
            System.out.println("Danh sách lái xe: " + drivers.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        System.out.println("Quay lại màn hình chính");
    }

    private static void addRoute() {
        System.out.println("\nNhập thông tin tuyến xe: ");
        System.out.println("\n--- Thêm Tuyến Xe ---");
        System.out.print("Nhập khoảng cách: ");
        String distance = scanner.nextLine();
        System.out.print("Nhập số điểm dừng: ");
        String numberOfStops = scanner.nextLine();


        try {
            BusRoute busRoute = new BusRoute(distance, numberOfStops);
            routes.add(busRoute);
            System.out.println("Thêm tuyến xe thành công. Mã LX: " + busRoute.getBusRouteCode());
            System.out.println("Danh sách tuyến xe: " + routes.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        System.out.println("Quay lại màn hình chính");
    }

    private static void assign() {
        System.out.println("\nNhập thông tin phân công: ");
        System.out.println("\n--- Bảng Phân Công ---");
        System.out.println("Danh sách phân công hiện tại: ");
        assignmentBoard.displayAssignments();
        System.out.println("\nDanh sách lái xe: ");
        int driverIndex = 0;
        for (BusDriver driver : drivers) {
            driverIndex++;
            System.out.println(driverIndex + ": " + driver.toString());
        }
        System.out.println("Danh sách tuyến xe: ");
        int routeIndex = 0;
        for (BusRoute route : routes) {
            routeIndex++;
            System.out.println(routeIndex + ": " + route.toString());
        }


        System.out.print("\nNhập lái xe muốn phân công (nhập số thứ tự hiện ở trên): ");
        String busDriverIndex = scanner.nextLine();
        if (!convertibableToInt(busDriverIndex)) {
            System.out.println("Chỉ chấp nhận dữ liệu dạng số");
            System.out.println("Quay lại màn hình chính");
            return;
        }
        int bIndex = Integer.parseInt(busDriverIndex);
        BusDriver busDriver = null;
        try {
            busDriver = drivers.get(bIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Lỗi: " + e.getMessage());
            System.out.println("Quay lại màn hình chính");
            return;
        }

        System.out.print("Nhập tuyến xe muốn phân công (nhập số thứ tự hiện ở trên): ");
        String busRouteIndex = scanner.nextLine();
        if (!convertibableToInt(busRouteIndex)) {
            System.out.println("Chỉ chấp nhận dữ liệu dạng số");
            System.out.println("Quay lại màn hình chính");
            return;
        }
        int rIndex = Integer.parseInt(busRouteIndex);
        BusRoute busRoute = null;
        try {
            busRoute = routes.get(rIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Lỗi: " + e.getMessage());
            System.out.println("Quay lại màn hình chính");
            return;
        }

        System.out.print("Số lượt cần lái trên tuyến này: ");
        String numberOfTrips = scanner.nextLine();
        if (!convertibableToInt(numberOfTrips)) {
            System.out.println("Chỉ chấp nhận dữ liệu dạng số");
            System.out.println("Quay lại màn hình chính");
            return;
        }
        assignmentBoard.addAssignment(busDriver, busRoute, Integer.parseInt(numberOfTrips));
    }

    private static void sortAssignment() {
        System.out.println("\nChọn kiểu sắp xếp phân công bên dưới: ");
        System.out.println("A. Theo Họ tên lái xe");
        System.out.println("B. Theo Số lượng tuyến đảm nhận trong ngày (giảm dần)");
        List<Assignment> assignments = assignmentBoard.getAssignments();
        String input = scanner.nextLine().toUpperCase();
        if (input.length() == 1) {
            switch (input) {
                case "A":
                    sortByDriverName(assignments);
                    assignmentBoard.displayAssignments();
                    System.out.println("----------------");
                    break;
                case "B":
                    sortByNumberOfRoutes(assignments);
                    assignmentBoard.displayAssignments();
                    System.out.println("----------------");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    break;
            }
        } else {
            System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    private static void sortByDriverName(List<Assignment> assignments) {
        Collections.sort(assignments, new DriverNameComparator());
    }

    private static void sortByNumberOfRoutes(List<Assignment> assignments) {
        Map<BusDriver, Integer> routeCountMap = new HashMap<>();

        for (Assignment assignment : assignments) {
            BusDriver driver = assignment.getDriver();
            routeCountMap.put(driver, routeCountMap.getOrDefault(driver, 0) + 1);
        }

        Collections.sort(assignments, (a1, a2) -> {
            BusDriver driver1 = a1.getDriver();
            BusDriver driver2 = a2.getDriver();
            int count1 = routeCountMap.get(driver1);
            int count2 = routeCountMap.get(driver2);
            return Integer.compare(count2, count1); // Sort in descending order
        });
    }

    private static void calculateTotalDistance() {
        List<Assignment> assignments = assignmentBoard.getAssignments();
        Map<BusDriver, Double> totalDistance = new HashMap<>();

        for (Assignment assignment : assignments) {
            BusDriver driver = assignment.getDriver();
            BusRoute route = assignment.getRoute();
            int numberOfTrips = assignment.getNumberOfTrips();
            double distance = route.getDistance() * numberOfTrips;
            totalDistance.merge(driver, distance, Double::sum);
        }

        if (totalDistance.isEmpty()) {
            System.out.println("Chưa có dữ liệu...");
        }

        for (Map.Entry<BusDriver, Double> entry : totalDistance.entrySet()) {
            BusDriver driver = entry.getKey();
            double distance = entry.getValue();

            System.out.println("Lái xe: " + driver.toString() + " | Tổng khoảng cách chạy xe trong ngày: " + distance + "km");
        }
    }

    private static boolean convertibableToInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}