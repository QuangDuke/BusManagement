package org.example;

import java.util.ArrayList;
import java.util.List;

public class AssignmentBoard {
    private List<Assignment> assignments = new ArrayList<>();

    public void addAssignment(BusDriver driver, BusRoute route, int numberOfTrips) {
        for (Assignment assignment : assignments) {
            if (assignment.getDriver().equals(driver) && assignment.getRoute().equals(route)) {
                System.out.println("\nTài xế đã được phân công tuyến xe này. Phân công thất bại!");
                return;
            }
        }

        int totalTrips = 0;
        for (Assignment assignment : assignments) {
            if (assignment.getDriver().equals(driver)) {
                totalTrips = assignment.getNumberOfTrips() + numberOfTrips;
                if (totalTrips > 15) {
                    System.out.println("\nTổng số lượt trong ngày của tài xế k được vượt quá 15. Phân công thất bai");
                    System.out.println("Tổng số lượt của tài xế " + driver + " hiện tại: " + assignment.getNumberOfTrips());
                    return;
                }
            }
        }

        assignments.add(new Assignment(driver, route, numberOfTrips));
        System.out.println("\nPhân công thành công!");
        System.out.println("Danh sách phân công mới: ");
        displayAssignments();
    }

    public void displayAssignments() {
        if (!assignments.isEmpty()) {
            for (Assignment assignment : assignments) {
                System.out.println(assignment.toString());
            }
        } else {
            System.out.println("--Trống--");
        }
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }


}
