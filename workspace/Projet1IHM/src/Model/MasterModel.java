package Model;

import java.util.ArrayList;

import Controleur.ColorControleur;
import Vue.CouleurVue;

public class MasterModel {
	
	private ArrayList<Model> listModel;
	
	public MasterModel(int nombreCouleur){
		listModel=new ArrayList<Model>();
		for(int i=0;i<nombreCouleur;++i){
			listModel.add(new Model(i,nombreCouleur));
		}
	}
	
	public Model getModel(int i){
		return listModel.get(i);
	}
	
	public void setModel(CouleurVue v,ColorControleur c,int i){
		listModel.get(i).addObserver(v);
		listModel.get(i).addObserver(c);
	}
	
	public void notifyObserversAll(){
		for(Model m : listModel)
			m.reNotifyObservers();
	}
}
