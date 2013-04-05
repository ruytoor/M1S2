package site;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class PropageMessageNode {

	/**
	 * demarre le client
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			String name = args[0];
			Registry registry = LocateRegistry.getRegistry();
			SiteItf site = (SiteItf) registry.lookup(name);
			site.propage(args[1].getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
