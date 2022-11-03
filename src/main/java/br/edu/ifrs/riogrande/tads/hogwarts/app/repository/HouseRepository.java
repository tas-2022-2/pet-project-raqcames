package br.edu.ifrs.riogrande.tads.hogwarts.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.edu.ifrs.riogrande.tads.hogwarts.app.model.House;

public interface HouseRepository extends Repository<House, Integer> {
    House save(House h);

    List<House> findAll();

    Optional<House> findById(Integer id);

    void delete(House h);
}
