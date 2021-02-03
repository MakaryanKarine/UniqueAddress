import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Settings {
    // user, pass and hostURl fields are used to access DB
    // logger is used to log programm actions
    private static final String user = "root";
    private static final String pass = "15L76K34h0m!45GpoN";
    private static final String hostURL = "jdbc:mysql://localhost:3306/addresses";
    static final Logger logger = LogManager.getLogger(Solution.class);

    // getters and setters for member variables
    public static String getPass() { return pass; }
    public static String getUser() { return user; }
    public static String getHostURL() { return hostURL; }

}
