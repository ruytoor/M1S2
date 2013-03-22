/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 * 
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package mainPackage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.commons.net.ftp.FTPClient;

/**
 * REST Web Service
 *
 * @author mkuchtiak
 */

@Stateless
@Path("/")
public class HelloWorldResource {

    @EJB

    /**
     * Retrieves representation of an instance of helloworld.HelloWorldResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getXml(@Context HttpServletRequest req) {
            // récupération de session de session
            HttpSession s= req.getSession(false);
            
            if(s==null||s.getAttribute("ftp")==null)
                
                return "<html><body><h1>Hello, Welcome to My FTP Web Client.</h1>"+
                    "<form method=\"post\">Name :<input type=\"text\" name=\"name\">" 
                    + "<br>PassWord :<input type=\"password\" name=\"pwd\">"
                    + "<input type=\"submit\" value=\"OK\">"
                    + "</form>"
                    + "</body></html>";
            
            FTPClient f;
            f=(FTPClient)s.getAttribute("ftp");
      try { 
            f.disconnect();
            s.removeAttribute("ftp");
                    } catch (IOException ex) {
            Logger.getLogger(HelloWorldResource.class.getName()).log(Level.SEVERE, null, ex);
        }
                   return "<html><body><h1>Hello in my ftp Client Web!</h1>" 
                           +"Deconnextion"
                     +"</body></html>";

    }

    /**
     * PUT method for updating an instance of HelloWorldResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String putXml(String content) {
        System.out.println("t1"+content);
        return this.getXml();
    }
     * */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String postXml(String content,@Context HttpServletRequest req) {
        //création de session
     HttpSession s= req.getSession(true);
            String []tmp=content.split("&");            
            String name=tmp[0].split("=")[1];
                   String pwd=tmp[1].split("=")[1];
            FTPClient f=Clientftp.getFTPClient(name, pwd);
            if(f==null)
                      return "<html><body><h1> Erreur de connection login fail "+content+"</h1></body></html>";
            s.setAttribute("ftp", f);
            String retour="Error";
            retour=Clientftp.getFileInHTLM(f);
         return "<html><body>"
                 + "<h1>Fichier</h1><br>"
                 + retour
                 + "</body></html>";
    }
}