package com.sxt.utils;
import com.sxt.GameWin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class  loginFrame extends JFrame {
    private JTextField usernameTextField;
    private JPasswordField passwordField;

    public void showLoginFrame() {
        setTitle("登录界面");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        // 用户名标签
        JLabel usernameLabel = new JLabel("用户名:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(usernameLabel, constraints);

        // 用户名文本框
        usernameTextField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(usernameTextField, constraints);

        // 密码标签
        JLabel passwordLabel = new JLabel("密码:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(passwordLabel, constraints);

        // 密码文本框
        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(passwordField, constraints);

        // 登录按钮
        JButton loginButton = new JButton("登录");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(loginButton, constraints);

        // 注册按钮
        JButton registerButton = new JButton("注册");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(registerButton, constraints);

        // 添加登录按钮的点击事件监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    int stat1 = UserDatabaseExample.checkUsernameExists(GameWin.connection,username);
                    if(stat1==1){//说明用户名存在
                        int stat = UserDatabaseExample.validateUser(GameWin.connection,username,password);
                        if(stat ==1){//用户名和密码都正确
                            dispose();
                            GameWin.state =0;
                        }else {
                            // 弹出账号未注册的文字
                            JOptionPane.showMessageDialog(loginFrame.this, "密码错误");
                        }
                    }else{
                        JOptionPane.showMessageDialog(loginFrame.this, "用户名不存在");
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // 添加注册按钮的点击事件监听器
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    int stat1 = UserDatabaseExample.checkUsernameExists(GameWin.connection,username);
                    if(stat1==1){//说明用户名存在
                        JOptionPane.showMessageDialog(loginFrame.this, "账号已注册");
                    }else{
                        dispose();
                        GameWin.state =0;
                        UserDatabaseExample.registerUser(GameWin.connection, username, password);//注册账号
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        pack();
        setLocationRelativeTo(null); // 居中显示窗口
        setVisible(true);
    }
}