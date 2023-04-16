package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BDHistorique {
	
	private Calendar calendar;
	private Map<String,Map<Recherche,Resultat>> mapHistorique = new HashMap<>();
	
	private List<String> listDateHeure = new ArrayList<>();
	private List<Recherche> listRecherche = new ArrayList<>();
	private List<Resultat> listResultat = new ArrayList<>();
	
	private BDHistorique() {
	}
	
	private static class BDHistoriqueHolder{
		private static final BDHistorique INSTANCE = new BDHistorique();
	}
	
	public static BDHistorique getInstance() {
		return BDHistoriqueHolder.INSTANCE;
	}
	
	public String visualiserHistorique() {
	
		return mapHistorique.toString();
	}
	
	
	public void ajouterHistorique(Recherche recherche,Resultat resultat) {
		calendar = Calendar.getInstance();	
		BDHistorique bdHistorique = BDHistorique.getInstance();
		Map<Recherche,Resultat> historique = new HashMap<>();
		historique.put(recherche,resultat);
		String dateHeure = "Le " + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(calendar.get(Calendar.MONTH)) + "/" + String.valueOf(calendar.get(Calendar.YEAR)) + " à " + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) +":"+String.valueOf(calendar.get(Calendar.MINUTE)) + ":" + String.valueOf(calendar.get(Calendar.SECOND));
		this.mapHistorique.put(dateHeure,historique);
		bdHistorique.recupListSepare();
	}
	
	public static void main(String[] args) {
		BDHistorique bdHistorique = BDHistorique.getInstance();
		Recherche recherche = new Recherche("musique",0);
		List<String> listeResultat = new ArrayList<>();
		listeResultat.add("danse-oui");
		listeResultat.add("danse-non");
		Resultat resultat = new Resultat(TypeRecherche.TEXTE,listeResultat,recherche);
		
		bdHistorique.ajouterHistorique(recherche,resultat);
		//bdHistorique.ajouterHistorique(recherche,resultat);
		//bdHistorique.ajouterHistorique(recherche,resultat);
		//bdHistorique.ajouterHistorique(recherche,resultat);
		
		bdHistorique.recupListSepare();
		//System.out.println(bdHistorique.visualiserHistorique());
	}
	
	public void recupListSepare() {
		listDateHeure = new ArrayList<>();
		listRecherche = new ArrayList<>();
		listResultat = new ArrayList<>();
		
		for (Map.Entry<String, Map<Recherche, Resultat>> entry : mapHistorique.entrySet()) {
		    String stringKey = entry.getKey();
		    Map<Recherche, Resultat> innerMap = entry.getValue();

		    for (Map.Entry<Recherche, Resultat> innerEntry : innerMap.entrySet()) {
		        Recherche rechercheKey = innerEntry.getKey();
		        Resultat resultatValue = innerEntry.getValue();

		        this.listDateHeure.add(stringKey);
		        this.listRecherche.add(rechercheKey);
		        this.listResultat.add(resultatValue);
		    }
		}
	}
	
	public List<String> getListDateHeure(){
		return this.listDateHeure;
	}
	
	public List<Recherche> getListRecherche(){
		return this.listRecherche;
	}
	
	public List<Resultat> getListResultat(){
		return this.listResultat;
	}
}
