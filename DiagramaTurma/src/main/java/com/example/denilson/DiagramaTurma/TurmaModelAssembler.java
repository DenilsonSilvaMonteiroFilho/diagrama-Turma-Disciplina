package com.example.denilson.DiagramaTurma;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class EmployeeModelAssembler implements RepresentationModelAssembler<Turma, EntityModel<Turma>> {

    @Override
    public EntityModel<Turma> toModel(Turma turma) {

        return EntityModel.of(turma, //
                linkTo(methodOn(TurmaController.class).one(turma.getId())).withSelfRel(),
                linkTo(methodOn(TurmaController.class).all()).withRel("employees"));
    }
}