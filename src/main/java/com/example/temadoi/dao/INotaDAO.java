package com.example.temadoi.dao;

import com.example.temadoi.models.Nota;
import java.util.List;

public interface INotaDAO {
    void addNota(Nota nota);
    List<Nota> getAllNote();
    List<Nota> getNoteByStudentId(int id);
    Nota getNotaById(int id);
    void updateNota(Nota nota);
    void deleteNota(int id);
}
