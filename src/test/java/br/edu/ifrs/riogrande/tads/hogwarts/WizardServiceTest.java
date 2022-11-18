package br.edu.ifrs.riogrande.tads.hogwarts;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import br.edu.ifrs.riogrande.tads.hogwarts.app.exceptions.NotFoundException;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.House;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Wizard;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.HouseRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.WizardRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.WizardService;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto.WizardRequest;

public class WizardServiceTest {

    WizardRepository wizardRepositoryDummy;
    HouseRepository houseRepositoryDummy;
    WizardService wizardService;

    @BeforeEach
    void init() {
        wizardRepositoryDummy = new WizardRepository() {
            @Override
            public void delete(Wizard w) {
            }

            @Override
            public List<Wizard> findAll() {
                return null;
            }

            @Override
            public Optional<Wizard> findById(Integer id) {
                return Optional.empty();
            }

            @Override
            public Wizard save(Wizard w) {
                return null;
            }
        };

        houseRepositoryDummy = new HouseRepository() {
            @Override
            public void delete(House h) {
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
            public House save(House h) {
                return null;
            }
        };
        wizardService = new WizardService(wizardRepositoryDummy, houseRepositoryDummy);
    }

    @Test
    void testHouseNotExistsThrowNotFoundException() throws NotFoundException {
        WizardRequest req = new WizardRequest();
        req.setHouseId(7438593);

        Assertions.assertThatThrownBy(() -> wizardService.save(req))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("House not found");
    }

    @Test
    void testWizardNotExistsThrowNotFoundException() throws NotFoundException {
        Assertions.assertThatThrownBy(() -> wizardService.findOrElseThrow(7438593))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("wizard not found");
    }

}
