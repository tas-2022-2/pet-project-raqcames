package br.edu.ifrs.riogrande.tads.hogwarts.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Class;

public interface ClassRepository extends Repository<Class, Integer> {
    Class save(Class c);

    List<Class> findAll();

    Optional<Class> findById(Integer id);

    void delete(Class c);
}
