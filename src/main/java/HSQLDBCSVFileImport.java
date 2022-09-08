import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HSQLDBCSVFileImport {
    private Connection connection;

    /* PLEASE DO NOT MODIFY
    public static void main(String... args) {
        HSQLDBCSVFileImport hsqldbcsvFileImport = new HSQLDBCSVFileImport();
        hsqldbcsvFileImport.init();
        hsqldbcsvFileImport.importCSVFile(Configuration.INSTANCE.dataPath + "records.csv");
        hsqldbcsvFileImport.shutdown();
    }*/

    public void startup() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String driverName = "jdbc:hsqldb:";
            String databaseURL = driverName + Configuration.INSTANCE.dataPath + "records.db";
            String username = "sa";
            String password = "";
            connection = DriverManager.getConnection(databaseURL, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void update(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlStatement);
            if (result == -1) {
                System.out.println("error executing " + sqlStatement);
            }
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void dropTable() {
        System.out.println("--- dropTable");

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("DROP TABLE data");
        System.out.println("sqlStringBuilder : " + sqlStringBuilder);

        update(sqlStringBuilder.toString());
    }

    public void createTable() {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE data ").append(" ( ");
        sqlStringBuilder.append("id BIGINT NOT NULL").append(",");
        sqlStringBuilder.append("weekDay INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("ticketType VARCHAR(1) NOT NULL").append(",");
        sqlStringBuilder.append("source INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("destination INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("isOffPeak VARCHAR(5) NOT NULL").append(",");
        sqlStringBuilder.append("numberOfRegisteredChildren INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(" )");
        update(sqlStringBuilder.toString());
    }

    public void init() {
        startup();
        dropTable();
        createTable();
    }

    public String buildSQLStatement(long id, int weekDay, String ticketType, int source, int destination, String isOffPeak, int numberOfRegisteredChildren) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO data (id,weekDay,ticketType,source,destination,isOffPeak,numberOfRegisteredChildren) VALUES (");
        stringBuilder.append(id).append(",");
        stringBuilder.append(weekDay).append(",");
        stringBuilder.append("'").append(ticketType).append("'").append(",");
        stringBuilder.append(source).append(",");
        stringBuilder.append(destination).append(",");
        stringBuilder.append("'").append(isOffPeak).append("'").append(",");
        stringBuilder.append(numberOfRegisteredChildren);
        stringBuilder.append(")");
        //System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public void insert(long id, int weekDay, String ticketType, int source, int destination, String isOffPeak, int numberOfRegisteredChildren) {
        update(buildSQLStatement(id, weekDay, ticketType, source, destination, isOffPeak, numberOfRegisteredChildren));
    }

    public void importCSVFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(";");
                insert(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), strings[2],
                        Integer.parseInt(strings[3]), Integer.parseInt(strings[4]), strings[5],
                        Integer.parseInt(strings[6]));
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void shutdown() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
}