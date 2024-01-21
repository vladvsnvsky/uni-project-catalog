package com.example.temadoi.dao;

import com.example.temadoi.models.Disciplina;
import com.example.temadoi.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class DisciplinaDAO implements IDisciplinaDAO{

    private SessionFactory sessionFactory;

    public DisciplinaDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void addDisciplina(Disciplina disciplina) {

    }

    @Override
    public List<Disciplina> getAllDiscipline() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Disciplina ", Disciplina.class).list();
        }
    }

    @Override
    public Disciplina getDisciplinaById(int id) {
        return null;
    }

    @Override
    public void updateDisciplina(Disciplina disciplina) {

    }

    @Override
    public void deleteDisciplina(int id) {

    }

    public List<Disciplina> getDisciplinesByTeacherId(int id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Disciplina WHERE profesor.id = :profesorId";
            Query<Disciplina> query = session.createQuery(hql, Disciplina.class);
            query.setParameter("profesorId", id);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
