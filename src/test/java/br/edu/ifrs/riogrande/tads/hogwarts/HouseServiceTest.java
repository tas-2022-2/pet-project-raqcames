package br.edu.ifrs.riogrande.tads.hogwarts;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import br.edu.ifrs.riogrande.tads.hogwarts.app.exceptions.NotFoundException;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.House;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.HouseRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.HouseService;

public class HouseServiceTest {

    HouseRepository houseRepositoryDummy;
    HouseService houseService;

    @BeforeEach
    void init() {
        houseRepositoryDummy = new HouseRepository() {
            @Override
            public void delete(House c) {
            }

            @Override
            public List<House> findAll() {
                return null;
            }

            @Override
            public Optional<House> findById(Integer id) {
                return Optional.empty();
            }

            @Override
            public House save(House c) {
                return null;
            }
        };

        houseService = new HouseService(houseRepositoryDummy);
    }

    @Test
    void testHouseNotExistsThrowNotFoundException() throws NotFoundException {
        Assertions.assertThatThrownBy(() -> houseService.findOrElseThrow(7438593))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("House not found");
    }

}
