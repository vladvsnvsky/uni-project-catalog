package com.example.temadoi.dao;

import com.example.temadoi.models.Profesor;

import java.util.List;

import com.example.temadoi.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProfesorDAO implements IProfesorDAO{

    private SessionFactory sessionFactory;

    public ProfesorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ProfesorDAO() {

    }

    public void addProfesor(Profesor profesor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(profesor);
            transaction.commit();
        }
    }

    public List<Profesor> getAllProfesori() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Profesor", Profesor.class).list();
        }
    }

    public Profesor getProfesorById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Profesor.class, id);
        }
    }

    public void updateProfesor(Profesor profesor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(profesor);
            transaction.commit();
        }
    }

    public void deleteProfesor(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Profesor profesor = session.get(Profesor.class, id);
            if (profesor != null) {
                session.delete(profesor);
            }
            transaction.commit();
        }
    }

    public Profesor findByUsernameAndPassword(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Profesor WHERE username = :username AND password = :password";
            Query<Profesor> query = session.createQuery(hql, Profesor.class);
            ((Query<?>) query).setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResult(); // Returnează studentul sau null dacă nu este găsit
        } catch (Exception e) {
            // Tratarea excepțiilor și logarea, dacă este necesar
            e.printStackTrace();
            return null;
        }
    }
}

