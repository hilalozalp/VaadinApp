package org.vaadin.example.backend.dto;

import java.time.LocalDate;

public class PersonDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String telNo;
    private String email;
    private String street;
    private String streetNo;
    private String postalCode;
    private String city;
    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PersonDTO(String firstname, String lastname, LocalDate birthdate, String telNo, String email, String street, String streetNo, String postalCode, String city, String country) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.telNo = telNo;
        this.email = email;
        this.street = street;
        this.streetNo = streetNo;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public PersonDTO(){

    }
}
