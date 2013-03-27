/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPackage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    
    @PUT
    @Path("/{var:.*}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String put(@PathParam("var") String var,String context) {
        return "<html><body><h1>Hello in my ftp Client Web!</h1>"
                +var+" "+context
                +"</body></html>";
    }

    @POST
    @Path("/{var:.*}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  //  @Produces(MediaType.TEXT_HTML)
    public Response post(@PathParam("var") String path,String content) {
        System.out.println(content);
        String parametre[]=HTML.splitParametre(content);
        if(parametre==null)
            
            return Response.ok("<html><body><h1> Erreur de connection <br><a href=\"..\">Retour</a></h1></body></html>",MediaType.TEXT_HTML).build();
        FTPClient f=Clientftp.getFTPClient(parametre[0], parametre[1]);
        if(f==null)
            return Response.ok("<html><body><h1> Erreur de connection login fail "+content+"</h1></body></html>",MediaType.TEXT_HTML).build();
        
        if(content.contains("d=download")){
            StreamingOutput s=
            InputStream in=Clientftp.getFile(f, path);
            
            ResponseBuilder r=Response.ok(in, MediaType.MULTIPART_FORM_DATA);
            r.header("Content-Disposition", "attachment; filename=\""+path.substring(path.lastIndexOf("/")) +"\"");
            return r.build();
        }
        
        String retour;
        retour=Clientftp.getFileInHTLM(f,path,parametre[0],parametre[1]);
        Clientftp.disconnect(f);
        return Response.ok(HTML.getHead()
                + "<h1>Fichier de /"+path+"</h1><br>"
                + HTML.getParent(path,parametre[0],parametre[1])
                + retour
                + "</body></html>",MediaType.TEXT_HTML).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String post(String content) {
        String parametre[]=HTML.splitParametre(content);
        if(parametre==null){
            return "<html><body><h1> Erreur de connection <br><a href=\"..\">Retour</a></h1></body></html>";
        }
        FTPClient f=Clientftp.getFTPClient(parametre[0], parametre[1]);
        if(f==null)
            return "<html><body><h1> Erreur de connection login fail "+content+"</h1></body></html>";
        String retour;
        retour=Clientftp.getFileInHTLM(f,null,parametre[0],parametre[1]);
        Clientftp.disconnect(f);
        return HTML.getHead()
                + "<h1>Fichier de /</h1><br>"
                + retour
                + "</body></html>";
    }
}
