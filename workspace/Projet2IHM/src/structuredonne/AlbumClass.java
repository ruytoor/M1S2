package structuredonne;

public class AlbumClass implements StructureMusique{
	private Musique myMusique;
	private String album;
	
	public AlbumClass(String album,Musique musique){
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
