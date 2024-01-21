package com.example.temadoi.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Creează o instanță de SessionFactory din configurarea hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Afișează un mesaj de eroare și termină execuția în cazul unei erori
            System.err.println("Crearea SessionFactory a eșuat." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Închide cache-urile și pool-urile de conexiuni
        getSessionFactory().close();
    }
}

