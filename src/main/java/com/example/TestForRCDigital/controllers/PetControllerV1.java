package com.example.TestForRCDigital.controllers;

import com.example.TestForRCDigital.entity.Pet;
import com.example.TestForRCDigital.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.TestForRCDigital.controllers.Path.Pets.PETS;
import static com.example.TestForRCDigital.controllers.Path.VersionApi.V1;

@RestController
@Tag(name = "Домашние питомцы v1", description = "Операции с данными о домашних питомцах")
@RequestMapping(V1+PETS)
@Slf4j
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PetControllerV1 {

    @Autowired
    private PetService petService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение всех питомцев")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Pet> getAllPets() {
        log.info("Getting all the pets");

        return petService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение питомца по ИД")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Optional<Pet> getPetById(
            @PathVariable @Parameter(description = "ИД питомца") int id
    ) {
        log.info("Getting the pet by id {}", id);

        return petService.getPetById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Создание питомца")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity createPet(
            @RequestParam()   @Parameter(description = "Имя питомца") String name
    ) {
        log.info("Create a pet: name {}", name);

        petService.createPet(name);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Изменение питомца")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity updatePet(
            @PathVariable @Parameter(description = "ИД питомца") int id,
            @RequestParam() @Parameter(description = "Имя питомца") String name
    ) {
        log.info("Update the pet by id {}, new name {}", id, name);

        petService.updatePet(id, name);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Удаление питомца по ИД")
    public ResponseEntity deletePetById(
            @PathVariable @Parameter(description = "ИД питомца")int id
    ) {
        log.info("Delete the pet by id {}", id);

        petService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
