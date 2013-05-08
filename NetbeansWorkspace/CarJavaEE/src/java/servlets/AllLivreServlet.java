package servlets;

import bibliotheque.Livre;
import bibliotheque.Panier;
import bibliotheque.ToolLivreEJB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 1 mai 2013
 */
@WebServlet(name = "AllLivreServlet", urlPatterns = {"/AllLivreServlet"})
public class AllLivreServlet extends HttpServlet {
    
    @EJB
    ToolLivreEJB tool;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s= request.getSession();
        if(s.getAttribute("panier")==null)
            s.setAttribute("panier", new Panier());
        Panier p=(Panier) s.getAttribute("panier");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Liste des livres</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Liste des Livres</h1>");
            List<Livre> liste=tool.getAll();
            out.print("<ul>");
            for(Livre l:liste)
                out.println("<li>"+l.toString()+"<form method=post action=\"AllLivreServlet\"><INPUT type=\"hidden\" name=\"titre\" value=\""+l.getTitre()+"\"><INPUT type=\"submit\" value=\"Ajouter au  Panier\"></form></li>");
            out.print("</ul>");
            out.println("<a href=\"index.jsp\">accueil</a>");
            out.println("<br>"+p.getHtmlCode());
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
        Livre l=tool.getLivre(request.getParameter("titre"));
        HttpSession s= request.getSession();
        if(s.getAttribute("panier")==null)
            s.setAttribute("panier", new Panier());
        Panier p=(Panier) s.getAttribute("panier");
        p.add(l);
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
