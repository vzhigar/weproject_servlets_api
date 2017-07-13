package by.gsu.epamlab.servlets;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JSPConstants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.factories.UserFactory;
import by.gsu.epamlab.interfaces.IUserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.gsu.epamlab.constants.Constants.*;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        if (login == null || password == null) {
            jumpError(ERR_EMPTY_LOGIN_PASSWORD, request, response);
            return;
        }

        login = login.trim();
        if (EMPTY.equals(login)) {
            jumpError(ERR_EMPTY_LOGIN, request, response);
            return;
        }

        IUserDAO userDAO = UserFactory.getClassFromFactory();
        try {
            User user = userDAO.getUser(login, password);
            if (user == null) {
                jumpError(Constants.ERR_LOGIN_OR_PASSWORD_INCORRECT, request, response);
            }
            session.setAttribute(Constants.LOGIN, login);
            session.setAttribute(USER, user);
            jumpPage(JSPConstants.MAIN_SERVLET, request, response);
        } catch (DAOException e) {
            jumpError(e.getMessage(), request, response);
        }
    }
}
