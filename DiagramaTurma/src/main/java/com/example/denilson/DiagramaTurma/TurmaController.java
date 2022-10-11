package com.example.denilson.DiagramaTurma;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public class TurmaController {

    private final TurmaRepository repository;

    TurmaController(TurmaRepository repository){
        this.repository = repository;
    }

    @GetMapping("/turmas")
    List<Turma> all() {
        return repository.findAll();
    }
    @PostMapping("/turma")
    Turma newEmployee(@RequestBody Turma newTurma) {
        return repository.save(newTurma);
    }

    @GetMapping("/turma/{id}")
    Turma one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new TurmaNotFoundException(id));
    }

    @PutMapping("/turma/{id}")
    Turma replaceTurma(@RequestBody Turma newTurma, @PathVariable Long id) {
        /*NAO ENTENDI ISSO ->*/
        return repository.findById(id)
                .map(turma -> {
                    turma.setDisciplina(newTurma.getDisciplina());
                    turma.setProfessor(newTurma.getProfessor());
                    return repository.save(turma);
                })
                .orElseGet(() -> {
                    newTurma.setId(id);
                    return repository.save(newTurma);
                });
    }

    @DeleteMapping("/turma/{id}")
    void deleteTurma(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
