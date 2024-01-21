package com.example.temadoi.models;

import java.util.List;

public class Medie {
    public String disciplina;
    public int valoare;

    public Medie(Disciplina disciplina, List<Nota> note){
        this.disciplina = disciplina.getNume();
        int s = 0;
        if(note.size()==0)
        {
            valoare = 0;
        }
        else{
            for(Nota n : note){
                s+=n.getValoare();
            }
            valoare = s/note.size();
        }
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getValoare() {
        return valoare;
    }

    public void setValoare(int valoare) {
        this.valoare = valoare;
    }
}
