package Main;

import java.text.ParseException;

import javax.swing.JFrame;

import Model.MasterModel;
import Vue.MasterVue;

public class Main {

	public static void main(String[] args) {
			new Launcher();
	}
	
	public static void start(int nbCouleur) {
		new MasterVue("Projet 1 IHM",nbCouleur+2,new MasterModel(nbCouleur+2));
	}
}
