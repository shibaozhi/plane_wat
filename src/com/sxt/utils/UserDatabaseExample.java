package com.sxt.utils;
import com.sxt.GameWin;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserDatabaseExample {
    //用户注册
    public static int registerUser(Connection connection, String username, String password) throws SQLException {
        String insertQuery = "INSERT INTO data_user (username, password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertQuery);
        statement.setString(1, username);
        statement.setString(2, password);
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected>0){
            GameWin.username = username;
            System.out.println("账号注册成功");
        }
        return 1;//注册成功
    }
    //更新得分
    public static void updateRecord(Connection connection, String username, int score) throws SQLException {
        String selectQuery = "SELECT score FROM data_user WHERE username = ?";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, username);
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            int currentScore = resultSet.getInt("score");
            if (score > currentScore) {
                String updateQuery = "UPDATE data_user SET score = ? WHERE username = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, score);
                updateStatement.setString(2, username);
                int rowsAffected = updateStatement.executeUpdate();
                System.out.println(rowsAffected + " 行记录已更新。");
            } else {
//                System.out.println("新的得分不大于数据库已有的得分，无需更新。");
            }
        }
    }
    //查询用户名是否存在
    public static int checkUsernameExists(Connection connection, String username) throws SQLException {
        String selectQuery = "SELECT * FROM data_user WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return 1;
        } else {
            System.out.println("用户名不存在！");
            return 0;
        }
    }
    //查询用户是否存在
    public static int validateUser(Connection connection, String username, String password) throws SQLException {
        String selectQuery = "SELECT * FROM data_user WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String foundUsername = resultSet.getString("username");
            GameWin.username = foundUsername;
            System.out.println("登录成功！用户名：" + foundUsername);
            return 1;
        } else {
            System.out.println("用户名或密码错误！");
        }
        return 0;
    }
    //获取得分,按从大到小进行排序
    public static List<Integer> getScoreData(Connection connection) throws SQLException {
        List<Integer> scoreDataList = new ArrayList<>();

        String selectQuery = "SELECT score FROM data_user";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int scoreData = resultSet.getInt("score");
            scoreDataList.add(scoreData);
        }

        // 对得分数据进行从大到小排序
        Collections.sort(scoreDataList, Collections.reverseOrder());

        // 只获取前六位数据
        if (scoreDataList.size() > 6) {
            scoreDataList = scoreDataList.subList(0, 6);
        }

        return scoreDataList;
    }
    //获取用户名
    public static List<String> getUsernameData(Connection connection) throws SQLException {
        List<String> usernameDataList = new ArrayList<>();

        String selectQuery = "SELECT username FROM data_user";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String usernameData = resultSet.getString("username");
            usernameDataList.add(usernameData);
        }

        return usernameDataList;
    }
    public class UserScore {
        private String username;
        private int score;

        public UserScore(String username, int score) {
            this.username = username;
            this.score = score;
        }

        public String getUsername() {
            return username;
        }

        public int getScore() {
            return score;
        }
    }
    //获取用户和用户的得分,并将其关联起来,将分数前10位的进行排名显示
    public class ScoreDataUtil {
        public static class UserScore {
            private String username;
            private int score;

            public UserScore(String username, int score) {
                this.username = username;
                this.score = score;
            }

            public String getUsername() {
                return username;
            }

            public int getScore() {
                return score;
            }
        }

        public static List<UserScore> getTopUserScores(Connection connection) throws SQLException {
            List<UserScore> userScores = new ArrayList<>();

            String selectQuery = "SELECT username, score FROM data_user";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                int score = resultSet.getInt("score");
                UserScore userScore = new UserScore(username, score);
                userScores.add(userScore);
            }

            // 对得分数据进行从大到小排序
            Collections.sort(userScores, Comparator.comparing(UserScore::getScore).reversed());

            // 只保留前8位数据
            if (userScores.size() > 8) {
                userScores = userScores.subList(0, 8);
            }

            return userScores;
        }
    }
}

