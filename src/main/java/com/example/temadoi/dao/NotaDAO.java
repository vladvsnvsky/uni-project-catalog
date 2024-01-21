package com.example.temadoi.dao;

import com.example.temadoi.models.Nota;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class NotaDAO implements INotaDAO{

    private SessionFactory sessionFactory;

    public NotaDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public NotaDAO() {

    }

    @Override
    public void addNota(Nota nota) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Începe o tranzacție
            transaction = session.beginTransaction();

            // Salvează nota în baza de date
            session.save(nota);

            // Trimite tranzacția
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Dacă apare o eroare, anulează tranzacția
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Nota> getAllNote() {
        return null;
    }

    @Override
    public List<Nota> getNoteByStudentId(int studentId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Nota> query = session.createQuery("FROM Nota WHERE student.id = :studentId", Nota.class);
            query.setParameter("studentId", studentId);
            return query.list();
        }
    }


    @Override
    public Nota getNotaById(int id) {
        try (Session session = sessionFactory.openSession()) {
            // Găsește nota cu ID-ul specificat
            return session.get(Nota.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateNota(Nota nota) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Începe o tranzacție
            transaction = session.beginTransaction();

            // Actualizează nota în baza de date
            session.update(nota);

            // Trimite tranzacția
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Dacă apare o eroare, anulează tranzacția
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNota(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Începe o tranzacție
            transaction = session.beginTransaction();

            // Găsește nota cu ID-ul specificat
            Nota nota = session.get(Nota.class, id);
            if (nota != null) {
                // Șterge nota din baza de date
                session.delete(nota);
            }

            // Trimite tranzacția
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Dacă apare o eroare, anulează tranzacția
            }
            e.printStackTrace();
        }
    }

    public List<Nota> getNoteByStudentAndDisciplina(int studentId, int disciplinaId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Nota> query = session.createQuery(
                    "FROM Nota WHERE student.id = :studentId AND disciplina.id = :disciplinaId", Nota.class);
            query.setParameter("studentId", studentId);
            query.setParameter("disciplinaId", disciplinaId);
            return query.list();
        }
    }

    public List<Nota> getNoteByDisciplineId(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Nota> query = session.createQuery(
                    "FROM Nota WHERE disciplina.id = :id", Nota.class);
            query.setParameter("id", id);
            return query.list();
        }
    }

    public double getAverageByStudentAndDiscipline(int studentId, int disciplinaId) {
        List<Nota> note = getNoteByStudentAndDisciplina(studentId, disciplinaId);
        int size = note.size();
        int sum = 0;
        for(Nota n : note)
            sum += n.getValoare();
        return sum /= size;
    }
}
