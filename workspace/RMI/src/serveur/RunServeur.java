package serveur;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import site.SiteImpl;

public class RunServeur {

	/**
	 * demarre le serveur
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			LocateRegistry.createRegistry(1099);
			//ArrayList<Integer> fils = new ArrayList<Integer>();
			SiteImpl siteImpl = new SiteImpl(1, null);
			String url = "//"+InetAddress.getLocalHost().getHostAddress()+"/TestRMI";
			System.out.println(url);
			Naming.rebind(url, siteImpl);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
//		
//		if(System.getSecurityManager()==null)
//			System.setSecurityManager(new RMISecurityManager());
//		SiteImpl site1;
//		try {
//			site1 = new SiteImpl(1,new ArrayList<Integer>());
//			SiteImpl stub1 =(SiteImpl) UnicastRemoteObject.exportObject(site1, 0);
//			Registry registry = LocateRegistry.getRegistry();
//			registry.rebind("1", stub1);
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
	}

}
