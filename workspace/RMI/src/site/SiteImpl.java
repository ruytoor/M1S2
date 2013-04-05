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
 *
 */
public class SiteImpl /*extends UnicastRemoteObject*/ implements SiteItf{

	private ArrayList<Integer> fils; 
	private int nom; // aide pour les tests
	public SiteItf pere;


	public SiteImpl(int nom, ArrayList<Integer> fils) throws RemoteException{
		super();
		this.nom = nom;
		this.fils = fils;
	}

	//propagation synchrone faux
	public void propage(byte[] donnees) throws RemoteException{
		for (Integer f : fils){
			System.out.println(this.nom+" : "+donnees);
			try {
				String name = "1";
				Registry registry = LocateRegistry.getRegistry("localhost");
				SiteImpl site;
				site = (SiteImpl) registry.lookup(name);
				site.propage("Salut".getBytes());
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 
	}

}
