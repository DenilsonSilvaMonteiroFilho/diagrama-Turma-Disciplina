package com.example.denilson.DiagramaTurma;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Turma {
    private @Id @GeneratedValue Long id;
    private String disciplina;
    private String professor;

    private String aluno;

    public Turma(){}

    public Turma(String disciplina, String professor, String aluno){
        this.disciplina = disciplina;
        this.professor = professor;
        this.aluno = aluno;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getAluno() { return aluno; }

    public void setAluno(String aluno) { this.aluno = aluno; }

}
