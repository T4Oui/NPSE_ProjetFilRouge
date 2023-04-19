package controleur;

import modele.TypeRechercheTexte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modele.BDHistorique;
import modele.Fichier;
import modele.ModeRecherche;
import modele.Parametre;
import modele.Recherche;
import modele.Resultat;
import modele.ThreadIndexation;
import modele.TypeRecherche;
import libraryJNA.LibraryTexteMoteur;

public class ControlRechercherTexte {
	private LibraryTexteMoteur libraryTexteMoteur = LibraryTexteMoteur.INSTANCE;
	private Parametre parametre = Parametre.getIntance();
	private ModeRecherche modeRecherche;
	private ThreadIndexation threadIndexation = new ThreadIndexation();
	private TypeRecherche typeRecherche = TypeRecherche.TEXTE;
	private BDHistorique bdHistorique = BDHistorique.getInstance();
	
	private String cheminResultatRech = "../../pfr_code/data/resultat_recherche.txt";
	
	public List<String> rechercherTexteMotClef(String motClef) {
		List<String> resultats = new ArrayList<>();
		
		String[] tableauMotclefs = motClef.split(" ");
		List<String> listeMotclefs = new ArrayList<>(Arrays.asList(tableauMotclefs));
		
//		// Gestion sans espace
//		
//		List<String> newListeMotclefs = new ArrayList<>();
//		
//		for(String element : listeMotclefs) {
//			String mot = "";
//			for (int i = 0; i < element.length(); i++) {
//			    if (element.charAt(0) == '-' || element.charAt(i) == '-') {
//			    	if(!mot.equals("")) {
//			    		
//			    	}
//			        
//			    }
//			    
//			}
//		}
		
		
//		// D�but Pour le Test
//		System.out.println("Mot Clefs : " + motClef);
//		System.out.println("Liste Mot clefs : " + listeMotclefs.toString());
//		// Fin Pour le Test

		if(listeMotclefs.size() == 1)
		{
			libraryTexteMoteur.rech_MC(motClef);
			resultats = Fichier.lire(cheminResultatRech);
			//Fichier.supprimer(cheminResultatRech);	

		} 
		else
		{

			List<List<String>> listeResultats = new ArrayList<>();
		
			List<List<String>> listeResultatsPlus = new ArrayList<>();
			List<List<String>> listeResultatsMoins = new ArrayList<>();
			List<String> plusOuMoins = new ArrayList<>();
			
			for(String element : listeMotclefs) {
				
				if (element.charAt(0) == '-') {
					element = element.substring(1);
					plusOuMoins.add("-");
		        }
				else if (element.charAt(0) == '+') {
					element = element.substring(1);
					plusOuMoins.add("+");
				}
				else {
					plusOuMoins.add("+");
				}
				
				System.out.println("Element : " + element);
				
				libraryTexteMoteur.rech_MC(element);
				List<String> resultat = Fichier.lire(cheminResultatRech);
				listeResultats.add(resultat);
				//Fichier.supprimer(cheminResultatRech);
			
			}
			
			
//			// D�but pour le Test		
//			System.out.println("Plus ou Moins : " + plusOuMoins.toString());
//			
//			List<String> resultat1 = Fichier.lire("Z:/22213821/MesDocuments/Java/workspace/PFR/src/rechercheTexteChemin.txt");
//			List<String> resultat2 = Fichier.lire("Z:/22213821/MesDocuments/Java/workspace/PFR/src/rechercheTexteChemin2.txt");
//			List<String> resultat3 = Fichier.lire("Z:/22213821/MesDocuments/Java/workspace/PFR/src/rechercheTexteChemin3.txt");
////			List<String> resultat4 = Fichier.lire("Z:/22213821/MesDocuments/Java/workspace/PFR/src/rechercheTexteChemin4.txt");
////			List<String> resultat5 = Fichier.lire("Z:/22213821/MesDocuments/Java/workspace/PFR/src/rechercheTexteChemin5.txt");
////			List<String> resultat6 = Fichier.lire("Z:/22213821/MesDocuments/Java/workspace/PFR/src/rechercheTexteChemin6.txt");
//			listeResultats.add(resultat1);
//			listeResultats.add(resultat2);
//			listeResultats.add(resultat3);
////			listeResultats.add(resultat4);
////			listeResultats.add(resultat5);
////			listeResultats.add(resultat6);
//			
//			System.out.println("Liste Resultats : " + listeResultats.toString());
//			// Fin pour le test
			
		
			
			int i = 0;
			for(String element: plusOuMoins) {
				if(element.equals("-")) {
					listeResultatsMoins.add(listeResultats.get(i));	
				}
				else {
					listeResultatsPlus.add(listeResultats.get(i));
				}
				i++;
			}
			
			// Gestion des espaces dans les chemins 
			List<List<String>> newListeResultatsMoins = new ArrayList<>();
			List<List<String>> newListeResultatsPlus = new ArrayList<>();
			
			for(List<String> list : listeResultatsMoins) {
				List<String> listIntermediaire = new ArrayList<>();
				
				for(String element: list) {
					element = element.split(" ")[0];
					listIntermediaire.add(element);
				}
				newListeResultatsMoins.add(listIntermediaire);
			}
			
			for(List<String> list : listeResultatsPlus) {
				List<String> listIntermediaire = new ArrayList<>();
				for(String element: list) {
					element = element.split(" ")[0];
					listIntermediaire.add(element);
				}
				newListeResultatsPlus.add(listIntermediaire);
			}
			
			listeResultatsPlus = newListeResultatsPlus;
			listeResultatsMoins = newListeResultatsMoins;
			// Fin Gestion des espaces dans les chemins 
			
			
			
//			// Debut pour le test
//			System.out.println("Liste Resultats Plus : " + listeResultatsPlus.toString());
//			System.out.println("Liste Resultats Moins : " + listeResultatsMoins.toString());
//			// Fin pour le test
			
			
			List<String> resultat = listeResultatsPlus.get(0);
			for (int j = 1; j < listeResultatsPlus.size(); j++) {
				
				System.out.println("Resultat"+ j +" : " + resultat);
				List<String> nouveauResultat = new ArrayList<>();
				for (String elementListe1 : resultat) {
		            if (listeResultatsPlus.get(j).contains(elementListe1)) {
		            	nouveauResultat.add(elementListe1);
		               //"L'élément " + elementListe1 + " n'est pas présent dans la liste 2.");
		            }
		        }
				resultat = nouveauResultat;
			}	
			
			for (int j = 0; j < listeResultatsMoins.size(); j++) {
				List<String> nouveauResultat = new ArrayList<>();
				for (String elementListe1 : resultat) {
		            if (!listeResultatsMoins.get(j).contains(elementListe1)) {
		            	nouveauResultat.add(elementListe1);
		               //"L'élément " + elementListe1 + " n'est pas présent dans la liste 2.");
		            }
		        }
				resultat = nouveauResultat;
			}
			
			resultats = resultat;
		}
		
//		String cheminFichierAOuvrir = resultats.get(0).split(" ")[0];
		ajouterHistorique(motClef, resultats);
		return resultats;
	}

	public List<String> rechercherTexteChemin(String chemin) {
		
		modeRecherche = parametre.getMode();
		if(modeRecherche == ModeRecherche.OUVERT)	{
			threadIndexation.start();
		}
		
		
		libraryTexteMoteur.recherche_comparaison_texte(chemin, 35);
		List<String> resultats = Fichier.lire(cheminResultatRech);	
		//Fichier.supprimer(cheminResultatRech);
		
		
		if(modeRecherche == ModeRecherche.OUVERT)	{
			threadIndexation.arret();
		}
		
		ajouterHistorique(chemin, resultats);
		return resultats;
	}
	
	private void ajouterHistorique(String motClef, List<String>  listeResultas) {
		Recherche recherche = new Recherche(motClef, 0);
		Resultat resultat = new Resultat(typeRecherche, listeResultas,recherche);
		bdHistorique.ajouterHistorique(recherche, resultat);
	}

}
