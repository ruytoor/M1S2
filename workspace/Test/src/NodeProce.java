import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.tree.DefaultMutableTreeNode;


public class NodeProce extends DefaultMutableTreeNode {

	public static Pattern p=Pattern.compile(".*\\((\\d+)\\).*");
	
	private int pid;
	private String titre;
	private double value; //energy
	
	
	public NodeProce(String titre){
		super(titre);
		this.titre=titre;
		Matcher m=p.matcher(titre);
		m.matches();
		pid=Integer.parseInt(m.group(1));
	}

	public int getPid() {
		return pid;
	}

	public String getTitre() {
		return titre;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}
