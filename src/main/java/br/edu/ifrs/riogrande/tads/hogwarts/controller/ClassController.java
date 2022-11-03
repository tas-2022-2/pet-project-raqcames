package br.edu.ifrs.riogrande.tads.hogwarts.controller;

import java.net.URI;
import java.util.*;

import javax.validation.Valid;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Class;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.ClassService;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto.ClassRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classes")
public class ClassController {
    
    private final ClassService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> add(@RequestBody @Valid ClassRequest body) {
        Class c = service.save(body);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Class>> list() {
        List<Class> classes = service.list();

        return ResponseEntity.ok(classes);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> show(@PathVariable Integer id) {
        Class c = service.findOrElseThrow(id);

        return ResponseEntity.ok(c);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable(name = "id") Integer id, @RequestBody @Valid ClassRequest body) {
        service.update(id, body);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void destroy(@PathVariable Integer id) {
        service.delete(id);
    }
}
