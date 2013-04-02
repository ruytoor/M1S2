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

	private ArrayList<SiteImpl> fils; 
	private int nom; // aide pour les tests
	
	
	public SiteImpl(int nom, ArrayList<SiteImpl> fils) throws RemoteException{
		super();
		this.nom = nom;
		this.fils = fils;
	}
	
	public void propage(byte[] donnees) throws RemoteException{
		for (SiteImpl f : fils){
			System.out.println(this.nom+" : "+donnees);
			f.propage(donnees);
			
		}
	}
}
