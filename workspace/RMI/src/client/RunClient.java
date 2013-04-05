package client;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import site.SiteImpl;

public class RunClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(System.getSecurityManager()==null)
			System.setSecurityManager(new RMISecurityManager());
		try{
			String name = "1";
			Registry registry = LocateRegistry.getRegistry(args[0]);
			SiteImpl site = (SiteImpl) registry.lookup(name);
			site.propage("Salut".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
