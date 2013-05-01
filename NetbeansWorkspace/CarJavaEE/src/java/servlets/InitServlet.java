package servlets;

import bibliotheque.ToolLivreEJB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author benjamin Ruytoor et Aurore Allart
 * @version 29 avril 2013
 */
public class InitServlet extends HttpServlet {
    
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
        System.out.println(out.toString());
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet initServlet</title>");
            out.println("</head>");
            out.println("<body>");
            try{
                tool.init();
                out.println("<h1>la Biliotheque a été initialisée</h1>");
            }catch(Exception e){
                out.println("<h1>la Biliotheque a déjà été initialisée</h1>");
                out.println("<form method=post action=\"initServlet\"><input type=\"submit\" name=\"livre\" value=\"reset\"></form>");
            }
            
            out.println("<form method=post action=\"initServlet\"><input type=\"submit\" name=\"panier\" value=\"reset Panier\"></form>");
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
        if(request.getParameter("livre")!=null)
            tool.clear();
        if(request.getParameter("panier")!=null)
            tool.clearPanier();
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
