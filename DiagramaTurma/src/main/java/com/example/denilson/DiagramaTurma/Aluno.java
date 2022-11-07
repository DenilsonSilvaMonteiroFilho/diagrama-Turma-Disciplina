package com.example.denilson.DiagramaTurma;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = ALUNO)
public class Aluno {

    private static final String ALUNO = "";
    private @Id @GeneratedValue Long id;
    private String nome;
    private @GeneratedValue String matricula;
    private Status status;

    public Aluno(){

    }
    public Aluno(String nome, Status status){
        this.nome = nome;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(this.id, aluno.id) && Objects.equals(this.nome, aluno.nome) &&
                Objects.equals(this.matricula, aluno.matricula) && this.status == aluno.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nome, this.matricula, this.status);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + this.id +
                ", nome='" + this.nome + '\'' +
                ", matricula='" + this.matricula + '\'' +
                ", status=" + this.status +
                '}';
    }
}
