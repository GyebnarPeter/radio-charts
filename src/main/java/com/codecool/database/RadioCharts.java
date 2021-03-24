package com.codecool.database;

import java.sql.*;

public class RadioCharts {

    private final String URL;
    private final String USER;
    private final String PASSWORD;

    public RadioCharts(String url, String user, String password) {
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }

    public String getMostPlayedSong() {
        String sql = "SELECT song FROM music_broadcast GROUP BY song ORDER BY SUM(times_aired) DESC LIMIT 1;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    public String getMostActiveArtist() {
        String sql = "SELECT artist FROM music_broadcast GROUP BY artist ORDER BY COUNT(DISTINCT song) DESC LIMIT 1;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

}
