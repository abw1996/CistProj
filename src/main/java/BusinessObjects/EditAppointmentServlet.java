import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Business.AppointmentsObject;
/**
 *
 * @author dpizo
 */
@WebServlet(urlPatterns = {"/EditAppointmentServlet"})
public class EditAppointmentServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            System.out.println("In EditAppointmentServlet");
            String appointmentID = request.getParameter("appointmentID");
            String apptDateTime = request.getParameter("date");
            String employeeID = request.getParameter("employeeID");
            String customerID = request.getParameter("customerID");
            String procCode = request.getParameter("procCode");
            
            System.out.println(appointmentID + apptDateTime + employeeID + customerID + procCode);
            
            AppointmentsObject appointment = new AppointmentsObject();
            if (appointmentID != null) {
                appointment.setAppointmentID(appointmentID);
                appointment.getAppointmentID();
            } 
            else if (apptDateTime != null) {
                appointment.setAppointmentDateTime(apptDateTime);
                appointment.getAppointmentDateTime();
            }
            else if (employeeID != null) {
                appointment.setEmployeeID(employeeID);
                appointment.getEmployeeID();
            }
            else if (customerID != null) {
                appointment.setCustomerID(customerID);
                appointment.getCustomerID();
            }
            else if (procCode != null) {
                appointment.setProcedureID(procCode);
                appointment.getProcedureID();
            }
            appointment.updateDB();
        }
        catch (Exception ex) {
            System.out.println(ex);
    }
        finally {
            System.out.println("EditAppointment Servlet Ending...");
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