package br.edu.ifrs.riogrande.tads.hogwarts.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Wizard;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.WizardService;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto.WizardRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/wizards")
public class WizardController {
    
    private final WizardService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> add(@RequestBody @Valid WizardRequest body) {
        Wizard h = service.save(body);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(h.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Wizard>> list() {
        List<Wizard> wizards = service.list();

        return ResponseEntity.ok(wizards);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wizard> show(@PathVariable(name = "id") Integer id) {
        Wizard w = service.findOrElseThrow(id);

        return ResponseEntity.ok(w);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(name = "id") Integer id, @RequestBody @Valid WizardRequest body) {

        service.update(id, body);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        service.delete(id);
    }

}
