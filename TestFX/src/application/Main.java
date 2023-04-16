package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.ModeRecherche;
import modele.Parametre;
import modele.ThreadIndexation;

public class Main extends Application {

	private ThreadIndexation threadIndexation = new ThreadIndexation();
	private ModeRecherche modeRecherche;
    @Override
    public void start(Stage primaryStage) throws Exception{
    	
    	Parent root=FXMLLoader.load(getClass().getResource("SceneDebutBarreDeProgression.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneDebutBarreDeProgression.fxml"));
    	root = loader.load();
    	SceneDebutBarreDeProgressionController sceneDebutBarreDeProgressionController = loader.getController();
    	sceneDebutBarreDeProgressionController.beginTimer();
    	
    	//Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        //Parent root = loader.load();
        //MainSceneController controller = loader.getController();
    	
    	//démarrage du thread au démarrage du moteur
    	this.threadIndexation.start();
    	System.out.println("Thread Start");
    	
    	
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("NPSE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}