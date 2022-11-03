package br.edu.ifrs.riogrande.tads.hogwarts.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.hogwarts.app.exceptions.NotFoundException;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Class;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.ClassRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto.ClassRequest;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class ClassService {
    private final ClassRepository repository;

    public Class save(ClassRequest request) {
        Class c = new Class();
        c.setName(request.getName());
        c.setTeacher(request.getTeacher());

        repository.save(c);

        return c;
    }

    public List<Class> list() {
        return repository.findAll();
    }

    public Optional<Class> find(Integer id) {
        return repository.findById(id);
    }

    public void update(Integer id, @Valid ClassRequest body) {
        Class c = findOrElseThrow(id);

        c.setName(body.getName());
        c.setTeacher(body.getTeacher());

        repository.save(c);
    }

    public void delete(Integer id) {
        Class c = findOrElseThrow(id);
        repository.delete(c);
    }

    public Class findOrElseThrow(Integer id) {
        Class c = repository.findById(id).orElseThrow(() -> new NotFoundException("Class not found"));
        return c;
    }

}
