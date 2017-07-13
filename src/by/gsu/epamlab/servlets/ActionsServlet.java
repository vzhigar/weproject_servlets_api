package by.gsu.epamlab.servlets;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JSPConstants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.utils.FilesDeleter;
import by.gsu.epamlab.utils.ParametersConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "actions", urlPatterns = "/actions")
public class ActionsServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String lastPage = (String) session.getAttribute(JSPConstants.LAST_PAGE);
        String uri = JSPConstants.URL_PREFIX + lastPage;
        ITaskDAO iTaskDAO = TaskFactory.getTaskFromFactory();

        final String FIX_PARAMETER = request.getParameter(JSPConstants.REQ_PARAM_FIX);
        final String DELETE_PARAMETER = request.getParameter(JSPConstants.REQ_PARAM_DELETE);
        final String RESTORE_PARAMETER = request.getParameter(JSPConstants.REQ_PARAM_RESTORE);
        final String EMPTY_RECYCLE = request.getParameter(JSPConstants.REQ_PARAM_EMPTY_RECYCLE);
        final String EDIT_PARAMETER = request.getParameter(JSPConstants.REQ_PARAM_EDIT);

        try {
            if (request.getParameterValues(JSPConstants.REQ_PARAM_ID) != null) {
                String[] id = request.getParameterValues(JSPConstants.REQ_PARAM_ID);

                if (FIX_PARAMETER != null) {
                    iTaskDAO.setFixed(id, true);
                    jumpPage(uri, request, response);
                }

                if (DELETE_PARAMETER != null) {
                    if (!JSPConstants.DELETED_TASKS.equals(lastPage)) {
                        iTaskDAO.setDeleted(id, true);
                        jumpPage(uri, request, response);
                    } else {
                        iTaskDAO.deleteTask(id);
                        List<Integer> list = ParametersConverter.convert(id);
                        String login = (String) session.getAttribute(Constants.LOGIN);
                        for (int i : list) {
                            FilesDeleter.deleteFiles(login, i);
                        }
                        jumpPage(uri, request, response);
                    }
                }

                if (RESTORE_PARAMETER != null) {
                    if (JSPConstants.DELETED_TASKS.equals(lastPage)) {
                        iTaskDAO.setDeleted(id, false);
                        jumpPage(uri, request, response);
                    } else {
                        iTaskDAO.setFixed(id, false);
                        jumpPage(uri, request, response);
                    }
                }

            } else {
                if (EMPTY_RECYCLE == null && EDIT_PARAMETER == null) {
                    jump(uri, "you must select task", request, response);
                    return;
                }
            }

            if (EMPTY_RECYCLE != null) {
                String login = (String) session.getAttribute(Constants.LOGIN);
                login = login.trim();
                iTaskDAO.emptyRecycle(login);
                List<Task> list = (List<Task>) session.getAttribute(JSPConstants.REQ_PARAM_DELETED_TASKS);
                for (Task task : list) {
                    int id = task.getId();
                    FilesDeleter.deleteFiles(login, id);
                }
                jumpPage(uri, request, response);
            }
        } catch (DAOException e) {
            jump(uri, e.getMessage(), request, response);
            return;
        }

        if (EDIT_PARAMETER != null) {
            String[] sTaskId = request.getParameterValues(JSPConstants.REQ_PARAM_EDIT);
            int taskId = ParametersConverter.convert(sTaskId).get(0);
            Task task = null;
            try {
                task = iTaskDAO.getTaskById(taskId);
            } catch (DAOException e) {
                jump(JSPConstants.EDIT_PAGE, e.getMessage(), request, response);
                return;
            }
            request.setAttribute(JSPConstants.REQ_PARAM_TASK, task);
            session.setAttribute(JSPConstants.REQ_PARAM_ID, taskId);
            jumpPage(JSPConstants.EDIT_PAGE, request, response);
        }
    }
 }
