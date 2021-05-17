package ezdollazbet.view;

import ezdollazbet.models.Player;
import ezdollazbet.models.TeamStats;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

public class TeamPlayerViewController {
	@Setter
	private ViewRefresher backSite;
	
	@FXML
	private TableView<Player> playersTable;
	
	@FXML
	private TableColumn<Player, String> firstNameColumn;
	@FXML
	private TableColumn<Player, String> secondNameColumn;
	@FXML
	private TableColumn<Player, String> positionColumn;
	@FXML
	private TableColumn<Player, Integer> marketValueColumn;
	@FXML
	private TableColumn<Player, Integer> overallColumn;
	@FXML
	private TableColumn<Player, Integer> ageColumn;
	
	@FXML
	private void initialize() {
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
		secondNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
		positionColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
		marketValueColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("marketValue"));
		overallColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("overall"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));

	}
	
	public void setData(ObservableList<Player> playerList) {
		this.playersTable.setItems(playerList);
	}
	
	@FXML
	private void goBack() {
		backSite.refreshView();
	}
	
	
}
