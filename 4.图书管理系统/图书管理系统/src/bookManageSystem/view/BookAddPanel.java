package bookManageSystem.view;

import bookManageSystem.bean.BookTypeBean;
import bookManageSystem.dao.BookDao;
import bookManageSystem.dao.BookTypeDao;
import bookManageSystem.tools.ComponentTools;
import bookManageSystem.tools.SimpleTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookAddPanel extends JPanel implements ActionListener {
    private SimpleTools simpleTools = new SimpleTools();
    private ComponentTools componentTools = new ComponentTools();
    private BookDao bookDao = new BookDao();

    private Box totalVBox, funcationHBox, nameAndAuthorHBox, sexAndPriceHBox, typeHBox, descriptionHBox, buttonHBox;
    private JLabel bookAddFuncationLabel, bookNameLabel, bookAuthorLabel, authorSexLabel, bookTypeLabel,
            descriptionLabel, bookPriceLabel;
    private JTextField bookNameTextField, bookAuthorTextField, bookPriceTextField;
    private JTextArea descriptionTextArea;
    private JRadioButton femaleRadioButton, maleRadioButton;
    private ButtonGroup radioButtonGroup;
    private JComboBox bookTypeComboBox;
    private JButton addButton, resetButton;

    public BookAddPanel() {
        this.add(createBookAddBox());
        /*--打包JAR包路径--*/
        String imagePath=new SimpleTools().getJARPath();
        componentTools.setIcons(new JButton[]{addButton, resetButton}, new String[]{imagePath+ "/images/add.png",
                imagePath+ "/images/reset.png"});
//        componentTools.setIcons(new JButton[]{addButton, resetButton}, new String[]{"src/bookManageSystem/images/add" +
//                ".png", "src/bookManageSystem/images/reset.png"});
        String getBookTypeSQL = "select * from tb_booktype";
        List bookTypeList = new BookTypeDao().getRecordsDataBySql(getBookTypeSQL);
        String[] typeNames = new String[bookTypeList.size()];
        for (int i = 0; i < bookTypeList.size(); i++) {
            BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(i);
            typeNames[i] = bookTypeBean.getBookTypeName();
        }
        componentTools.addComboBoxItems(bookTypeComboBox, typeNames);
        addButton.addActionListener(this::actionPerformed);
        resetButton.addActionListener(this::actionPerformed);
    }

    public Box createBookAddBox() {
        totalVBox = Box.createVerticalBox();

        funcationHBox = Box.createHorizontalBox();
        bookAddFuncationLabel = new JLabel("图书添加功能");
        bookAddFuncationLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        funcationHBox.add(bookAddFuncationLabel);
        totalVBox.add(funcationHBox);
        totalVBox.add(Box.createVerticalStrut(30));

        nameAndAuthorHBox = Box.createHorizontalBox();
        bookNameLabel = new JLabel("图书名称：");
        bookNameTextField = new JTextField(10);
        bookAuthorLabel = new JLabel("图书作者：");
        bookAuthorTextField = new JTextField(10);
        nameAndAuthorHBox.add(bookNameLabel);
        nameAndAuthorHBox.add(Box.createHorizontalStrut(40));
        nameAndAuthorHBox.add(bookNameTextField);
        nameAndAuthorHBox.add(Box.createHorizontalStrut(40));
        nameAndAuthorHBox.add(bookAuthorLabel);
        nameAndAuthorHBox.add(Box.createHorizontalStrut(40));
        nameAndAuthorHBox.add(bookAuthorTextField);
        totalVBox.add(nameAndAuthorHBox);
        totalVBox.add(Box.createVerticalStrut(30));

        sexAndPriceHBox = Box.createHorizontalBox();
        authorSexLabel = new JLabel("作者性别：");
        Box sexHBox = Box.createHorizontalBox();
        femaleRadioButton = new JRadioButton("女");
        maleRadioButton = new JRadioButton("男");
        sexHBox.add(femaleRadioButton);
        sexHBox.add(maleRadioButton);
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(femaleRadioButton);
        radioButtonGroup.add(maleRadioButton);
        bookPriceLabel = new JLabel("图书价格：");
        bookPriceTextField = new JTextField(5);
        sexAndPriceHBox.add(authorSexLabel);
        nameAndAuthorHBox.add(Box.createHorizontalStrut(50));
        sexAndPriceHBox.add(sexHBox);
        nameAndAuthorHBox.add(Box.createHorizontalStrut(100));
        sexAndPriceHBox.add(bookPriceLabel);
        nameAndAuthorHBox.add(Box.createHorizontalStrut(10));
        sexAndPriceHBox.add(bookPriceTextField);
        totalVBox.add(sexAndPriceHBox);
        totalVBox.add(Box.createVerticalStrut(30));

        typeHBox = Box.createHorizontalBox();
        bookTypeLabel = new JLabel("图书类别：");
        bookTypeComboBox = new JComboBox();
        typeHBox.add(bookTypeLabel);
        typeHBox.add(Box.createHorizontalStrut(40));
        typeHBox.add(bookTypeComboBox);
        totalVBox.add(typeHBox);
        totalVBox.add(Box.createVerticalStrut(30));

        descriptionHBox = Box.createHorizontalBox();
        descriptionLabel = new JLabel("图书描述：");
        descriptionTextArea = new JTextArea(10, 40);
        descriptionHBox.add(descriptionLabel);
        descriptionHBox.add(Box.createHorizontalStrut(40));
        descriptionHBox.add(descriptionTextArea);
        totalVBox.add(descriptionHBox);
        totalVBox.add(Box.createVerticalStrut(30));

        buttonHBox = Box.createHorizontalBox();
        addButton = new JButton("添加");
        resetButton = new JButton("重置");
        buttonHBox.add(addButton);
        buttonHBox.add(Box.createHorizontalStrut(80));
        buttonHBox.add(resetButton);
        totalVBox.add(buttonHBox);

        return totalVBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = bookNameTextField.getText();
            String author = bookAuthorTextField.getText();
            String sex = "";
            if (maleRadioButton.isSelected()) {
                sex = maleRadioButton.getText();
            } else if (femaleRadioButton.isSelected()) {
                sex = femaleRadioButton.getText();
            }
            String price = bookPriceTextField.getText();
            String type = (String) bookTypeComboBox.getModel().getSelectedItem();
            String description = descriptionTextArea.getText();

            String bookTypeSQL = "select * from tb_booktype where btName='" + type + "';";
            List bookTypeList = new BookTypeDao().getRecordsDataBySql(bookTypeSQL);
            BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(0);
            int bookTypeId = bookTypeBean.getBookTypeId();
            String sql =
                    "insert into tb_book(bBookName, bAuthor, bSex, bPrice, bBookDescription, btId) values('" + name + "'," +
                            "'" + author + "','" + sex + "'," + price + ",'" + description + "'," + bookTypeId + ");";
            boolean isOK = new BookDao().dataChange(sql);
            if (isOK) {
                componentTools.reset(bookNameTextField, bookAuthorTextField, bookPriceTextField, descriptionTextArea);
                componentTools.reset(radioButtonGroup);
                componentTools.reset(bookTypeComboBox);
                JOptionPane.showMessageDialog(null, "添加成功！");
            } else {
                JOptionPane.showMessageDialog(null, "添加失败！");
            }
        }
        if (e.getSource() == resetButton) {
            componentTools.reset(bookNameTextField, bookAuthorTextField, bookPriceTextField, descriptionTextArea);
            componentTools.reset(radioButtonGroup);
            componentTools.reset(bookTypeComboBox);
        }
    }
}
