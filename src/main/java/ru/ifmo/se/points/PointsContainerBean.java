package ru.ifmo.se.points;

import java.util.List;

import jakarta.inject.Named;
import ru.ifmo.se.persistence.PointService;

@Named("points")
public class PointsContainerBean {

    private PointService dataBase;

    public PointsContainerBean() {
        dataBase = new PointService();
    }

    public synchronized List<Point> getPointsByUserName(String userName) {
        return dataBase.findPointsByUserName(userName);
    }

    public synchronized void addPoint(Point bean) {
        dataBase.savePoint(bean);
    }

    public synchronized void clearPointsByUserName(String userName) {
        dataBase.clearPointsByUserName(userName);
    }
}
