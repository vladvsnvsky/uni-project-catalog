package com.example.temadoi.models;


import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nume")
    private String nume;

    @Column(name = "prenume")
    private String prenume;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    // Constructori, getteri și setteri


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

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
}

