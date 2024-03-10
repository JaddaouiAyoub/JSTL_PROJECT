package Validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@FacesValidator("customValidator")
public class CustomValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String fieldId = component.getId();
        String fieldValue = value.toString();

        if ("fullName".equals(fieldId)) {
            // Validation spécifique pour le champ "Full Name"
            if (fieldValue.length() < 2 || fieldValue.length() > 50) {
                FacesMessage message = new FacesMessage("Le champ Full Name doit contenir entre 2 et 50 caractères.");
                throw new ValidatorException(message);
            }
        } else if ("email".equals(fieldId)) {
            if (fieldValue.isEmpty()) {
                FacesMessage message = new FacesMessage(" Erreur de validation. Vous devez indiquer une valeur.");
                throw new ValidatorException(message);
            }
            // Validation spécifique pour le champ "Email"
            else if (!fieldValue.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                FacesMessage message = new FacesMessage("Veuillez saisir une adresse e-mail valide ex : test@gmail.com");
                throw new ValidatorException(message);
            }
        } else if ("department".equals(fieldId)) {
            // Validation spécifique pour le champ "Department"
            if (fieldValue.isEmpty()) {
                FacesMessage message = new FacesMessage("Le champ Department est obligatoire.");
                throw new ValidatorException(message);
            }
        } else if ("hireDate".equals(fieldId)) {
            if (fieldValue.isEmpty()) {
                FacesMessage message = new FacesMessage(" Erreur de validation. Vous devez indiquer une valeur.");
                throw new ValidatorException(message);
            }
            // Validation pour le champ hireDate (format yyyy-mm-dd)
            else if (!fieldValue.matches("[\\d\\-]+")) {
                FacesMessage message = new FacesMessage("Le champ doit être de la forme : yyyy-MM-dd.");
                throw new ValidatorException(message);
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);

            try {
                Date hireDate = dateFormat.parse(fieldValue);
                // Vous pouvez ajouter des validations supplémentaires pour la date si nécessaire
            } catch (ParseException e) {
                FacesMessage message = new FacesMessage("La date doit être de la forme AAAA-MM-JJ.");
                throw new ValidatorException(message);
            }
        }else if ("phoneNumber".equals(fieldId)) {
            if (fieldValue.isEmpty()) {
                FacesMessage message = new FacesMessage(" Erreur de validation. Vous devez indiquer une valeur.");
                throw new ValidatorException(message);
            }
            // Validation pour le champ phoneNumber (nombre, taille maximale 10)
            else if (!fieldValue.matches("\\d{10}")) {
                FacesMessage message = new FacesMessage("Le champ doit être un nombre avec une taille de 10 chiffres.");
                throw new ValidatorException(message);
            }
        }
    }
        // Ajoutez d'autres validations pour les autres champs si nécessaire
    }
