package org.vaadin.example.frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import org.vaadin.example.backend.dto.PersonDTO;
import org.vaadin.example.backend.service.PersonService;

public class PersonForm extends VerticalLayout {

    private final PersonService personService;

    private final Dialog dialog;
    private final Binder<PersonDTO> binder = new Binder<>(PersonDTO.class);

    private Runnable onSaveCallback;

    private TextField firstname = new TextField("Vorname");
    private TextField lastname = new TextField("Nachname");
    private DatePicker birthdate = new DatePicker("Geburtsdatum");
    private TextField telNo = new TextField("Telefon");
    private EmailField email = new EmailField("Email");
    private TextField street = new TextField("Straße");
    private TextField streetNo = new TextField("Hausnummer");
    private TextField postalCode = new TextField("PLZ");
    private TextField city = new TextField("Ort");
    private TextField country = new TextField("Land");

    private Button saveButton = new Button("Save", event -> savePerson());
    private Button cancelButton = new Button("Cancel", event -> closeDialog());

    private PersonDTO personDTO;

    public PersonForm(PersonDTO personDTO, PersonService personService, Dialog dialog) {
        this.personService = personService;
        this.dialog = dialog;
        this.personDTO = personDTO;

        binder.bindInstanceFields(this);
        binder.readBean(personDTO);

        FormLayout formLayout = new FormLayout();
        formLayout.add(firstname, lastname, birthdate, telNo, email, street, streetNo, postalCode, city, country);
        add(formLayout, createButtonsLayout());


    }

    private HorizontalLayout createButtonsLayout() {
        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, cancelButton);
        return buttonsLayout;
    }

    /*private void savePerson() {
        PersonDTO personDTO = new PersonDTO();
        //PersonDTO savedPersonDTO = personService.saveOrUpdatePerson(personDTO);
        try {
            binder.writeBean(personDTO);
            personService.saveOrUpdatePerson(personDTO);
            closeDialog();


        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }*/

    private void savePerson() {
        //PersonDTO personDTO = new PersonDTO();

        try {
            binder.writeBean(personDTO);
            personService.saveOrUpdatePerson(personDTO);
            closeDialog();
            if(onSaveCallback != null){
                onSaveCallback.run();
            }

        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }


    private void closeDialog() {
        if (dialog != null) {
            dialog.close();
        }
    }

    public void setOnSaveCallback(Runnable onSaveCallback) {
        this.onSaveCallback = onSaveCallback;
    }
}
