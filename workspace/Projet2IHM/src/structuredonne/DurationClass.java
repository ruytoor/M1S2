package structuredonne;

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
