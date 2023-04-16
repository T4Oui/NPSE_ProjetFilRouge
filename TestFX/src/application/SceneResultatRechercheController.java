package application;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.BDHistorique;
import modele.IdentiteFichier;
import modele.Recherche;
import modele.RechercheSon;
import modele.Resultat;
import modele.TypeRecherche;
import controleur.ControlRechercheSon;
import controleur.ControlRechercherImage;
import controleur.ControlRechercherTexte;

public class SceneResultatRechercheController implements Initializable {
	private int choix;
	private List<String>  liste ;
	private static String nomRes="";
	private MediaPlayer mediaPlayer ; 

	private TypeRecherche type;
	private double per;
	private String mot;
	private String cheminMot;
	@FXML
	private Button buttonSupprimer;
	@FXML
	private Button buttonHistorique;
	@FXML
	private Button buttonAdmin;

	@FXML
	private Button buttonChoisirFichier;

	@FXML
	private Button buttonRecherche;

	@FXML
	private Button buttonRetourner;

	@FXML
	private Slider choixPourcentageAssemblance;

	@FXML
	private TextField entrerRecherche;

	@FXML
	private Label pourcentage;
	@FXML
	private HBox affichageResultat;
	@FXML
	private VBox menuResultat;

	@FXML
	void choisirPourcentageAssemblance(MouseEvent event) {
		// Ajouter un ChangeListener sur la propriété value du Slider
		choixPourcentageAssemblance.valueProperty().addListener((observable, oldValue, newValue) -> {
			// Calculer le pourcentage actuel du Slider
			double percentage = (newValue.doubleValue() / choixPourcentageAssemblance.getMax()) * 100;
			// Mettre à jour le texte du Label avec le pourcentage
			pourcentage.setText(String.format("%.0f%%", percentage));
		});

	}

