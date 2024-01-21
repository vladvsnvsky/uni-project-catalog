package com.example.temadoi.models;
import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "discipline")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nume")
    private String nume;

    @ManyToOne
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;

    @OneToMany(mappedBy = "disciplina")
    private List<Nota> note;

    // Constructori, getteri și setteri

    public Disciplina(int id, String nume, Profesor profesor, List<Nota> note) {
        this.id = id;
        this.nume = nume;
        this.profesor = profesor;
        this.note = note;
    }

    public Disciplina() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Nota> getNote() {
        return note;
    }

    public void setNote(List<Nota> note) {
        this.note = note;
    }
}
