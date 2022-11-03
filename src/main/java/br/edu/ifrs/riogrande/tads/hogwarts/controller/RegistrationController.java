package br.edu.ifrs.riogrande.tads.hogwarts.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Registration;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.RegistrationService;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto.RegistrationRequest;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto.RegistrationStatusRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registrations")
public class RegistrationController {

    private final RegistrationService service;
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> add(@RequestBody @Valid RegistrationRequest body) {
        Registration h = service.save(body);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(h.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Registration>> list() {
        List<Registration> registrations = service.list();

        return ResponseEntity.ok(registrations);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Registration> show(@PathVariable(name = "id") Integer id) {
        Registration r = service.findOrElseThrow(id);

        return ResponseEntity.ok(r);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(name = "id") Integer id, @RequestBody @Valid RegistrationStatusRequest body) {
        service.updateStatus(id, body);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        service.delete(id);
    }

}
