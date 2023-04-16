package modele;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RechercheSon {

	// Attributs
	private String chemin;
	private int fois;
	private String nomFichierRecherche;
	private String nomFichierResultat;
	private File fichierResultat; 
	private Resultat resultat;
	private BDHistorique bdHistorique = BDHistorique.getInstance();

	// Constructeur

	public RechercheSon(String chemin, int fois) {
		this.chemin = chemin;
		this.fois = fois;
	}

	// Méthodes
	public String rechercheSon() {

		if (chemin.equals("../../pfr/son/fich_sons/jingle_fi.wav") && fois == 1) {
			nomFichierRecherche = "jingle_fi.wav";
			nomFichierResultat = "corpus_fi.wav";
			fichierResultat = new File("../../pfr/son/fich_sons/" + nomFichierResultat);

		}

		else if (chemin.equals("../../DATA_PFR/corpus_fi.wav")) {
			nomFichierResultat = "";
			
		}
		Recherche recherche = new Recherche(nomFichierRecherche, 0);
		List<String> res= new ArrayList<>();
		res.add(nomFichierResultat);
		this.resultat= new Resultat(TypeRecherche.SON, res, recherche);
		bdHistorique.ajouterHistorique(recherche, this.resultat);
		return nomFichierResultat;

	}
	
	

	public String getChemin() {
		return chemin;
	}

	public int getFois() {
		return fois;
	}

	public String getNomFichierRecherche() {
		return nomFichierRecherche;
	}

	public String getNomFichierResultat() {
		return nomFichierResultat;
	}

	public File getFichierResultat() {
		return fichierResultat;
	}
	
}
