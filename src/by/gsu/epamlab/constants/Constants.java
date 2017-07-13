package by.gsu.epamlab.constants;

public class Constants {
    //DB Constants
    public static final String DB_URL = "jdbc:mysql://localhost/web?useSSL=false";
    public static final String DB_USER = "web";
    public static final String DB_PASSWORD = "web";
    public static final String SQL_DRIVER = "org.gjt.mm.mysql.Driver";
    //DB Queries
    public static final String GET_ALL_TASKS = "SELECT * FROM web.tasks WHERE fixed = 0 AND deleted = 0 AND login = ?";
    public static final String GET_TODAY_TASKS = "SELECT * FROM web.tasks WHERE fixed = 0 AND deleted = 0 AND login = ? AND date <= CURDATE()";
    public static final String GET_TOMORROW_TASKS = "SELECT * FROM web.tasks WHERE fixed = 0 AND deleted = 0 AND login = ? AND date = CURDATE() + 1";
    public static final String GET_SOMEDAY_TASKS = "SELECT * FROM web.tasks WHERE fixed = 0 AND deleted = 0 AND login = ? AND date > CURDATE() + 1";
    public static final String GET_FIXED_TASKS = "SELECT * FROM web.tasks WHERE fixed = 1 AND deleted = 0 AND login = ?";
    public static final String GET_DELETED_TASKS = "SELECT * FROM web.tasks WHERE deleted = 1 AND login = ?";

    public static final String TASKS = "tasks";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String USER = "user";
    public static final String ERR_MESSAGE = "errorMessage";
    public static final String EMPTY = "";
    public static final String FILE_UPLOAD_ROOT = "C:/webApp/";
    public static final String ERR_EMPTY_LOGIN_PASSWORD = "Login or password is empty";
    public static final String ERR_EMPTY_LOGIN = "Login is empty";
    public static final String ERR_LOGIN_OR_PASSWORD_INCORRECT = "Invalid login or password";
    public static final String ERR_CLASS_LOADING_PROBLEM = "Class loading problem";
    //Parameters names
    public static final String USER_EXISTS = "User exists, please use other login";
    //JSP pages
    public static final String MAIN_PAGE = "/main.jsp";
    public static final String REGISTER_PAGE = "/register.jsp";
    public static final String LOGIN_PAGE = "/login.jsp";
    public static final String ADD_PAGE = "/add.jsp";
    public static final String EDIT_PAGE = "/edit.jsp";
    //errors
    public static final String ERR_RESULT_SET_CLOSE = "ResultSet closing problem : ";
    public static final String ERR_STATEMENT_CLOSE = "Statement closing problem : ";
    public static final String ERR_CONNECTION_CLOSE = "Connection closing problem : ";
    public static final String ERR_PREPARED_STATEMENT_CLOSE = "Prepared statement closing problem: ";
    public static final String ERR_DRIVER_NOT_FOUND = "Driver not found: ";
    public static final String ERR_SQL_EXCEPTION = "SQL exception: ";
    public static final String ERR_ENTER_TASK_DATE = "Enter Task Date";
}
