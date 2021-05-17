package ezdollazbet.view;

import javafx.fxml.FXML;
import lombok.Setter;

public class TeamPlayerViewController {
	@Setter
	private ViewRefresher backSite;
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void goBack() {
		System.out.println("going back");
		backSite.refreshView();
	}
	
	
}
