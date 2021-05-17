package ezdollazbet.view;

import ezdollazbet.models.Player;
import ezdollazbet.models.TeamStats;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.Setter;

public class TeamPlayerViewController {
	@Setter
	private ViewRefresher backSite;
	@Setter
	private String teamName;
	
	@FXML
	private TableView playersTable;
	
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
		
	}
	
	@FXML
	private void goBack() {
		System.out.println("going back");
		backSite.refreshView();
	}
	
	
}
