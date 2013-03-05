/**
 * gere de la connection du client
 * 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 7 fevrier 2013
 */
package FTP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FtpRequest implements Runnable {

	protected Socket sock;
	protected BufferedReader buff;
	private PrintWriter out;

	protected int dataport;
	protected String dataAdrIP;
	protected int modePort=0; // 0 non initialiser 1 active 2 passif 

	private PrintWriter dataOutString;
	protected Socket dataSock;
	protected ServerSocket ss;

	public String name;
	private boolean exit;
	protected Etat etatClient;

	protected String path;

	/**
	 * constructeur et creation de thread
	 * @param sock une connexion rentrante
	 */
	public FtpRequest(Socket sock){
		this.sock=sock;
		try {
			out= new PrintWriter(sock.getOutputStream(),false);
			buff = new BufferedReader(new InputStreamReader(sock.getInputStream()));

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		dataAdrIP=new String();
		exit=false;
		//path = "\\";
		// mettre // sous windows
		path="/";
		etatClient=Etat.NONLOGER;
		new Thread(this).start();
	}


	/**
	 * point d'amorce du thread
	 */
	public void run(){
		bienvenue();

		//ecoute les commandes du client
		while(!exit){
			try {
				String s=buff.readLine();
				if(s!=null)
					new ParseCommande(this, s);
				else{

					exit=true;
				}
			} catch (IOException e) {
				exit=true;
				e.printStackTrace();
			}
		}
		// une fin propre
		try {
			sock.close();
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

	/**
	 * message d'accueil
	 * 
	 */
	private void bienvenue(){
		response(203, "Bienvenue  !!");
	}

	/**
	 * met fin à la boucle d'attente de commande
	 *  
	 */
	public void quit(){
		exit=true;
	}

	/**
	 * retourne au client un message sur le port commande
	 * @param codeRep : code de retour (3 chiffres)
	 * @param message
	 */
	public void response(int codeRep,String message){
		out.print(codeRep);			
		out.println(" "+message);
		out.flush();
	}


	/**
	 * ferme la connexion sur le port data
	 * 
	 */
	public void closeData(){
		if(dataport==1){
			try {
				dataSock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				dataSock.close();
				/*	ss.close();
				ss=null;*/
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dataSock=null;
	}

	/**
	 * ouvre la connexion sur du port data
	 */
	public void openData(){


		if(modePort==1){
			if(dataSock==null){
				response(150, "Ouverture Data ");
			}
			else{
				if(!dataSock.isClosed()){
					response(125, "DATA deja ouvert");
					return;
				}
				else{
					dataSock=null;
					response(150, "Ouverture Data ");	
				}
			}
			openData(dataAdrIP, dataport);
		}else{ // PASV
			if(dataSock!=null)
				response(150, "Ouverture Data ");
			else
				response(125, "DATA deja ouvert");
			try {
				dataSock=ss.accept();
				dataOutString=new PrintWriter(dataSock.getOutputStream(),false);
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}



	/**
	 * 
	 * ouvre une connexion sur le port data en mode actif
	 * @param host : adresse IP
	 * @param port : le port
	 */
	private void openData(String host,int port){
		try {
			dataSock=new Socket(InetAddress.getByName(host), port);
			dataOutString=new PrintWriter(dataSock.getOutputStream(),false);
		} catch (IOException e) {
			e.printStackTrace();
			dataSock=null;
		}

	}


	/**
	 * envoyer une chaine de caractere sur le port data en ASCII
	 * @param message : le message 
	 */
	public void sendDataString(String message){
		if(dataSock==null){
			this.response(500, "erreur data socket");
			return;
		}
		dataOutString.println(message);
		dataOutString.flush();
	}
	/**
	 * envoie un fichier
	 * @param f : un fichier à envoyer
	 */
	public void sendDatabin(File f){
		try {
			System.out.println(f);
			byte[] tmpb=new byte[256];
			FileInputStream fstream =new FileInputStream(f);
			DataOutputStream dataOut=new DataOutputStream(dataSock.getOutputStream());
			while(fstream.read(tmpb)!=-1){
				dataOut.write(tmpb);
			}
			fstream.close();
			dataOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * recoit un fichier
	 * @param f : le fichier  ! doit être créer !
	 */
	public void receiveDatabin(File f){
		try {
			byte[] tmpb=new byte[256];
			FileOutputStream fstream = new FileOutputStream(f);
			DataInputStream dataIn=new DataInputStream(dataSock.getInputStream());
			while(dataIn.read(tmpb)!=-1){
				fstream.write(tmpb);
			}
			fstream.close();
			dataIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * verification des logins client
	 * 
	 * @param user : le nom de l'utilisateur
	 * @return true si le nom et dans la base de donnees
	 */
	public boolean logOk(String user){
		if (user.equals(Serveur.username))
			return true;
		else
			return false;

	}

	/**
	 * verification du mot de passe du client
	 * 
	 * @param mdp - le mot de passe
	 * @return true si le mot de passe et ok
	 * 
	 */
	public boolean mdpOk(String mdp){
		if (mdp.equals(Serveur.mdp))
			return true;
		else
			return false;

	}

	/**
	 * verification si le client est logé
	 *  @return true s'il est logé
	 */
	public boolean estLoge(){
		if(etatClient!=Etat.LOGER)
			this.response(530, "pas logger");
		return etatClient==Etat.LOGER;
	}

}
