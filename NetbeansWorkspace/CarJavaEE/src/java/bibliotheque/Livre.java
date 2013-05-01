/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotheque;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author benjamin
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
    
    @Id
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public String getAuteur() {
        return auteur;
    }
    
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    
    
    public int getAnnee() {
        return annee;
    }
    
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    
    public int hashCode() {
        int hash = 0;
        hash += (titre != null ? titre.hashCode() : 0);
        return hash;
    }
    
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livre)) {
            return false;
        }
        Livre other = (Livre) object;
        if ((this.titre == null && other.titre != null) || (this.titre != null && !this.titre.equals(other.titre))) {
            return false;
        }
        return true;
    }
    
    public String toString() {
        return titre + (auteur==null||auteur.isEmpty()?"":" écrit par "+auteur) + (annee<0?"":" en "+annee) + ".";
    }
    

}
