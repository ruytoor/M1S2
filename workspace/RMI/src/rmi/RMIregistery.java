package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * instancie l'annuaire RMI
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 avril 2013
 */
public class RMIregistery {

	public static void main(String args[]) throws RemoteException, InterruptedException  {

		Registry l = LocateRegistry.createRegistry(1099);
		while(true){
			Thread.sleep(100);
		//	for(String s :l.list())
		//		System.out.println("->"+s);
		}

	}
}
