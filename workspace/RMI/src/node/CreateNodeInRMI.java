package node;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import site.SiteImpl;
import site.SiteItf;

/**
 * classe qui crée les noeuds RMI du graphe ou de l'arbre
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 avril 2013
 */
public class CreateNodeInRMI {

	/**
	 * @param args [0] =son nom    [1 et +] = ses fils
	 */
	public static void main(String[] args) {
		try {	
			ArrayList<String> fils =new ArrayList<String>();
			if(args.length>1){
				for(int i=1;i<args.length;++i)
					fils.add(args[i]);
			}
			SiteItf site = new SiteImpl(args[0],fils);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind(args[0], site);
		} catch (RemoteException e) {
			System.exit(2);
		} catch (AlreadyBoundException e) {
			System.exit(3);
		}
		return ;
	}

}
