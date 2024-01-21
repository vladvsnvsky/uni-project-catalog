package com.example.temadoi.dao;

import com.example.temadoi.models.Student;
import java.util.List;

public interface IStudentDAO {
    void addStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(int id);
    void updateStudent(Student student);
    void deleteStudent(int id);
}
