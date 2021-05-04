package ezdollazbet.view;

import ezdollazbet.EzDollazBetApp;
import javafx.application.Platform;
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
	    Platform.runLater(() -> {
			ValidationSupport validLogin = new ValidationSupport();
	        validLogin.setErrorDecorationEnabled(false); 
	        validLogin.registerValidator(loginField, Validator.createEmptyValidator("Pole nie mo¿e byæ puste"));
	        validLogin.errorDecorationEnabledProperty().bind(loginField.focusedProperty());
	        logInButton.disableProperty().bind(validLogin.invalidProperty());
	      });

	}
	
	
	@FXML
	private void logIn(){
		

        
		String login = loginField.getText();
		String password = passwordField.getText();
		
		
		//if(mainApp.checkLoginEntry(loginField.getText(),passwordField.getText())) {
			mainApp.initLayout();
		//}
	}

}
