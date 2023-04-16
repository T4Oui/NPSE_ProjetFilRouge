package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;

public class SceneDebutBarreDeProgressionController implements Initializable{
	
	private Timer timer;
	
	private TimerTask task;
	
	private double augmentation = 0;
	@FXML
	private ProgressBar myProgressbar;
	@FXML
	private Button  boutonContinuer;
	@FXML
	private Label labelVeuillezPatienter;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myProgressbar.setStyle("-fx-accent: green;");
		
	}

	public void continuer(ActionEvent event) throws InterruptedException, IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		Scene scene = new Scene(root);
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
	}
	
	
	public void beginTimer() {
        timer = new Timer();
 

        task = new TimerTask() {
            public void run() {
                augmentation += 0.1;
                myProgressbar.setProgress(augmentation);
                System.out.println(augmentation);
                //labelVeuillezPatienter.setText(Integer.toString((int)Math.round(augmentation*100))+"%");
                if(augmentation == 0.9999999999999999) cancelTimer();
            }
        };

        timer.scheduleAtFixedRate(task, 0, 3000);
    }
	
	

	public void cancelTimer() {
	    timer.cancel();
	    //boutonContinuer.setDisable(false);
		boutonContinuer.setVisible(true);
		labelVeuillezPatienter.setVisible(false);
	     
	}
	



}
