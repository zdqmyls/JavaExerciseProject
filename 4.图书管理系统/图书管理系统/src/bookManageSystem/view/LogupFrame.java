package bookManageSystem.view;

import bookManageSystem.tools.ComponentTools;
import bookManageSystem.tools.SimpleTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogupFrame extends JFrame implements ActionListener {
    private SimpleTools simpleTools = new SimpleTools();
    private ComponentTools componentTools = new ComponentTools();
    private JPanel panel;
    private Box totalVBox, systemLabelBox, usernameBox, passwordBox, buttonBox;
    private JLabel systemLabel, usernameLabel, passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton logupButton, resetButton;

    public LogupFrame() {
        this.setTitle("登录");
        this.setBounds(400, 400, 600, 500);

        this.setContentPane(this.createLogupPane());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);

        logupButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    public JPanel createLogupPane() {
        panel = new JPanel();
        totalVBox = Box.createVerticalBox();

        systemLabelBox = Box.createHorizontalBox();
        systemLabel = new JLabel("图书管理系统");
        systemLabel.setFont(new Font("微软雅黑", Font.BOLD, 40));
        systemLabelBox.add(systemLabel);

        usernameBox = Box.createHorizontalBox();
        usernameLabel = new JLabel("用户名：");
        usernameTextField = new JTextField(20);
        usernameBox.add(usernameLabel);
        usernameBox.add(Box.createHorizontalStrut(20));
        usernameBox.add(usernameTextField);

        passwordBox = Box.createHorizontalBox();
        passwordLabel = new JLabel("密    码：");
        passwordField = new JPasswordField(20);
        passwordBox.add(passwordLabel);
        passwordBox.add(Box.createHorizontalStrut(20));
        passwordBox.add(passwordField);

        buttonBox = Box.createHorizontalBox();
        logupButton = new JButton("登录");
        resetButton = new JButton("重置");
        buttonBox.add(logupButton);
        buttonBox.add(Box.createHorizontalStrut(80));
        buttonBox.add(resetButton);

        totalVBox.add(systemLabelBox);
        totalVBox.add(Box.createVerticalStrut(80));
        totalVBox.add(usernameBox);
        totalVBox.add(Box.createVerticalStrut(80));
        totalVBox.add(passwordBox);
        totalVBox.add(Box.createVerticalStrut(80));
        totalVBox.add(buttonBox);

        panel.add(totalVBox);

        /*--打包JAR包路径--*/
        String imagePath=new SimpleTools().getJARPath();
        new ComponentTools().setIcons(new JLabel[]{systemLabel, usernameLabel, passwordLabel},
                new String[]{imagePath+"/images/logo.png", imagePath+"/images/userName.png",imagePath+ "/images/password.png"});
        new ComponentTools().setIcons(new JButton[]{logupButton, resetButton}, new String[]{imagePath+"/images/login.png", imagePath+"/images/reset.png"});
//        new ComponentTools().setIcons(new JLabel[]{systemLabel, usernameLabel, passwordLabel}, new String[]{"src/bookManageSystem/images/logo.png", "src/bookManageSystem/images/userName.png", "src" + "/bookManageSystem/images/password.png"});
//        new ComponentTools().setIcons(new JButton[]{logupButton, resetButton}, new String[]{"src/bookManageSystem/images/login.png", "src/bookManageSystem/images/reset.png"});
        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logupButton) {
            if (simpleTools.isEmpty(usernameTextField.getText()) && simpleTools.isEmpty(passwordField.getText())) {
                if (usernameTextField.getText().equals("张三") && passwordField.getText().equals("123456")) {
                    int isOK = JOptionPane.showConfirmDialog(null, "恭喜您，登录成功！");
                    if (isOK == JOptionPane.OK_OPTION) {
                        componentTools.reset(usernameTextField, passwordField);
                        new MainFrame().setVisible(true);
                        this.setVisible(false);
                    } else {
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "对不起，登录失败！");
                }
            } else {
                JOptionPane.showMessageDialog(null, "不能为空！");
            }
        }
        if (e.getSource() == resetButton) {
            componentTools.reset(usernameTextField, passwordField);
        }
    }

}
