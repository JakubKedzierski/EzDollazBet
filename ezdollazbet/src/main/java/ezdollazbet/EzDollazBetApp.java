package ezdollazbet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import ezdollazbet.models.ClientDAO;
import ezdollazbet.models.TeamStats;
import ezdollazbet.models.TeamStatsDAO;
import ezdollazbet.view.StartUpController;
import ezdollazbet.view.UserInterfaceContoller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EzDollazBetApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("EzDollazBet");
		this.primaryStage.setResizable(false);
		initUserStartUpDialog();
	}
	

	public void initLayout() {		
		initRootLayout();
		initTabsLayout();
		initUserLayout();
	}
	
	public void initUserStartUpDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/UserLoginLayout.fxml"));
			AnchorPane loginLayout = (AnchorPane) loader.load();
			loginLayout.getStylesheets().add(EzDollazBetApp.class.getResource("view/box.css").toExternalForm());
			
			StartUpController controller = loader.getController();
			controller.setMainApp(this);

			Scene scene = new Scene(loginLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException error) {
			error.printStackTrace();
			this.stop();
		}
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			rootLayout.getStylesheets().add(EzDollazBetApp.class.getResource("view/box.css").toExternalForm());

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.centerOnScreen();
		} catch (IOException error) {
			error.printStackTrace();
			this.stop();
		}
	}
	
	public void initTabsLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/TabsLayout.fxml"));
			AnchorPane tabLayout = (AnchorPane) loader.load();
			
			UserInterfaceContoller controller = loader.getController();
			
			ObservableList<TeamStats> teamStats = TeamStatsDAO.getAllTeamStats();
			controller.setTeamStats(teamStats);
			
			rootLayout.setCenter(tabLayout);
		} catch (IOException error) {
			error.printStackTrace();
			this.stop();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initUserLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/UserLayout.fxml"));
			AnchorPane userLayout = (AnchorPane) loader.load();
			rootLayout.setLeft(userLayout);
		} catch (IOException error) {
			error.printStackTrace();
			this.stop();

		}
	}

	@Override
	public void stop() {
		System.out.println("App stopped");
	}

	public static void main(String[] args) {
		launch(args);
	}

}
