package structureDeDonnees;

/**
 * classe de la duree du morceau de la musique 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class DurationClass implements StructureMusique{
	private Musique myMusique;
	private String duration;
	
	/*ne pas sauvegarder la duree en seconde dans un int, lors du chargement de la bibliotheque 
	 *traitement lourds pour un si grands nombre d'entrée, cela nuirait à la recherche dynamique
	 */
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
	
	public int getSeconde(){
		String tmp[]=duration.split(":");
		int minutes=Integer.parseInt(tmp[0]);
		int secondes=Integer.parseInt(tmp[1]);
		return secondes+minutes*60;
	}
}
