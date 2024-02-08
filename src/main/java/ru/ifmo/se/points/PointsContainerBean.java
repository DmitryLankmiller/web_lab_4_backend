package ru.ifmo.se.points;

import java.util.List;

import jakarta.ejb.Singleton;
import ru.ifmo.se.persistence.PointService;

@Singleton(name = "points")
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
