package ezdollazbet.view;

import ezdollazbet.models.Bet;
import ezdollazbet.models.Game;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
		if(game.getMatchDay().get()!=null) {
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

	@FXML
	private void bookWin() {

	}
}
