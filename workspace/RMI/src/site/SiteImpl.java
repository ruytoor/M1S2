package site;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * 
 * classe qui implemente l'interface RMI SiteItf
 * @author Aurore Allart et Benjamin Ruytoor
 * @version 9 avril 2013
 */
public class SiteImpl extends UnicastRemoteObject implements SiteItf{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7632588616130667266L;
	private ArrayList<String> fils; 
	private String nom; // aide pour les tests
	private String lastMessage;


	public SiteImpl(String nom, ArrayList<String> fils) throws RemoteException{
		super();
		this.nom = nom;
		this.fils = fils;
		this.lastMessage="";
	}

	public void propage(byte[] donnees) throws RemoteException{
		String mes=new String(donnees);
		if(mes.compareTo(lastMessage)==0)// identique au dernier message
			return;
		else
			lastMessage=mes;
		System.out.println(this.nom+" : "+mes);
		Registry registry = LocateRegistry.getRegistry("localhost");
		for (String f : fils){
			try {
				SiteItf site= (SiteItf) registry.lookup(f);
				//site.propage(donnees);
			} catch (NotBoundException e) {
				System.err.println("Erreur le systeme a planté");
				System.exit(5);
			}

		} 
	}

}
