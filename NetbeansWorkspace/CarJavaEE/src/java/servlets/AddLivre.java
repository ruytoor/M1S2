/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import bibliotheque.Livre;
import bibliotheque.ToolLivreEJB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author benjamin
 */
@WebServlet(name = "ddLivre", urlPatterns = {"/addLivre"})
public class AddLivre extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        tool.ajouter(new Livre(request.getParameter("titre"), request.getParameter("auteur"), request.getParameter("annee")));
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Livre ajouté</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Livre ajouté</h1>");
            out.println("<h2>recapitulatif :" + request.getParameter("titre") + " " + request.getParameter("auteur") + " " + request.getParameter("annee") + " a été ajouté</h2>");
            out.println("<a href=\"index.jsp\">accueil</a>");
            out.println("</body>");
            out.println("</html>");
             
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        response.sendRedirect("index.jsp");
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
    }// </editor-fold>
}
