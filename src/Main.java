
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final String dbURL = "jdbc:mysql://localhost:3306/java24";
        final String user = "root";
        final String password = "1234";
        char again = 'y';
        Scanner scanner = new Scanner(System.in);

        try(Connection conn = DriverManager.getConnection(dbURL,user,password)) {
            System.out.println("Connected");

            while (again == 'y'){

                System.out.println("What you want to do?");
                System.out.println("r - read data");
                System.out.println("i - insert data");
                System.out.println("d - delete data");
                char action = scanner.nextLine().charAt(0);

                if (action == 'r'){
                    readData(conn);
                } else if (action == 'i'){
                    System.out.println("Enter username");
                    String userName = scanner.nextLine();

                    System.out.println("Enter password");
                    String pswd = scanner.nextLine();

                    System.out.println("Enter full name");
                    String fullName = scanner.nextLine();

                    System.out.println("Enter email");
                    String email = scanner.nextLine();

                    insertData(conn, userName, pswd, fullName, email);
                } else if (action == 'd'){
                    System.out.println("Enter username that you want to delete");
                    String userName = scanner.nextLine();

                    deleteData(conn, userName);
                }

                System.out.println("Do you want to do something more? y/n");
                again = scanner.nextLine().charAt(0);

                
            }

        } catch (SQLException e){
            System.out.println("Something went wrong");
        }

    }


    public static void readData (Connection conn) throws SQLException {
        String sql = "SELECT * FROM users";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){

            int userID = resultSet.getInt(1);
            String userName = resultSet.getString(2);
            String pswd = resultSet.getString(3);
            String fullname = resultSet.getString("fullname");
            String email = resultSet.getString("email");

            String output = "User info: \n\t id: %d \n\t username: %s \n\t password: %s " +
                            "\n\t fullname: %s \n\t email: %s";

            System.out.println(String.format(output, userID, userName, pswd, fullname, email));

        }

    }

    public static void insertData (Connection conn, String userName, String pswd, String fullName, String email) throws SQLException{

        String sql = "INSERT INTO users (username, password, fullname, email) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, pswd);
        preparedStatement.setString(3, fullName);
        preparedStatement.setString(4, email);

        int rowInserted = preparedStatement.executeUpdate();

        if(rowInserted > 0){
            System.out.println("A new user was inserted successfully");
        }else{
            System.out.println("Something went wrong");
        }

    }

    public static void deleteData (Connection conn, String userName) throws SQLException {
        String sql = "DELETE FROM users WHERE username = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, userName);

        int rowDeleted = preparedStatement.executeUpdate();
        if(rowDeleted > 0){
            System.out.println("A user was deleted successfully");
        }else {
            System.out.println("Something went wrong");
        }
    }

}


