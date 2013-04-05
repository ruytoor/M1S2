package test;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import org.junit.Test;

import site.SiteImpl;
import site.SiteItf;

import static org.junit.Assert.*;

/**
 * classe Main
 * @author Aurore Allart et Benjamin Ruytoor
 *
 */
public class MyTest {

	/**
	 * @param args contient le nom du noeud et le nom des fils
	 */
	@Test
	public void coucouTest() {
		ArrayList<Process> list=new ArrayList<Process>();
		Runtime r = Runtime.getRuntime();
		try {
		//	LocateRegistry.createRegistry(1099);
			String[] argCpapa = {"serveur.RunServeur","1","2","3","4","5","6"};
			Process papa=r.exec("java",argCpapa);

			for(int i=2;i<7;i++){
				String[] argC = {"serveur.RunServeur",""+i};
				Process p=r.exec("java",argC);
				list.add(p);
			}
			String [] argPro={"client.PropageMessageNode","1","coucou"};
			Process pPapa=r.exec("java",argPro);

			for(Process p:list){
				byte t[]=new byte[100];
				p.getInputStream().read(t);
				String s=new String(t);
				System.out.println(s);
				assertTrue(String.valueOf(s).contains("coucou"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testCreation(){
		System.setSecurityManager(new RMISecurityManager());
		try{
			//	SiteItf sI = (SiteItf)Naming.lookup("//localhost/SiteImpl");
			//ArrayList<SiteItf> fils = new ArrayList<SiteItf>();
			//SiteImpl sI = new SiteImpl(1, fils);
			//Naming.rebind("//localhost/SiteImpl", sI);
			//Runtime r = Runtime.getRuntime();
			//String[] argP = {"main.MyTest","1","coucou"};
			//Process p = r.exec("java", argP);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

