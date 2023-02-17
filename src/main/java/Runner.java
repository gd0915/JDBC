import java.sql.Statement;

public class Runner {

    public static void main(String[] args) {
        //1. Step: Registration to the driver
        //2. Step: Create connection with database
        JdbcUtils.connectToDatabase("localhost", "postgres", "postgres", "4168263009htc");

        //3. Step: Create statement
        JdbcUtils.createStatement();

        //4. Step: Execute the query
        //JdbcUtils.execute("CREATE TABLE workers(worker_id VARCHAR(50), worker_name VARCHAR(20), worker_salary INT)");
        //JdbcUtils.createTable("workers", "worker_id VARCHAR(50)", "worker_name VARCHAR(20)", "worker_salary INT" );

        //JdbcUtils.createTable("Students", "name Varchar(20)", "id int", "address VARCHAR(50)");
        //JdbcUtils.createTable("Students","name VARCHAR(20)","id INT","address VARCHAR(50)","tel BIGINT");

        //JdbcUtils.insertDataIntoTable("Students","name 'John'");
        //JdbcUtils.insertDataIntoTable("Students","name 'Mark'","id 123","tel 1234567890","address 'Ankara'");

        //JdbcUtils.dropTable("workers");
        //JdbcUtils.dropTable("Students");

        //5. Step: Close the connection and statement
        JdbcUtils.closeConnectionAndStatement();
    }
}
