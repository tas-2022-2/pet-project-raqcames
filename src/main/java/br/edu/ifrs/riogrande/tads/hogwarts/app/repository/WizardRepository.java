package br.edu.ifrs.riogrande.tads.hogwarts.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Wizard;

public interface WizardRepository extends Repository<Wizard, Integer> {
    Wizard save(Wizard w);

    List<Wizard> findAll();

    Optional<Wizard> findById(Integer id);

    void delete(Wizard w);
}
