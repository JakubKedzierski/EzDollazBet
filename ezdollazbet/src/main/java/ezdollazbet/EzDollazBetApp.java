package ezdollazbet;

import java.io.IOException;

import ezdollazbet.view.UserLayoutController;
import javafx.application.Application;
import javafx.application.Platform;
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

		initRootLayout();
		initTabsLayout();
		initUserLayout();
	}

	public void initTabsLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/TabsLayout.fxml"));
			AnchorPane tabLayout = (AnchorPane) loader.load();
			rootLayout.setCenter(tabLayout);
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

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException error) {
			error.printStackTrace();
			this.stop();
		}
	}
	
	public void initUserLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/UserLayout.fxml"));
			AnchorPane userLayout = (AnchorPane) loader.load();
			rootLayout.setLeft(userLayout);
			
			UserLayoutController controller = loader.getController();
			controller.setMainApp(this);
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
