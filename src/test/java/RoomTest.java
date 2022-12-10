import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertFalse;

public class RoomTest {
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
    @Test
    public void roomTest(){
        // Connect to the database
        JdbcUtils.connectToDatabase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987" );
        JdbcUtils.createStatement();


        //User sends the query to get the names of created_by column from "room" table
        List<Object> created_bys = JdbcUtils.getColumnList("created_by", "room");
        System.out.println("created_bys " + created_bys);

        //Assert that there are some rooms created by "john doe".
        //1st Way: by using lambda
        long num_of_rooms = created_bys.stream().filter(t-> t.equals("john doe")).count();
        System.out.println("num_of_rooms " + num_of_rooms);

        assertFalse(num_of_rooms>0);

        //2nd Way:by using for Each loop
        int counter = 0;
        for(Object w : created_bys){
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
