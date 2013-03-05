package Client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.InetAddress;

import org.apache.commons.net.ftp.*;
import org.junit.Test;

import FTP.*;

import static org.junit.Assert.*;

public class ClientTest {

	private static String username = "Benj";
	private static String mdp = "moi";

	/**
	 * creation d'un client FTP
	 * @return le client
	 */
	private FTPClient connectClient(){
		FTPClient client = new FTPClient();
		try{
			client.connect(InetAddress.getLocalHost(), Serveur.PORT);

		}catch (IOException e){
			System.err.println("Could not connect to server.");
			e.printStackTrace();
			System.exit(1);
			client = null;
		}
		return client;
	}

	/**
	 * deconnection du client
	 * @param client
	 * @throws IOException
	 */
	private void disconnectClient(FTPClient client) throws IOException{
		try{
			client.disconnect();
		}catch (IOException e){
			System.out.println("Could not disconnect to server");
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * test sur le parametre de la commande USER non valide
	 * @throws IOException 
	 */
	@Test
	public void commandeUserParamNonValide() throws IOException{
		FTPClient client = this.connectClient();
		boolean test = client.login("toto", mdp);
		System.out.print("commandeUserParamNonValide : "+test);
		System.out.println(" : Nom d'utilisateur non valide");
		assertFalse("Nom d'utilisateur invalide", test);
		this.disconnectClient(client);
	}

	/**
	 * test sur le parametre de la commande USER valide
	 * @throws IOException 
	 */
	@Test
	public void commandeUserParamValide() throws IOException{
		FTPClient client = this.connectClient();
		boolean test = client.login(username, mdp);
		System.out.print("commandeUserParamValide : "+test);
		System.out.println(" : Nom d'utilisateur valide");
		assertTrue("Nom d'utilisateur valide", test);
		this.disconnectClient(client);
	}

	/**
	 * test sur le parametre de la commande PASS non valide
	 * @throws IOException 
	 */
	@Test
	public void commandePassParamNonValide() throws IOException{
		FTPClient client = this.connectClient();
		boolean test = client.login(username, "fffff");
		System.out.print("commandePassParamNonValide : "+test);
		System.out.println(" : Mot de passe non valide");
		assertFalse("Nom de passeword invalide", test);
		this.disconnectClient(client);
	}	

	/**
	 * test sur le parametre de la commande PASS valide
	 * @throws IOException 
	 */
	@Test
	public void commandePassParamValide() throws IOException{
		FTPClient client = this.connectClient();
		boolean test = client.login(username, mdp);
		System.out.print("commandePassParamValide : "+test);
		System.out.println(" : Mot de passe et username valide");
		assertTrue("username et passeword valide", test);
		this.disconnectClient(client);
	}	

	/**
	 * quitter serveur 
	 * @throws IOException 
	 */
	@Test
	public void commandeQuit() throws IOException{
		FTPClient client = this.connectClient();
		int test = client.quit();
		System.out.print("commandeQuit : "+test);
		System.out.println(" : quit ok");
		assertEquals("quitter correctement", 221, test);
		this.disconnectClient(client);
	}

	/**
	 * test sur le parametre de la commande RETR non valide
	 * @throws IOException 
	 */
	@Test
	public void commandeRetrParamNonValide() throws IOException{
		FTPClient client = this.connectClient();
		File f = new File("test.txt");

		try{
			client.login(username, mdp);
			byte tmp[]=new byte[1024];		
			f.createNewFile();
			FileOutputStream outfile=new FileOutputStream(f);
			InputStream indata = client.retrieveFileStream("dlme1.txt");
			if(indata==null){

				System.out.print("commandeRetrParamNonValide : "+client.getReplyCode());
				System.out.println(" : transfert non reussi");
				assertEquals("parametre de la commande RETR invalide",501, client.getReplyCode());
			}else{
				while(indata.read(tmp)!=0){
					outfile.write(tmp);
				}
			}		
		}catch (Exception e){

		}
		this.disconnectClient(client);
	}

	/**
	 * test sur le parametre de la commande RETR valide
	 * @throws IOException 
	 */
	@Test
	public void commandeRetrParamValide() throws IOException{
		FTPClient client = this.connectClient();
		client.login(username, mdp);
		File f = new File("test.txt");
		byte tmp[]=new byte[1024];		
		f.createNewFile();
		FileOutputStream outfile=new FileOutputStream(f);
		InputStream indata = client.retrieveFileStream("dlme.txt");
		System.out.println(indata);
		while(indata.read(tmp)!=0){
			outfile.write(tmp);
		}
		System.out.print("commandeRetrParamValide : "+f.getTotalSpace());
		System.out.println(" : transfert reussi");
		assertEquals("parametre de la commande RETR valide", 530, f.getTotalSpace());
		this.disconnectClient(client);
	}

	/**
	 * test sur le parametre de la commande STOR non valide
	 * @throws IOException 
	 */
	@Test
	public void commandeStorParamNonValide() throws IOException{
		FTPClient client = this.connectClient();
		int test = client.stor(null);
		assertEquals("parametre Stor non valide", 530, test);
		this.disconnectClient(client);
	}	

	/**
	 * test sur le parametre de la commande STOR valide
	 * @throws IOException 
	 */
	@Test
	public void commandeStorParamValide() throws IOException{
		FTPClient client = this.connectClient();
		int test = client.stor(null);
		assertEquals("parametre stor valide", 530, test);
		this.disconnectClient(client);
	}

	/**
	 * test sur le parametre de la commande CWD non valide
	 * @throws IOException 
	 */
	@Test
	public void commandeCwdParamNonValide() throws IOException{
		FTPClient client = this.connectClient();
		int test = client.cwd("M1S2");
		assertEquals("parametre cwd non valide", 530, test);
		this.disconnectClient(client);
	}	

	/**
	 * test sur le parametre de la commande CWD valide
	 * @throws IOException 
	 */
	@Test
	public void commandeCwdParamValide() throws IOException{
		FTPClient client = this.connectClient();
		int test = client.cwd(null);
		assertEquals("parametre cwd valide", 530, test);
		this.disconnectClient(client);
	}

	/**
	 * test sur la commande list
	 * @throws IOException
	 */
	@Test
	public void commandeList() throws IOException{
		FTPClient client = this.connectClient();
		int test = client.list();
		System.out.print("commandeList : "+test);
		System.out.println(" liste ok");
		assertEquals("ouverture du port data",530 , test);
		//client.pasv();
		client.port(InetAddress.getLocalHost(), Serveur.DATAPORT);
		//client.enterLocalPassiveMode();
		System.out.println(client.listFiles().length);
		for(FTPFile f :client.listFiles()){
			System.out.println(f.getRawListing());
			System.out.println(f.toFormattedString());

		}
		this.disconnectClient(client);
	}
}
