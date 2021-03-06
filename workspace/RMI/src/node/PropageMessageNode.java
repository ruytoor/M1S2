package node;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import site.SiteItf;

/**
 * propage le message
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 avril 2013
 */
public class PropageMessageNode {

	/**
	 * propage le message dans les noeuds du graphe ou de l'arbre
	 * @param args 0 = numero du noeud (nom)
	 * 			   1 = message
	 */
	public static void main(String[] args) {
		try{
			String name = args[0];
			
			Registry registry = LocateRegistry.getRegistry();
			while(registry.list().length==0){
				Thread.sleep(2000);
				registry = LocateRegistry.getRegistry();
			}
			SiteItf site = (SiteItf) registry.lookup(name);
			site.propage(args[1].getBytes());
		} catch (Exception e) {
			System.exit(3);
		}
		return ;
	}

}
