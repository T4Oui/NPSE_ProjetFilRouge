package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import controleur.ControlIndexer;
import controleur.ControlRechercherImage;
import controleur.ControlRechercherTexte;

public class ThreadIndexation extends Thread {
	private boolean condition = true;
	private int nbFichier=0;
	private IdentiteFichier liste =IdentiteFichier.getInstance();
	private ModeRecherche modeRecherche ;
	private Parametre parametre = Parametre.getIntance();
	private List<String> oldFileList = new ArrayList<>();
	
	public ThreadIndexation() {}
	
	public void arret() {
		condition = false;
		System.out.println("Thread fermé");
	}
	
	public void run() {
		System.out.println("Thread ouvert");
		String directory = "../../DATA_PFR";
		ControlIndexer controlIndexer = new ControlIndexer();
		File oldDirectory = new File(directory);
		this.oldFileList = Arrays.asList(oldDirectory.list());
		
		do {
			try {
				Thread.sleep(30000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			if(parametre.getMode()==ModeRecherche.OUVERT) {
				File newDirectory = new File(directory);
				List<String> newFileList = Arrays.asList(newDirectory.list());
				List<String> newFiles = new ArrayList<>();
		        for (String fileName : newFileList) {
		            if (!oldFileList.contains(fileName)) {
		                newFiles.add(fileName);
		            }
		        }
		        
		        System.out.println("NewFiles : " + newFiles.toString() + "taille old :" + oldFileList.size() + "/taille new :"+newFileList.size());
				if (oldFileList.size() < newFileList.size()){
					this.oldFileList=newFileList;
					//recup la liste des nouveaux fichiers
					System.out.println("Taille lste diff");
					
					for(String newFile : newFiles) {
						
						//détermination de la présence du fichier txt associé aux jpg ou bmp 
						boolean presenceTxt = false;
						
						String extension ="";
						int dotIndex = newFile.lastIndexOf('.');
						if(dotIndex>0) {
							extension=newFile.substring(dotIndex+1);
						}
						
						if(extension.equals("bmp") || extension.equals("jpg")) {
							String fileNameWithoutExtension="";
							int extensionIndex = newFile.lastIndexOf(".");
							if (extensionIndex != -1) {
							    fileNameWithoutExtension = newFile.substring(0, extensionIndex);
							}
							for(String newFile2 : newFiles) {
								String fileNameWithoutExtension2="";
								int extensionIndex2 = newFile.lastIndexOf(".");
								if (extensionIndex2 != -1) {
								    fileNameWithoutExtension2 = newFile2.substring(0, extensionIndex2);
								}
								
								if(fileNameWithoutExtension2.equals(fileNameWithoutExtension)) {
									String extension1 ="";
									int dotIndex1 = newFile2.lastIndexOf('.');
									if(dotIndex1>0) {
										extension1=newFile2.substring(dotIndex+1);
									}
									if(extension1.equals("txt")) {
										presenceTxt = true;
									}
								}
							}
						}
						
						
						System.out.println("Extension : " + extension.toString());
						
						if ((extension.equals("bmp") || extension.equals("jpg")) && presenceTxt == true){
							this.oldFileList=newFileList;
							
							//On enleve l'extension 
							String fileNameWithoutExtension="";
							int extensionIndex = newFile.lastIndexOf(".");
							if (extensionIndex != -1) {
							    fileNameWithoutExtension = newFile.substring(0, extensionIndex);
							}
							//File dest_image = new File ("../../pfr/image/fich_images");
							Path dest_image = Paths.get("../../pfr/image/fich_images");
							Path dest_image_txt = Paths.get("../../pfr/image/fich_images/txt");
							Path source_img = Paths.get("../../DATA_PFR/"+newFile);
							Path source_txt = Paths.get("../../DATA_PFR/"+fileNameWithoutExtension+".txt");
							
							try {
								//déplace d'un repertoire à un autre
								Files.copy(source_img, dest_image.resolve(source_img.getFileName()));
								Files.copy(source_txt, dest_image_txt.resolve(source_txt.getFileName()));
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							//boolean ok = source.renameTo(new File(dest_image, source.getName()));
				
							//if(!ok) {
								//gestion erreurs
							//}		
								//if(extension.equals("txt")) {
									controlIndexer.indexationUneImage(fileNameWithoutExtension+".txt");
									System.out.println("Après idx");
									//compter le nombre de ligne d'un fichier
									String fileName="";
									String fileName2="";
									int ident=0;
									if(extension=="bmp") {
										fileName = "../../pfr/image/descripteurs_images/base_descripteur_image_NB.txt";
										fileName2 ="../../pfr/image/descripteurs_images/base_image_NB.txt";
										ident=4000;
									}
									else if(extension=="jpg"){
										fileName = "../../pfr/image/descripteurs_images/base_descripteur_image_couleur.txt";
										fileName2 = "../../pfr/image/descripteurs_images/base_image_couleur.txt";
										ident=3000;
										
									}
								    
									if(fileName != "" && fileName2 != "") {
										int countFile = 0;
									        
									    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
									    	while (br.readLine() != null) {
									    		countFile++;
									        }
									    } catch (IOException e) {
									    	e.printStackTrace();
									    }
									    System.out.println("Cpt fichier 1 :"+countFile);
									    int countFile2 = 0;
								        
									    try (BufferedReader br = new BufferedReader(new FileReader(fileName2))) {
									    	while (br.readLine() != null) {
									    		countFile2++;
									        }
									    } catch (IOException e) {
									    	e.printStackTrace();
									    }
									    System.out.println("Cpt fichier 2 :"+countFile2);
									    controlIndexer.modifier(ident + countFile+1, countFile, fileName);
									    controlIndexer.modifier(ident + countFile2+1, countFile2, fileName2);
									}
								//}
									
						}else if (extension.equals("xml")){
							this.oldFileList=newFileList;
							System.out.println("Dans xml");
							//File dest_texte = new File ("../../pfr/texte/fich_textes");
							Path dest_texte = Paths.get("../../pfr/texte/fich_textes");
							Path source = Paths.get("../../DATA_PFR/"+newFile);
							//déplace d'un repertoire à un autre
							//boolean ok = source.renameTo(new File(dest_texte, source.getName()));
							try {
								Files.copy(source, dest_texte.resolve(source.getFileName()));
							} catch (IOException e) {
								e.printStackTrace();
							}
							//if(!ok) {
								//gestion erreurs
							//}
							System.out.println("New File : " + newFile.toString());
							
							String fileNameWithoutExtension="";
							int extensionIndex = newFile.lastIndexOf(".");
							if (extensionIndex != -1) {
							    fileNameWithoutExtension = newFile.substring(0, extensionIndex);
							    System.out.println(fileNameWithoutExtension);
							}
							controlIndexer.indexerUnTexte(fileNameWithoutExtension);
						}
					}
				}		
			}
		}while (condition);
	}
	
}
			