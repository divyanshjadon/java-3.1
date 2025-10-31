import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        // Simple hardcoded validation (replace with DB validation as needed)
        if ("admin".equals(user) && "12345".equals(pass)) {
            out.println("<html><body>"); 
            out.println("<h2>Welcome, " + user + "!</h2>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>"); 
            out.println("<h3>Invalid credentials! Please try again.</h3>");
            out.println("<a href=\"login.html\">Back to Login</a>");
            out.println("</body></html>");
        }
    }
}
