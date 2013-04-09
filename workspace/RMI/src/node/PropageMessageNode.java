package node;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import site.SiteItf;


public class PropageMessageNode {

	/**
	 * demarre le client
	 * @param args
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
			e.printStackTrace();
			System.exit(666);
		}
		return ;
	}

}
