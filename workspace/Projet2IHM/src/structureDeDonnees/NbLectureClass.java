package structureDeDonnees;

/**
 * classe du nombre de lecture d'un morceau
 * @author Aurore Allart et Benjamin Ruytoor
 * @version 10 mai 2013
 */
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

	/**
	 * incrémente le nombre de lecture du morceau
	 */
	public void setNbLecture(){
		this.nbLecture ++;
	}
}
