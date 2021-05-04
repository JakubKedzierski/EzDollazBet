package ezdollazbet.view;

import ezdollazbet.EzDollazBetApp;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Setter;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class StartUpController {

	@Setter
	EzDollazBetApp mainApp;

	@FXML
	private Button logInButton;

	@FXML
	private TextField loginField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private void initialize() {

		ValidationSupport validLogin = new ValidationSupport();
		validLogin.setErrorDecorationEnabled(false);
		validLogin.registerValidator(loginField, Validator.createEmptyValidator("Pole nie mo¿e byæ puste"));
		validLogin.errorDecorationEnabledProperty().bind(loginField.focusedProperty());
		
		ValidationSupport validPass = new ValidationSupport();
		validPass.setErrorDecorationEnabled(false);
		validPass.registerValidator(passwordField, Validator.createEmptyValidator("Pole nie mo¿e byæ puste"));
		validPass.errorDecorationEnabledProperty().bind(passwordField.focusedProperty());
		
		logInButton.disableProperty().bind( Bindings.or(
				validLogin.invalidProperty(),
				validPass.invalidProperty()));
	}

	@FXML
	private void logIn() {

		String login = loginField.getText();
		String password = passwordField.getText();

		mainApp.initLayout();

	}

}
