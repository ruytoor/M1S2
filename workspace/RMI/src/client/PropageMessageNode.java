package client;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import site.SiteImpl;

public class PropageMessageNode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			String name = "1";
			Registry registry = LocateRegistry.getRegistry();
			SiteImpl site = (SiteImpl) registry.lookup(name);
			site.propage("coucou".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
