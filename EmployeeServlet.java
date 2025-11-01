import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Update these constants with your DB details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/companydb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String empid = request.getParameter("empid");
        String listAll = request.getParameter("list");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

            if (empid != null && !empid.isEmpty()) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM Employee WHERE EmpID = ?");
                ps.setInt(1, Integer.parseInt(empid));
                ResultSet rs = ps.executeQuery();
                out.println("<h3>Search Result</h3>");
                printTableHeader(out);
                while (rs.next()) {
                    printTableRow(out, rs);
                }
                printTableFooter(out);
                rs.close();
                ps.close();
            } else {
                // show all if ?list=all or no empid provided
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");
                out.println("<h3>Employee List</h3>");
                printTableHeader(out);
                while (rs.next()) {
                    printTableRow(out, rs);
                }
                printTableFooter(out);
                rs.close();
                stmt.close();
            }
            con.close();
        } catch (Exception e) {
            out.println("<h3>Error:</h3>" + e.getMessage());
        }
    }

    private void printTableHeader(PrintWriter out) {
        out.println("<table border='1'><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");
    }

    private void printTableRow(PrintWriter out, ResultSet rs) throws java.sql.SQLException {
        out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>" +
                    rs.getString("Name") + "</td><td>" +
                    rs.getDouble("Salary") + "</td></tr>");
    }

    private void printTableFooter(PrintWriter out) {
        out.println("</table><br><a href='searchEmployee.html'>Back</a>");
    }
}
