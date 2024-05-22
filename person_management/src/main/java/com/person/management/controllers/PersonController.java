package com.person.management.controllers;

import com.person.management.dtos.person.PersonCreateDTO;
import com.person.management.dtos.person.PersonEditDTO;
import com.person.management.dtos.person.PersonViewDTO;
import com.person.management.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Busca de pessoa por id",
            description = "Este endpoint get, consiste na busca uma pessoa",
            tags = {"Person"})
    @GetMapping("/{personId}")
    public ResponseEntity<PersonViewDTO> findById(@PathVariable Long personId) {
        PersonViewDTO personViewDTO = personService.findById(personId);
        return ResponseEntity.status(HttpStatus.OK).body(personViewDTO);
    }

    @Operation(summary = "Listagem de pessoas",
            description = "Este endpoint get, consiste na listagem de todas as pessoas",
            tags = {"Person"})
    @GetMapping()
    public ResponseEntity<List<PersonViewDTO>> listAll() {
        List<PersonViewDTO> listAll = personService.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(listAll);
    }

    @Operation(summary = "Criação de uma pessoa",
            description = "Este endpoint post, consiste na criação de uma pessoa",
            tags = {"Person"})
    @PostMapping()
    public ResponseEntity<PersonViewDTO> createPerson(@Valid @RequestBody PersonCreateDTO personCreateDTO) {
        PersonViewDTO personViewDTO = personService.createPerson(personCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(personViewDTO);
    }

    @Operation(summary = "Edição de uma pessoa",
            description = "Este endpoint patch, consiste na atualização parcial de uma pessoa",
            tags = {"Person"})
    @PatchMapping("/{personId}")
    public ResponseEntity<PersonViewDTO> editPerson(@PathVariable Long personId, @Valid @RequestBody PersonEditDTO personEditDTO) {
        PersonViewDTO personViewDTO = personService.editPerson(personId, personEditDTO);
        return ResponseEntity.status(HttpStatus.OK).body(personViewDTO);
    }

    @Operation(summary = "Delete de uma pessoa",
            description = "Este endpoint delete, consiste no delete de uma pessoa",
            tags = {"Person"})
    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long personId) {
        personService.deletePerson(personId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
