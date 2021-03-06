package ezdollazbet.view;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.sql.SQLException;
import java.util.Date;

import ezdollazbet.EzDollazBetApp;
import ezdollazbet.models.Bet;
import ezdollazbet.models.BookedBet;
import ezdollazbet.models.Game;
import ezdollazbet.models.Player;
import ezdollazbet.models.TeamStats;
import ezdollazbet.models.UserSession;
import ezdollazbet.models.dao.BetDAO;
import ezdollazbet.models.dao.BookedBetDAO;
import ezdollazbet.models.dao.GameDAO;
import ezdollazbet.models.dao.PlayerDAO;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Setter;

public class UserInterfaceContoller implements ViewRefresher {

	@FXML
	private ScrollPane matchesScroller;
	@FXML
	private VBox matchesBox;
	@FXML
	private TableView<TeamStats> statsTable = new TableView<TeamStats>();
	@FXML
	private TableColumn<TeamStats, String> teamNameColumn;
	@FXML
	private TableColumn<TeamStats, Integer> gamesColumn;
	@FXML
	private TableColumn<TeamStats, Integer> winsColumn;
	@FXML
	private TableColumn<TeamStats, Integer> drawsColumn;
	@FXML
	private TableColumn<TeamStats, Integer> losesColumn;
	@FXML
	private TableColumn<TeamStats, Integer> pointsColumn;
	@FXML
	private TableColumn<TeamStats, Integer> placeColumn;
	@FXML
	private AnchorPane statsPanel;
	@FXML
	private BorderPane tableStatsPane;
	private AnchorPane playersView;
	@Setter
	private ViewRefresher refresher;
	
	@FXML
	private TableView<BookedBet> userBetsList;
	
	@FXML
	private TableColumn<BookedBet, String> bookedHostColumn;
	@FXML
	private TableColumn<BookedBet, String> bookedGuestColumn;
	@FXML
	private TableColumn<BookedBet, String> bookedStatusColumn;
	@FXML
	private TableColumn<BookedBet, Date> bookedDayColumn;
	@FXML
	private TableColumn<BookedBet, Double> bookedStakeColumn;
	@FXML
	private TableColumn<BookedBet, Integer> bookedTypeColumn;
	@FXML
	private TableColumn<BookedBet, Double> bookedOddColumn;
	@FXML
	private TableColumn<BookedBet, Double> bookedPossibleWinColumn;
	
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
		placeColumn.setCellValueFactory(new PropertyValueFactory<TeamStats, Integer>("place"));
		
		bookedHostColumn.setCellValueFactory(new PropertyValueFactory<BookedBet, String>("host"));
		bookedGuestColumn.setCellValueFactory(new PropertyValueFactory<BookedBet, String>("guest"));
		bookedStatusColumn.setCellValueFactory(new PropertyValueFactory<BookedBet, String>("matchStatus"));
		bookedDayColumn.setCellValueFactory(new PropertyValueFactory<BookedBet, Date>("matchDay"));
		bookedStakeColumn.setCellValueFactory(new PropertyValueFactory<BookedBet, Double>("stake"));
		bookedTypeColumn.setCellValueFactory(new PropertyValueFactory<BookedBet, Integer>("betType"));
		bookedOddColumn.setCellValueFactory(new PropertyValueFactory<BookedBet, Double>("bettingOdd"));
		bookedPossibleWinColumn.setCellValueFactory(new PropertyValueFactory<BookedBet, Double>("possibleWinStake"));
		

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
				gameBox.setPadding(new Insets(20, 20, 20, 20));

				ObservableList<Bet> betList = BetDAO.getBetsByGame(game);

				BoxController controller = loader.getController();
				controller.setGame(game);
				controller.setBetList(betList);
				controller.setRefresher(this);
				controller.initializeBet();
				matchesBox.getChildren().add(gameBox);

			} catch (IOException error) {
				error.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@FXML
	private void showTeamPlayers(MouseEvent e) {
		if (e.getClickCount() == 2) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(EzDollazBetApp.class.getResource("view/TeamPlayersView.fxml"));
			try {
				this.playersView = (AnchorPane) loader.load();
				statsPanel.getChildren().setAll(playersView);
				TeamPlayerViewController contoller = loader.getController();
				contoller.setBackSite(this);
				ObservableList<Player> playerList = PlayerDAO.getPlayersByTeamName(statsTable.getSelectionModel().getSelectedItem().getTeamName());
				contoller.setData(playerList);
			
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}
	
	public void setTeamStats(ObservableList<TeamStats> data) {
		statsTable.setItems(data);
	}
	
	public void setBookedBets(ObservableList<BookedBet> data) {
		userBetsList.setItems(data);
	}

	@Override
	public void refreshView() {
		statsPanel.getChildren().setAll(tableStatsPane);
		UserSession session = UserSession.getSession();
		ObservableList<BookedBet> bookedBets= FXCollections.observableArrayList();
		try {
			bookedBets = BookedBetDAO.getBookedBetsByUserLogin(session.getLogin());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBookedBets(bookedBets);
		refresher.refreshView();
	}
}
