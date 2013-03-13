package Model;

import java.util.ArrayList;

public class MasterModel {
	
	private ArrayList<Model> listModel;
	
	public MasterModel(int nombreCouleur){
		listModel=new ArrayList<Model>();
		for(int i=0;i<nombreCouleur;++i){
			listModel.add(new Model(i,nombreCouleur));
		}
	}
	
	
}
