package structureDeDonnees;

/**
 * classe du titre de la musique 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class TitleClass implements StructureMusique{
	private Musique myMusique;
	private String title;
	
	public TitleClass(String title,Musique musique){
		this.title=title;
		this.myMusique=musique;
	}
	public String toString() {
		return title;
	}
	public Musique getMusique() {
		return myMusique;
	}
}
