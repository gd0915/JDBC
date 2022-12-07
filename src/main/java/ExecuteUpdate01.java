import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","4168263009htc");
        Statement st = con.createStatement();

        //1.Example: Update the number of employees to 16000 if the number of employees is less than the average number of employees
        String sql1 = "UPDATE companies SET number_of_employees = 16000 WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies)";
        int numOfRecordsUpdated = st.executeUpdate(sql1);
        System.out.println("numOfRecordsUpdated = " + numOfRecordsUpdated);

        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet1 = st.executeQuery(sql2);
        while(resultSet1.next()){
            System.out.println(resultSet1.getInt("company_id") + "--" + resultSet1.getString("company") + "--" + resultSet1.getInt("number_of_employees"));
        }

        con.close();
        st.close();


    }
}
