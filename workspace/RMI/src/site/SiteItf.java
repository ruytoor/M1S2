package site;

/**
 * interface RMI
 * @author Aurore Allart et Benjamin Ruytoor
 * @version 9 avril 2013
 */
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SiteItf extends Remote{
	
	/**
	 * propage les donnees aux fils
	 * @param donnees tableau de donnees
	 * @throws RemoteException
	 */
	public void propage(byte[] donnees) throws RemoteException;

}
