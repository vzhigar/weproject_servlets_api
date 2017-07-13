package by.gsu.epamlab.servlets;

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
import java.sql.Date;
import java.time.LocalDate;

@WebServlet (name = "addTask", urlPatterns = "/addTask")
public class AddTaskServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String)session.getAttribute(Constants.LOGIN);
        login = login.trim();
        String name = request.getParameter(JSPConstants.REQ_PARAM_NAME);
        String description = request.getParameter(JSPConstants.REQ_PARAM_DESCRIPTION);
        String lastPage = (String)session.getAttribute(JSPConstants.LAST_PAGE);
        Date date = null;
        String uri = JSPConstants.URL_PREFIX + lastPage;

        if(JSPConstants.TODAY_TASKS.equals(lastPage)) {
            date = new Date(System.currentTimeMillis());
        } else if (JSPConstants.TOMORROW_TASKS.equals(lastPage)) {
            Date tmpDate = new Date(System.currentTimeMillis());
            LocalDate tomorrow = tmpDate.toLocalDate().plusDays(1);
            date = Date.valueOf(tomorrow);
        } else {
            String sDate = request.getParameter(JSPConstants.REQ_PARAM_DATE);
            if (sDate != null && !Constants.EMPTY.equals(sDate)) {
                date = Date.valueOf(sDate);
            } else {
                jump(Constants.ADD_PAGE, Constants.ERR_ENTER_TASK_DATE, request, response);
                return;
            }
        }
        ITaskDAO iTaskDAO = TaskFactory.getTaskFromFactory();
        try {
            iTaskDAO.addTask(login, name, date, description);
        } catch (DAOException e) {
            jump(uri, e.getMessage(), request, response);
            return;
        }
        response.sendRedirect(uri);
    }
}
