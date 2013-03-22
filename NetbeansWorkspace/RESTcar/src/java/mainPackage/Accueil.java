
package mainPackage;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author mkuchtiak
 */

@Stateless
@Path("/")
public class Accueil {
    @EJB
    /**
     * Retrieves representation of an instance of helloworld.HelloWorldResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getXml(@Context HttpServletRequest req) {
            // récupération de session de session    
                return "<html><body><h1>Hello, Welcome to My FTP Web Client.</h1>"+
                    "<form method=\"post\" action=\"FTP/\">Name :<input type=\"text\" name=\"name\">" 
                    + "<br>PassWord :<input type=\"password\" name=\"pwd\">"
                    + "<input type=\"submit\" value=\"OK\">"
                    + "</form>"
                    + "</body></html>";
    }
}