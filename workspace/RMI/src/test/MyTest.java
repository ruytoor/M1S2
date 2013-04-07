package test;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.junit.Test;

/**
 * classe Main
 * @author Aurore Allart et Benjamin Ruytoor
 *
 */
public class MyTest {

	/**
	 * @param args contient le nom du noeud et le nom des fils
	 */


	/*
	Registry r;

	@Before
	public void startRmiRegistery(){
		try {
			if(r==null)
				r=LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@After
	public void stopRmiRegistery(){
		try {

			for (String s:r.list())
				r.unbind(s);

		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 */
	@Test
	public void coucouTest()  {
		ArrayList<Process> list=new ArrayList<Process>();
		File directory= new File("/home/benjamin/M1S2/workspace/RMI/bin");
		Runtime runtime=Runtime.getRuntime();
		try {
			Process rmiRegistre=runtime.exec("java rmi.RMIregistery");
			
			ProcessBuilder pb=new ProcessBuilder("java","node.CreateNodeInRMI","1","2","3","4","5","6");
			pb.directory(directory);
			pb.redirectErrorStream(true);
			Process papa=pb.start();
			
			for(int i=2;i<7;i++){
				Process p=runtime.exec("java node.CreateNodeInRMI "+i);
				list.add(p);
			}
			Process propage=runtime.exec("java node.PropageMessageNode 1 coucou");
			
			int codeRetour;
			try {
				if((codeRetour=propage.waitFor())!=0){
					BufferedReader bufferRegistre=new BufferedReader(new InputStreamReader(propage.getErrorStream()));
					System.out.println(codeRetour);
					papa.destroy();
					rmiRegistre.destroy();
					for(Process p : list)
						p.destroy();
					assertTrue(false);
					return;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			BufferedReader bufferRegistre=new BufferedReader(new InputStreamReader(rmiRegistre.getInputStream()));

			while(!bufferRegistre.ready()){
				System.out.println(bufferRegistre.readLine());
			}
			
			String sTMP="";
			BufferedReader bufferPapa=new BufferedReader(new InputStreamReader(papa.getInputStream()));
			sTMP=bufferPapa.readLine();
			System.out.println(sTMP);
			//assertTrue(String.valueOf(sTMP).contains("coucou"));

			for(Process p:list){
				BufferedReader bufferP=new BufferedReader(new InputStreamReader(p.getInputStream()));
				sTMP=bufferP.readLine();
				System.out.println(sTMP);
				assertTrue(String.valueOf(sTMP).contains("coucou"));
				p.destroy();
			}
			rmiRegistre.destroy();
			papa.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/*
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
	 */
}
