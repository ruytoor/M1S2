package Vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneLayout;

import Model.MasterModel;

public class MasterVue extends JFrame{

	ArrayList<CouleurVue> listVue;
	MasterModel masterM;
	int nbCouleur;
	public MasterVue(String titre,int nbCouleur,MasterModel m){
		super(titre);
		masterM=m;
		this.nbCouleur=nbCouleur;
		listVue=new ArrayList<CouleurVue>();
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(nbCouleur, 1));

		for(int i=0;i<nbCouleur;++i){
			CouleurVue c=new CouleurVue(i+1,i*255/(nbCouleur-1));
			p.add(c);
			masterM.setModel(c, i);
		}

		if(this.nbCouleur<4){
			setPreferredSize(new Dimension(600, nbCouleur*100));
			getContentPane().add(p);
		}else {
			setPreferredSize(new Dimension(630, 400));
			JScrollPane scrollP=new JScrollPane(p);
			getContentPane().add(scrollP);
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		masterM.notifyObserversAll();
		pack();
		setVisible(true);
	}

}
