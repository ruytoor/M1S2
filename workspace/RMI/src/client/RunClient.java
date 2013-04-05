package client;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import site.SiteImpl;

public class RunClient {

	/**
	 * demarre le client
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			String name = "1";
			//Registry registry = LocateRegistry.getRegistry(args[0]);
			SiteImpl site = (SiteImpl) Naming.lookup(name);
			site.propage("Salut".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
