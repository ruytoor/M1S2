
package client;

import static   com.jayway.restassured.RestAssured.*;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * test Unitaire
 * @author Benjamin Ruytoor et Aurore Allart
 */
public class ClientFTPTest {
    
    @Test
    public void connection(){
        String s = get("http://localhost:8080/RESTcar/").asString();
        //System.out.println(s);
        assertTrue("connection ok", s.contains("Hello"));
    }
    
    @Test
    public void commandUserParamNonValid(){
        String s = given().param("name", "toto").param("pwd", "ben").post("http://localhost:8080/RESTcar/FTP/").asString();
        //System.out.println(s);
        assertTrue("nom utilisateur invalide", s.contains("login fail"));
    }
    
    @Test
    public void commandUserParamValid(){
        
        String s2= given().param("name", "Benj").param("pwd", "ben").post("http://localhost:8080/RESTcar/FTP/").asString();
        
        //System.out.println(s2);
        assertFalse("nom utilisateur valide", s2.contains("Error"));
    }
    
    @Test
    public void commandPwdParamNonValid(){
        String s= given().param("name", "Benj").param("pwd", "toto").post("http://localhost:8080/RESTcar/FTP/").asString();
        //System.out.println(s);
        assertTrue("Mot de passe invalide", s.contains("login fail"));
    }
    
    @Test
    public void commandPwdParamValid(){
        String s= given().param("name", "Benj").param("pwd", "ben").post("http://localhost:8080/RESTcar/FTP/").asString();
        //System.out.println(s);
        assertFalse("mot de passe valide", s.contains("Error"));
    }
    
    @Test
    public void commandList(){
        String s= given().param("name", "Benj").param("pwd", "ben").post("http://localhost:8080/RESTcar/FTP/").asString();
        //System.out.println(s);
        
        assertTrue("liste ok", s.contains("Fichier de /"));
    }
    
    @Test
    public void commandRetrParamNonValid(){
        String s= given().param("name", "Benj").param("pwd", "ben").post("http://localhost:8080/RESTcar/FTP/").asString();
        //System.out.println(s);
        assertFalse("retr non ok", s.contains(s));
    }
    
    @Test
    public void commandRetrParamValid(){
        String s= given().param("name", "Benj").param("pwd", "ben").post("http://localhost:8080/RESTcar/FTP/").asString();
        //System.out.println(s);
        assertFalse("retr ok", s.contains(s));
    }
    
    @Test
    public void commandStorParamNonValid(){
        String s= given().param("name", "Benj").param("pwd", "ben").post("http://localhost:8080/RESTcar/FTP/").asString();
        //System.out.println(s);
        assertFalse("stor non ok", s.contains(s));
    }
    
    @Test
    public void commandStorParamValid(){
        String s= given().param("name", "Benj").param("pwd", "ben").post("http://localhost:8080/RESTcar/FTP/").asString();
        //System.out.println(s);
        assertFalse("stor ok", s.contains(s));
    }
    
    @Test
    public void commandCwdParamValid(){
        String s= given().param("name", "Benj").param("pwd", "ben").post("http://localhost:8080/RESTcar/FTP/Doc").asString();
        //System.out.println(s);
        assertTrue("cwd ok", s.contains("Fichier de /Doc"));
    }
    
    @Test
    public void commandCwdParamNonValid(){
        String s= given().param("name", "Benj").param("pwd", "ben").post("http://localhost:8080/RESTcar/FTP/ffrr").asString();
        //System.out.println(s);
        assertTrue("cwd non ok", s.contains("Erreur Path"));
    }
    
    @Test
    public void commandAjouter(){
        String s= given().param("name", "Benj").param("pwd", "ben").param("nom", "dossierTest").param("new","new").post("http://localhost:8080/RESTcar/FTP/.").asString();
      //  System.out.println(s);
        assertTrue("ajouter dossier", s.contains("dossierTest"));
     
        String s2= given().param("name", "Benj").param("pwd", "ben").param("sd","Supprimer").post("http://localhost:8080/RESTcar/FTP/dossierTest").asString();
        //System.out.println(s2);
        assertFalse("supprimer dossier", s2.contains("dossierTest"));
        
    }
   /* 
    @Test
    public void commandSupprimer(){
        String s= given().param("name", "Benj").param("pwd", "ben").post("http://localhost:8080/RESTcar/FTP/").asString();
        //System.out.println(s);
        assertFalse("supprimer dossier ok", s.contains(s));
    }
     * */
}
