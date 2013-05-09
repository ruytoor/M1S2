package bibliotheque;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 1 mai 2013
 */
@Entity
public class Panier {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id; 
    @OneToMany
    private Collection<Livre> panier;
    
    
    public Panier(){
        panier=new ArrayList<Livre>();
    }
    
    /**
     * test si le panier est vide
     * @return 
     */
    public boolean isEmpty(){
        return panier.isEmpty();
    }
    
    /**
     * ajoute un livre au panier
     * @param l 
     */
    public void add(Livre l){
        panier.add(l);
    }
 
    /**
     * retourne la liste du panier en code HTML
     * @return 
     */
    public String getHtmlCode(){
        String retour ="Panier :";
        if(panier.isEmpty())
            return retour+"vide";
        for(Livre l:panier)
            retour+="<br>"+l.toString();
        return retour;
    }

    /**
     * vide le panier
     */
    public void reset() {
        panier.clear();
    }
}
