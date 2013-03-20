package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import tool.MyColor;

import Controleur.ColorControleur;
import Controleur.ColorControleurHSV;
import Controleur.ColorControleurPreCalcule;
import Model.MasterModel;
import Model.ModelPreCalcule;

/*
 * Cette classe est la vue générale  exemple : si on doit ajouter un menu ou ce sera ici
 * 
 * la vue est composer de n sous-vue de classe CouleurVue
 * 
 * c'est ici qu'on intencie les models et qu'on crée les couleurs
*/
public class MasterVue extends JFrame{

	ArrayList<CouleurVue> listVue;
//	MasterModel masterM;
	int nbCouleur;
	public MasterVue(String titre,int nbCouleur,MasterModel m){
		super(titre);
		//masterM=m;
		this.nbCouleur=nbCouleur;
		listVue=new ArrayList<CouleurVue>();
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(nbCouleur-2, 1));

		
		// Création des Listes de couleurs -> une liste par niveau de gris
		ArrayList<ArrayList<MyColor>> tab=new ArrayList<ArrayList<MyColor>>();
		for(int i=0;i<nbCouleur-1;++i){
			tab.add(new ArrayList<MyColor>());
		}
		// Création des couleurs et association à leurs liste
		for(int r=0;r<256;r+=8){
			for(int g=0;g<256;g+=8){
				for(int b=0;b<256;b+=8){
					MyColor c=new MyColor(r, g, b);
					int gris=calculeGreyLvl(c);
					for(int i=1;i<nbCouleur-1;++i){
						if((i*255/(nbCouleur-1)<gris+5)&&(i*255/(nbCouleur-1)>gris-5)){
							tab.get(i).add(c);
						}
					}
				}
			}
		//System.out.println(r+"/255");
		}
		
		// Trie des couleur selon leurs teintes
		for(int i=0;i<nbCouleur-1;++i){
			List<MyColor> l=tab.get(i).subList(0, tab.get(i).size());
			//System.out.println("sort");
			Collections.sort(l);
			tab.add(i,new ArrayList<MyColor>(l));
			tab.remove(i+1);
			//System.out.println(tab.get(i).size());
			
		}

		// associe les CouleurVue avec les ModelPreCalculé et leur liste de couleurs
		for(int i=1;i<nbCouleur-1;++i){
			ModelPreCalcule m2=new ModelPreCalcule( tab.get(i));
			ColorControleurPreCalcule c=new ColorControleurPreCalcule(tab.get(i).size()-1, m2);
			//ColorControleurHSV c=new ColorControleurHSV(masterM.getModel(i));
			CouleurVue v=new CouleurVue(i+1,i*255/(nbCouleur-1),c);
			p.add(v);
			m2.addObserver(v);
			c.setRandom();
			//masterM.setModel(v,c,i);
			//c.randomColor();
		}

		//affichage dans la JFreme 
		if((this.nbCouleur-2)<4){
			setPreferredSize(new Dimension(600, (nbCouleur-2)*100));
			getContentPane().add(p);
		}else {
			setPreferredSize(new Dimension(630, 400));
			JScrollPane scrollP=new JScrollPane(p);
			getContentPane().add(scrollP);
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	//	masterM.notifyObserversAll();
		pack();
		setVisible(true);
	}
	
	//Méthode pour calculer le niveau de gris
	public static int calculeGreyLvl(Color c) {
		return (int) ((c.getGreen()*0.59)+(c.getBlue()*0.11)+(c.getRed()*0.3));
	}

}
