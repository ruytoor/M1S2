import java.awt.Frame;
import java.awt.ScrollPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;


public class Main {


	public static void main(String args[]){
		Runtime r= Runtime.getRuntime();
		try {
			Process p=r.exec("pstree -Apl");
			InputStream in=p.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(in));
			NodeProce root=new NodeProce("erreur(0)");
			String line;
			ArrayList<NodeProce> parent=new ArrayList<NodeProce>();
			while((line=br.readLine()) != null){
				String []mot=line.split("-\\+-|---|\\|-| \\| |`-");
		//		System.out.print(mot.length+" ");
				for(int niv=0;niv<mot.length;niv++){
					NodeProce nodeTmp;
				//	System.out.print("["+mot[niv]+"]->");
					if(parent.isEmpty()){//root
						root=new NodeProce(mot[niv]);
						parent.add(root);
					}else if(!mot[niv].trim().isEmpty()){
						if(niv<parent.size()){
							while(niv<=parent.size()-1){
								parent.remove(parent.size()-1);
							}
						}
						nodeTmp=new NodeProce(mot[niv]);
						parent.get(niv-1).add(nodeTmp);
						parent.add(nodeTmp);
					}
				}
			}
			JFrame f=new JFrame("test");
			f.add(new JScrollPane(new JTree(root)));
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.pack();
			f.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
