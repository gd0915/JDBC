import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {
    private static Connection connection;
    private static Statement statement;

    //1. Step: Registration to the driver
    //2. Step: Create connection with database
    public static Connection connectToDatabase(String hostName,String databaseName,String username,String password) {

        try {
            Class.forName("org.postgresql.Driver"); //postgresql==> Driver name
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+databaseName, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Connection is success!");
        return connection;
    }


    //3. Step: Create statement
    public static Statement createStatement() {

        try {
            statement =connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Statement created!");
        return statement;
    }

    //4. Step: Execute the query
    // This method when we use it with DDL(Create, Alter, Delete operations) will return false everytime
    // When we use it with DQL(Select - read the data) it will return true when we get the data; otherwise it will return false
    public static boolean execute(String query){
        boolean isExecute;

        try {
            isExecute = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Query Executed");
        return isExecute;
    }

    //5. Step: Close the connection and statement
    public static void closeConnectionAndStatement() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (connection.isClosed() && statement.isClosed()) {
                System.out.println("Connection and statement closed!");
            } else {
                System.out.println("Connection and statement not closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //3. Example: Drop the table
    public static void dropTable(String tableName){

        try {
            statement.execute("DROP TABLE "+tableName);
            System.out.println("Table "+tableName+" dropped!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //The method to create table
    //columnName_DataType => This part is an array, so we can use for each loop
    public static void createTable(String tableName,String... columnName_DataType){
        StringBuilder columnName_DataTypeString = new StringBuilder("");
        for(String w:columnName_DataType){
            columnName_DataTypeString.append(w).append(",");
        }

        columnName_DataTypeString.deleteCharAt(columnName_DataTypeString.lastIndexOf(","));

        try {
            statement.execute("CREATE TABLE "+tableName+"("+columnName_DataTypeString+" )");
            System.out.println("Table " + tableName + " is created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //The method to insert data into table
    public static void insertDataIntoTable(String tableName,String... columnName_Value){

        StringBuilder columnNames = new StringBuilder("");
        StringBuilder values = new StringBuilder("");

        for(String w:columnName_Value) {
            int firstSpace = w.indexOf(" ");

            columnNames.append(w.substring(0, firstSpace)).append(",");  //1
            values.append(w.substring(firstSpace)).append(",");  //2
        }

//        for(String w:columnName_Value){
//            columnNames.append(w.split(" ")[0]).append(",");// split() method split the column names from each other and append() method put comma at the end
//            values.append(w.split(" ")[1]).append(",");//split() method split the values from each other and append() method put comma at the end
//        }//This creation does not work properly with the values that consists of multiple words

        //"INSERT INTO "+tableName+"(id, name, address) VALUES(123, 'john', 'new york')"
        columnNames.deleteCharAt(columnNames.lastIndexOf(","));
        values.deleteCharAt(values.lastIndexOf(","));

        String query = "INSERT INTO "+tableName+"("+columnNames+") VALUES("+values+")";

        try {
            statement.execute(query);
            System.out.println("Data inserted into table "+tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //The method to get column data into a list
    public static List<Object> getColumnList(String columnName,String tableName){
        ResultSet resultSet;
        List<Object> columnData = new ArrayList<>();

        String query = "SELECT "+columnName+" FROM "+tableName;

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                columnData.add(resultSet.getObject(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return columnData;
    }

    public static ResultSet executeQuery(String query){
        ResultSet resultSet;

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Query Executed");
        return resultSet;
    }

    public static void printQuery(ResultSet resultSet , String column_name)  {

        while(true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println(resultSet.getObject(column_name));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }



}

