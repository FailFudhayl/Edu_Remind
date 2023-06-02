package eduremind.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import eduremind.config.ConnectDB;

public class ControllerDB extends ConnectDB {
    public static int LoginValidation(String userName, String pass) {
        connection();
        Integer id = null;
        query = "SELECT id FROM UserTB WHERE name=? AND pass=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, pass);
            try (ResultSet login = preparedStatement.executeQuery()) {
                while (login.next()) {
                    id = login.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static boolean insrtRegis(String username, String pass) {
        connection();
        query = "INSERT INTO UserTb (name, pass) VALUES (?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pass);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<String> getAllTugas(Integer userid) {
        connection();
        ArrayList<String> tugasList = new ArrayList<>();
        query = "SELECT tugas FROM tugasTB WHERE userid=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tugasList.add(resultSet.getString("tugas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return tugasList;

        }
        return tugasList;
    }

    public static ArrayList<String> getAllCatatan(Integer userid) {
        connection();
        ArrayList<String> catatanList = new ArrayList<>();
        query = "SELECT catatan FROM catatanTB WHERE userid=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                catatanList.add(resultSet.getString("catatan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return catatanList;

        }
        return catatanList;
    }

    public static void insertTugas(Integer userid, String tugas) {
        connection();
        query = "INSERT INTO tugasTB (userid, tugas) VALUES (?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userid);
            preparedStatement.setString(2, tugas);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//     public static void insertCatatan(Integer userid, String catatan) {
//         connection();
//         query = "INSERT INTO catatanTB (userid, catatan) VALUES (?,?)";
//         try {
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setInt(1, userid);
//             preparedStatement.setString(2, catatan);
//             preparedStatement.executeUpdate();
//             preparedStatement.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void deleteTugas(Integer userid, String tugas) {
//         connection();
//         query = "DELETE FROM tugasTB WHERE userid=? AND tugas=?";
//         try {
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setInt(1, userid);
//             preparedStatement.setString(2, tugas);
//             preparedStatement.executeUpdate();
//             preparedStatement.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void deleteCatatan(Integer userid, String catatan) {
//         connection();
//         query = "DELETE FROM catatanTB WHERE userid=? AND catatan=?";
//         try {
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setInt(1, userid);
//             preparedStatement.setString(2, catatan);
//             preparedStatement.executeUpdate();
//             preparedStatement.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void updateTugas(Integer userid, String tugas) {
//         connection();
//         query = "UPDATE tugasTB SET tugas=? WHERE userid=?";
//         try {
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setInt(1, userid);
//             preparedStatement.setString(2, tugas);
//             preparedStatement.executeUpdate();
//             preparedStatement.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void updateCatatan(Integer userid, String catatan) {
//         connection();
//         query = "UPDATE catatanTB SET catatan=? WHERE userid=?";
//         try {
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setInt(1, userid);
//             preparedStatement.setString(2, catatan);
//             preparedStatement.executeUpdate();
//             preparedStatement.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
}
