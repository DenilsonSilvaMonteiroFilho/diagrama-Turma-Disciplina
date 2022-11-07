package com.example.denilson.DiagramaTurma;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity <?> newTurma(@RequestBody Turma newTurma) {
        EntityModel<Turma> entityModel = assembler.toModel(repository.save(newTurma));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/turma/{id}")
    EntityModel<Turma> one(@PathVariable Long id) {

        Turma turma = repository.findById(id) //
                .orElseThrow(() -> new TurmaNotFoundException(id));

        return assembler.toModel(turma);
    }

    @PutMapping("/turma/{id}")
    ResponseEntity<?> replaceTurma(@RequestBody Turma newTurma, @PathVariable Long id) {
        Turma updatedTurma = repository.findById(id)
                .map(turma -> {
                    turma.setDisciplina( newTurma.getDisciplina());
                    turma.setProfessor(newTurma.getProfessor());
                    return repository.save(turma);
                })
                .orElseGet(() -> {
                    newTurma.setId(id);
                    return repository.save(newTurma);
                });
        EntityModel<Turma> entityModel = assembler.toModel(updatedTurma);

        return ResponseEntity.created((entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()))
                .body(entityModel);
    }

    @DeleteMapping("/turma/{id}")
    ResponseEntity<?> deleteTurma(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
