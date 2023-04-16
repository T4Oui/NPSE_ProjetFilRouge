package application;
import javafx.util.Duration;
import modele.BDHistorique;
import modele.Resultat;
import modele.TypeRecherche;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SceneResultatHistoriqueController implements Initializable{

	private String mot ; 
    @FXML
    private Button buttonAdmin;

    @FXML
    private Button buttonRetourner;
    
    @FXML
    private TextField entrerRecherche;
    @FXML
    private VBox menuResultat;
    @FXML
    private HBox resultat;
    private MediaPlayer mediaPlayer ;
    private int indice ; 
    private BDHistorique bdHistorique = BDHistorique.getInstance();

    @FXML
    void clickButtonAdmin(ActionEvent event) throws Exception {
    	if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    	Parent root = FXMLLoader.load(getClass().getResource("SceneMot_De_Passe.fxml"));
    	Scene scene = new Scene(root);
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();

    }

    @FXML
    void clickButtonRetourner(ActionEvent event) throws Exception {
    	if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
			Parent root = FXMLLoader.load(getClass().getResource("SceneHistorique.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
    }
    public void setMot(String m) {
    	mot  = m ; 
    }

    public void setIndice (int i  ){
    	indice = i ;
    	
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initialVBox();
		
	}
	public void initialTextField () {
		entrerRecherche.setEditable(false);
		entrerRecherche.setText(mot);
		
	}
	
	public String getAbsolutePath(String relativePath) {
	    Path path = Paths.get(relativePath).toAbsolutePath().normalize();
	    return path.toString();
	}
	
	public void initialVBox () {
		//int typeRecherche = 3;
		List<Resultat>  liste = bdHistorique.getListResultat() ;
		TypeRecherche type = liste.get(this.indice).getTypeResultat();
		System.out.println("TYPE : "+type);
		List<String> listeRes = liste.get(this.indice).getListeResultat();
	    menuResultat.setPrefHeight(4+(70*liste.size()));
	    for (String r : listeRes ) {
	    	Button bREsultat = new Button () ; 
	    	String affichage = r ; 
	    	Label label = new Label(affichage);
	    	
	    	bREsultat.setOnAction(new EventHandler<ActionEvent>() {
	    		
				  @Override 
				  public void handle (ActionEvent event)  {
					  try { 
					  switch (type) {
					  case TEXTE : 
						  String[] rSplit = r.split(" ");
						  String chemin = rSplit[0];
						  //String chemin =r;
						  Label texte = new Label () ; 
					        // Créer un objet File à partir du chemin d'accès
					        File fichier = new File(chemin);
					        
					        // Lire le contenu du fichier et l'afficher dans le label
					        StringBuilder contentBuilder = new StringBuilder();
					        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
					            String sCurrentLine;
					            while ((sCurrentLine = br.readLine()) != null) {
					                contentBuilder.append(sCurrentLine).append("\n");
					            }
					        } catch (IOException e) {
					            e.printStackTrace();
					        }
					        String contenuFichier = contentBuilder.toString();
					        texte.setText(contenuFichier);
					  
						     resultat.getChildren().clear() ;
						     
						     
						     texte.setFont(new Font (10));
						     resultat.getChildren().add(texte); 
						     break ; 
					  case IMAGE : 
						  resultat.getChildren().clear() ;
						  //System.out.println("NOM:"+nomRes);
						  String[] rSplit2 = r.split(" ");
						  Image im = new Image ( "file:"+ "../../pfr/image/fich_images/"+rSplit2[1]) ; 
						  System.out.println(im);
						  ImageView image = new ImageView ();
						  image.setPreserveRatio(true);
						  image.setFitWidth(400);
						  image.setImage(im);
						  resultat.getChildren().add(image); 
						  break ; 
					  
					  case SON : 
						  if (mediaPlayer != null) {
					            mediaPlayer.stop();
					        }
					  resultat.getChildren().clear();
					 
					  Media media = new Media("file:"+getAbsolutePath("../../DATA_PFR/corpus_fi.wav"));
	                  mediaPlayer = new MediaPlayer(media);

	                  // Création de la ProgressBar
	                  ProgressBar progressBar = new ProgressBar();
	                  progressBar.setProgress(0);

	                  // Ajout d'un ChangeListener pour mettre à jour la ProgressBar en temps réel
	                  mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
	                      @Override
	                      public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
	                          progressBar.setProgress(mediaPlayer.getCurrentTime().toMillis() / mediaPlayer.getTotalDuration().toMillis());
	                      }
	                  });

	                 
	                  //Button buttonStopper = new Button () ; 
	                  //Button buttonCommencer = new Button() ; 
	               // Ajout de la ProgressBar et lecture du fichier audio
	                  progressBar.setPrefWidth(400) ;
	                  progressBar.setPrefHeight(40) ;
	                  resultat.getChildren().add(progressBar);
	                  mediaPlayer.play();

					  break ; 
					  }
					  
						    		 
							
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
	    	menuResultat.getChildren().add(bREsultat); 
	    	
	    }
	}


}
