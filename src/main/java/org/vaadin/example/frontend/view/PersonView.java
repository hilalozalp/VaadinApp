package org.vaadin.example.frontend.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.backend.dto.PersonDTO;
import org.vaadin.example.backend.service.PersonService;


@Route(value = "")

@PageTitle("Contacts | Vaadin CRM")
public class PersonView extends VerticalLayout {
    private final Grid<PersonDTO> personGrid = new Grid<>(PersonDTO.class);
    private final PersonService personService;


    public PersonView(@Autowired PersonService personService) {
        this.personService = personService;
        initLayout();
        loadPersonData();
    }




    private void initLayout() {
        add(createToolbar(), personGrid);
        personGrid.setColumns(
                "firstname",
                "lastname",
                "birthdate",
                "telNo",
                "email",
                "street",
                "streetNo",
                "postalCode",
                "city",
                "country"
        );


        personGrid.addColumn(new ComponentRenderer<>(personDTO -> {
            HorizontalLayout actionsLayout = new HorizontalLayout();

            Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            deleteButton.addClickListener(event -> showDeleteConfirmationDialog(personDTO));

            Button editButton = new Button(new Icon(VaadinIcon.EDIT));
            editButton.addClickListener(event -> openPersonForm(personDTO));

            actionsLayout.add(editButton, deleteButton);
            return actionsLayout;
        })).setHeader("Actions");


    }



    private void loadPersonData() {
        personGrid.setItems(personService.getAllPersons());
    }


    private HorizontalLayout createToolbar() {
        Button addPersonButton = new Button("Add Person");
        addPersonButton.addClickListener(e -> openPersonForm(new PersonDTO()));

        HorizontalLayout toolbar = new HorizontalLayout(addPersonButton);
        return toolbar;
    }

    private void openPersonForm(PersonDTO personDTO) {
        Dialog dialog = new Dialog();
        PersonForm personForm = new PersonForm(personDTO, personService, dialog);

        personForm.setOnSaveCallback(() ->{
            loadPersonData();
        });
        dialog.add(personForm);
        dialog.open();
    }


    private void showDeleteConfirmationDialog(PersonDTO personDTO) {
        Dialog confirmationDialog = new Dialog();
        confirmationDialog.setHeaderTitle("Sind Sie sich sicher, dass Sie diese Person wirklich löschen wollen?");

        Button confirmButton = new Button("Ja", event -> {
            deletePerson(personDTO);
            confirmationDialog.close();
        });
        confirmButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

        Button cancelButton = new Button("Abbrechen", event -> confirmationDialog.close());

        HorizontalLayout buttonsLayout = new HorizontalLayout(confirmButton, cancelButton);
        confirmationDialog.add(new VerticalLayout(new Text("Diese Aktion kann nicht rückgängig gemacht werden."), buttonsLayout));

        confirmationDialog.open();
    }



    private void deletePerson(PersonDTO personDTO) {
        if (personDTO.getId() != null) {
            personService.deletePerson(personDTO.getId());
            loadPersonData();
            Notification.show("Person deleted", 5000, Notification.Position.TOP_CENTER);
        } else {
            Notification.show("Person ID is null, unable to delete");
        }
    }

}
