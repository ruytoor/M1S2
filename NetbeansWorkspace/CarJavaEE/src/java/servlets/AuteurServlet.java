package servlets;

import bibliotheque.ToolLivreEJB;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 29 avril 2013
 */
public class AuteurServlet extends HttpServlet {
    
        @EJB
    ToolLivreEJB tool;
        
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List l=tool.getAllAuteur();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet auteurServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Liste des auteurs</h1>");
            out.print("<ul>");
            for(Object o:l)
                if(o!=null)
                    out.println("<li>"+o.toString()+"</li>");
            out.print("</ul>");
            out.print("<br>");
            out.println("<a href=\"index.jsp\">accueil</a>");
            out.println("</body>");
            out.println("</html>");
            
        } finally {
            out.close();
        }
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
