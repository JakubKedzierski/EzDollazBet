package ezdollazbet.view;

import ezdollazbet.EzDollazBetApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Setter;


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
	private void initialize() {}
	
	
	@FXML
	private void logIn(){
		if(mainApp.checkLoginEntry(loginField.getText(),passwordField.getText())) {
			mainApp.initLayout();
		}
	}

}
