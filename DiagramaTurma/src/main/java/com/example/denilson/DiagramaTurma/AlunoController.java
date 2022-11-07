package com.example.denilson.DiagramaTurma;

import org.hibernate.EntityMode;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AlunoController {
    private final AlunoRepository alunoRepository;
    private final AlunoModelAssembler assemblerAluno;

    public AlunoController(AlunoRepository alunoRepository, AlunoModelAssembler assemblerAluno) {
        this.alunoRepository = alunoRepository;
        this.assemblerAluno = assemblerAluno;
    }
    @GetMapping("/alunos")
    CollectionModel<EntityModel<Aluno>> all(){

        List<EntityModel<Aluno>> alunos = alunoRepository.findAll().stream()
                .map(assemblerAluno::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(alunos,
                linkTo(methodOn(AlunoController.class).all()).withSelfRel());
    }
    @GetMapping("/aluno/{id}")
    EntityModel<Aluno> one(@PathVariable Long id){
        Aluno aluno = alunoRepository.findById(id) //
                .orElseThrow(() -> new AlunoNotFoundExeception(id));

        return assemblerAluno.toModel(aluno);
    }
    @PostMapping("/orders")
    ResponseEntity<EntityModel<Aluno>> newOrder(@RequestBody Aluno aluno) {

        aluno.setStatus(StatusAluno.MATRICULADO);
        Aluno newAluno = alunoRepository.save(aluno);

        return ResponseEntity //
                .created(linkTo(methodOn(AlunoController.class).one(newAluno.getId())).toUri()) //
                .body(assemblerAluno.toModel(newAluno));
    }
}
