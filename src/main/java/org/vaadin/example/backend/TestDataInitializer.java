package org.vaadin.example.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.vaadin.example.backend.dto.PersonDTO;
import org.vaadin.example.backend.entity.Person;
import org.vaadin.example.backend.mapper.PersonMapper;
import org.vaadin.example.backend.service.PersonService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestDataInitializer implements CommandLineRunner {

    private final PersonService personService;
    private final PersonMapper personMapper;

    public TestDataInitializer(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        personService.deleteAll();

        // Erstellen Sie Testdaten
        PersonDTO person1 = new PersonDTO("Darth", "Vader", LocalDate.of(1980,5,15), "06641234567", "daddyvader@gmail.com", "Milchstraße", "66", "6969", "Dark Side","Mustafar");
        PersonDTO person2 = new PersonDTO("Padme", "Amadalia", LocalDate.of(1985,10,14), "06641234437", "padme@gmail.com", "Milchstraße", "5", "1234", "Naboocity","Naboo");

        PersonDTO person3 = new PersonDTO("Andi", "Front", LocalDate.of(1978,6,6), "06601234567", "andifront@gmail.com", "Kurzmussweg", "4", "6800", "Feldkirch","Österreich");


        // Wandeln Sie die DTOs in Entitäten um
        List<Person> persons = List.of(person1, person2, person3).stream()
                .map(personMapper::toEntity)
                .collect(Collectors.toList());

        // Speichern Sie die Testdaten in der Datenbank
        personService.saveAll(persons);
    }
}
