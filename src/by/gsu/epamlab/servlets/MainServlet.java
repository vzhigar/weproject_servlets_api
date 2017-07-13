package by.gsu.epamlab.servlets;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.comparators.TaskComparator;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JSPConstants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "main", urlPatterns = "/main")
public class MainServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(Constants.LOGIN);
        login = login.trim();
        ITaskDAO iTaskDAO = TaskFactory.getTaskFromFactory();
        String URI = request.getRequestURI();
        String requestLastPage = (String) request.getAttribute(JSPConstants.LAST_PAGE);

        try {
            if (JSPConstants.REQ_URL_TODAY.equals(URI) || JSPConstants.TODAY_TASKS.equals(requestLastPage)) {
                session.setAttribute(JSPConstants.LAST_PAGE, JSPConstants.TODAY_TASKS);
                List<Task> today = iTaskDAO.getTasks(login, Constants.GET_TODAY_TASKS);
                request.setAttribute(Constants.TASKS, today);
                jumpPage(Constants.MAIN_PAGE, request, response);
            }

            if (JSPConstants.REQ_URL_TOMORROW.equals(URI) || JSPConstants.TOMORROW_TASKS.equals(requestLastPage)) {
                session.setAttribute(JSPConstants.LAST_PAGE, JSPConstants.TOMORROW_TASKS);
                List<Task> tomorrow = iTaskDAO.getTasks(login, Constants.GET_TOMORROW_TASKS);
                request.setAttribute(Constants.TASKS, tomorrow);
                jumpPage(Constants.MAIN_PAGE, request, response);
            }

            if (JSPConstants.REQ_URL_SOMEDAY.equals(URI) || JSPConstants.SOMEDAY_TASKS.equals(requestLastPage)) {
                session.setAttribute(JSPConstants.LAST_PAGE, JSPConstants.SOMEDAY_TASKS);
                List<Task> someday = iTaskDAO.getTasks(login, Constants.GET_SOMEDAY_TASKS);
                request.setAttribute(Constants.TASKS, someday);
                jumpPage(Constants.MAIN_PAGE, request, response);
            }

            if (JSPConstants.REQ_URL_FIXED.equals(URI) || JSPConstants.FIXED_TASKS.equals(requestLastPage)) {
                session.setAttribute(JSPConstants.LAST_PAGE, JSPConstants.FIXED_TASKS);
                List<Task> fixed = iTaskDAO.getTasks(login, Constants.GET_FIXED_TASKS);
                fixed.sort(new TaskComparator());
                request.setAttribute(Constants.TASKS, fixed);
                jumpPage(Constants.MAIN_PAGE, request, response);
            }

            if (JSPConstants.REQ_URL_RECYCLE.equals(URI) || JSPConstants.DELETED_TASKS.equals(requestLastPage)) {
                session.setAttribute(JSPConstants.LAST_PAGE, JSPConstants.DELETED_TASKS);
                List<Task> deleted = iTaskDAO.getTasks(login, Constants.GET_DELETED_TASKS);
                session.setAttribute(JSPConstants.REQ_PARAM_DELETED_TASKS, deleted);
                deleted.sort(new TaskComparator());
                request.setAttribute(Constants.TASKS, deleted);
                jumpPage(Constants.MAIN_PAGE, request, response);
            }

            else {
                List<Task> list = iTaskDAO.getTasks(login, Constants.GET_ALL_TASKS);
                list.sort(new TaskComparator());
                request.setAttribute(Constants.TASKS, list);
                jumpPage(Constants.MAIN_PAGE, request, response);
            }
        } catch (DAOException e) {
            jump(URI, e.getMessage(), request, response);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