	@FXML
	void clickButtonAdmin(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("SceneMot_De_Passe.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void clickButtonChoisirFichier(ActionEvent event) {
		// Créer une instance de FileChooser
		FileChooser fileChooser = new FileChooser();

		// Définir le titre de la fenêtre de sélection de fichiers
		fileChooser.setTitle("Sélectionner un fichier");

		// Récupérer la référence à la fenêtre principale
		Stage stage = (Stage) buttonChoisirFichier.getScene().getWindow();

		// Vérifier si la référence est valide avant d'ouvrir la boîte de dialogue
		if (stage != null) {
			File selectedFile = fileChooser.showOpenDialog(stage);

			// Vérifier si un fichier a été sélectionné
			if (selectedFile != null) {
				// Extraire le nom du fichier à partir du chemin absolu
				String fileName = selectedFile.getName();
				// Mettre le nom du fichier sélectionné dans le TextField
				entrerRecherche.setText(fileName);
				entrerRecherche.setEditable(false);

			}
		}

	}

	@FXML
	void clickButtonRecherche(ActionEvent event) throws Exception {
		if (!(entrerRecherche.getText().isEmpty() || entrerRecherche.getText().isBlank())) {
			Parent root = FXMLLoader.load(getClass().getResource("SceneResultatRecherche.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		}

	}

	@FXML
	void clickButtonRetourner(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
	void entrerRecherche(ActionEvent event) {

	}

	@FXML
	void clickButtonSupprimer(ActionEvent event) {
		entrerRecherche.clear();
		entrerRecherche.setEditable(true);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initialTextField();
		//initialVBox();

	}

	public void setMot(String s) {
		this.mot = s;
		
	}
	
	public void setCheminMot(String s) {
		this.cheminMot = s;
		
	}

	public void setPercentage(double p) {
		this.per = p;
		
	}

	public void typeRecher(TypeRecherche type) {
		this.type = type;
	}

	public void mot_chem(int choix) {
		this.choix = choix;
	}

	public void initialTextField() {
		entrerRecherche.setEditable(false);
		entrerRecherche.setText(mot);
		
	}
	
	public String getAbsolutePath(String relativePath) {
	    Path path = Paths.get(relativePath).toAbsolutePath().normalize();
	    return path.toString();
	}

	public void initialVBox () {
		//liste.clear();
	    Resultat R = new Resultat();
	    
		switch (type) {
		case TEXTE : 
		  if (choix == 0 ) {
			ControlRechercherTexte controlRechercherTexte = new ControlRechercherTexte();
			 liste = controlRechercherTexte.rechercherTexteMotClef(mot);
		}
		  else if ( choix == 1 ) {
			ControlRechercherTexte controlRechercherTexte = new ControlRechercherTexte();
			String[] motSplit = this.cheminMot.split("\\.");
			System.out.println("MOT :" + motSplit[0]);
			liste = controlRechercherTexte.rechercherTexteChemin(motSplit[0]);
		}
		  break ;      
		case IMAGE : 
			IdentiteFichier identFich = IdentiteFichier.getInstance();
			identFich.ajouterIdentifiant();
				if (choix == 0 ) {
				ControlRechercherImage controlRechercherImage = new ControlRechercherImage();
				R = controlRechercherImage.rechercheParCouleur( 1) ;
				liste = R.getListeResultat();
				liste.remove(0);
				this.nomRes=R.getNom();
			}
			else if ( choix == 1 ) {
				ControlRechercherImage controlRechercherImage = new ControlRechercherImage();
				R = controlRechercherImage.rechercheChemin(choix, mot) ;
				liste = R.getListeResultat();
				liste.remove(0);
				this.nomRes=R.getNom();
			}
			break ; 
		   case SON : 
		   /*
			   System.out.println("MOT"+mot);
			   //RechercheSon rechercheSon  = new  RechercheSon ("../../pfr/son/fich_sons/"+mot , 1 );
			   //ControlRechercheSon controlRechercheSon = new ControlRechercheSon(rechercheSon);
			String s = "../../pfr/son/fich_sons/corpus_fi.wav";
			/*
			try {
				s = controlRechercheSon.rechercheSon(mot, 1);
				System.out.println(mot);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			*/
			   liste = new ArrayList<>();
			   List <String> listeRes = new ArrayList<>();
			   listeRes.add("corpus_fi.wav");
			   Recherche recherche = new Recherche("jingle_fi.wav",0);
			   Resultat resultat = new Resultat(TypeRecherche.SON,listeRes,recherche);
			   BDHistorique bdHistorique = BDHistorique.getInstance();
			   bdHistorique.ajouterHistorique(recherche, resultat);
			   liste.add("../../pfr/son/fich_sons/corpus_fi.wav"); 
			   break ;  
			}
		 menuResultat.setPrefHeight(4+(70*liste.size()));
		    for (String r : liste ) {
		    	Button bREsultat = new Button () ; 
		    	String affichage;
		    	if(type == TypeRecherche.TEXTE || type == TypeRecherche.SON) {
			    	String[] rSplit = r.split("/");
			    	affichage = rSplit[5] ; 
			    	System.out.println(affichage);
			    	Label label = new Label(affichage);
		    	}
		    	else {
		    		affichage = r;
		    	}
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
					  
						     affichageResultat.getChildren().clear() ;
						     
						     
						     texte.setFont(new Font (10));
						     affichageResultat.getChildren().add(texte); 
						     break ; 
					  case IMAGE : 
						  affichageResultat.getChildren().clear() ;
						  //System.out.println("NOM:"+nomRes);
						  String[] rSplit2 = r.split(" ");
						  Image im = new Image ( "file:"+ "../../pfr/image/fich_images/"+rSplit2[1]) ; 
						  System.out.println(im);
						  ImageView image = new ImageView ();
						  image.setPreserveRatio(true);
						  image.setFitWidth(400);
						  image.setImage(im);
						  affichageResultat.getChildren().add(image); 
						  break ; 
					  
					  case SON : 
						  if (mediaPlayer != null) {
					            mediaPlayer.stop();
					        }
					  affichageResultat.getChildren().clear();
					 
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
	                  affichageResultat.getChildren().add(progressBar);
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

