package ezdollazbet.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

		
		for (int i = 0; i < 70; i++) {
			AnchorPane anchorPane = new AnchorPane();
			String style = String.format("-fx-background: rgb(%d, %d, %d);" + "-fx-background-color: -fx-background;",
					15, 15, 15);
			anchorPane.setStyle(style);
			Label label = new Label("Pane ");
			AnchorPane.setLeftAnchor(label, 5.0);
			AnchorPane.setTopAnchor(label, 5.0);
			Button button = new Button("Remove");
			button.setOnAction(evt -> matchesBox.getChildren().remove(anchorPane));
			AnchorPane.setRightAnchor(button, 5.0);
			AnchorPane.setTopAnchor(button, 5.0);
			AnchorPane.setBottomAnchor(button, 5.0);
			anchorPane.getChildren().addAll(label, button);
			matchesBox.getChildren().add(anchorPane);
		}
	}
}
