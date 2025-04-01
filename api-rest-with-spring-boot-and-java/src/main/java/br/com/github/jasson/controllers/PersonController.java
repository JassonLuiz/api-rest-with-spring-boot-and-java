package br.com.github.jasson.controllers;

import br.com.github.jasson.data.dto.PersonDTO;
import br.com.github.jasson.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @GetMapping()
    public List<PersonDTO> findAll(){
        return service.findAll();
    }

    @PostMapping()
    public PersonDTO create(@RequestBody PersonDTO personDTO){
        return service.create(personDTO);
    }

    @PutMapping()
    public PersonDTO update(@RequestBody PersonDTO personDTO){
        return service.update(personDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
