package bibliotheque;

import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Init;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Singleton;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
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
    
    @PostActivate
    @PostConstruct
    public void toolInit(){
        emf = Persistence.createEntityManagerFactory("CarJavaEEPU");
        em=emf.createEntityManager();
         transac = em.getTransaction();
    }
    
    @PrePassivate
    @PreDestroy
    public void toolDestroy(){
       em.close();
       emf.close();
    }
    
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
    
    public void ajouter(Livre l){

        transac.begin();
        em.persist(l);
        transac.commit();
        
    }

    public void ajouter(Panier p){

        transac.begin();
        em.persist(p);
        transac.commit();
        
    }
    public List<Livre> getAll(){

        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Livre> cq=cb.createQuery(Livre.class);
        
        Root<Livre> l=cq.from(Livre.class);
        cq.select(l);
        TypedQuery<Livre> q = em.createQuery(cq);
        List<Livre> allLivre = q.getResultList();
        
        return allLivre;
    }
    
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
    
    public List<String> getAllAuteur(){

        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<String> cq=cb.createQuery(String.class);
        
        Root<Livre> l=cq.from(Livre.class);
        cq.groupBy(l.get(Livre_.auteur));
        cq.select(l.get(Livre_.auteur));
        TypedQuery<String> q = em.createQuery(cq);
        List<String> Livre = q.getResultList();
        
        return Livre;
    }
    
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
