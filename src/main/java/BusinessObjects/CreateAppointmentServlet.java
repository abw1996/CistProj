package BusinessObjects;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author dpizo
 */
@WebServlet(urlPatterns = {"/CreateAppointmentServlet"})
public class CreateAppointmentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String apptDateTime = request.getParameter("dateTime");
        apptDateTime = apptDateTime.replace("T", " ") + ":00";
        String employeeID = request.getParameter("employeeID");
        String customerID = request.getParameter("customerID");
        String procCode = request.getParameter("procedureID");
            
        Appointment appointment = new Appointment();
        String apptID = appointment.createID();
        appointment.insertDB(apptID, apptDateTime, employeeID, procCode, customerID);
            
        RequestDispatcher rd = request.getRequestDispatcher("index.html");
        rd.forward(request, response);
        try (PrintWriter out = response.getWriter()) {
            
        }
        
        catch (Exception ex) {
            System.out.println(ex);
            
        
        
    }
        finally {
            System.out.println("CreateAppointmentServlet Servlet Ending...");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
