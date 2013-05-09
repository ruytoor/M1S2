package structuredonne;

public class YearClass implements StructureMusique{
	private Musique myMusique;
	private String year;
	
	public YearClass(String year,Musique musique){
		this.year=year;
		this.myMusique=musique;
	}
	public String toString() {
		return year;
	}
	public Musique getMusique() {
		return myMusique;
	}
}
