package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainSceneController {
	
	@FXML
	private Button buttonHistorique;
	
    @FXML
    private Button buttonAdmin;

    @FXML
    private Button buttonImage;

    @FXML
    private Button buttonSon;

    @FXML
    private Button buttonTexte;

    @FXML
    void clickButtonAdmin(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("SceneMot_De_Passe.fxml"));
    	Scene scene = new Scene(root);
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();

    }

    @FXML
        void clickButtonSon(ActionEvent event) throws Exception    {
		Parent root = FXMLLoader.load(getClass().getResource("SceneRechercheSon.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        	

        }


    @FXML
    void clickButtonImage(ActionEvent event)  throws Exception{
    	Parent root = FXMLLoader.load(getClass().getResource("SceneRechercheImage.fxml"));
    	Scene scene = new Scene(root);
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();

    }
    
    @FXML
	void clickButtonHistorique(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("SceneHistorique.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();

	}

    @FXML
    void clickButtonTexte(ActionEvent event) throws Exception{
    	Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
    	Scene scene = new Scene(root);
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    	

    }

}




