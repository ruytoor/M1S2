package structuredonne;

public class GenreClass implements StructureMusique{
	private Musique myMusique;
	private String genre;
	
	public GenreClass(String genre,Musique musique){
		this.genre=genre;
		this.myMusique=musique;
	}
	public String toString() {
		return genre;
	}
	public Musique getMusique() {
		return myMusique;
	}
}
