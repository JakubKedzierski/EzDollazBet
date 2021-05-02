package ezdollazbet;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EzDollazBetApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("EzDollazBet");

        initRootLayout();
        FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("view/TabsLayout.fxml"));
		AnchorPane clientStartUpDialog = (AnchorPane) loader.load();
		rootLayout.setCenter(clientStartUpDialog);
	}
	
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);        
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
