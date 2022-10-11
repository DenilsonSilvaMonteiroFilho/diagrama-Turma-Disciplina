package com.example.denilson.DiagramaTurma;

public class TurmaNotFoundException extends RuntimeException {
    public TurmaNotFoundException(Long id) {
        super("Could not find turma " + id);
    }
}
