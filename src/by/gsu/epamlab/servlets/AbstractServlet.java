package by.gsu.epamlab.servlets;

import by.gsu.epamlab.constants.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.constants.Constants.EMPTY;
import static by.gsu.epamlab.constants.Constants.ERR_MESSAGE;

@WebServlet(name = "AbstractServlet")
public class AbstractServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void jump(String uri, String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ERR_MESSAGE, message);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(uri);
        dispatcher.forward(request, response);
    }

    protected void jumpPage(String uri, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        jump(uri, EMPTY, request, response);
    }

    protected void jumpError(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        jump(Constants.LOGIN_PAGE, message, request, response);
    }

}
