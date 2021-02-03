import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Console {
    // readFilePath field is used to hold IpV4 Address file
    private static String readFilePath;
    // UniqueAdrsCount field is used to hold the count of unique addresses
    private static Long UniqueAdrsCount;

    // getters and setters for member variables
    public static Long getUniqueAdrsCount() {
        return UniqueAdrsCount;
    }

    public static void setUniqueAdrsCount(Long uniqueAdrsCount) {
        UniqueAdrsCount = uniqueAdrsCount;
    }

    public static String getReadFilePath() {
        return readFilePath;
    }

    public static void setReadFilePath(String readFilePath) {
        Console.readFilePath = readFilePath;
    }

    // fromFileToDB function is used to read from a file line by line and insert into addresses DB
    static void fromFileToDB(String filePath) throws IOException, SQLException {
        Settings.logger.info("function \"fromFileToDB\" runs . . .");
        // thisLine field is used for temproary keep of each line
        String thisLine = null;

        // open input stream for reading purpose.
        FileReader fr = new FileReader(readFilePath);
        BufferedReader br = new BufferedReader(fr, 16384);

        //open connection for accessing database
        String sqlUpdate = "INSERT INTO addresses.ipv4addresses(IP) VALUES(?)";
        Connection con = DriverManager.getConnection(Settings.getHostURL(),Settings.getUser(), Settings.getPass());
        PreparedStatement pstmt = con.prepareStatement(sqlUpdate);

        while ((thisLine = br.readLine()) != null) {
            pstmt.setString(1, thisLine);
            pstmt.executeUpdate();
        }
        con.close();
        br.close();
        Settings.logger.info("function \"fromFileToDB\" is successfuly finished . . .");
    }

    // countUniqueItem function is used to count unique Ipv4 addresses
    static Long countUniqueItem() throws SQLException {
        Settings.logger.info("function \"countUniqueItem\" runs  . . .");
        String sqlQuery = "select count(distinct(IP)) as IP from addresses.ipv4addresses";
        Connection con = DriverManager.getConnection(Settings.getHostURL(),Settings.getUser(), Settings.getPass());
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        Long uniqueAdrsCount = Long.valueOf(0);
        while(rs.next()) {
            uniqueAdrsCount = rs.getLong("IP");
        }
        con.close();
        Settings.logger.info("function \"countUniqueItem\" is  successfuly finished . . .");
        return uniqueAdrsCount;
    }

    // function cleanUpDB is used to delete all records in a table
    static void cleanUpDBTable() throws SQLException {
        Settings.logger.info("function \"cleanUpDB\" runs  . . .");
        String sqlQuery = "truncate addresses.ipv4addresses";
        Connection con = DriverManager.getConnection(Settings.getHostURL(),Settings.getUser(), Settings.getPass());
        Statement stmt = con.createStatement();
        stmt.execute(sqlQuery);

        con.close();
        Settings.logger.info("function \"cleanUpDB\" is  successfuly finished . . .table truncated.");
    }

    public static void console() {
        Scanner sc = new Scanner(System.in);
        Settings.logger.info("-----------Program started . . . function \"console\" runs . . .-----------");
        System.out.println("Enter IpV4 address file path: ");
        readFilePath = sc.nextLine();
        if(readFilePath.isEmpty()) {
            System.out.println("Nothing was entered. Please try again");
            console();
        }else {
            Settings.logger.info("Enter IpV4 addresses file path: " + readFilePath);
            System.out.println("Please wait a little . . . program makes calculations.");
            try {
                fromFileToDB(readFilePath);
                setUniqueAdrsCount(countUniqueItem());
                Settings.logger.info("Number of unique IpV4 addresses in the file: " + getUniqueAdrsCount());
                System.out.println("Number of unique IpV4 addresses in the file: " + getUniqueAdrsCount());
                cleanUpDBTable();

            } catch (IOException e) {
                Settings.logger.error(e.getMessage(), e);
                e.printStackTrace();
            } catch (SQLException e) {
                Settings.logger.error(e.getMessage(), e);
                e.printStackTrace();
            }
            Settings.logger.info("-----------Function \"console\" is finished . . . Program successfully completed.----------- ");
        }
    }
}
