package mainPackage;

import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;
import org.apache.commons.net.ftp.FTPClient;

/**
 * la passerelle REST
 * @author Benjamin Ruytoor et Aurore Allart
 */
@Stateless
@Path("/FTP")
public class Ftp {
    @EJB
    
    /**
     * permet de telecharger le fichier passer en parametre
     * @param path le chemin absolu du dossier
     * @param inputfile le fichier
     * @param pwd le mot de passe de l'utilisateur
     * @param name le nom de l'utilisateur
     * @param nomf le nom du fichier
     * @return la reponse du serveur ok si le fichier existe, error sinon
     */
    @POST
    @Path("/{var:.*}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("var") String path,@FormDataParam("file") File inputfile,@FormDataParam("pwd") String pwd,@FormDataParam("name") String name,@FormDataParam("nomf") String nomf) throws IOException {
        FTPClient f=Clientftp.getFTPClient(name, pwd);
        
        if(f.storeFile(path+nomf, new FileInputStream(inputfile)))
            return Response.ok(this.post(path,"name="+name+"&pwd="+pwd),MediaType.TEXT_HTML).build();
        else
            return Response.ok("<html><body>Error</body></html>",MediaType.TEXT_HTML).build();
    }
    
    /**
     * permet de stocker le fichier passer en parametre
     * @param inputfile le nom du fichier
     * @param pwd le mot de passe de l'utilisateur
     * @param name le nom de l'utilisateur
     * @param nomf le nom du fichier
     * @return la reponse du serveur 
     * @throws IOException 
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadR(@FormDataParam("file") File inputfile,@FormDataParam("pwd") String pwd,@FormDataParam("name") String name,@FormDataParam("nomf") String nomf) throws IOException {
        FTPClient f=Clientftp.getFTPClient(name, pwd);
        if(f.storeFile("/"+nomf, new FileInputStream(inputfile)))
            return Response.ok(this.post("name="+name+"&pwd="+pwd),MediaType.TEXT_HTML).build();
        else
            return Response.ok("<html><body>Error</body></html>",MediaType.TEXT_HTML).build();
    }
    
    /**
     * construit la page HTML et repond  a l'utilisateur
     * @param path le chemin absolu du dossier
     * @param content le lien du site
     * @return la reponse du serveur
     * @throws IOException 
     */
    @POST
    @Path("/{var:.*}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //  @Produces(MediaType.TEXT_HTML)
    public Response post(@PathParam("var") String path,String content) throws IOException {
        
        String Message="";
        
        //Gestion erreur
        String parametre[]=HTML.splitParametre(content);
        if(parametre==null)
            return Response.ok("<html><body><h1> Erreur de connection <br><a href=\"..\">Retour</a></h1></body></html>",MediaType.TEXT_HTML).build();
        FTPClient f=Clientftp.getFTPClient(parametre[0], parametre[1]);
        if(f==null)
            return Response.ok("<html><body><h1>login fail</h1></body></html>",MediaType.TEXT_HTML).build();
        
        // Gestion téléchargement
        if(content.contains("d=download")){
            try {
                final StreamingOutput out = Clientftp.getFile(f, path);
                ResponseBuilder r=Response.ok(out, MediaType.MULTIPART_FORM_DATA);
                r.header("Content-Disposition", "attachment; filename=\""+path.substring(path.lastIndexOf("/")+1)+"\"");        
                return r.build();
            } catch (IOException ex) {
                Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
                return Response.ok(HTML.getHead()
                        + "<h1> Erreur Téléchargement</h1><br>"
                        + HTML.getfoot(),MediaType.TEXT_HTML).build();
            } 
        }
        
        //Gestion Supprimer
        if(content.contains("sf=Supprimer")){
            try {
                f.deleteFile(path);
                if(path.lastIndexOf("/")>0)
                    path=path.substring(0, path.lastIndexOf("/"));
                else
                    path=".";
            } catch (IOException ex) {
                Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
                Message+="Erreur Supprésion fichier";
            }
        }
        if(content.contains("sd=Supprimer")){
            if(!f.removeDirectory(path))
                Message+="Erreur pour supprimer dossier (doit etre vide)";
            else
                if(path.lastIndexOf("/")>0)
                    path=path.substring(0, path.lastIndexOf("/"));
                else
                    path=".";
        }
        
        //Gestion Nouveau Dossier
        if(content.contains("new=new")){
            try {
                String tmp=content.substring(content.indexOf("nom=")+4, content.indexOf("&new=new"));
                if(tmp.compareTo("")==0)
                    Message+="Entrez un nom de fichier";
                else
                    Clientftp.newFolder(f,"/"+path+"/"+URLDecoder.decode(tmp));
            } catch (IOException ex) {
                Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
                return Response.ok(HTML.getHead()
                        + "<h1> Erreur Creation de dossier</h1><br>"
                        + HTML.getfoot(),MediaType.TEXT_HTML).build();
            }
        }
        
        //Gestion ListFile
        try {
            String fileHTML;
            fileHTML=Clientftp.getFileInHTML(f,path,parametre[0],parametre[1]);
            Clientftp.disconnect(f);
            if(fileHTML==null)
                    return Response.ok(HTML.getHead()
                    + "<h1> Erreur Path</h1><br>"
                    + HTML.getfoot(),MediaType.TEXT_HTML).build();
            return Response.ok(HTML.getHead()
                    + "<h1>Fichier de /"+(path.contentEquals(".")?"":path)+"</h1><br>"
                    + Message
                    + HTML.getOptionUpload(path, parametre[0], parametre[1])
                    + HTML.getParent(path,parametre[0],parametre[1])
                    + HTML.getOptionNouveauDossier(path, parametre[0], parametre[1])
                    + fileHTML
                    + HTML.getfoot(),MediaType.TEXT_HTML).build();
        } catch (IOException ex) {
            Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
            return Response.ok(HTML.getHead()
                    + "<h1> Erreur Listing Fichier</h1><br>"
                    + HTML.getfoot(),MediaType.TEXT_HTML).build();
        }
    }
    
    /**
     * meme chose que la methode precedente
     * @param content le lien du site
     * @return la reponse du serveur
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String post(String content) {
        String Message="";
        String parametre[]=HTML.splitParametre(content);
        if(parametre==null){
            return "<html><body><h1> Erreur de connection</h1></body></html>";
        }
        FTPClient f=Clientftp.getFTPClient(parametre[0], parametre[1]);
        if(f==null)
            return "<html><body><h1> login fail </h1></body></html>";
        
        //Gestion Nouveau Dossier
        if(content.contains("new=new")){
            try {
                String tmp=content.substring(content.indexOf("nom=")+4, content.indexOf("&new=new"));
                if(tmp.compareTo("")==0)
                    Message+="Entrez un nom de fichier";
                else
                    Clientftp.newFolder(f,"/"+URLDecoder.decode(tmp));
            } catch (IOException ex) {
                Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
                return HTML.getHead()+"<h1> Erreur Creation de dossier</h1><br>"
                        + HTML.getfoot();
            }
        }
        
        
        try {
            String fileHTML;
            fileHTML=Clientftp.getFileInHTML(f,null,parametre[0],parametre[1]);
            Clientftp.disconnect(f);
            return HTML.getHead()
                    + "<h1>Fichier de /</h1><br>"
                    + Message
                    + HTML.getOptionUpload(".", parametre[0], parametre[1])
                    + HTML.getOptionNouveauDossier(".", parametre[0], parametre[1])
                    + fileHTML
                    + HTML.getfoot();
        } catch (IOException ex) {
            Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
            return "<html><body><h1> Erreur listing FTP </h1></body></html>";
        }
    }
}
