package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class SceneMot_De_PasseController {
	
	private Scene scene;
	private Stage stage;
	
	private int nombreErreur = 0;

    @FXML
    private Button boutonValider;

    @FXML
    private Label messageDeRetour;

    @FXML
    private PasswordField motDePasse;
    
	@FXML
	private Button boutonRetourner;

    @FXML
    public void validation(ActionEvent event){
    	
    	if (motDePasse.getText().equals("feur") && nombreErreur< 3 ) {
	    	boutonValider.setOnMouseClicked( eventValidation -> {
	    		//Cas mot de passe correct
	        		messageDeRetour.setText("Mot de passe correct");
	        		Parent root;
					try {
						root = FXMLLoader.load(getClass().getResource("SceneModifierParametre.fxml"));
					 	Scene scene = new Scene(root);
		    	    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		    	    	stage.setScene(scene);
		    	    	stage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			});
    	}
    	
    	
    	
    	if (!motDePasse.getText().equals("feur") && nombreErreur == 0) {
    		boutonValider.setOnMouseClicked( eventErreur1 -> {
        	//Essai raté 1 
        		messageDeRetour.setText("Mot de passe incorrect. Il vous reste 2 essais");
        		nombreErreur++;
    		});
    	}
    	
    	
    	
    	if (!motDePasse.getText().equals("feur") && nombreErreur == 1) {
    		boutonValider.setOnMouseClicked( eventErreur2 -> {
	    	//Essai raté 2
	    		messageDeRetour.setText("Mot de passe incorrect. Il vous reste 1 essai");
	    		nombreErreur++;
    		});
    	}
    	
    	
    	
    	if (!motDePasse.getText().equals("feur") && nombreErreur == 2) {
    		boutonValider.setOnMouseClicked( eventErreur3 -> {
	    	//Essai raté 3
	    		messageDeRetour.setText("Mot de passe incorrect. Il ne vous reste aucun essai");
	    		nombreErreur++;
	    		if (nombreErreur >= 3) {
	    		   	Parent root;
					try {
						root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
					 	Scene scene = new Scene(root);
		    	    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		    	    	stage.setScene(scene);
		    	    	stage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	   
	    	    
	    		}
    		});
    	}
    
    }
    
    @FXML
	void clickBoutonRetourner(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();

	}

}