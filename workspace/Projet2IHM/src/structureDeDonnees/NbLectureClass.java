package structureDeDonnees;

public class NbLectureClass implements StructureMusique {

	private Musique musique;
	private int nbLecture;
	
	public NbLectureClass(int nbLecture, Musique musique){
		this.nbLecture = nbLecture;
		this.musique = musique;
	}
	
	public String toString(){
		return this.nbLecture+"";
	}
	
	@Override
	public Musique getMusique() {
		return musique;
	}

	public void setNbLecture(){
		this.nbLecture ++;
	}
}
