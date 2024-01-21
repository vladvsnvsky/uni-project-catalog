package com.example.temadoi.dao;

import com.example.temadoi.models.Disciplina;
import java.util.List;

public interface IDisciplinaDAO {
    void addDisciplina(Disciplina disciplina);
    List<Disciplina> getAllDiscipline();
    Disciplina getDisciplinaById(int id);
    void updateDisciplina(Disciplina disciplina);
    void deleteDisciplina(int id);
}
