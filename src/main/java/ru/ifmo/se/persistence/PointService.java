package ru.ifmo.se.persistence;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ru.ifmo.se.points.Point;

public class PointService {
    private final EntityManager entityManager;

    public PointService() {
        entityManager = HibernateUtil.getEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void savePoint(Point p) {
        entityManager.getTransaction().begin();
        entityManager.persist(p);
        entityManager.getTransaction().commit();
    }

    public List<Point> findPointsByUserName(String userName) {
        TypedQuery<Point> query = entityManager.createQuery("select p from Point p where p.userName=:userName",
                Point.class);
        query.setParameter("userName", userName);
        List<Point> points = (List<Point>) query.getResultList();

        return points;
    }

    public void clearPointsByUserName(String userName) {
        entityManager.getTransaction().begin();
        var query = entityManager.createQuery("delete from Point p where p.userName=:userName");
        query.setParameter("userName", userName);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

}
