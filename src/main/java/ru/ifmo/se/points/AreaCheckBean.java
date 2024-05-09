package ru.ifmo.se.points;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.inject.Named;
import ru.ifmo.se.points.Point.ShotResult;

@Named("area")
public class AreaCheckBean {
    public Point calculate(double valueX, double valueY, int valueR) {
        Point point = new Point();

        point.setX(valueX);
        point.setY(valueY);
        point.setR(valueR);

        long starTime = System.nanoTime();
        if ((valueX <= 0 && valueY <= 0 && Math.pow(valueX, 2) + Math.pow(valueY, 2) <= Math.pow(valueR, 2)) ||
                (valueX <= 0 && valueY >= 0 && -valueX <= ((double) valueR) / 2 && valueY <= valueR) ||
                valueX >= 0 && valueY >= 0 && valueY + valueX <= valueR)
            point.setShotResult(ShotResult.HIT);
        else
            point.setShotResult(ShotResult.MISS);
        long endTime = System.nanoTime();

        point.setExecutionTime(endTime - starTime);
        point.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        return point;

    }

    public void log() {
        System.out.println("works");
    }
}
