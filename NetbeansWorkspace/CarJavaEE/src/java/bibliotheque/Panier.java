/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author benjamin
 */
@Entity
public class Panier {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id; 
    @OneToMany
    private Collection<Livre> panier;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Panier(){
        panier=new ArrayList<Livre>();
    }
    
    public boolean isEmpty(){
        return panier.isEmpty();
    }
    
    public void add(Livre l){
        panier.add(l);
    }
 
    public String getHtmlCode(){
        String retour ="Panier :";
        if(panier.isEmpty())
            return retour+"vide";
        for(Livre l:panier)
            retour+="<br>"+l.toString();
        return retour;
    }

    public void reset() {
        panier.clear();
    }
}
