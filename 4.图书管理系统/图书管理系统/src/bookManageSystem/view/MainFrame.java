package bookManageSystem.view;

import bookManageSystem.tools.ComponentTools;
import bookManageSystem.tools.SimpleTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private SimpleTools simpleTools = new SimpleTools();
    private ComponentTools componentTools = new ComponentTools();

    private JMenuBar menuBar;
    private JMenu bookTypeManageMenu, bookManageMenu, otherMenu;
    private JMenuItem bookTypeAddMenuItem, bookTypeManageMenuItem, bookAddMenuItem, bookManageMenuItem, exitMenuItem,
            aboutSoftMenuItem;

    public MainFrame() {
        this.setTitle("惰惰龟图书管理系统");
        this.setBounds(400, 400, 800, 700);

        this.setJMenuBar(this.createMenuBar());
        this.setContentPane(createMainPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);

        bookTypeAddMenuItem.addActionListener(this);
        bookTypeManageMenuItem.addActionListener(this);
        bookAddMenuItem.addActionListener(this);
        bookManageMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
        aboutSoftMenuItem.addActionListener(this);
    }

    public JMenuBar createMenuBar() {
        menuBar = new JMenuBar();

        // 【图书类别管理】菜单
        bookTypeManageMenu = new JMenu("图书类别管理");
        bookTypeAddMenuItem = new JMenuItem("图书类别添加");
        bookTypeManageMenuItem = new JMenuItem("图书类别维护");
        bookTypeManageMenu.add(bookTypeAddMenuItem);
        bookTypeManageMenu.add(bookTypeManageMenuItem);
        menuBar.add(bookTypeManageMenu);

        // 【图书管理】菜单
        bookManageMenu = new JMenu("图书管理");
        bookAddMenuItem = new JMenuItem("图书添加");
        bookManageMenuItem = new JMenuItem("图书维护");
        bookManageMenu.add(bookAddMenuItem);
        bookManageMenu.add(bookManageMenuItem);
        menuBar.add(bookManageMenu);

        // 【其他】菜单
        otherMenu = new JMenu("其他");
        exitMenuItem = new JMenuItem("退出");
        aboutSoftMenuItem = new JMenuItem("关于软件");
        otherMenu.add(exitMenuItem);
        otherMenu.add(aboutSoftMenuItem);
        menuBar.add(otherMenu);

        /*--打包JAR包路径--*/
        String imagePath=new SimpleTools().getJARPath();
        componentTools.setIcons(
                new JMenuItem[]{bookTypeAddMenuItem, bookTypeManageMenuItem, bookAddMenuItem, bookManageMenuItem, exitMenuItem, aboutSoftMenuItem},
                new String[]{imagePath+"/images/add.png", imagePath+"/images/edit.png", imagePath+ "/images/add.png", imagePath+"/images/edit.png", imagePath+ "/images/exit.png", imagePath+"/images/about.png"}
        );
//        componentTools.setIcons(
//                new JMenuItem[]{bookTypeAddMenuItem, bookTypeManageMenuItem, bookAddMenuItem, bookManageMenuItem, exitMenuItem, aboutSoftMenuItem},
//                new String[]{"src/bookManageSystem/images/add.png", "src/bookManageSystem/images/edit.png", "src" +
//                        "/bookManageSystem/images/add.png", "src/bookManageSystem/images/edit.png", "src" +
//                        "/bookManageSystem/images/exit.png", "src/bookManageSystem/images/about.png"}
//        );

        return menuBar;
    }

    public JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        /*--打包JAR包路径--*/
        String imagePath=new SimpleTools().getJARPath();
        ImageIcon ii = new ComponentTools().iconSize(new ImageIcon(imagePath+"/images /bookmanagesystem.png"), 600, 400);
//        ImageIcon ii = new ComponentTools().iconSize(new ImageIcon("src/bookManageSystem/images/bookmanagesystem.png"), 600, 400);
        label.setIcon(ii);
        panel.add(label);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookTypeAddMenuItem) {
            this.setContentPane(new BookTypeAddPanel());
            this.setVisible(true);
        }
        if (e.getSource() == bookTypeManageMenuItem) {
            this.setContentPane(new BookTypeManagePanel());
            this.setVisible(true);
        }
        if (e.getSource() == bookAddMenuItem) {
            this.setContentPane(new BookAddPanel());
            this.setVisible(true);
        }
        if (e.getSource() == bookManageMenuItem) {
            this.setContentPane(new BookManagePanel());
            this.setVisible(true);
        }
        if (e.getSource() == exitMenuItem) {
            System.exit(0);
        }
        if (e.getSource() == aboutSoftMenuItem) {
            new AboutSoftDialog().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new SimpleTools().initGlobalFont(new Font("微软雅黑", Font.PLAIN, 16));
        new MainFrame().setVisible(true);
    }
}
