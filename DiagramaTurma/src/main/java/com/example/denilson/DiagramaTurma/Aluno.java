package com.example.denilson.DiagramaTurma;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Aluno")
public class Aluno {

    private static final String ALUNO = "";
    private @Id @GeneratedValue Long id;
    private String nome;
    private @GeneratedValue String matricula;
    private StatusAluno statusAluno;

    public Aluno(){

    }
    public Aluno(String nome, StatusAluno statusAluno){
        this.nome = nome;
        this.statusAluno = statusAluno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public StatusAluno getStatus() {
        return statusAluno;
    }

    public void setStatus(StatusAluno statusAluno) {
        this.statusAluno = statusAluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(this.id, aluno.id) && Objects.equals(this.nome, aluno.nome) &&
                Objects.equals(this.matricula, aluno.matricula) && this.statusAluno == aluno.statusAluno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nome, this.matricula, this.statusAluno);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + this.id +
                ", nome='" + this.nome + '\'' +
                ", matricula='" + this.matricula + '\'' +
                ", statusAluno=" + this.statusAluno +
                '}';
    }
}
