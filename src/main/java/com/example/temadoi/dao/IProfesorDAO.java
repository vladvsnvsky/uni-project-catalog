package com.example.temadoi.dao;

import com.example.temadoi.models.Profesor;
import java.util.List;

public interface IProfesorDAO {
    void addProfesor(Profesor profesor);
    List<Profesor> getAllProfesori();
    Profesor getProfesorById(int id);
    void updateProfesor(Profesor profesor);
    void deleteProfesor(int id);
}
