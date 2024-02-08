package ru.ifmo.se.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceUtil {
    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if (entityManager == null)
            buildEntityManager();
        return entityManager;
    }

    private static void buildEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Point");
        entityManager = factory.createEntityManager();
    }
}
