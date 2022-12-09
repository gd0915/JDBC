import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Query01_Practice {
    /*
   Given
     User connects to the database
     (Host name: medunna.com, Database name: medunna_db, Usename: medunna_user, Password: medunna_pass_987))

   When
     User sends the query to get the names of created_by column from "room" table

   Then
     Verify that there are some rooms created by "john doe".

   And
     User closes the connection
  */
    public static void main(String[] args) throws SQLException {
        // Connect to the database
        JdbcUtils.connectToDatabase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987" );

        //Create statement
        JdbcUtils.createStatement();

        // Send the query to get the names of created_by column from "room" table
        // Print the names of created_by column from "room" table

       ResultSet resultSet1 = JdbcUtils.executeQuery("SELECT created_by FROM room");

       JdbcUtils.printQuery(resultSet1,"created_by");

        System.out.println("==============");
       //Verify that there are some rooms created by "john_doe"
       ResultSet resultSet2 = JdbcUtils.executeQuery("SELECT created_by FROM room WHERE created_by = 'john doe'");
       JdbcUtils.printQuery(resultSet2, "created_by");

       ResultSet resultSet3 = JdbcUtils.executeQuery("SELECT COUNT(created_by) as num_of_rooms FROM room WHERE created_by = 'john doe'");
       JdbcUtils.printQuery(resultSet3, "num_of_rooms");

       List<Object> list = JdbcUtils.getColumnList("created_by", "room");
       //System.out.println(list);
       int counter = 0;
        for(Object w : list){
            if(w.equals("john doe")){
                counter++;
            }
        }
        System.out.println("counter = " + counter);

        assertFalse(counter>0);

        //close connection
       JdbcUtils.closeConnectionAndStatement();




    }
}
