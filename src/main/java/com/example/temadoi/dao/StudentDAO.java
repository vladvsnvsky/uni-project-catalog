package com.example.temadoi.dao;

import com.example.temadoi.models.Student;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class StudentDAO implements IStudentDAO{

    private SessionFactory sessionFactory;

    public StudentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public StudentDAO() {

    }

    public void addStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        }
    }

    public List<Student> getAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }

    public Student getStudentById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, id);
        }
    }

    public void updateStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        }
    }

    public void deleteStudent(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
            }
            transaction.commit();
        }
    }

    public Student findByUsernameAndPassword(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Student WHERE username = :username AND password = :password";
            Query<Student> query = session.createQuery(hql, Student.class);
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

