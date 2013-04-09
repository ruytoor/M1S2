package site;


import java.io.Serializable;
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
 *
 */
public class SiteImpl extends UnicastRemoteObject implements SiteItf{

	private ArrayList<String> fils; 
	private String nom; // aide pour les tests
	private String lastMessage;


	public SiteImpl(String nom, ArrayList<String> fils) throws RemoteException{
		super();
		this.nom = nom;
		this.fils = fils;
		this.lastMessage="";
	}

	//propagation synchrone faux
	public void propage(byte[] donnees) throws RemoteException{
		String mes=new String(donnees);
		if(mes.compareTo(lastMessage)==0)// idnetique au dernier message
			return;
		else
			lastMessage=mes;
		System.out.println(this.nom+" : "+mes);
		Registry registry = LocateRegistry.getRegistry("localhost");
		for (String f : fils){
			try {
				SiteItf site= (SiteItf) registry.lookup(f);
				site.propage(donnees);
			} catch (NotBoundException e) {
				System.err.println("Erreur le systeme a planté");
				System.exit(666);
			}

		} 
	}

}
