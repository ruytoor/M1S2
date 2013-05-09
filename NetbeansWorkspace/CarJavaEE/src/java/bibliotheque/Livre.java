package bibliotheque;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *v
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 1 mai 2013
 */
@Entity
public class Livre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String titre;
    private String auteur;
    private int annee;
    
    public Livre() {
    }
    
    public Livre(String titre,String auteur,String annee){
        this.titre=titre;
        if(annee.isEmpty())
            this.annee=-1;
        else
            this.annee=Integer.parseInt(annee);
        if(auteur.isEmpty())
            this.auteur=null;
        else
            this.auteur=auteur;
    }
    
    public String getTitre() {
        return titre;
    }
    
    /**
     * modifie le titre du livre
     * @param titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    /**
     * retourne le nom de l'auteur du livre
     * @return 
     */
    public String getAuteur() {
        return auteur;
    }
    
    /**
     * modifie le nom de l'auteur
     * @param auteur 
     */
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    
    /**
     * retourne l'annee du livre
     * @return l'annee
     */
    public int getAnnee() {
        return annee;
    }
    
    /**
     * modifie l'annee du livre
     * @param annee 
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    
    /**
     * retourne le hashcode du livre
     * @return int
     */
    public int hashCode() {
        int hash = 0;
        hash += (titre != null ? titre.hashCode() : 0);
        return hash;
    }
    
    /**
     * test l'equalite entre 2 livres
     * @param object
     * @return true si les 2 livres sont identiques
     */
    public boolean equals(Object object) {
        if (!(object instanceof Livre)) {
            return false;
        }
        Livre other = (Livre) object;
        if ((this.titre == null && other.titre != null) || (this.titre != null && !this.titre.equals(other.titre))) {
            return false;
        }
        return true;
    }
    
    /**
     * retourne les informations du livre
     * @return 
     */
    public String toString() {
        return titre + (auteur==null||auteur.isEmpty()?"":" écrit par "+auteur) + (annee<0?"":" en "+annee) + ".";
    }
    

}
