import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    // Shove all our table create methods.
    // You'll need jdbc for this.

    public static Connection conn;

    public static void connect() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(
                        "jdbc:sqlite:couurseDatabase.db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(String query) throws SQLException {
        Statement stmt = null;
        connect();
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stmt.close();
    }

    public static ResultSet getResultSet(String query) throws SQLException {
        connect();
        Statement stmt = conn.createStatement();
        ResultSet RS = stmt.executeQuery(query);
        return RS;
    }

    public static void insertData(ArrayList<String> array) throws SQLException {
        connect();
        Statement stmt = conn.createStatement();
        for (String thisStatement : array) {
            stmt.execute(thisStatement);
        }
        stmt.close();
    }

    public static void createTables() {
        createModule();
        createSection();
        createCourse();
    }

    public static void createModule() {
        connect();
        ArrayList<String> insertStatements = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Module "
                    + "(MODULE_ID NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "MODULE_NAME TEXT NOT NULL,"
                    + "MODULE_DESCRIPTION TEXT NOT NULL "
                    + ");";
            stmt.execute(query);

            insertStatements.add("INSERT INTO Module(MODULE_ID, MODULE_NAME, MODULE_DESCRIPTION )"
                    + "VALUES ('1','Programming','It's a cool course')");

            insertData(insertStatements);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void createResource() {
        connect();
        ArrayList<String> insertStatements = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Resource "
                    + "(RESOURCE_ID NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "RESOURCE_TITLE TEXT NOT NULL, "
                    + "RESOURCE_DESCRIPTION TEXT NOT NULL, "
                    + "RESOURCE_DATA TEXT NOT NULL, "
                    + "FOREIGN KEY (SECTION_ID) REFERENCES Section (SECTION_ID) "
                    + ");";

            stmt.execute(query);

            insertStatements.add("INSERT INTO Resource(RESOURCE_ID, RESOURCE_TITLE, RESOURCE_DESCRIPTION, RESOURCE_DATA, SECTION_ID)"
                    + "VALUES ('1','INFS1603','It's a bad course','1','1')");

            insertData(insertStatements);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createSection() {
        connect();
        ArrayList<String> insertStatements = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Section "
                    + "(SECTION_ID NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "SECTION_NAME TEXT NOT NULL, "
                    + "SECTION_DESCRIPTION TEXT NOT NULL, "
                    + "SEQUENCE_NO TEXT NOT NULL, "
                    + "FOREIGN KEY (MODULE_ID) REFERENCES Module (MODULE_ID) "
                    + ");";

            stmt.execute(query);

            insertStatements.add("INSERT INTO Section(SECTION_ID, SECTION_NAME, SECTION_DESCRIPTION, SEQUENCE_NO, MODULE_ID)"
                    + "VALUES ('1','INFS1603','It's a bad course','1','1')");

            insertData(insertStatements);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createCourse() {
        connect();
        ArrayList<String> insertStatements = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Course "
                    + "(COURSE_ID NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "COURSE_NAME TEXT NOT NULL,"
                    + "SCHOOL_NAME TEXT NOT NULL "
                    + ");";
            stmt.execute(query);

            insertStatements.add("INSERT INTO Course(COURSE_ID, COURSE_NAME, SCHOOL_NAME )"
                    + "VALUES ('1','Programming','School Amazing')");

            insertData(insertStatements);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void createCourseHaveMoodules() {
        connect();
        ArrayList<String> insertStatements = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Courses_Have_Modules) "
                    + "(CHM_ID NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "SEQUENCE_NO TEXT NOT NULL,"
                    + "FOREIGN KEY (MODULE_ID) REFERENCES Module (MODULE_ID), "
                    + "FOREIGN KEY (COURSE_ID) REFERENCES Course (COURSE_ID) "
                    + ");";
            stmt.execute(query);

            insertStatements.add("INSERT INTO Course(CHM_ID, SEQUENCE_NO, MODEL_ID, COURSE_ID )"
                    + "VALUES ('1','1','1','1')");

            insertData(insertStatements);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void createModuleLearningOutcomes() {
        connect();
        ArrayList<String> insertStatements = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Module_Learning_Outcomes) "
                    + "(MLO_ID NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "MLO_NAME TEXT NOT NULL,"
                    + "MLO_DESCRIPTION TEXT NOT NULL, "
                    + "SEQUENCE_NO TEXT NOT NULL,"
                    + "FOREIGN KEY (MODULE_ID) REFERENCES Module (MODULE_ID) "
                    + ");";
            stmt.execute(query);

            insertStatements.add("INSERT INTO Module_Learning_Outcomes(MLO_ID, MLO_DESCRIPTION, SEQUENCE_NO, MODULE_ID )"
                    + "VALUES ('1','test','1','1')");

            insertData(insertStatements);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
