/**
 * classe regroupant le traitement des commandes
 * 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 7 fevrier 2013
 * 
 */


package FTP;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Date;


public class ParseCommande {

	/**
	 * enumeration regroupant toutes les commandes<br>
	 * USER : nom de l'utilisation<br>
	 * PORT : port de la connexion data<br>
	 * PASV : mode passif<br>
	 * PWD : permet de connaitre la valeur du répertoire<br>
	 * PASS : mot de passe<br>
	 * LIST : liste les fichiers du dossier courant<br>
	 * RETR : récupere un fichier distant<br>
	 * STOR : stocke un fichier courant<br>
	 * QUIT : termine la session FTP en cours<br>
	 * CWD : change de repertoire<br>
	 * CDUP : cd ..<br>
	 * ERR : fictif : commande non repertoriee<br>
	 * 
	 */
	public enum com{
		USER,PORT,PASV,PWD,PASS,LIST,RETR,STOR,QUIT,CWD,CDUP,ERR;
	}

	private String commande;
	private String [] args;
	private FtpRequest ftpR;



	/**
	 * analyse la chaine de caractere envoye par le client
	 * 
	 * @param sock - la classe qui s'occupe des connections
	 * @param commande - la commande ftp
	 */
	public ParseCommande(FtpRequest sock, String commande){
		this.ftpR=sock;

		try{
			args=commande.split(" ");
			this.commande = args[0];
		}catch (Exception e) {
			System.err.println("erreur traitement commande -"+ftpR.sock.isConnected()+"-");
			if(ftpR.sock.isClosed())
				ftpR.quit();
		}
		this.parse();
	}

	/**
	 * traitement de la commande
	 */
	public void parse() {
		com c;
		try{
			c=com.valueOf(commande);
		}catch (Exception e) {
			c=com.ERR;
		}
		switch (c) {
		case USER:
			processUSER();
			break;
		case PWD:
			processPWD();
			break;
		case PASS:
			processPASS();
			break;
		case RETR:
			processRETR();
			break;
		case STOR:
			processSTOR();
			break;
		case QUIT:
			processQUIT();
			break;
		case LIST:
			processLIST();
			break;
		case CWD:
			processCWD();
			break;
		case CDUP:
			processCDUP();
			break;
		case PASV:
			processPASV();
			break;
		case PORT:
			processPORT();
			break;
		default:
			ftpR.response(202, "Command not implemented");
			break;
		}
	}

	/**
	 * mode passif de la connexion data
	 */
	private void processPASV() {
		ftpR.modePort=2;

		try {
			ftpR.dataAdrIP=InetAddress.getLocalHost().getHostAddress();
			//	System.out.println(InetAddress.getLocalHost().getHostAddress());
			ftpR.ss=new ServerSocket(0);
			ftpR.dataport=ftpR.ss.getLocalPort();
			int port1=ftpR.dataport/256;
			int port2=ftpR.dataport%256;
			String tmp[]=ftpR.dataAdrIP.split("\\.");
			ftpR.response(227, "Entering Passive Mode ("+tmp[0]+","+tmp[1]+","+tmp[2]+","+tmp[3]+","+port1+","+port2+").");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * mode actif de la connexion data
	 */
	private void processPORT() {
		String []host=args[1].split(","); // h1,h2,h3,h4,p1,p2
		ftpR.dataAdrIP=host[0]+"."+host[1]+"."+host[2]+"."+host[3];
		ftpR.dataport=(Integer.parseInt(host[4])*256)+(Integer.parseInt(host[5]));
		ftpR.modePort=1;
		ftpR.response(200, "PORT ok mais pas ouvert");
	}

	/**
	 * traitement la commande PWD
	 */
	private void processPWD(){
		if(ftpR.estLoge()){
			ftpR.response(213, ftpR.path);
		}
	}

	/**
	 * traitement la commande USER
	 */
	private void processUSER(){
		ftpR.name = args[1];
		if(ftpR.logOk(ftpR.name))
			ftpR.response(331, "log -> "+ftpR.name+" need MDP");
		else
			ftpR.response(530, "username invalide");
	}
	/**
	 * traitement la commande PASS
	 */
	private void processPASS(){
		String mdp = args[1];
		if(ftpR.mdpOk(mdp)){
			ftpR.response(230, "mot de passe correct");
			ftpR.etatClient=Etat.LOGER;
		}
		else
			ftpR.response(430, "mod de passe invalide");
	}
	/**
	 * liste les répertoires et les fichiers
	 * et envoie OK au client
	 */
	private void processLIST(){
		if(ftpR.estLoge()){
			ftpR.openData();
			File tmp=new File(Serveur.dossierPath+ftpR.path);
			for(File f : tmp.listFiles()){
				String s;
				Date d=new Date(f.lastModified());
				// A revoir 
				if(f.isDirectory())
					s="drwxrwxrwx 2 personne 0 "+(d.getMonth()+1)+" "+d.getDate()+" "+(d.getYear()+1900)+" "+f.getName()+"/";
				else
					s="-rwxrwxrwx 1 personne "+f.length()+" "+(d.getMonth()+1)+" "+d.getDate()+" "+(d.getYear()+1900)+" "+f.getName();
				//	System.out.println(s);
				ftpR.sendDataString(s);
			}
			ftpR.closeData();
			//ftpR.response(226, "List close data");
			//
			ftpR.response(250, "List end");
		}
	}

	/**
	 * traitement la commande RETR
	 */
	private void processRETR(){
		if(ftpR.estLoge()){
			if(new File(Serveur.dossierPath+ftpR.path+args[1]).canRead()){
				ftpR.openData();
				ftpR.sendDatabin(new File(Serveur.dossierPath+ftpR.path+args[1]));
				ftpR.closeData();
				ftpR.response(250, "fichier envoye");
			}
			else
				ftpR.response(501, "name file invalid");
		}
	}

	/**
	 * traitement la commande STOR
	 */
	private void processSTOR(){
		if(ftpR.estLoge()){
			File f=new File(Serveur.dossierPath+ftpR.path+args[1]);
			try {
				f.createNewFile();
				ftpR.openData();
				ftpR.receiveDatabin(f);
				ftpR.closeData();
				ftpR.response(250, "fichier recu");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ftpR.openData();


		}
	}

	/**
	 * traitement la commande QUIT
	 */
	private void processQUIT(){
		ftpR.response(221, "Au revoir");
		ftpR.quit();
	}

	/**
	 * traitement la commande CWD
	 */
	private void processCWD(){
		if(ftpR.estLoge()){
			if(args[1].substring(0, 1).compareTo("/")==0){
				if(args[1].compareTo("/")==0)
					ftpR.path="/";
				ftpR.path=args[1];
			}
			else
				ftpR.path+=args[1];
			if(ftpR.path.lastIndexOf("/")!=ftpR.path.length()-1)
				ftpR.path+="/";
			ftpR.response(250, "repertoire :"+ftpR.path);
		}
	}
	/**
	 * traitement la commande CDUP
	 */
	private void processCDUP(){
		if(ftpR.estLoge()){
			ftpR.path=ftpR.path.substring(0, ftpR.path.length()-1);
			ftpR.path=ftpR.path.substring(0, ftpR.path.lastIndexOf("/")+1);

			ftpR.response(200, "CDUP ok");
		}
	}
}
