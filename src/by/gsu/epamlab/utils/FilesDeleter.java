package by.gsu.epamlab.utils;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JSPConstants;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FilesDeleter {
    public static void deleteFiles(String login, int id) {
        try {
            FileUtils.deleteDirectory(new File(Constants.FILE_UPLOAD_ROOT + login + JSPConstants.URL_PREFIX + id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
