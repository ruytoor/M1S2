package site;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * 
 * classe qui implemente l'interface RMI SiteItf
 * @author Aurore Allart et Benjamin Ruytoor
 *
 */
public class SiteImpl extends UnicastRemoteObject implements SiteItf{

	private ArrayList<SiteItf> fils; 
	private int nom; // aide pour les tests
	public SiteItf pere;
	
	
	public SiteImpl(int nom, ArrayList<SiteItf> fils) throws RemoteException{
		super();
		this.nom = nom;
		this.fils = fils;
	}
	
	//propagation synchrone faux
	public void propage(byte[] donnees) throws RemoteException{
		for (SiteItf f : fils){
			System.out.println(this.nom+" : "+donnees);
			f.propage(donnees);
			
		}
	}
}
