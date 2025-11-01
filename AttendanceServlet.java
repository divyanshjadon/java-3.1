import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/schooldb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("studentid");
        String date = request.getParameter("date"); // yyyy-mm-dd
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Attendance (StudentID, Date, Status) VALUES (?, ?, ?)"); 
            ps.setInt(1, Integer.parseInt(sid));
            ps.setString(2, date);
            ps.setString(3, status);
            ps.executeUpdate();
            ps.close();
            con.close();
            out.println("<h3>Attendance recorded successfully for Student ID: " + sid + "</h3>");
            out.println("<a href='attendance.jsp'>Back</a>");
        } catch (Exception e) {
            out.println("<h3>Error:</h3>" + e.getMessage());
        }
    }
}
