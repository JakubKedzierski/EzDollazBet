package ezdollazbet.view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

import ezdollazbet.models.Bet;
import ezdollazbet.models.BetDAO;
import ezdollazbet.models.Client;
import ezdollazbet.models.ClientDAO;
import ezdollazbet.models.Game;
import ezdollazbet.models.UserSession;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import lombok.Setter;

public class BoxController {

	@FXML
	private Button buttonBookWin;

	@FXML
	private Button buttonBookDraw;

	@FXML
	private Button buttonBookLose;

	@FXML
	private Label hostLabel;

	@FXML
	private Label guestLabel;

	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;

	@FXML
	private Label emptyBets;

	@FXML
	private Label data;

	@Setter
	private Game game;

	@Setter
	private ObservableList<Bet> betList;

	@FXML
	private void initialize() {

	}

	public void initializeBet() {
		if (game.getMatchDay().get() != null) {
			Platform.runLater(() -> {
				data.setVisible(true);
				data.setText(game.getMatchDay().get().toString());
			});

		}

		if (betList != null) {
			if (betList.isEmpty()) {
				Platform.runLater(() -> {
					label1.setVisible(false);
					label2.setVisible(false);
					label3.setVisible(false);
					emptyBets.setVisible(true);
				});
			}
			for (Bet bet : betList) {
				Platform.runLater(() -> {
					label1.setVisible(true);
					label2.setVisible(true);
					label3.setVisible(true);

					if (bet.getBetType().intValue() == 1) {
						buttonBookWin.setVisible(true);
						buttonBookWin.setText(Double.toString(bet.getBettingOdd().doubleValue()));
					} else if (bet.getBetType().intValue() == 0) {
						buttonBookDraw.setVisible(true);
						buttonBookDraw.setText(Double.toString(bet.getBettingOdd().doubleValue()));
					} else if (bet.getBetType().intValue() == 3) {
						buttonBookLose.setVisible(true);
						buttonBookLose.setText(Double.toString(bet.getBettingOdd().doubleValue()));
					}
				});
			}
		}

		Platform.runLater(() -> {
			hostLabel.setText(game.getHost().get());
			guestLabel.setText(game.getGuest().get());
		});
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

	private int bookWithStake() {
		TextInputDialog bookDialog = new TextInputDialog("Obstawianie zak³adu");
		((Button) bookDialog.getDialogPane().lookupButton(ButtonType.OK)).setText("Obstaw");
		Button okButton = (Button) bookDialog.getDialogPane().lookupButton(ButtonType.OK);
		((Button) bookDialog.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Cofnij");
		TextField inputField = bookDialog.getEditor();
		inputField.setPromptText("20");

		BooleanBinding isInvalid = Bindings.createBooleanBinding(() -> isInvalid(inputField.getText()),
				inputField.textProperty());
		okButton.disableProperty().bind(isInvalid);
		bookDialog.setHeaderText("Obstawiasz mecz: " + game.getHost().get() + " - " + game.getGuest().get()
				+ "\nWpisz kwotê jak¹ chcesz obstawiæ");

		Optional<String> result = bookDialog.showAndWait();
		int stake = 0;
		if (result.isPresent())
			stake = Integer.parseInt(result.get());

		return stake;
	}

	@FXML
	private void book(Event e) {
		Object button = e.getSource();
		int stake = bookWithStake();
		if (stake == 0)
			return;
		String clientLogin = UserSession.getSession().getLogin();
		Client client = new Client();
		try {
			client = ClientDAO.getClientByLogin(clientLogin);
		} catch (SQLException e1) {
			e1.printStackTrace();
			return;
		}

		if (client.getBalance().get() < stake) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Nie masz odpowiedniej iloœci pieniêdzy na koncie");
			alert.setContentText("Brak wystarczaj¹cej iloœci pieniêdzy na koncie.\nZak³ad zostanie anulowany");
			alert.setTitle("B³¹d przy tworzeniu zak³adu");
			alert.showAndWait();
			return;
		}

		try {
			for (Bet bet : betList) {
				if (button.equals(buttonBookWin) && bet.getBetType().get() == 1) {
					BetDAO.bookBet(bet, client, stake);
				} else if (button.equals(buttonBookDraw) && bet.getBetType().get() == 0) {
					BetDAO.bookBet(bet, client, stake);
				} else if (button.equals(buttonBookLose) && bet.getBetType().get() == 2) {
					BetDAO.bookBet(bet, client, stake);
				}
			}
			client.decrementBalance(stake);
			ClientDAO.updateClient(client);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
