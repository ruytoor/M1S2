package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIregistery {

	public static void main(String args[]) throws RemoteException, InterruptedException  {

			Registry l =LocateRegistry.createRegistry(1099);
			while(true){
				Thread.sleep(10000);
				for(String s :l.list())
				System.err.println("->"+s);
			}
		
	}
}
