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
	public SiteItf pere;


	public SiteImpl(String nom, ArrayList<String> fils) throws RemoteException{
		super();
		this.nom = nom;
		this.fils = fils;
	}

	//propagation synchrone faux
	public void propage(byte[] donnees) throws RemoteException{
		System.out.println(this.nom+" : "+new String(donnees));
		Registry registry = LocateRegistry.getRegistry("localhost");
		for (String f : fils){
			try {
				SiteImpl site= (SiteImpl) registry.lookup(f);
				site.propage(donnees);
			} catch (NotBoundException e) {
				System.err.println("Erreur le systeme a planté");
				System.exit(666);
			}

		} 
	}

}
