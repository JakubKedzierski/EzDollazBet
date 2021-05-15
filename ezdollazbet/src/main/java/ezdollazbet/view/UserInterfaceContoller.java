package ezdollazbet.view;

import java.io.IOException;
import java.sql.SQLException;

import ezdollazbet.EzDollazBetApp;
import ezdollazbet.models.Bet;
import ezdollazbet.models.BetDAO;
import ezdollazbet.models.Game;
import ezdollazbet.models.GameDAO;
import ezdollazbet.models.TeamStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UserInterfaceContoller {

	@FXML
	private ScrollPane matchesScroller;
	@FXML
	private VBox matchesBox;
	@FXML
	private TableView<TeamStats> statsTable = new TableView<TeamStats>();
	@FXML 
	private TableColumn teamNameColumn;
	@FXML 
	private TableColumn gamesColumn;
	@FXML 
	private TableColumn winsColumn;
	@FXML 
	private TableColumn drawsColumn;
	@FXML 
	private TableColumn losesColumn;
	@FXML 
	private TableColumn pointsColumn;
	@FXML 
	private TableColumn placeColumn;

	@FXML
	private void initialize() {
		matchesScroller.setFitToWidth(true);
		matchesBox.setSpacing(25);
		teamNameColumn.setCellValueFactory(new PropertyValueFactory<TeamStats, String>("teamName"));
		gamesColumn.setCellValueFactory(new PropertyValueFactory<TeamStats, Integer>("games"));
		winsColumn.setCellValueFactory(new PropertyValueFactory<TeamStats, Integer>("wins"));
		drawsColumn.setCellValueFactory(new PropertyValueFactory<TeamStats, Integer>("draws"));
		losesColumn.setCellValueFactory(new PropertyValueFactory<TeamStats, Integer>("loses"));
		pointsColumn.setCellValueFactory(new PropertyValueFactory<TeamStats, Integer>("points"));


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
				gameBox.setPadding(new Insets(20,20,20,20));
				
				ObservableList<Bet> betList = BetDAO.getBetsByGame(game);
				
				BoxController controller = loader.getController();
				controller.setGame(game);
				controller.setBetList(betList);
				controller.initializeBet();
				matchesBox.getChildren().add(gameBox);
				
				
			} catch (IOException error) {
				error.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
	
	public void setTeamStats(ObservableList<TeamStats> data) {
		statsTable.setItems(data);
	}
}
