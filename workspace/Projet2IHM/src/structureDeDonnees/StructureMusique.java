package structureDeDonnees;

/**
 * Interface caracterisant la structure de donnee d'un morceau de musique 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public interface StructureMusique {

	/**
	 * retourne le nom de l'element de la structure
	 * @return le nom de l'element de la structure
	 */
	public String toString();
	/**
	 * retourne la musique de l'objet
	 * @return la musique
	 */
	public Musique getMusique();

}
