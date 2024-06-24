package org.vaadin.example.backend.mapper;

import org.springframework.stereotype.Component;
import org.vaadin.example.backend.dto.PersonDTO;
import org.vaadin.example.backend.entity.Person;

@Component
public class PersonMapper {
    public  PersonDTO toDTO(Person person){
        PersonDTO personDTO = new PersonDTO();

        personDTO.setId(person.getPersonId());
        personDTO.setFirstname(person.getFirstname());
        personDTO.setLastname(person.getLastname());
        personDTO.setBirthdate(person.getBirthdate());
        personDTO.setTelNo(person.getTelNo());
        personDTO.setEmail(person.getEmail());
        personDTO.setStreet(person.getStreet());
        personDTO.setStreetNo(person.getStreetNo());
        personDTO.setPostalCode(person.getPostalCode());
        personDTO.setCity(person.getCity());
        personDTO.setCountry(person.getCountry());

        return personDTO;
    }

    public Person toEntity(PersonDTO personDTO){
        Person person = new Person();

        person.setPersonId(personDTO.getId());
        person.setFirstname(personDTO.getFirstname());
        person.setLastname(personDTO.getLastname());
        person.setBirthdate(personDTO.getBirthdate());
        person.setTelNo(personDTO.getTelNo());
        person.setEmail(personDTO.getEmail());
        person.setStreet(personDTO.getStreet());
        person.setStreetNo(personDTO.getStreetNo());
        person.setPostalCode(personDTO.getPostalCode());
        person.setCity(personDTO.getCity());
        person.setCountry(personDTO.getCountry());

        return person;
    }

}
