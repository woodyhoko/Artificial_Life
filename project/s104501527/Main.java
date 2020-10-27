package project.s104501527;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Controller controller = new Controller ();
        fxmlLoader.setController(controller);
        
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Bio-Sim");
        Scene scene = new Scene ( root, 1500, 1000 );
        controller.setScene ( scene );
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("123");
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
