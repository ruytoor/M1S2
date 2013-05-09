package structureDeDonnees;

/**
 * classe de l'album de la musique 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class AlbumClass implements StructureMusique{
	private Musique myMusique;
	private String album;
	
	public AlbumClass(String album, Musique musique){
		this.album=album;
		this.myMusique=musique;
	}
	
	public String toString() {
		return album;
	}
	
	public Musique getMusique() {
		return myMusique;
	}
}
