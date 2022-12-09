import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","4168263009htc");
        Statement st = con.createStatement();

        //1.Example: Update the number of employees to 9999 if the company name is IBM by using prepared statement
        //1.Step: Create Prepared Statement Query
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";

        //2.Step: Create Prepared Statement Object
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3.Step: Assigning Values by using 'set'
        pst1.setInt(1, 19999);
        pst1.setString(2, "IBM");

        //4.Execute the query
        int  numOfRecordsUpdated = pst1.executeUpdate();
        System.out.println("numOfRecordsUpdated = " + numOfRecordsUpdated);

        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet1= st.executeQuery(sql2);

        while(resultSet1.next()) {
            System.out.println(resultSet1.getInt(1) + "--" + resultSet1.getString(2) +"--" + resultSet1.getInt(3));
        }

        //2.Example: Update the number of employees to 5555 if the company name is GOOGLE by using prepared statement
        pst1.setInt(1, 15555);
        pst1.setString(2, "GOOGLE");

        int numOfRecordsUpdated2 = pst1.executeUpdate();
        System.out.println("numOfRecordsUpdated2 = " + numOfRecordsUpdated2);

        String sql3 = "SELECT * FROM companies";
        ResultSet resultSet2 = st.executeQuery(sql3);
        while(resultSet2.next()) {
            System.out.println(resultSet2.getInt("company_id") + "--" + resultSet2.getString("company") +"--" + resultSet2.getInt("number_of_employees"));
        }

        con.close();
        st.close();
        pst1.close();

    }
}
