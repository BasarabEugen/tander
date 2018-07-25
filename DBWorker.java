package com.tander.database;
/*
 * Created by BASARAB EUGEN on 22.07.2018
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DBWorker {
    private static final String URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=" +
            "false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String user;
    private String password;

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void insertData(int number) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, user, password);
            cleanData(connection);
            String query = "insert into tabtest(field) values (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            long startTime = System.currentTimeMillis();
            for (int i = 1; i <= number; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.execute();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Inserted " + number + " of records in " + (endTime - startTime) / 1000 + " seconds");

        } catch (SQLException e) {
            System.out.println("DB error");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Integer> readData() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, user, password);
            String query = "SELECT FIELD FROM test.tabtest";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            List<Integer> numbers = new ArrayList<Integer>();
            while (rs.next()) {
                numbers.add(rs.getInt("FIELD"));
            }
            return numbers;

        } catch (SQLException e) {
            System.out.println("DB error");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ArrayList<Integer>();
    }

    private void cleanData(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("delete from tabtest");
    }
}
