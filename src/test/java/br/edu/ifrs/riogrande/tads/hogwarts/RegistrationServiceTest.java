package br.edu.ifrs.riogrande.tads.hogwarts;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifrs.riogrande.tads.hogwarts.app.exceptions.NotFoundException;
import br.edu.ifrs.riogrande.tads.hogwarts.app.exceptions.ServiceException;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Class;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Registration;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Status;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Wizard;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.ClassRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.RegistrationRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.WizardRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.RegistrationService;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto.RegistrationRequest;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto.RegistrationStatusRequest;

public class RegistrationServiceTest {

    RegistrationRepository registrationRepositoryStub;
    WizardRepository wizardRepositoryStub;
    ClassRepository classRepositoryStub;
    RegistrationService registrationService;

    @BeforeEach
    void init() {
        registrationRepositoryStub = new RegistrationRepositoryStub();
        wizardRepositoryStub = new WizardRepositoryStub();
        classRepositoryStub = new ClassRepositoryStub();

        registrationService = new RegistrationService(registrationRepositoryStub, wizardRepositoryStub,
                classRepositoryStub);
    }

    @Test
    void testClassNotExistsThrowNotFoundException() throws NotFoundException {
        RegistrationRequest req = new RegistrationRequest();
        req.setClassId(34789345);

        Assertions.assertThatThrownBy(() -> registrationService.save(
                req))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Class not found");
    }

    @Test
    void testWizardNotExistsThrowNotFoundException() throws NotFoundException {
        RegistrationRequest req = new RegistrationRequest();
        req.setClassId(1);
        req.setWizardId(78934234);

        Assertions.assertThatThrownBy(() -> registrationService.save(req))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Wizard not found");
    }

    @Test
    void testWizardAlreadyRegisteredThrowServiceException() throws ServiceException {
        RegistrationRequest req = new RegistrationRequest();
        req.setClassId(1);
        req.setWizardId(1);

        Assertions.assertThatThrownBy(() -> registrationService.save(req))
                .isInstanceOf(ServiceException.class)
                .hasMessage("Wizard is already registered in class");
    }

    @Test
    void testRegistrationNotExistsThrowNotFoundException() throws NotFoundException {
        Assertions.assertThatThrownBy(() -> registrationService.findOrElseThrow(123345))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Registration not found");
    }

    @Test
    void testInvalidRegistrationStatusThrowIllegalArgumentException() throws IllegalArgumentException {
        RegistrationStatusRequest req = new RegistrationStatusRequest();
        req.setStatus("INVALID");

        Assertions.assertThatThrownBy(() -> registrationService.updateStatus(1, req))
                .isInstanceOf(
                        IllegalArgumentException.class)
                .hasMessage("Invalid registration status");
    }

    @Test
    void testNotAcceptSameStatusThrowServiceException() throws ServiceException {
        RegistrationStatusRequest req = new RegistrationStatusRequest();
        req.setStatus("APPROVED");

        Assertions.assertThatThrownBy(() -> registrationService.updateStatus(1, req))
                .isInstanceOf(ServiceException.class)
                .hasMessage("Can not set same status again");
    }

    @Test
    void testNotAcceptNewStatusAfterEvaluatedThrowServiceException() throws ServiceException {
        RegistrationStatusRequest req = new RegistrationStatusRequest();
        req.setStatus("FAILED");

        Assertions.assertThatThrownBy(() -> registrationService.updateStatus(1, req))
                .isInstanceOf(ServiceException.class)
                .hasMessage("Can not change status of evaluated class registration");
    }

    static class RegistrationRepositoryStub implements RegistrationRepository {

        @Override
        public void delete(Registration r) {

        }

        @Override
        public boolean existsByCAndWizard(Class c, Wizard w) {
            return c.getId() == 1 && w.getId() == 1;
        }

        @Override
        public List<Registration> findAll() {
            return null;
        }

        @Override
        public Optional<Registration> findById(Integer id) {
            if (id == 1) {
                Registration r = new Registration();
                r.setStatus(Status.APPROVED);

                return Optional.of(r);
            }

            return Optional.empty();
        }

        @Override
        public Registration save(Registration r) {
            return null;
        }

    }

    static class WizardRepositoryStub implements WizardRepository {

        @Override
        public void delete(Wizard w) {

        }

        @Override
        public List<Wizard> findAll() {
            return null;
        }

        @Override
        public Optional<Wizard> findById(Integer id) {
            if (id == 1) {
                Wizard w = new Wizard();
                w.setId(1);
                return Optional.of(w);
            }

            return Optional.empty();
        }

        @Override
        public Wizard save(Wizard w) {
            return null;
        }

    }

    static class ClassRepositoryStub implements ClassRepository {

        @Override
        public void delete(Class c) {

        }

        @Override
        public List<Class> findAll() {
            return null;
        }

        @Override
        public Optional<Class> findById(Integer id) {
            if (id == 1) {
                Class c = new Class();
                c.setId(1);
                return Optional.of(c);
            }

            return Optional.empty();
        }

        @Override
        public Class save(Class c) {
            return null;
        }

    }

}
