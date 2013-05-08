package structuredonne;

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
