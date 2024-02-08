package ru.ifmo.se.points;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Point implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "userName")
    @NotNull
    private String userName;

    @Column(name = "x")
    @NotNull
    private double X;

    @Column(name = "y")
    @NotNull
    private double Y;

    @Column(name = "r")
    private Integer R;

    @Column(name = "shotResult")
    @NotNull
    @Enumerated
    private ShotResult shotResult;

    @Column(name = "time")
    @NotNull
    private String time;

    @Column(name = "executionTime")
    @NotNull
    private long executionTime;

    public Point() {
        X = 0;
        Y = 0;
        R = 0;
        shotResult = ShotResult.MISS;
        time = LocalTime.NOON.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        executionTime = 0;
    }

    public String getUserName() {
        return userName;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public int getR() {
        return R;
    }

    public ShotResult getShotResult() {
        return shotResult;
    }

    public String getTime() {
        return time;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setX(double x) {
        X = x;
    }

    public void setY(double y) {
        Y = y;
    }

    public void setR(int r) {
        R = r;
    }

    public void setShotResult(ShotResult shotResult) {
        this.shotResult = shotResult;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public enum ShotResult {
        HIT("hit"), MISS("miss");

        private final String name;

        private ShotResult(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
