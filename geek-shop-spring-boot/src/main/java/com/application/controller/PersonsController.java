package com.application.controller;

import com.application.entity.Persons;
import com.application.service.PersonsService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonsController {
    private static final Logger log = Logger.getLogger(PersonsController.class);
    private final PersonsService personsService;

    @GetMapping("/persons")
    public ResponseEntity<List<Persons>> getAllAccounts() {
        log.info("[GET] Endpoint: /persons");
        return ResponseEntity.ok(new ArrayList<>(personsService.getAllPersons()));
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Persons> getPersonById(@PathVariable int id) {
        log.info("[GET] Endpoint: /persons");
        log.debug(String.format("Parameters: id: %d", id));
        return ResponseEntity.ok(personsService.getPersonById(id));
    }

    @GetMapping(value = "/persons", params = "fullName")
    public ResponseEntity<List<Persons>> getPersonsByFullName(@RequestParam String fullName) {
        log.info("[GET] Endpoint: /persons");
        log.debug(String.format("Parameters: fullName: %s", fullName));
        return ResponseEntity.ok(new ArrayList<>(personsService.getPersonsByFullName(fullName)));
    }

    @PostMapping("/persons")
    public ResponseEntity<Persons> saveNewPerson(@RequestBody Persons person) {
        log.info("[POST] Endpoint: /persons");
        return ResponseEntity.ok(personsService.savePerson(person));
    }

    @PostMapping("/persons/{id}")
    public ResponseEntity<Integer> deletePerson(@PathVariable int id) {
        log.info("[POST] Endpoint: /persons");
        log.debug(String.format("Parameters: id: %d", id));
        return ResponseEntity.ok(personsService.deletePerson(id));
    }

    @PutMapping("/persons")
    public ResponseEntity<Integer> updatePerson(@RequestBody Persons person) {
        log.info("[PUT] Endpoint: /persons");
        log.debug(String.format("Parameters: id: %d", person.getPersonId()));
        return ResponseEntity.ok(personsService.updatePerson(person));
    }
}
