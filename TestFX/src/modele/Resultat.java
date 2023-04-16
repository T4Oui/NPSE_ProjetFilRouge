package modele;

import java.util.List;

import controleur.ControlRechercherImage;

public class Resultat {
	private List<String> listeResultat;
	private TypeRecherche typeResultat;
	private TypeRechercheCouleur typeResultatCouleur;
	private Recherche recherche;
	private String nom;
	
	public Resultat(TypeRecherche typeResultat, List<String> listeResultat, Recherche recherche)	{
		this.typeResultat = typeResultat;
		this.listeResultat=listeResultat;
		this.recherche=recherche;
	}
	
	public Resultat()
	{
		
	}
	
	public String getNom() {
		switch (this.typeResultatCouleur)
		{
		case NOIRETBLANC :
			nom=this.listeResultat.get(0);
			nom = nom.toString().replace(".txt", ".bmp");
			return(nom);
		case COULEUR :
			nom=this.listeResultat.get(0);
			nom = nom.toString().replace(".txt", ".jpg");
			return nom;
		default:
			return null;
			
		}
	}
	
	public Resultat(TypeRechercheCouleur type, List<String> listeResultat, Recherche recherche)	{
		this.typeResultatCouleur = type;
		this.listeResultat=listeResultat;
		this.recherche=recherche;
	}
	
	public TypeRecherche getTypeResultat() {
		return this.typeResultat;
	}
	
	public TypeRechercheCouleur getTypeResultatCouleur() {
		return this. typeResultatCouleur;
	}
	
	public List<String> getListeResultat() {
		return this.listeResultat;
	}
	
	public Recherche getRecherche()
	{
		return this.recherche;
	}
	
	public String toString ()
	{
		return " ==> Resultat : "+this.listeResultat.toString().substring(1, this.listeResultat.toString().length()-1);
	}
}
