package structureDeDonnees;

/**
 * classe de la duree du morceau de la musique 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class DurationClass implements StructureMusique{
	private Musique myMusique;
	private String duration;
	
	public DurationClass(String duration,Musique musique){
		this.duration=duration;
		this.myMusique=musique;
	}
	public String toString() {
		return duration;
	}
	public Musique getMusique() {
		return myMusique;
	}
}
