package inventory.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class DataBase {

    private final String DB_URL = "jdbc:mysql://localhost/inventory";
    private final String USER = "root";
    private final String PASS = "Zerome@3677@";
    private static DataBase dataBase = null;
    private DataBase() {
    }

    public static DataBase getInstance(){
        if(dataBase == null) dataBase = new DataBase();
        return dataBase;
    }
    public boolean inject(String sql) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement statement = connection.prepareStatement(sql);
            int affected = statement.executeUpdate();
            if (affected > 0) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }
    public List<HashMap<String, Object>> eject(String sql) {
        List<HashMap<String, Object>> data;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            data = dataList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return data;
    }
    
    private  List<HashMap<String, Object>> dataList(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<HashMap<String, Object>> records = new ArrayList<>();
        int column = metaData.getColumnCount();
        while (resultSet.next()) {
            HashMap<String, Object> data = new HashMap<>();
            for (int i = 1; i <= column; i++) {
                data.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            records.add(data);
        }
        return records;
    }
}
