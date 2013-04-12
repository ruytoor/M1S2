package test;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests
 * @author Aurore Allart et Benjamin Ruytoor
 * @version 9 avril 2013
 */
public class MyTest {

	/**
	 * @param args contient le nom du noeud et le nom des fils
	 */
	Registry r;
	@Before
	public void startRmiRegistery(){
		try {
			if(r==null)
				r=LocateRegistry.createRegistry(1099);
			else
				r=LocateRegistry.getRegistry();
		} catch (RemoteException e) {
			//System.err.println("probleme au demarrage de l annuaire RMI");
		}
	}

	@After
	public void stopRmiRegistry(){
		try {
			r=LocateRegistry.getRegistry();
			for (String s:r.list()){
				r.unbind(s);
			}
		} catch (AccessException e) {
			System.err.println("stopRmiRegistry : if this registry is local and it denies the caller access to perform this operation");
		} catch (RemoteException e) {
			System.err.println("stopRmiRegistry : probleme lors de la desinscription");
		} catch (NotBoundException e) {
			e.printStackTrace();
			System.err.println("stopRmiRegistry : if name is not currently bound");
		}
	}

	/**
	 * test avec un arbre
	 * le message est d'abord envoy� � la racine de l'arbre
	 */
	@Test
	public void testArbre()  {
		//     1
		//  /  |  \
		// 2   3   6
		// |       |
		// 4       5
		
		// 1 -> coucou

		ArrayList<Process> list=new ArrayList<Process>();
		Runtime runtime=Runtime.getRuntime();
		try {

			Process papa=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 1 2 3 6");
			list.add(papa);
			Process proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 2 4");
			list.add(proc);
			proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 3");
			list.add(proc);
			proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 4");
			list.add(proc);
			proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 5");
			list.add(proc);
			proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 6 5");
			list.add(proc);
			Process propage=runtime.exec("java -classpath bin/ node.PropageMessageNode 1 coucou");

			int codeRetour;
			try {
				if((codeRetour=propage.waitFor())!=0){	// cas d'erreur	
					String tmpS;
					BufferedReader bufferRegistre=new BufferedReader(new InputStreamReader(propage.getErrorStream()));
					while((tmpS=bufferRegistre.readLine())!=null)
						System.out.println(tmpS);
					System.out.println(codeRetour);
					papa.destroy();
					for(Process p : list)
						p.destroy();
					assertTrue(false);
					return;
				}
			} catch (InterruptedException e) {
				System.err.println("testArbre : process interrompu");
			}

			int i=0;
			for(Process p:list){
				String sTmp="";
				BufferedReader bufferP=new BufferedReader(new InputStreamReader(p.getInputStream()));
				i++;
				if(bufferP.ready()){
					sTmp=bufferP.readLine();
				}
				assertTrue(sTmp.contains("coucou"));

				p.destroy();
			}
		} catch (IOException e) {
			System.err.println("testArbre : lecture buffer");
		}

	}


	/**
	 * test avec un arbre 
	 * le message est d'abord envoye au noeud 6
	 */
	@Test
	public void testArbre2()  {
		//     1
		//  /  |  \
		// 2   3   6
		// |       |
		// 4<----->5
		// 6 -> coucou
		ArrayList<Process> list=new ArrayList<Process>();
		Runtime runtime=Runtime.getRuntime();
		try {

			Process papa=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 1 2 3 6");
			list.add(papa);
			Process proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 2 4");
			list.add(proc);
			proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 3");
			list.add(proc);
			proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 4 5");
			list.add(proc);
			proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 5 4");
			list.add(proc);
			proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 6 5");
			list.add(proc);
			Process propage=runtime.exec("java -classpath bin/ node.PropageMessageNode 6 coucou");

			int codeRetour;
			try {
				if((codeRetour=propage.waitFor())!=0){	// cas d'erreur	
					String tmpS;
					BufferedReader bufferRegistre=new BufferedReader(new InputStreamReader(propage.getErrorStream()));
					while((tmpS=bufferRegistre.readLine())!=null)
						System.out.println(tmpS);
					System.out.println(codeRetour);
					papa.destroy();
					for(Process p : list)
						p.destroy();
					assertTrue(false);
					return;
				}
			} catch (InterruptedException e) {
				System.err.println("testArbre2 : process interrompu");
			}

			String sTmp="";
			int i=0;
			for(Process p:list){
				BufferedReader bufferP=new BufferedReader(new InputStreamReader(p.getInputStream()));
				i++;
				if(bufferP.ready()){
					sTmp=bufferP.readLine();
				}

				if(i==6||i==5||i==4)
					assertTrue(sTmp.contains("coucou"));

				p.destroy();
			}
		} catch (IOException e) {
			System.err.println("testArbre2 : lecture buffer");
		}

	}

