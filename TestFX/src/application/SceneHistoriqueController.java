package application;

import java.awt.Event;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import modele.BDHistorique;
import modele.Recherche;
import modele.Resultat;
import modele.TypeRecherche;

public class SceneHistoriqueController  implements Initializable {

	@FXML
    private AnchorPane PaneHistorique;
    @FXML
    private Button buttonAdmin;
    
    @FXML
    private VBox resultatHistorique;
    
    @FXML
    private Button buttonRetourner;
   
    private BDHistorique bdHistorique = BDHistorique.getInstance();

    private String mot ; 
    private int i ;

    @FXML
    void clickButtonAdmin(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("SceneMot_De_Passe.fxml"));
    	Scene scene = new Scene(root);
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();

    }

    @FXML
    void clickButtonRetourner(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();

    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
		    List<String>  liste = new ArrayList<>() ;
		    List<String> listeDH = bdHistorique.getListDateHeure();
		    List<Recherche> listeRecherche = bdHistorique.getListRecherche();
		    System.out.println(listeRecherche);
		    System.out.println(bdHistorique.visualiserHistorique());
		    
		    //Fin ajout historique*/
		    /*
		    liste.add("r1");  
		    liste.add("r2");
		    liste.add("r3");
		    */
		    i = 0 ; 
		    PaneHistorique.setPrefHeight(4+(70*liste.size()));
		    
		    for(Recherche r : listeRecherche ) {
		    	String fusion = listeDH.get(i) + " : " + r.getNom();
		    	Button bREsultat = new Button () ; 
		    	//String affichage = r+ " : " + r ; 
		    	String affichage = fusion; 
		    	Label label = new Label(affichage);
		    	//System.out.println("Ici"+getI());
		    	bREsultat.setOnAction(new EventHandler<ActionEvent>() {
		    		
				  @Override 
				  public void handle (ActionEvent event)  {
					  try {
						     
							FXMLLoader loader = new FXMLLoader((getClass().getResource("SceneResultatHistorique.fxml")));
							
							Parent root = loader.load();
							SceneResultatHistoriqueController controller = loader.getController() ; 
							//System.out.println("La"+getI());
							controller.setMot(r.getNom());
							controller.setIndice(i);
							controller.initialTextField();
							Scene scene = new Scene(root);
							Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
							stage.setScene(scene);
							stage.show();
					  }catch(Exception e){
						  e.printStackTrace() ; 
					  }
				  }
		    	});
		    	bREsultat.setStyle("-fx-background-color : rgba(221, 244, 199, 0.6) ; -fx-border-color :  #018037 ; -fx-border-width : 2  ");
		  
		    	bREsultat.setGraphic(label);
		    	label.setOpacity(1);
		    	bREsultat.setPrefHeight(70);
		    	bREsultat.setMinHeight(70);
		    	bREsultat.setPrefWidth(890);
		    	resultatHistorique.getChildren().add(bREsultat); 
		    	i++ ;
		    }
	}
	
	

				
		    
            	
	

}