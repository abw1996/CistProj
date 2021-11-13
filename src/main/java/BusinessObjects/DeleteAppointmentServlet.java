package BusinessObjects;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author dpizo
 */
@WebServlet(urlPatterns = {"/DeleteAppointmentServlet"})
public class DeleteAppointmentServlet extends HttpServlet {
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
        
            String apptID = request.getParameter("appointmentID");
            Appointment appointment = new Appointment();
            appointment.deleteDB(apptID);
            
            if (request.getParameter("isCustomer").equals("TRUE")) {
                Customer customer = new Customer(request.getParameter("customerEmail"));
                HttpSession sess = request.getSession();
                sess.setAttribute("customer", customer);
                RequestDispatcher rd = request.getRequestDispatcher("CustomerAppointments.jsp");
                rd.forward(request, response);
            } else {
                Employee employee = new Employee(request.getParameter("currentEmployeeID"));
                HttpSession sess = request.getSession();
                sess.setAttribute("employee", employee);
                RequestDispatcher rd = request.getRequestDispatcher("EmployeeAppointments.jsp");
                rd.forward(request, response);
            }
            
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
            
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
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
