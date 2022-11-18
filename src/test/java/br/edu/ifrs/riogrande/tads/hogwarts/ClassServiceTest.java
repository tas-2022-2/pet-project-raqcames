package br.edu.ifrs.riogrande.tads.hogwarts;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import br.edu.ifrs.riogrande.tads.hogwarts.app.exceptions.NotFoundException;
import br.edu.ifrs.riogrande.tads.hogwarts.app.model.Class;
import br.edu.ifrs.riogrande.tads.hogwarts.app.repository.ClassRepository;
import br.edu.ifrs.riogrande.tads.hogwarts.app.services.ClassService;

public class ClassServiceTest {

    ClassRepository classRepositoryDummy;
    ClassService classService;

    @BeforeEach
    void init() {
        classRepositoryDummy = new ClassRepository() {
            @Override
            public void delete(Class c) {
            }

            @Override
            public List<Class> findAll() {
                return null;
            }

            @Override
            public Optional<Class> findById(Integer id) {
                return Optional.empty();
            }

            @Override
            public Class save(Class c) {
                return null;
            }
        };

        classService = new ClassService(classRepositoryDummy);
    }

    @Test
    void testClassNotExistsThrowNotFoundException() throws NotFoundException {
        Assertions.assertThatThrownBy(() -> classService.findOrElseThrow(7438593))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Class not found");
    }

}
