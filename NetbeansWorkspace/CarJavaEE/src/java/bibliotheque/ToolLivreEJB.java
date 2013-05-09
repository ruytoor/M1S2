package bibliotheque;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 29 avril 2013
 */
@Stateless
@LocalBean

public class ToolLivreEJB {
    
    private EntityManagerFactory emf;
    private EntityManager  em;
    private EntityTransaction transac ;
    
    /**
     * initialisation des attributs
     */
    @PostActivate
    @PostConstruct
    public void toolInit(){
        emf = Persistence.createEntityManagerFactory("CarJavaEEPU");
        em=emf.createEntityManager();
        transac = em.getTransaction();
    }
    
    /**
     * destruction des attributs
     */
    @PrePassivate
    @PreDestroy
    public void toolDestroy(){
        em.close();
        emf.close();
    }
    
    /**
     * initialise la base de données par une liste de livre
     */
    public void init(){
        
        transac.begin();
        Livre l=new Livre("Rhapsody - La symphonie des siecles","Elizabeth Haydon","1999");
        em.persist(l);
        l=new Livre("Chant de la Belgariade : Le pion blanc des presages","David Eddings","1982");
        em.persist(l);
        l=new Livre("Chant de la Belgariade : La Reine des sortileges","David Eddings","1982");
        em.persist(l);
        l=new Livre("Waylander","David Gemmell","1986");
        em.persist(l);
        transac.commit();
    }
    
    /**
     * ajoute un livre dans la base de donnees
     * @param l le livre à ajouter
     */
    public void ajouter(Livre l){
        
        transac.begin();
        em.persist(l);
        transac.commit();
        
    }
    
    /**
     * ajoute un panier dans la base de base de donnees
     * @param p le panier à ajouter
     */
    public void ajouter(Panier p){
        
        transac.begin();
        em.persist(p);
        transac.commit();
        
    }
    
    /**
     * retourne tous les livres de la base de donnees
     * @return les livres de base de donnees
     */
    public List<Livre> getAll(){
        
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Livre> cq=cb.createQuery(Livre.class);
        
        Root<Livre> l=cq.from(Livre.class);
        cq.select(l);
        TypedQuery<Livre> q = em.createQuery(cq);
        List<Livre> allLivre = q.getResultList();
        
        return allLivre;
    }
    
    /**
     * retourne le livre dont le titre est donne
     * @param titre du livre a trouver dans la base de donnees.
     * @return le livre
     */
    public Livre getLivre(String titre){
        
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Livre> cq=cb.createQuery(Livre.class);
        
        Root<Livre> l=cq.from(Livre.class);
        cq.select(l);
        Predicate predicate = cb.equal(l.get(Livre_.titre),titre);
        cq.where(predicate);
        TypedQuery<Livre> q = em.createQuery(cq);
        return q.getSingleResult();
    }
    
    /**
     * retourne la liste des Auteurs des livres de la base de donnees
     * @return la liste des Auteurs
     */
    public List<String> getAllAuteur(){
        
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<String> cq=cb.createQuery(String.class);
        
        Root<Livre> l=cq.from(Livre.class);
        cq.groupBy(l.get(Livre_.auteur));
        cq.select(l.get(Livre_.auteur));
        TypedQuery<String> q = em.createQuery(cq);
        List<String> allAuteur = q.getResultList();
        
        return allAuteur;
    }
    
    /**
     * retire tous les livres de la base de donnees
     */
    public void clear(){
        
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Livre> cq=cb.createQuery(Livre.class);
        
        Root<Livre> l=cq.from(Livre.class);
        cq.select(l);
        TypedQuery<Livre> q = em.createQuery(cq);
        List<Livre> allLivre = q.getResultList();
        
        transac.begin();
        for(Livre livre:allLivre)
            em.remove(livre);
        transac.commit();
    }
    
    /**
     * retire les paniers de la base de donnees
     */
    public void clearPanier(){
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Panier> cq=cb.createQuery(Panier.class);
        
        Root<Panier> p=cq.from(Panier.class);
        cq.select(p);
        TypedQuery<Panier> q = em.createQuery(cq);
        List<Panier> allPanier = q.getResultList();
        
        transac.begin();
        for(Panier ptmp:allPanier)
            em.remove(ptmp);
        transac.commit();
    }
    
    /**
     * retourne tous les paniers de la base de donnees
     * @return tous les paniers
     */
    public List<Panier> getAllPanier() {
        
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Panier> cq=cb.createQuery(Panier.class);
        
        Root<Panier> p=cq.from(Panier.class);
        cq.select(p);
        TypedQuery<Panier> q = em.createQuery(cq);
        List<Panier> AllPanier = q.getResultList();
        
        return AllPanier;
    }
}
