package TicTacToe;

import java.sql.*;

public class DB {

   final String dbURL = "jdbc:mysql://localhost:3306/java24";
   final String user = "root";
   final String password = "1234";

   
   public void readResults() {

      Statement stmt = null; 
      try (Connection conn = DriverManager.getConnection(dbURL,user,password)){
         
         // STEP 3: Execute a query 
         System.out.println("Connected database successfully..."); 
         stmt = conn.createStatement(); 
         String sql = "SELECT * FROM Tictactoe";
         ResultSet rs = stmt.executeQuery(sql);

         System.out.println();
         System.out.println("Result history");
         
         // STEP 4: Extract data from result set 
         while(rs.next()) { 
            // Retrieve by column name
            String firstPlayer = rs.getString("firstPlayer");
            String secondPlayer = rs.getString("secondPlayer");
            String winner = rs.getString("winner");

            
            // Display values
            System.out.print("Player 1: " + firstPlayer + "\t");
            System.out.print("Player 2: " + secondPlayer + "\t Winner: " + winner);
            System.out.println();

         } 
         // STEP 5: Clean-up environment 
         rs.close(); 
      }  catch(Exception e) {
         // Handle errors for Class.forName 
         e.printStackTrace();
      } // end try

   };

   public void insertResult(String playerOne, String playerTwo, String winner) throws SQLException {
      Statement stmt = null;
      try {
         Connection conn = DriverManager.getConnection(dbURL,user,password);
         // STEP 3: Execute a query
         stmt = conn.createStatement();
         String sql = "INSERT INTO Tictactoe (firstPlayer, secondPlayer, winner) VALUES('"+ playerOne+"', '"+ playerTwo +"','" +winner + "');";

         stmt.executeUpdate(sql);
         System.out.println("Inserted records into the table...");

      } catch(Exception e) {
         // Handle errors for Class.forName
         e.printStackTrace();
      } // end try

   }
}