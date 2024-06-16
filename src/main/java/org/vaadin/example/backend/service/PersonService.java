package org.vaadin.example.backend.service;

import org.springframework.stereotype.Service;
import org.vaadin.example.backend.dto.PersonDTO;
import org.vaadin.example.backend.entity.Person;
import org.vaadin.example.backend.mapper.PersonMapper;
import org.vaadin.example.backend.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;


    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonDTO> getAllPersons(){
        return personRepository.findAll().stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO saveOrUpdatePerson(PersonDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);
        Person savedPerson = personRepository.save(person);
        return personMapper.toDTO(savedPerson);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

    public List<Person> saveAll(Iterable<Person> persons) {
        return personRepository.saveAll(persons);
    }

}
