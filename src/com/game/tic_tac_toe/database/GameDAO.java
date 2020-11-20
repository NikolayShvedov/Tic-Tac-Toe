package com.game.tic_tac_toe.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class GameDAO {
    private String URL = "jdbc:mysql://localhost:3306/tic_tac_toe"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";
    private String USERNAME = "root";
    private String PASSWORD = "root";

    public int savingGameResults(String currentPlayer) throws ClassNotFoundException {

        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        String time_the_winner_game = String.valueOf(time);

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO game (game_date, user_winner_name) VALUES (?,?);")) {

            preparedStatement.setString(1, time_the_winner_game);
            preparedStatement.setString(2, currentPlayer);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
