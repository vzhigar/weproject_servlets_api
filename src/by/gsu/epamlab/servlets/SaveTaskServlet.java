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

@WebServlet (name = "save", urlPatterns = "/save")
public class SaveTaskServlet extends AbstractServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute(JSPConstants.REQ_PARAM_ID);
        String name = request.getParameter(JSPConstants.REQ_PARAM_NAME);
        String sDate = request.getParameter(JSPConstants.REQ_PARAM_DATE);

        if (sDate == null || Constants.EMPTY.equals(sDate)) {
            jump(Constants.EDIT_PAGE, Constants.ERR_ENTER_TASK_DATE, request, response);
            return;
        }

        Date date = Date.valueOf(sDate);
        String description = request.getParameter(JSPConstants.REQ_PARAM_DESCRIPTION);
        ITaskDAO iTaskDAO = TaskFactory.getTaskFromFactory();

        try {
            iTaskDAO.updateTask(id, name, date, description);
        } catch (DAOException e) {
            jump(Constants.EDIT_PAGE, e.getMessage(), request, response);
            return;
        }

        String lastPage = (String) session.getAttribute(JSPConstants.LAST_PAGE);
        String uri = JSPConstants.URL_PREFIX + lastPage;
        response.sendRedirect(uri);
    }
}
