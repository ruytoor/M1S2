package Main;

import Vue.MasterVue;
/**
 * classe Main qui lance le Launcher
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 21 mars 2013
 */
public class Main {

	public static void main(String[] args) {
			new Launcher();
	}
	
	public static void start(int nbCouleur) {
		new MasterVue("Projet 1 IHM",nbCouleur+2);
	}
}
