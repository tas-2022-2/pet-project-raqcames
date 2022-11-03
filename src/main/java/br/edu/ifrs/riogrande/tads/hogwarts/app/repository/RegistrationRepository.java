package br.edu.ifrs.riogrande.tads.hogwarts.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Class;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Registration;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Wizard;

public interface RegistrationRepository extends Repository<Registration, Integer> {
    Registration save(Registration r);

    List<Registration> findAll();

    Optional<Registration> findById(Integer id);

    void delete(Registration r);

    boolean existsByCAndWizard(Class c, Wizard w);
}
