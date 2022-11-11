package br.edu.ifrs.riogrande.tads.hogwarts.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.hogwarts.app.exceptions.NotFoundException;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.House;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Wizard;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.WizardRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.HouseRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto.WizardRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WizardService {

    private final WizardRepository wizardRepository;
    private final HouseRepository houseRepository;

    public Wizard save(WizardRequest request) {
        Wizard w = new Wizard();
        w.setName(request.getName());
        w.setAge(request.getAge());

        House h = houseRepository.findById(request.getHouseId())
                .orElseThrow(() -> new NotFoundException("House not found"));
        w.setHouse(h);

        wizardRepository.save(w);

        return w;
    }

    public List<Wizard> list() {
        return wizardRepository.findAll();
    }

    public Optional<Wizard> find(Integer id) {
        return wizardRepository.findById(id);
    }

    public void update(Integer id, WizardRequest body) {
        Wizard w = findOrElseThrow(id);

        w.setName(body.getName());
        w.setAge(body.getAge());

        House h = houseRepository.findById(body.getHouseId())
                .orElseThrow(() -> new NotFoundException("House not found"));
        w.setHouse(h);

        wizardRepository.save(w);
    }

    public void delete(Integer id) {
        Wizard w = findOrElseThrow(id);
        wizardRepository.delete(w);
    }

    public Wizard findOrElseThrow(Integer id) {
        Wizard w = wizardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("wizard not found"));

        return w;
    }

}
