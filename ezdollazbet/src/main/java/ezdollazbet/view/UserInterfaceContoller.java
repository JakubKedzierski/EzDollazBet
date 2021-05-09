package ezdollazbet.view;

import java.io.IOException;
import java.sql.SQLException;

import ezdollazbet.EzDollazBetApp;
import ezdollazbet.models.Game;
import ezdollazbet.models.GameDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UserInterfaceContoller {

	@FXML
	ScrollPane matchesScroller;
	@FXML
	VBox matchesBox;

	@FXML
	private void initialize() {
		matchesScroller.setFitToWidth(true);

		ObservableList<Game> gamesList = FXCollections.observableArrayList();
		try {
			gamesList = GameDAO.getAllAwaitingGames();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Error during handling database connection");
			e.printStackTrace();
		}

		for (Game game : gamesList) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(EzDollazBetApp.class.getResource("view/GameBox.fxml"));
				AnchorPane gameBox = (AnchorPane) loader.load();
				matchesBox.getChildren().add(gameBox);
			} catch (IOException error) {
				error.printStackTrace();
			}

		}

	}
}
