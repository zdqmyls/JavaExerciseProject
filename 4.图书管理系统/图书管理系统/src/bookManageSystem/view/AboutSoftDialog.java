package bookManageSystem.view;

import bookManageSystem.tools.ComponentTools;
import bookManageSystem.tools.SimpleTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutSoftDialog extends JDialog implements ActionListener,MouseListener {
    private ComponentTools componentTools=new ComponentTools();
    private JPanel aboutSoftPanel;
    private Box totalHBox,leftHBox,rightVBox;
    private JLabel iconLabel,systemLabel,editionLabel,hyperlinkLabel;
    private JButton closeButton;

    public AboutSoftDialog(){
        this.setTitle("关于软件");
        this.setBounds(400,400,500,300);

        this.setContentPane(this.createAboutSoftPanel());
        this.setVisible(false);

        closeButton.addActionListener(this::actionPerformed);
        hyperlinkLabel.addMouseListener(this);
    }

    public JPanel createAboutSoftPanel(){
        aboutSoftPanel=new JPanel();
        aboutSoftPanel.setLayout(new BorderLayout());

        totalHBox=Box.createHorizontalBox();

        leftHBox=Box.createHorizontalBox();
        iconLabel=new JLabel();
        /*--打包JAR包路径--*/
        String imagePath=new SimpleTools().getJARPath();
        iconLabel.setIcon(componentTools.iconSize(new ImageIcon(imagePath+"/images/panda.png"),
                160,160));
//        iconLabel.setIcon(componentTools.iconSize(new ImageIcon("src/bookManageSystem/images/panda.png"),160,160));
        leftHBox.add(iconLabel);
        totalHBox.add(leftHBox);

        rightVBox=Box.createVerticalBox();
        systemLabel=new JLabel("惰惰龟图书管理系统");
        systemLabel.setFont(new Font("微软雅黑",Font.PLAIN,30));
        editionLabel=new JLabel("版本 1.0");
        editionLabel.setFont(new Font("微软雅黑",Font.PLAIN,30));
        hyperlinkLabel=new JLabel("<html><u>相关GitHub链接</u></html>");
        hyperlinkLabel.setForeground(new Color(0, 149, 200));
        hyperlinkLabel.setFont(new Font("微软雅黑",Font.PLAIN,20));
        rightVBox.add(systemLabel);
        rightVBox.add(Box.createVerticalStrut(50));
        rightVBox.add(editionLabel);
        rightVBox.add(Box.createVerticalStrut(50));
        rightVBox.add(hyperlinkLabel);
        totalHBox.add(Box.createHorizontalStrut(20));
        totalHBox.add(rightVBox);

        aboutSoftPanel.add(totalHBox,BorderLayout.NORTH);

        closeButton=new JButton("关闭");
        Box buttonHBox=Box.createHorizontalBox();
        buttonHBox.add(closeButton);
        aboutSoftPanel.add(buttonHBox,BorderLayout.EAST);

        return aboutSoftPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==closeButton){
            this.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("https://github.com/lck100/JavaExerciseProject/tree/master/1" +
                    ".%E7%AE%A1%E5%AE%B6%E5%A9%86%E7%B3%BB%E7%BB%9F/%E7%AE%A1%E5%AE%B6%E5%A9%86%E7%B3%BB%E7%BB%9F%EF%BC%88JavaFX%E7%89%88%EF%BC%89"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        hyperlinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hyperlinkLabel.setForeground(new Color(0,0,0));
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
