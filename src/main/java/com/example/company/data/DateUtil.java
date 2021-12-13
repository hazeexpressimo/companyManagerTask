package com.example.company.data;

import java.time.LocalTime;

public class DateUtil {
    public static int getDeltaHours(LocalTime empStart, LocalTime empEnd, LocalTime depStart, LocalTime depEnd) {
        int deltaHours;
        int deltaStart;
        int deltaEnd;
        if (empStart.isAfter(depStart)) {
            deltaStart = empStart.minusHours(depStart.getHour()).getHour();
        } else {
            deltaStart = depStart.minusHours(empStart.getHour()).getHour();
        }
        if (empEnd.isAfter(depEnd)) {
            deltaEnd = empEnd.minusHours(depEnd.getHour()).getHour();
        } else {
            deltaEnd = depEnd.minusHours(empEnd.getHour()).getHour();
        }
        deltaHours = deltaStart + deltaEnd;
        return deltaHours;
    }
}
