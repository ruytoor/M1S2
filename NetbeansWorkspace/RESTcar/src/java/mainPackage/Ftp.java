/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPackage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author benjamin
 */
@Stateless
@Path("/FTP")
public class Ftp {
    @EJB

    /*
     * /FTP/fichier
     * un fichier peut être un dossier ou un fichier
     * 
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
                InputStream in = null;
                try {
                    in = Clientftp.getFile(f, path);
                    ResponseBuilder r=Response.ok(in, MediaType.MULTIPART_FORM_DATA);
                    r.header("Content-Disposition", "attachment; filename=\""+path.substring(path.lastIndexOf("/")) +"\"");
                    return r.build();
                } catch (IOException ex) {
                    Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
                    return Response.ok(HTML.getHead()
                    + "<h1> Erreur Téléchargement</h1><br>"
                    + "</body></html>",MediaType.TEXT_HTML).build();
                } finally {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
                                            return Response.ok(HTML.getHead()
                    + "<h1> Erreur Téléchargement</h1><br>"
                    + "</body></html>",MediaType.TEXT_HTML).build();
                    }
                }
            }
            
            //Gestion Supprimer
            if(content.contains("sf=Supprimer")){
            try {
                f.deleteFile(path);
                path=path.substring(0, path.lastIndexOf("/"));
            } catch (IOException ex) {
                Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
                Message+="Erreur Supprésion fichier";
            }
            } 
            if(content.contains("sd=Supprimer")){
                if(!f.removeDirectory(path))
                    Message+="Erreur Supprésion dossier (doit être vide)";
                else
                    if(path.lastIndexOf("/")>=0)
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
                    + "</body></html>",MediaType.TEXT_HTML).build();
                }
            }
       
          
            
            //Gestion ListFile
        try {    
            String fileHTML;
            fileHTML=Clientftp.getFileInHTLM(f,path,parametre[0],parametre[1]);
            Clientftp.disconnect(f);
            return Response.ok(HTML.getHead()
                    + "<h1>Fichier de /"+(path.contentEquals(".")?"":path)+"</h1><br>"
                    + Message
                    + HTML.getParent(path,parametre[0],parametre[1])
                    + HTML.getOptionNouveauDossier(path, parametre[0], parametre[1])
                    + fileHTML
                    + "</body></html>",MediaType.TEXT_HTML).build();
        } catch (IOException ex) {
            Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
            return Response.ok(HTML.getHead()
                    + "<h1> Erreur Listing Fichier</h1><br>"
                    + "</body></html>",MediaType.TEXT_HTML).build();
        }
    }
    
    
    /*
     * racine
     * 
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
                    + "</body></html>";
                }
            }
            
            
     try {
            String fileHTML;
            fileHTML=Clientftp.getFileInHTLM(f,null,parametre[0],parametre[1]);
            Clientftp.disconnect(f);
            return HTML.getHead()
                    + "<h1>Fichier de /</h1><br>"
                    + Message
                    + HTML.getOptionNouveauDossier(".", parametre[0], parametre[1])
                    + fileHTML
                    + "</body></html>";
        } catch (IOException ex) {
            Logger.getLogger(Ftp.class.getName()).log(Level.SEVERE, null, ex);
            return "<html><body><h1> Erreur listing FTP </h1></body></html>";
        }
    }
}
