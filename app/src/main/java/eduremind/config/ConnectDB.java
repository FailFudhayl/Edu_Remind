// package eduremind.config;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class ConnectDB {
//     private static final String DB_URL = "jdbc:sqlite:EduRemindDB.db";

//     protected static Connection connection;
//     protected static PreparedStatement preparedStatement;
//     protected static ResultSet resultSet;
//     protected static String query;

//     protected static void connection() {
//         try {
//             connection = DriverManager.getConnection(DB_URL);
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
// }
