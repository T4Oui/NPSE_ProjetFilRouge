package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import modele.TypeRecherche;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import java.io.File;

import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class Scene1Controller {
	private double percentage ;
	private int choix  =0 ; // 0 mot clef 1 chemin  
	@FXML
	private Button buttonSupprimer;
	@FXML
	private Button buttonHistorique;
	@FXML
	private Label pourcentage;
	@FXML
	private Button buttonAdmin;
	@FXML
	private Button buttonRetourner;
	@FXML
	private Button buttonRecherche;
	@FXML
	private TextField entrerRecherche;
	@FXML
	private Slider choixPourcentageAssemblance;
	@FXML
	private Button buttonChoisirFichier;
	private String motARechercher = null;
	private String cheminMot;

	// Event Listener on Button[#buttonAdmin].onAction
	@FXML
	public void clickButtonAdmin(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("SceneMot_De_Passe.fxml"));
    	Scene scene = new Scene(root);
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
	}

	// Event Listener on Button[#buttonRetourner].onAction
	@FXML
	public void clickButtonRetourner(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	// Event Listener on Button[#buttonRecherche].onAction
	@FXML
	public void clickButtonRecherche(ActionEvent event) throws Exception {
		if (!(entrerRecherche.getText().isEmpty() || entrerRecherche.getText().isBlank())) {
		
		FXMLLoader loader = new FXMLLoader((getClass().getResource("SceneResultatRecherche.fxml")));
		
		Parent root = loader.load();
		SceneResultatRechercheController controller = loader.getController() ; 
		//System.out.println("La"+getI());
		System.out.println("Ici"+entrerRecherche.getText());
		controller.setMot(entrerRecherche.getText());
		controller.setCheminMot(cheminMot);
		controller.setPercentage(percentage);
		controller.typeRecher(TypeRecherche.TEXTE);
		controller.mot_chem(choix);
		controller.initialVBox () ; 
		controller.initialTextField ();
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();

	}
	}

	// Event Listener on TextField[#entrerRecherche].onAction
	@FXML
	public void entrerRecherche(ActionEvent event) {
		motARechercher = entrerRecherche.getText();
		// TODO Autogenerated
	}

	// Event Listener on Slider[#choixPercentageAssemblance].onDragDetected
	@FXML
	public void choisirPourcentageAssemblance(MouseEvent event) {
		// Ajouter un ChangeListener sur la propriété value du Slider
		choixPourcentageAssemblance.valueProperty().addListener((observable, oldValue, newValue) -> {
			// Calculer le pourcentage actuel du Slider
			percentage = (newValue.doubleValue() / choixPourcentageAssemblance.getMax()) * 100;
			// Mettre à jour le texte du Label avec le pourcentage
			pourcentage.setText(String.format("%.0f%%", percentage));
		});

		// TODO Autogenerated
	}

	@FXML
	void clickButtonHistorique(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("SceneHistorique.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();

	}

	// Event Listener on Button[#buttonChoisirFichier].onAction
	@FXML
	public void clickButtonChoisirFichier(ActionEvent event) throws Exception {
		choix = 1 ;
		// Créer une instance de FileChooser
		FileChooser fileChooser = new FileChooser();

		// Définir le titre de la fenêtre de sélection de fichiers
		fileChooser.setTitle("Sélectionner un fichier");
		
		ExtensionFilter xmlFilter = new ExtensionFilter("Fichiers XML (*.xml)", "*.xml");
	    fileChooser.getExtensionFilters().add(xmlFilter);

		// Récupérer la référence à la fenêtre principale
		Stage stage = (Stage) buttonChoisirFichier.getScene().getWindow();

		// Vérifier si la référence est valide avant d'ouvrir la boîte de dialogue
		if (stage != null) {
			File selectedFile = fileChooser.showOpenDialog(stage);

			// Vérifier si un fichier a été sélectionné
			if (selectedFile != null) {
				// Extraire le nom du fichier à partir du chemin absolu
				String fileName = selectedFile.getName();
		
				SceneResultatRechercheController controller = new  SceneResultatRechercheController();
				this.cheminMot = selectedFile.getPath();
				System.out.println("OUI:"+selectedFile.getPath());
				// Mettre le nom du fichier sélectionné dans le TextField
				entrerRecherche.setText(fileName);
				entrerRecherche.setEditable(false);

			}
		}
	}

	@FXML
	void clickButtonSupprimer(ActionEvent event) {
		choix = 0 ; 
		entrerRecherche.clear();
		entrerRecherche.setEditable(true);

	}
}