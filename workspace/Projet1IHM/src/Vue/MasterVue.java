package Vue;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import Model.MasterModel;

public class MasterVue extends JFrame{

	MasterModel masterM;
	int nbCouleur;
	public MasterVue(String titre,int nbCouleur,MasterModel m){
		super(titre);
		masterM=m;
		this.nbCouleur=nbCouleur;
		JScrollPane scrollP=new JScrollPane();
		getContentPane().add(scrollP);
		scrollP.setLayout(new ScrollPaneLayout());
		
		//scrollP.setPreferredSize(new Dimension(600, 400));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

}
