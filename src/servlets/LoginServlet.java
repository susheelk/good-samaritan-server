package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.User;

@WebServlet("/login")
public class LoginServlet extends AbstractServlet {

    @Override
    protected ResponseEntity<?> processGet(HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<User> re = new ResponseEntity<User>();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = db.getUserByLogin(email, password);
        re.setData(user);

        if(user.getName() == null) {
            response.setStatus(400);
            re.setError("Invalid Username or Password!!");
        }
        return re;
    }


}