	/**
	 * test avec un arbre 
	 * avec 2 messages diff�rents
	 */
	@Test
	public void testArbre3()  {
		//     1 
		//  /  |  \
		// 2   3   6
		// |       |
		// 4       5
		// 1 > coucou
		// 2 > beuh

		ArrayList<Process> list=new ArrayList<Process>();
		Runtime runtime=Runtime.getRuntime();
		try {

			Process papa=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 1 2 3 6");
			list.add(papa);
			Process proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 2 4");
			list.add(proc);
			proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 3");
			list.add(proc);
			proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 4");
			list.add(proc);
			proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 5");
			list.add(proc);
			proc=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 6 5");
			list.add(proc);
			Process propage=runtime.exec("java -classpath bin/ node.PropageMessageNode 1 coucou");
			Process propage2=runtime.exec("java -classpath bin/ node.PropageMessageNode 2 beuh");

			int codeRetour;
			try {
				if((codeRetour=propage.waitFor())!=0){	// cas d'erreur	
					String tmpS;
					BufferedReader bufferRegistre=new BufferedReader(new InputStreamReader(propage.getErrorStream()));
					while((tmpS=bufferRegistre.readLine())!=null)
						System.out.println(tmpS);
					System.out.println(codeRetour);
					papa.destroy();
					for(Process p : list)
						p.destroy();
					assertTrue(false);
					return;
				}
			} catch (InterruptedException e) {
				System.err.println("testArbre3 : process interrompu pour le message coucou");
			}
			try {
				if((codeRetour=propage2.waitFor())!=0){	// cas d'erreur	
					String tmpS;
					BufferedReader bufferRegistre=new BufferedReader(new InputStreamReader(propage2.getErrorStream()));
					while((tmpS=bufferRegistre.readLine())!=null)
						System.out.println(tmpS);
					System.out.println(codeRetour);
					papa.destroy();
					for(Process p : list)
						p.destroy();
					assertTrue(false);
					return;
				}
			} catch (InterruptedException e) {
				System.err.println("testArbre3 : process interrompu pour le message beuh");
			}

			String sTmp="";
			int i=0;
			for(Process p:list){
				BufferedReader bufferP=new BufferedReader(new InputStreamReader(p.getInputStream()));
				i++;
				if(bufferP.ready()){
					sTmp=bufferP.readLine();
				}
				if(i==2||i==4)
					assertTrue(sTmp.contains("beuh")||sTmp.contains("coucou"));
				else
					assertTrue(sTmp.contains("coucou"));

				p.destroy();
			}
		} catch (IOException e) {
			System.err.println("testArbre3 : lecture buffer");
		}

	}


	/**
	 * test avec un graphe avec 2 messages diff�rents
	 */
	@Test
	public void testGraph(){
		// 1 -> 2 -> 3 -> 1
		
		//1 -> coucou
		//3 -> beuh
		ArrayList<Process> list=new ArrayList<Process>();
		Runtime runtime=Runtime.getRuntime();
		try {

			Process p=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 1 2");
			list.add(p);
			p=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 2 3");
			list.add(p);
			p=runtime.exec("java -classpath bin/ node.CreateNodeInRMI 3 1");
			list.add(p);
			Process propage=runtime.exec("java -classpath bin/ node.PropageMessageNode 1 coucou");
			Process propage2=runtime.exec("java -classpath bin/ node.PropageMessageNode 3 beuh");

			int codeRetour;
			try {
				if((codeRetour=propage.waitFor())!=0){	// cas d'erreur	
					String tmpS;
					BufferedReader bufferRegistre=new BufferedReader(new InputStreamReader(propage.getErrorStream()));
					while((tmpS=bufferRegistre.readLine())!=null)
						System.out.println(tmpS);
					System.out.println(codeRetour);
					for(Process ptmp : list)
						ptmp.destroy();
					assertTrue(false);
					return;
				}
			} catch (InterruptedException e) {
				System.err.println("testGraphe : process interrompu avec le message coucou");
			}
			try {
				if((codeRetour=propage2.waitFor())!=0){	// cas d'erreur	
					String tmpS;
					BufferedReader bufferRegistre=new BufferedReader(new InputStreamReader(propage2.getErrorStream()));
					while((tmpS=bufferRegistre.readLine())!=null)
						System.out.println(tmpS);
					System.out.println(codeRetour);
					for(Process ptmp : list)
						ptmp.destroy();
					assertTrue(false);
					return;
				}
			} catch (InterruptedException e) {
				System.err.println("testGraphe : process interrompu avec le message beuh");
			}


			int i=0;
			for(Process ptmp:list){

				String sTmp="";
				String sTmp2="";
				
				BufferedReader bufferP=new BufferedReader(new InputStreamReader(ptmp.getInputStream()));
				i++;
				
				if(bufferP.ready()){
					sTmp=bufferP.readLine();
					sTmp2=bufferP.readLine();
				}
				//System.out.println(sTmp+"  :  "+sTmp2+"  :   "+bufferP.ready());
				assertTrue((sTmp.contains("beuh")||sTmp.contains("coucou"))&&(sTmp2.contains("beuh")||sTmp2.contains("coucou")));
				while(bufferP.ready())
					System.out.println(bufferP.readLine());
				ptmp.destroy();
			}
			
		} catch (IOException e) {
			System.err.println("testGraphe : lecture buffer");			
		}


	}
}
