package by.gsu.epamlab.servlets;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.factories.UserFactory;
import by.gsu.epamlab.interfaces.IUserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "register", urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);

        IUserDAO userDAO = UserFactory.getClassFromFactory();
        if (Constants.EMPTY.equals(login) || Constants.EMPTY.equals(password)) {
            jumpError(Constants.ERR_EMPTY_LOGIN_PASSWORD, Constants.REGISTER_PAGE, request, response);
        }
        try {
            if (userDAO.getUser(login, password) == null) {
                userDAO.addUser(login, password);
                HttpSession session = request.getSession();
                session.setAttribute(Constants.LOGIN, login);
                response.sendRedirect(Constants.LOGIN_PAGE);
            } else {
                jumpError(Constants.USER_EXISTS, Constants.REGISTER_PAGE, request, response);
            }
        } catch (DAOException e) {
            jumpError(e.getMessage(), Constants.REGISTER_PAGE, request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void jumpError(String cause, String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        req.setAttribute(Constants.ERR_MESSAGE, cause);
        dispatcher.forward(req, resp);
    }
}
