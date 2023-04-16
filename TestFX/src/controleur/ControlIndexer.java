package controleur;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import libraryJNA.LibraryImageMoteur;
import libraryJNA.LibrarySonMoteur;
import libraryJNA.LibraryTexteMoteur;
import modele.IdentiteFichier;
import modele.ModeRecherche;
import modele.Parametre;
import modele.ThreadIndexation;

public class ControlIndexer {
	

	
	public void indexationTout(String chemin) {
		indexationTexte();
		indexationImage();
		indexationSon();
		
	}

	public void indexationTexte() {
		// appel indexationTexte(chemin) de C
		LibraryTexteMoteur libraryTexteMoteur = LibraryTexteMoteur.INSTANCE;
		libraryTexteMoteur.indexation_texte();
	}

	public void indexerUnTexte(String chemin) {
		// appel indexationTexte(chemin) de C
		try {
            // Chemin de l'exécutable à lancer
            String executablePath = "../../pfr_code/texte/src/indexation_texte.o " + chemin;
            
            // Création du processus
            Process process = Runtime.getRuntime().exec(executablePath);
            
            // Lecture de la sortie standard
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            // Lecture de la sortie d'erreur
            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = reader.readLine()) != null) {
                System.err.println(line);
            }
            
            // Attendre la fin de l'exécution
            int exitCode = process.waitFor();
            System.out.println("L'exécution a terminé avec le code de sortie " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void indexationImage() {
		// appel indexationImage(chemin) de C
		LibraryImageMoteur libraryImageMoteur = LibraryImageMoteur.INSTANCE;
		libraryImageMoteur.indexation_image();
		IdentiteFichier ident = IdentiteFichier.getInstance();
		ident.ajouterIdentifiant();
	}
	
	public void indexationUneImage(String nom) {
		// appel indexationImage(chemin) de C
		try {
            // Chemin de l'exécutable à lancer
            String executablePath = "pfr_code/image/src/indexation_image.o " + nom;
            
            // Création du processus
            Process process = Runtime.getRuntime().exec(executablePath);
            
            // Lecture de la sortie standard
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            // Lecture de la sortie d'erreur
            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = reader.readLine()) != null) {
                System.err.println(line);
            }
            
            // Attendre la fin de l'exécution
            int exitCode = process.waitFor();
            System.out.println("L'exécution a terminé avec le code de sortie " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void indexationSon() {
		// appel indexationSon(chemin) de C
		LibrarySonMoteur librarySonMoteur = LibrarySonMoteur.INSTANCE;
		librarySonMoteur.indexation_son();
	}
	
	public void modifier  ( int nou , int nbrLig,String fileName){
	     
        String line ;
        
        try {
            // Ouvre le fichier de configuration en lecture
            File configFile = new File(fileName);
            Scanner scanner = new Scanner(configFile);
            StringBuilder buffer = new StringBuilder();
            int i = 1 ; 
            // Lit le fichier ligne par ligne
            while (scanner.hasNextLine() ) {
                line = scanner.nextLine();
                if (i == nbrLig) {
                	String[] restLigne = line.split("\\s+") ;
                	int debut = 1;
                	restLigne = Arrays.copyOfRange(restLigne, debut, restLigne.length);
                	String  ligne = String.join( " " , restLigne) ; 
	                    // Modifie la ligne contenant le nombre
                    line = nou  + " " +  ligne  ;
                }
                buffer.append(line).append(System.lineSeparator());
                i++ ;  
            }
            scanner.close();
            
            // Écrit les modifications dans le fichier
            PrintWriter writer = new PrintWriter(configFile);
            writer.write(buffer.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}