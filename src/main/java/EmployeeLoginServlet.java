import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import Business.EmployeeObject;

/**
 *
 * @author dpizo
 */
@WebServlet(urlPatterns = {"/EmployeeLoginServlet"})
public class EmployeeLoginServlet extends HttpServlet {

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
            System.out.println("In Employee Login Servlet");
            String idgui = request.getParameter("employeeID");
            System.out.println("Employee Id is: " + idgui);
            String pwgui = request.getParameter("password");
            System.out.println("Employee Password is: " + pwgui);
            
            EmployeeObject employee = new EmployeeObject();
            employee.selectDB(idgui);
            
            String pwdb = employee.getPW();
            if(pwgui.equals(pwdb)) {
                System.out.println("Found");
                HttpSession session = request.getSession();
                session.setAttribute("employee", employee);
                RequestDispatcher rs = request.getRequestDispatcher("EmployeePage.jsp");
                rs.forward(request, response);
            }
            else {
                System.out.println("Not Found");
                RequestDispatcher rs = request.getRequestDispatcher("LoginError.jsp");
                rs.forward(request, response);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        finally {
            System.out.println("Employee Login Servlet Ending");
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
