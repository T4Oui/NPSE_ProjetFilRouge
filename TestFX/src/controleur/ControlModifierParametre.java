package controleur;

import modele.Parametre;

public class ControlModifierParametre {
         Parametre parametre  ; 
         ControlIndexer controlIndexer ; 
         
        // public ControlModifierParametre ( ) {
        	// parametre = Parametre.getIntance ();
        	 
         //}
         
         public ControlModifierParametre (ControlIndexer controlIndexer ) {
        	 parametre = Parametre.getIntance ();
        	 this.controlIndexer = controlIndexer ; 
         }
         
         
	public String visualiserParametre() {
		String s = parametre.toString();
		return s ; 
	}
	
	public void modifierNbrOcc(int nbrAcc) {
		parametre.setNbrOcc ( nbrAcc);
		//controlIndexer.indexationTexte(); 
	}
	public void modifierNbrBit(int nbrBit) {
		parametre.setNbrBit(nbrBit);
		//controlIndexer.indexationImage();
	}
	public void modifierNbrEch ( int nbrEch) {
		parametre.setNbrEch ( nbrEch) ; 
		//controlIndexer.indexationSon(); 
	}
	public void modifierIntervalle (int intervalle) {
		parametre.setIntervalle ( intervalle) ; 
		//controlIndexer.indexationSon (); 
	}
	public void modifierModeRecherche (int mode) {
		parametre.setModeRecherche (mode); 
	}
	public int getNbrBit () {
		return parametre.getNbrBit() ; 
	}
	public int getNbrOcc() {
		return parametre.getNbOcc();
	}
	public  int getNbrEch() {
		return parametre.getNbrEch();
	}
	public int getIntervalle () {
		return parametre.getIntervalle();
	}
	public int getMode () {
		return parametre.getModeInt();
	}
}