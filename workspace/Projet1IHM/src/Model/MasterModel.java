package Model;

import java.util.ArrayList;

import Vue.CouleurVue;

public class MasterModel {
	
	private ArrayList<Model> listModel;
	
	public MasterModel(int nombreCouleur){
		listModel=new ArrayList<Model>();
		for(int i=0;i<nombreCouleur;++i){
			listModel.add(new Model(i,nombreCouleur));
		}
	}
	
	public void setModel(CouleurVue v,int i){
		listModel.get(i).addObserver(v);
	}
	
	public void notifyObserversAll(){
		for(Model m : listModel)
			m.reNotifyObservers();
	}
}
