package com.sxt.utils;
import com.sxt.GameWin;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserDatabaseExample {
    //�û�ע��
    public static int registerUser(Connection connection, String username, String password) throws SQLException {
        String insertQuery = "INSERT INTO data_user (username, password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertQuery);
        statement.setString(1, username);
        statement.setString(2, password);
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected>0){
            GameWin.username = username;
            System.out.println("�˺�ע��ɹ�");
        }
        return 1;//ע��ɹ�
    }
    //���µ÷�
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
                System.out.println(rowsAffected + " �м�¼�Ѹ��¡�");
            } else {
//                System.out.println("�µĵ÷ֲ��������ݿ����еĵ÷֣�������¡�");
            }
        }
    }
    //��ѯ�û����Ƿ����
    public static int checkUsernameExists(Connection connection, String username) throws SQLException {
        String selectQuery = "SELECT * FROM data_user WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return 1;
        } else {
            System.out.println("�û��������ڣ�");
            return 0;
        }
    }
    //��ѯ�û��Ƿ����
    public static int validateUser(Connection connection, String username, String password) throws SQLException {
        String selectQuery = "SELECT * FROM data_user WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String foundUsername = resultSet.getString("username");
            GameWin.username = foundUsername;
            System.out.println("��¼�ɹ����û�����" + foundUsername);
            return 1;
        } else {
            System.out.println("�û������������");
        }
        return 0;
    }
    //��ȡ�÷�,���Ӵ�С��������
    public static List<Integer> getScoreData(Connection connection) throws SQLException {
        List<Integer> scoreDataList = new ArrayList<>();

        String selectQuery = "SELECT score FROM data_user";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int scoreData = resultSet.getInt("score");
            scoreDataList.add(scoreData);
        }

        // �Ե÷����ݽ��дӴ�С����
        Collections.sort(scoreDataList, Collections.reverseOrder());

        // ֻ��ȡǰ��λ����
        if (scoreDataList.size() > 6) {
            scoreDataList = scoreDataList.subList(0, 6);
        }

        return scoreDataList;
    }
    //��ȡ�û���
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
    //��ȡ�û����û��ĵ÷�,�������������,������ǰ10λ�Ľ���������ʾ
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

            // �Ե÷����ݽ��дӴ�С����
            Collections.sort(userScores, Comparator.comparing(UserScore::getScore).reversed());

            // ֻ����ǰ8λ����
            if (userScores.size() > 8) {
                userScores = userScores.subList(0, 8);
            }

            return userScores;
        }
    }
}

