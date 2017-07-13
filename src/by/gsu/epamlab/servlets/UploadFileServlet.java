package by.gsu.epamlab.servlets;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JSPConstants;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.interfaces.ITaskDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class UploadFileServlet extends AbstractServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(Constants.LOGIN);
        int id = (int) session.getAttribute(JSPConstants.REQ_PARAM_ID);
        final String DIR_SEPARATOR = "/";
        String lastPage = (String) session.getAttribute(JSPConstants.LAST_PAGE);
        String uri = JSPConstants.URL_PREFIX + lastPage;
        ITaskDAO iTaskDAO = TaskFactory.getTaskFromFactory();
        String fileName = null;
        File uploadedFile = null;

        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = iterator.next();

                    if (!item.isFormField()) {
                        fileName = item.getName();

                        String directory = Constants.FILE_UPLOAD_ROOT + login.trim() + DIR_SEPARATOR + id;
                        File path = new File(directory);
                        if (!path.exists()) {
                            boolean status = path.mkdirs();
                        }

                        uploadedFile = new File(path + DIR_SEPARATOR + fileName);
                        System.out.println(uploadedFile.getAbsolutePath() + " " + id);
                        item.write(uploadedFile);
                    }
                }
                //Task task = iTaskDAO.getTaskById(id);
                //task.setFileName(uploadedFile.getAbsolutePath());
                //System.out.println("task fileName = " + task.getFileName());
                iTaskDAO.setFileName(id, uploadedFile.getAbsolutePath().trim());
                //iTaskDAO.setFileName(id , null);
                response.sendRedirect(uri);

            } catch (Exception e) {
                jump(uri, e.getMessage(), request, response);
            }
        }
    }
}
