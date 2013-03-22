/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPackage;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
    public String getXml(@PathParam("var") String var,String context) {
        return "<html><body><h1>Hello in my ftp Client Web!</h1>"
                +var+" "+context
                +"</body></html>";
    }
    /*
    @GET
    @Path("/{var:.*}")
    @Produces(MediaType.TEXT_HTML)
    public String getXml(@PathParam("var") String var,String context) {
        
        return "<html><body><h1>Hello in my ftp Client Web!</h1>"
                +var+"  "+context
                +"</body></html>";
    }
    */
    
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String postXml(String content) {
        String parametre[]=HTML.splitParametre(content);
        String path=null;
        if(parametre==null){
            return "<html><body><h1> Erreur de connection <br><a href=\"..\">Retour</a></h1></body></html>";
        }else if(parametre.length>2)
            path=parametre[2];
        FTPClient f=Clientftp.getFTPClient(parametre[0], parametre[1]);
        if(f==null)
            return "<html><body><h1> Erreur de connection login fail "+content+"</h1></body></html>";
        String retour="Error";
        retour=Clientftp.getFileInHTLM(f,path,parametre[0],parametre[1]);
        Clientftp.disconnect(f);
        return "<html><body>"
                + "<h1>Fichier de /"+(path==null?"":path)+"</h1><br>"
                + HTML.getParent(path)
                + retour
                + "</body></html>";
    }
}
