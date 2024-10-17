package org.example;

import java.util.Comparator;

public class DriverNameComparator implements Comparator<Assignment> {
    @Override
    public int compare(Assignment a1, Assignment a2) {
        return a1.getDriver().getBusDriverName().compareTo(a2.getDriver().busDriverName);
    }
}
