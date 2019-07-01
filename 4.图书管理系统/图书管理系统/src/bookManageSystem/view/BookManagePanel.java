package bookManageSystem.view;

import bookManageSystem.bean.BookTypeBean;
import bookManageSystem.dao.BookDao;
import bookManageSystem.dao.BookTypeDao;
import bookManageSystem.tools.ComponentTools;
import bookManageSystem.tools.SimpleTools;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookManagePanel extends JPanel implements ActionListener, ListSelectionListener {
    private ComponentTools componentTools = new ComponentTools();
    private SimpleTools simpleTools = new SimpleTools();

    private Box totalVBox, funcationHBox, checkHBox, tableHBox, sexRadioButtonHBox, descriptionHBox, buttonHBox,
            showContentHBox1, showContentHBox2;
    private JComboBox bookTypeComboBox, bookTypeComboBox2;
    private JLabel bookManageFuncationLabel, bookNameLabel, bookTypeLabel, idLabel, priceLabel, bookNameLabel2,
            bookAuthorLabel, bookTypeLabel2, bookAuthorLabel2, authorSexLabel, bookDescriptionLabel;
    private JTextField bookNameTextField, bookNameTextField2, bookAuthorTextField, bookAuthorTextField2, idTextField,
            priceTextField;
    private JTextArea bookDescriptionTextArea;
    private JButton checkButton, resetButton, resetButton2, alterButton, deleteButton;
    private ButtonGroup sexButtonGroup;
    private JScrollPane tableScrollPanel;
    private JRadioButton femaleRadioButton, maleRadioButton;
    private JTable table;
    private DefaultTableModel tableModel;


    public BookManagePanel() {
        this.add(this.createBookManageBox());
        String getBookTypeSQL = "select * from tb_booktype;";
        List bookTypeList = new BookTypeDao().getRecordsDataBySql(getBookTypeSQL);
        String[] typeNames = new String[bookTypeList.size()];
        for (int i = 0; i < bookTypeList.size(); i++) {
            BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(i);
            typeNames[i] = bookTypeBean.getBookTypeName();
        }
        componentTools.addComboBoxItems(bookTypeComboBox, typeNames);
        componentTools.addComboBoxItems(bookTypeComboBox2, typeNames);
        checkButton.addActionListener(this::actionPerformed);
        resetButton.addActionListener(this::actionPerformed);
        alterButton.addActionListener(this::actionPerformed);
        deleteButton.addActionListener(this::actionPerformed);
        resetButton2.addActionListener(this::actionPerformed);
        table.getSelectionModel().addListSelectionListener(this::valueChanged);
    }

    public Box createBookManageBox() {
        totalVBox = Box.createVerticalBox();

        funcationHBox = Box.createHorizontalBox();
        bookManageFuncationLabel = new JLabel("图书维护功能");
        bookManageFuncationLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        funcationHBox.add(bookManageFuncationLabel);
        totalVBox.add(funcationHBox);
        totalVBox.add(Box.createVerticalStrut(15));

        checkHBox = Box.createHorizontalBox();
        bookNameLabel = new JLabel("图书名称：");
        bookNameTextField = new JTextField();
        bookAuthorLabel = new JLabel("图书作者：");
        bookAuthorTextField = new JTextField();
        bookTypeLabel = new JLabel("图书类别：");
        bookTypeComboBox = new JComboBox();
        checkButton = new JButton("查询");
        resetButton = new JButton("重置");
        checkHBox.add(bookNameLabel);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(bookNameTextField);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(bookAuthorLabel);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(bookAuthorTextField);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(bookTypeLabel);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(bookTypeComboBox);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(checkButton);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(resetButton);
        totalVBox.add(checkHBox);
        totalVBox.add(Box.createVerticalStrut(15));

        tableHBox = Box.createHorizontalBox();
        tableScrollPanel = new JScrollPane();
        tableScrollPanel.setViewportView(this.createTable("select bId,bBookName,bAuthor,bSex,bPrice,bBookDescription," +
                "btName from tb_book,tb_booktype where tb_book.btId=tb_booktype.btId;"));
        tableScrollPanel.setPreferredSize(new Dimension(700, 250));
        tableHBox.add(tableScrollPanel);
        totalVBox.add(tableHBox);
        totalVBox.add(Box.createVerticalStrut(15));

        showContentHBox1 = Box.createHorizontalBox();
        idLabel = new JLabel("编号：");
        idTextField = new JTextField(5);
        bookNameLabel2 = new JLabel("图书名称：");
        bookNameTextField2 = new JTextField(5);
        authorSexLabel = new JLabel("作者性别：");
        sexRadioButtonHBox = Box.createHorizontalBox();
        femaleRadioButton = new JRadioButton("女");
        maleRadioButton = new JRadioButton("男");
        sexButtonGroup = new ButtonGroup();
        sexButtonGroup.add(femaleRadioButton);
        sexButtonGroup.add(maleRadioButton);
        sexRadioButtonHBox.add(femaleRadioButton);
        sexRadioButtonHBox.add(maleRadioButton);
        showContentHBox1.add(idLabel);
        showContentHBox1.add(Box.createHorizontalStrut(10));
        showContentHBox1.add(idTextField);
        showContentHBox1.add(Box.createHorizontalStrut(10));
        showContentHBox1.add(bookNameLabel2);
        showContentHBox1.add(Box.createHorizontalStrut(10));
        showContentHBox1.add(bookNameTextField2);
        showContentHBox1.add(Box.createHorizontalStrut(10));
        showContentHBox1.add(authorSexLabel);
        showContentHBox1.add(Box.createHorizontalStrut(10));
        showContentHBox1.add(sexRadioButtonHBox);
        totalVBox.add(showContentHBox1);
        totalVBox.add(Box.createVerticalStrut(15));

        showContentHBox2 = Box.createHorizontalBox();
        priceLabel = new JLabel("价格：");
        priceTextField = new JTextField(5);
        bookAuthorLabel2 = new JLabel("图书作者：");
        bookAuthorTextField2 = new JTextField(5);
        bookTypeLabel2 = new JLabel("图书类别：");
        bookTypeComboBox2 = new JComboBox();
        showContentHBox2.add(priceLabel);
        showContentHBox2.add(Box.createHorizontalStrut(10));
        showContentHBox2.add(priceTextField);
        showContentHBox2.add(Box.createHorizontalStrut(10));
        showContentHBox2.add(bookAuthorLabel2);
        showContentHBox2.add(Box.createHorizontalStrut(10));
        showContentHBox2.add(bookAuthorTextField2);
        showContentHBox2.add(Box.createHorizontalStrut(10));
        showContentHBox2.add(bookTypeLabel2);
        showContentHBox2.add(Box.createHorizontalStrut(10));
        showContentHBox2.add(bookTypeComboBox2);
        totalVBox.add(showContentHBox2);
        totalVBox.add(Box.createVerticalStrut(15));

        descriptionHBox = Box.createHorizontalBox();
        bookDescriptionLabel = new JLabel("图书描述：");
        bookDescriptionTextArea = new JTextArea(3, 10);
        descriptionHBox.add(bookDescriptionLabel);
        descriptionHBox.add(bookDescriptionTextArea);
        totalVBox.add(descriptionHBox);
        totalVBox.add(Box.createVerticalStrut(15));

        buttonHBox = Box.createHorizontalBox();
        alterButton = new JButton("修改");
        deleteButton = new JButton("删除");
        resetButton2 = new JButton("重置");
        buttonHBox.add(alterButton);
        buttonHBox.add(Box.createHorizontalStrut(100));
        buttonHBox.add(deleteButton);
        buttonHBox.add(Box.createHorizontalStrut(100));
        buttonHBox.add(resetButton2);
        totalVBox.add(buttonHBox);

        /*--打包JAR包路径*/
        String imagePath=new SimpleTools().getJARPath();
        componentTools.setIcons(new JButton[]{alterButton, deleteButton,resetButton2}, new String[]{imagePath+"/images/edit.png",
                imagePath+"/images/delete.png",imagePath+"/images/reset.png"});
//        componentTools.setIcons(new JButton[]{alterButton, deleteButton,resetButton2}, new String[]{"src/bookManageSystem" +
//                "/images/edit.png", "src/bookManageSystem/images/delete.png","src/bookManageSystem/images/reset.png"});

        return totalVBox;
    }

    public JTable createTable(String sql) {
        String[][] rowdatas = new BookDao().ListToArray(new BookDao().getRecordsDataBySql(sql));
        String[] headers = {"编号", "图书名称", "图书作者", "作者性别", "图书价格", "图书描述", "图书类别"};
        table = new JTable();
        table.setRowHeight(30);
        tableModel = new DefaultTableModel(rowdatas, headers);
        table.setModel(tableModel);
        return table;
    }

    public void refreshTable() {
        String sql = "select bId,bBookName,bAuthor,bSex,bPrice,bBookDescription,btName from tb_book,tb_booktype " +
                "where tb_book.btId=tb_booktype.btId;";
        String[][] rowdatas = new BookDao().ListToArray(new BookDao().getRecordsDataBySql(sql));
        String[] headers = {"编号", "图书名称", "图书作者", "作者性别", "图书价格", "图书描述", "图书类别"};
        tableModel.setDataVector(rowdatas, headers);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {
            String sql =
                    "select bId,bBookName,bAuthor,bSex,bPrice,bBookDescription,btName from tb_book,tb_booktype where "
                            + "tb_book.btId=tb_booktype.btId ";
            if (simpleTools.isEmpty(bookNameTextField.getText()) && simpleTools.isEmpty(bookAuthorTextField.getText()) && bookTypeComboBox.getSelectedIndex() < 0) {
                sql += " and 1=1;";
            } else {
                if (bookNameTextField.getText().length() > 0) {
                    sql += " and bBookName like '%" + bookNameTextField.getText() + "%'";
                }
                if (bookAuthorTextField.getText().length() > 0) {
                    sql += " and bAuthor like '%" + bookAuthorTextField.getText() + "%'";
                }
                String booktype = (String) bookTypeComboBox.getModel().getSelectedItem();
                if (simpleTools.isEmpty(booktype)) {
                    sql += " and btName='" + booktype + "';";
                }
            }
            String[][] rowdatas = new BookDao().ListToArray(new BookDao().getRecordsDataBySql(sql));
            String[] headers = {"编号", "图书名称", "图书作者", "作者性别", "图书价格", "图书描述", "图书类别"};
            tableModel.setDataVector(rowdatas, headers);
        }
        if (e.getSource() == resetButton) {
            String sql =
                    "select bId,bBookName,bAuthor,bSex,bPrice,bBookDescription,btName from tb_book,tb_booktype where "
                            + "tb_book.btId=tb_booktype.btId ";
            String[][] rowdatas = new BookDao().ListToArray(new BookDao().getRecordsDataBySql(sql));
            String[] headers = {"编号", "图书名称", "图书作者", "作者性别", "图书价格", "图书描述", "图书类别"};
            tableModel.setDataVector(rowdatas, headers);
            componentTools.reset(bookNameTextField, bookAuthorTextField);
            componentTools.reset(bookTypeComboBox);
        }
        if (e.getSource() == alterButton) {
            String id = idTextField.getText();
            String bookName = bookNameTextField2.getText();
            String sex = "";
            if (femaleRadioButton.isSelected()) {
                sex = femaleRadioButton.getText();
            } else {
                sex = maleRadioButton.getText();
            }
            Float price = Float.parseFloat(priceTextField.getText());
            String bookAuthor = bookAuthorTextField2.getText();
            String bookType = (String) bookTypeComboBox2.getModel().getSelectedItem();
            String bookDescription = bookDescriptionTextArea.getText();
            String alterSQL =
                    "update tb_booktype,tb_book set bBookName='" + bookName + "',bAuthor='" + bookAuthor + "',bSex='" + sex + "'," + "bPrice" + "=" + price + "," + "tb_booktype.btName='" + bookType + "',bBookDescription='" + bookDescription + "' where " + "tb_book.btId=tb_booktype" + ".btId " + "and" + " bId=" + id + ";";
            boolean isOK = new BookTypeDao().dataChange(alterSQL);
            if (isOK) {
                refreshTable();
                componentTools.reset(idTextField, bookNameTextField2, priceTextField, bookAuthorTextField2,
                        bookDescriptionTextArea);
                componentTools.reset(sexButtonGroup);
                componentTools.reset(bookTypeComboBox2);
                JOptionPane.showMessageDialog(null, "修改成功！");
            } else {
                JOptionPane.showMessageDialog(null, "修改失败！");
            }
        }
        if (e.getSource() == deleteButton) {
            String id = idTextField.getText();
            String sql1 = "set FOREIGN_KEY_CHECKS=0;";
            String deleteSQL = "delete from tb_book where bId=" + id + ";";
            String sql2 = "set FOREIGN_KEY_CHECKS=1;";
            int isOK = JOptionPane.showConfirmDialog(null, "是否确认删除？");
            if (isOK == JOptionPane.OK_OPTION) {
                new BookDao().dataChange(sql1);
                boolean is = new BookDao().dataChange(deleteSQL);
                new BookDao().dataChange(sql2);
                if (is) {
                    refreshTable();
                    componentTools.reset(idTextField, bookNameTextField2, priceTextField, bookAuthorTextField2,
                            bookDescriptionTextArea);
                    componentTools.reset(sexButtonGroup);
                    componentTools.reset(bookTypeComboBox2);
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败！");
                }
            } else {
                return;
            }
        }
        if (e.getSource() == resetButton2) {
            componentTools.reset(idTextField, bookNameTextField2, priceTextField, bookAuthorTextField2,
                    bookDescriptionTextArea);
            componentTools.reset(sexButtonGroup);
            componentTools.reset(bookTypeComboBox2);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int getSelectedRowIndex = table.getSelectedRow();
        if (getSelectedRowIndex == -1) {
            refreshTable();
        } else {
            idTextField.setText((String) table.getValueAt(getSelectedRowIndex, 0));
            bookNameTextField2.setText((String) table.getValueAt(getSelectedRowIndex, 1));
            boolean b = table.getValueAt(getSelectedRowIndex, 3).equals("男");
            if (b) {
                maleRadioButton.setSelected(true);
            } else {
                femaleRadioButton.setSelected(true);
            }
            priceTextField.setText((String) table.getValueAt(getSelectedRowIndex, 4));
            bookAuthorTextField2.setText((String) table.getValueAt(getSelectedRowIndex, 2));
            bookTypeComboBox2.setSelectedItem(table.getValueAt(getSelectedRowIndex, 6));
            bookDescriptionTextArea.setText((String) table.getValueAt(getSelectedRowIndex, 5));
        }
    }
}
