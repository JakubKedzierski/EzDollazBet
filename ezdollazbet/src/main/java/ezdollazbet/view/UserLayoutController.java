package ezdollazbet.view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

import ezdollazbet.EzDollazBetApp;
import ezdollazbet.models.Client;
import ezdollazbet.models.UserSession;
import ezdollazbet.models.dao.ClientDAO;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class UserLayoutController implements ViewRefresher {
	
	Client client = null;
	
	@FXML 
	private ImageView logo;
	@FXML 
	private ImageView bag;
	
	@FXML
	private Label userInformationLabel;
	
	@FXML
	private Label loginLabel;
	@FXML
	private Label saldoLabel;
	
	@FXML
	private ListView userBetsList;
	
	@FXML
	private Button changeClientButton;
	
	@FXML
	private Button insertMoneyButton;
	
	@FXML
	private void initialize() {
		UserSession session = UserSession.getSession();
		
		try {
			client = ClientDAO.getClientByLogin(session.getLogin());
		} catch (SQLException e) {
			client = new Client();
			e.printStackTrace();
		}
		
		userInformationLabel.setText(client.getFirstName().get() + " " + client.getLastName().get());
		loginLabel.setText(client.getLogin().get());
		saldoLabel.setText(Double.toString(client.getBalance().get()));

		
	}
	
	public void refreshView() {
		try {
			client = ClientDAO.getClientByLogin(client.getLogin().get());
		} catch (SQLException e) {
			client = new Client();
			e.printStackTrace();
		}
		saldoLabel.setText(Double.toString(client.getBalance().get()));
		userInformationLabel.setText(client.getFirstName().get() + " " + client.getLastName().get());
		loginLabel.setText(client.getLogin().get());
	}
	
	public void changeClientDetails() {
		Dialog<HashMap<String, String>> dialog = new Dialog<>();

		dialog.setTitle("Zmiana ustawieñ konta");
		dialog.setHeaderText("Zmieñ dane klienta");

		ButtonType loginButtonType = new ButtonType("Zaakceptuj", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));


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
		name.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});



		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> name.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				HashMap<String, String> result = new HashMap<String, String>();
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
				client.setAge(Integer.parseInt(age));
				client.setFirstName(resultMap.get("name"));
				client.setLastName(resultMap.get("surname"));
				ClientDAO.updateClient(client);
			} catch (NumberFormatException | SQLException e) {
				System.out.println("B³¹d przy wstawianiu klienta do bazy danych");
				e.printStackTrace();
			}
		});
		
		refreshView();
	}
	
	private boolean isInvalid(String score) {
		if (score == null || score.equals(""))
			return true;

		double result = 0;
		try {
			result = Double.parseDouble(score);
		} catch (NumberFormatException e) {
			return true;
		} catch (NullPointerException e) {
			return true;
		}
		if (result <= 0) {
			return true;
		}

		return false;
	}
	
	@FXML
	private void insertMoney() {
		TextInputDialog bookDialog = new TextInputDialog("Wp³ata pieniêdzy");
		((Button) bookDialog.getDialogPane().lookupButton(ButtonType.OK)).setText("Wp³aæ");
		Button okButton = (Button) bookDialog.getDialogPane().lookupButton(ButtonType.OK);
		((Button) bookDialog.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Cofnij");
		TextField inputField = bookDialog.getEditor();
		inputField.setPromptText("20");

		BooleanBinding isInvalid = Bindings.createBooleanBinding(() -> isInvalid(inputField.getText()),
				inputField.textProperty());
		okButton.disableProperty().bind(isInvalid);
		bookDialog.setHeaderText("Wpisz kwotê jak¹ chcesz zasiliæ twoje konto");

		Optional<String> result = bookDialog.showAndWait();
		int stake = 0;
		if (result.isPresent())
			stake = Integer.parseInt(result.get());

		client.increacseBalance(stake);
		try {
			ClientDAO.updateClient(client);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		refreshView();
	}
}
