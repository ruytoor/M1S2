package test;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * classe Main
 * @author Aurore Allart et Benjamin Ruytoor
 *
 */
public class Main {

	/**
	 * @param args contient le nom du noeud et le nom des fils
	 */
	@Test
	public void coucouTest() {
		ArrayList<Process> list=new ArrayList<>();
		Runtime r = Runtime.getRuntime();
		try {
			String[] argCpapa = {"main.Main","1","2-3-4-5-6","coucou"};
			Process papa=r.exec("java",argCpapa);

			for(int i=2;i<7;i++){
				String[] argC = {"main.Main",""+i};
				Process p=r.exec("java",argC);
				list.add(p);
			}


			for(Process p:list){
				byte t[]=new byte[100];
				p.getInputStream().read(t);
				assertTrue(String.valueOf(t).compareTo("coucou")==0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
