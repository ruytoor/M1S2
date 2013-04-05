package serveur;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import site.SiteImpl;

public class CreateNodeInRMI {

	/**
	 * @param args [0] =son nom    [1 et +] = ses fils
	 */
	public static void main(String[] args) {
		SiteImpl site1;
		try {
			ArrayList<String> fils =new ArrayList<String>();
			if(args.length>1){
				for(int i=2;i<args.length;++i)
					fils.add(args[i]);
			}
			site1 = new SiteImpl(args[0],fils);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind(args[0], site1);
		} catch (RemoteException e) {
			System.err.println("Err 1");
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			System.err.println("Err 2");
			e.printStackTrace();
		}
	}

}
