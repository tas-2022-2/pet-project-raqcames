package br.edu.ifrs.riogrande.tads.hogwarts.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.hogwarts.app.exceptions.NotFoundException;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.House;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.HouseRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto.HouseRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository repository;

    public House save(HouseRequest request) {
        House h = new House();
        h.setName(request.getName());
        h.setFounder(request.getFounder());

        repository.save(h);

        return h;
    }

    public List<House> list() {
        return repository.findAll();
    }

    public Optional<House> find(Integer id) {
        return repository.findById(id);
    }

    public void update(Integer id, HouseRequest body) {
        House h = findOrElseThrow(id);

        h.setName(body.getName());
        h.setFounder(body.getFounder());

        repository.save(h);
    }

    public void delete(Integer id) {
        House h = findOrElseThrow(id);
        repository.delete(h);
    }

    public House findOrElseThrow(Integer id) {
        House h = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("House not found"));

        return h;
    }
}
