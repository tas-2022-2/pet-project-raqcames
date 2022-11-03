package br.edu.ifrs.riogrande.tads.hogwarts.app.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.hogwarts.app.exceptions.NotFoundException;
import br.edu.ifrs.riogrande.tads.hogwarts.app.exceptions.ServiceException;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Class;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Registration;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Status;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Wizard;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.ClassRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.RegistrationRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.WizardRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto.RegistrationRequest;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto.RegistrationStatusRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository repository;
    private final WizardRepository wizardRepository;
    private final ClassRepository classRepository;

    public Registration save(RegistrationRequest request) {
        Class c = classRepository.findById(request.getClassId()).orElseThrow(() -> new NotFoundException("Class not found"));
        Wizard w = wizardRepository.findById(request.getWizardId()).orElseThrow(() -> new NotFoundException("Wizard not found"));

        if (repository.existsByCAndWizard(c, w)) {
            throw new ServiceException("Wizard is already registered in class");
        }

        Registration r = new Registration();
        r.setStatus(Status.IN_PROGRESS);
        r.setC(c);
        r.setWizard(w);

        repository.save(r);

        return r;
    }

    public List<Registration> list() {
        return repository.findAll();
    }

    public Optional<Registration> find(Integer id) {
        return repository.findById(id);
    }

    public void updateStatus(Integer id, RegistrationStatusRequest body) {
        Status status = null;
        try {
            status = Status.valueOf(body.getStatus());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid registration status");
        }

        Registration r = findOrElseThrow(id);

        if (r.getStatus() == status) {
            throw new ServiceException("Can not set same status again");
        }

        if (r.getStatus() == Status.APPROVED || r.getStatus() == Status.FAILED) {
            throw new ServiceException("Can not change status of evaluated class registration");
        }

        r.setStatus(status);

        repository.save(r);
    }

    public void delete(Integer id) {
        Registration r = findOrElseThrow(id);
        repository.delete(r);
    }

    public Registration findOrElseThrow(Integer id) {
        Registration r = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Registration not found"));
        
        return r;
    }
}
