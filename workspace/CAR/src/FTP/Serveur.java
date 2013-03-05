/**
 * classe Serveur 
 * 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 7 fevrier 2013
 * 
 */


package FTP;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class Serveur {

	public static final int PORT=1025;
	public static final int DATAPORT=1026;
	
	private static ServerSocket socServer;
	protected static File dossier;
	protected static String dossierPath;
	
	protected static String username = "Benj";
	protected static String mdp = "moi";
	
	/**
	 * lance le serveur
	 * @param args : chemin absolu du dossier
	 */
	public static void main(String[] args) {
		// args -> dossier pour ddl
		
		//initialisation pour lecture du dossier
		dossier=new File(args[0]);
		if(!dossier.canRead()){
			System.err.println("erreur de lecture du dossier");
		//exit
		}
		dossierPath=dossier.getPath();
		dossierPath=dossierPath.substring(0, dossierPath.length());
		
		//initialisation côté communication
		try {
			socServer=new ServerSocket(Serveur.PORT);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("impossible de créer le socket serveur");
		}

		//attente de client
		while(true){
			try {
				new FtpRequest(socServer.accept());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
/*
 * Critères
 * 1> fonctionalités : vérifié avec filezilla
 * 2> code : vérifié avec une lecture +question/réponses
 * 3> code : vérifié avec présentation par les étudiants (+ diagramme optionel)
 * 4> tests : vérifié par une présentation des tests units(ou autres)
 */
