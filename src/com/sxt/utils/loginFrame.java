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
        setTitle("��¼����");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        // �û�����ǩ
        JLabel usernameLabel = new JLabel("�û���:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(usernameLabel, constraints);

        // �û����ı���
        usernameTextField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(usernameTextField, constraints);

        // �����ǩ
        JLabel passwordLabel = new JLabel("����:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(passwordLabel, constraints);

        // �����ı���
        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(passwordField, constraints);

        // ��¼��ť
        JButton loginButton = new JButton("��¼");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(loginButton, constraints);

        // ע�ᰴť
        JButton registerButton = new JButton("ע��");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(registerButton, constraints);

        // ��ӵ�¼��ť�ĵ���¼�������
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    int stat1 = UserDatabaseExample.checkUsernameExists(GameWin.connection,username);
                    if(stat1==1){//˵���û�������
                        int stat = UserDatabaseExample.validateUser(GameWin.connection,username,password);
                        if(stat ==1){//�û��������붼��ȷ
                            dispose();
                            GameWin.state =0;
                        }else {
                            // �����˺�δע�������
                            JOptionPane.showMessageDialog(loginFrame.this, "�������");
                        }
                    }else{
                        JOptionPane.showMessageDialog(loginFrame.this, "�û���������");
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // ���ע�ᰴť�ĵ���¼�������
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    int stat1 = UserDatabaseExample.checkUsernameExists(GameWin.connection,username);
                    if(stat1==1){//˵���û�������
                        JOptionPane.showMessageDialog(loginFrame.this, "�˺���ע��");
                    }else{
                        dispose();
                        GameWin.state =0;
                        UserDatabaseExample.registerUser(GameWin.connection, username, password);//ע���˺�
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        pack();
        setLocationRelativeTo(null); // ������ʾ����
        setVisible(true);
    }
}