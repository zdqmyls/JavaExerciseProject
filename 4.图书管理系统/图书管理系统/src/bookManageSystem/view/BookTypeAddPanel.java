package bookManageSystem.view;

import bookManageSystem.dao.BookTypeDao;
import bookManageSystem.tools.ComponentTools;
import bookManageSystem.tools.SimpleTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookTypeAddPanel extends JPanel implements ActionListener {
    private ComponentTools componentTools = new ComponentTools();

    private Box totalVBox, funcationBox, typeNameBox, typeDescriptionBox, buttonBox;
    private JLabel typeNameLabel, bookTypeAddFuncationLabel, typeDescriptionLabel;
    private JTextArea typeDescriptionTextArea;
    private JTextField typeNameTextField;
    private JButton addButton, resetButton;


    public BookTypeAddPanel() {
        this.add(this.createBookTypeAddBox());
        /*--打包JAR包路径--*/
        String imagePath=new SimpleTools().getJARPath();
        componentTools.setIcons(new JButton[]{addButton, resetButton}, new String[]{imagePath+ "/images/add.png", imagePath+"/images/reset.png"});
//        componentTools.setIcons(new JButton[]{addButton, resetButton}, new String[]{"src/bookManageSystem/images/add.png", "src/bookManageSystem/images/reset.png"});
        addButton.addActionListener(this::actionPerformed);
        resetButton.addActionListener(this::actionPerformed);
    }

    public Box createBookTypeAddBox() {
        totalVBox = Box.createVerticalBox();

        funcationBox = Box.createHorizontalBox();
        bookTypeAddFuncationLabel = new JLabel("图书类别添加功能");
        bookTypeAddFuncationLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        funcationBox.add(bookTypeAddFuncationLabel);
        totalVBox.add(funcationBox);
        totalVBox.add(Box.createVerticalStrut(60));

        typeNameBox = Box.createHorizontalBox();
        typeNameLabel = new JLabel("图书类别名称：");
        typeNameTextField = new JTextField(20);
        typeNameBox.add(typeNameLabel);
        typeNameBox.add(Box.createHorizontalStrut(50));
        typeNameBox.add(typeNameTextField);
        totalVBox.add(typeNameBox);
        totalVBox.add(Box.createVerticalStrut(60));

        typeDescriptionBox = Box.createHorizontalBox();
        typeDescriptionLabel = new JLabel("图书类别描述：");
        typeDescriptionTextArea = new JTextArea(10, 20);
        typeDescriptionTextArea.setLineWrap(true);
        typeDescriptionBox.add(typeDescriptionLabel);
        typeDescriptionBox.add(Box.createHorizontalStrut(50));
        typeDescriptionBox.add(typeDescriptionTextArea);
        totalVBox.add(typeDescriptionBox);
        totalVBox.add(Box.createVerticalStrut(60));

        buttonBox = Box.createHorizontalBox();
        addButton = new JButton("添加");
        resetButton = new JButton("重置");
        buttonBox.add(addButton);
        buttonBox.add(Box.createHorizontalStrut(80));
        buttonBox.add(resetButton);
        totalVBox.add(buttonBox);

        return totalVBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String bookTypeName = typeNameTextField.getText();
            String bookTypeDescription = typeDescriptionTextArea.getText();
            String sql =
                    "insert into tb_bookType (btName, btDescription) values ('" + bookTypeName + "','" + bookTypeDescription + "');";
            boolean isOK = new BookTypeDao().dataChange(sql);
            if (isOK) {
                JOptionPane.showMessageDialog(null, "添加成功！");
                componentTools.reset(typeNameTextField, typeDescriptionTextArea);
            } else {
                JOptionPane.showMessageDialog(null, "添加失败！");
            }
        }
        if (e.getSource() == resetButton) {
            componentTools.reset(typeNameTextField, typeDescriptionTextArea);
        }
    }
}
