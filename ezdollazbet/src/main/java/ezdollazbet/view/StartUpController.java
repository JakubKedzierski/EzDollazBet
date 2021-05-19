package ezdollazbet.view;

import ezdollazbet.EzDollazBetApp;
import ezdollazbet.models.UserSession;
import ezdollazbet.models.dao.AcountDAO;
import ezdollazbet.models.dao.ClientDAO;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Pair;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.prefs.Preferences;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class StartUpController {

	@Setter
	EzDollazBetApp mainApp;

	@FXML
	private Button logInButton;

	@FXML
	private Button registerButton;

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

		logInButton.disableProperty().bind(Bindings.or(validLogin.invalidProperty(), validPass.invalidProperty()));
		loginField.setPromptText("Login");
		passwordField.setPromptText("Has³o");
		Platform.runLater(() -> loginField.requestFocus());

		loginField.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER || ev.getCode() == KeyCode.DOWN) {
				passwordField.requestFocus();
				ev.consume();
			}
		});

		passwordField.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				logInButton.fire();
				ev.consume();
			}
			if (ev.getCode() == KeyCode.UP) {
				loginField.requestFocus();
				ev.consume();
			}
		});

	}

	@FXML
	private void logIn() {

		String login = loginField.getText();
		String password = passwordField.getText();
		boolean goodCredentials = false;

		try {
			goodCredentials = AcountDAO.areCredentialsGood(login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (goodCredentials) {
			ResultSet client;
			try {
				client = ClientDAO.getClientSetByLogin(login);
				client.next();
				UserSession session = UserSession.getSession(login, client.getInt("ClientID"));
			} catch (SQLException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error during database operations");
				alert.show();
				loginField.setText("");
				passwordField.setText("");
				e.printStackTrace();
				return;
			}
			mainApp.initLayout();
		} else {
			loginField.setText("");
			passwordField.setText("");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Nie znaleziono takiego u¿ytkownika.");
			alert.setContentText("Sprobuj zalogowaæ siê ponownie");
			alert.show();
		}

	}

	@FXML
	private void register() {
		Dialog<HashMap<String, String>> dialog = new Dialog<>();

		dialog.getDialogPane().getChildren().add(new Label("testtt"));
		dialog.setTitle("Rejestracja");
		dialog.setHeaderText("Zarejestruj siê");

		ButtonType loginButtonType = new ButtonType("Zarejestruj siê", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Login");
		PasswordField password = new PasswordField();
		password.setPromptText("Has³o");

		grid.add(new Label("Login:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Has³o:"), 0, 1);
		grid.add(password, 1, 1);

		TextField name = new TextField();
		name.setPromptText("Imie");
		grid.add(new Label("Imie:"), 0, 2);
		grid.add(name, 1, 2);

		TextField surname = new TextField();
		surname.setPromptText("Nazwisko");
		grid.add(new Label("Nazwisko:"), 0, 3);
		grid.add(surname, 1, 3);

		DatePicker birthData = new DatePicker();
		grid.add(new Label("Data urodzenia:"), 0, 4);
		grid.add(birthData, 1, 4);
		Label warningText = new Label();
		grid.add(warningText, 1, 5);
		warningText.setVisible(false);

		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		loginButton.addEventFilter(ActionEvent.ACTION, event -> {
			if (username.getText().isBlank()) {
				event.consume();
				warningText.setText("Niektore z pol s¹ puste!");
				warningText.setVisible(true);
			}
			if (password.getText().isBlank()) {
				event.consume();
				warningText.setText("Niektore z pol s¹ puste!");
				warningText.setVisible(true);
			}
			if (name.getText().isBlank()) {
				event.consume();
				warningText.setText("Niektore z pol s¹ puste!");
				warningText.setVisible(true);
			}
			if (surname.getText().isBlank()) {
				event.consume();
				warningText.setText("Niektore z pol s¹ puste!");
				warningText.setVisible(true);
			}
			if (birthData.getValue() == null) {
				event.consume();
				warningText.setText("Niektore z pol s¹ puste!");
				warningText.setVisible(true);
			}
		});

		username.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> username.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				HashMap<String, String> result = new HashMap<String, String>();
				result.put("login", username.getText());
				result.put("password", password.getText());
				result.put("name", name.getText());
				result.put("surname", surname.getText());

				int today = LocalDate.now().getYear();
				int age = today - birthData.getValue().getYear();
				result.put("age", Integer.toString(age));

				return result;
			}
			return null;
		});

		Optional<HashMap<String, String>> result = dialog.showAndWait();

		result.ifPresent(resultMap -> {
			String age = resultMap.get("age");

			try {
				ClientDAO.insertClient(resultMap.get("login"), resultMap.get("name"), resultMap.get("surname"),
						resultMap.get("password"), Integer.parseInt(age));
			} catch (NumberFormatException | SQLException e) {
				System.out.println("B³¹d przy wstawianiu klienta do bazy danych");
				e.printStackTrace();
			}
		});

	}

}
