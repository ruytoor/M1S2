package structureDeDonnees;

/**
 * classe de l'artiste de la musique 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class ArtistClass implements StructureMusique{
	private Musique myMusique;
	private String artist;
	
	public ArtistClass(String artist, Musique musique){
		this.artist=artist;
		this.myMusique=musique;
	}
	
	public String toString() {
		return artist;
	}
	public Musique getMusique() {
		return myMusique;
	}
}
