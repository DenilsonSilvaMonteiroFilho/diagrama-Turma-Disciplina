package com.example.denilson.DiagramaTurma;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TurmaController {

    private final TurmaRepository repository;

    private final TurmaModelAssembler assembler;

    TurmaController(TurmaRepository repository, TurmaModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/turmas")
    CollectionModel<EntityModel<Turma>> all() {

        List<EntityModel<Turma>> employees = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(TurmaController.class).all()).withSelfRel());
    }
    @PostMapping("/turma")
    Turma newEmployee(@RequestBody Turma newTurma) {
        return repository.save(newTurma);
    }

    @GetMapping("/turma/{id}")
    EntityModel<Turma> one(@PathVariable Long id) {

        Turma turma = repository.findById(id) //
                .orElseThrow(() -> new TurmaNotFoundException(id));

        return assembler.toModel(turma);
    }

    @PutMapping("/turma/{id}")
    Turma replaceTurma(@RequestBody Turma newTurma, @PathVariable Long id) {
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
