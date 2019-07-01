package bookManageSystem.view;

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

public class BookTypeManagePanel extends JPanel implements ActionListener, ListSelectionListener {
    private ComponentTools componentTools = new ComponentTools();

    private Box totalVBox, funcationHBox, bookTypeCheckHBox, tableHBox, idAndTypeHBox, descriptionHBox, buttonHBox;
    private JButton checkButton, alterButton, deleteButton;
    private JLabel bookTypeManageLabel, bookTypeNameLabel1, bookTypeNameLabel2, idLabel, descriptionLabel;
    private JTextField bookTypeNameTextField1, bookTypeNameTextField2, idTextField;
    private JTextArea descriptionTextArea;
    private JScrollPane tableScrollPanel;
    private JTable table;
    private DefaultTableModel tableModel;

    public BookTypeManagePanel() {
        this.add(createBookTypeManageVBox());
        /*--打包JAR包路径--*/
        String imagePath=new SimpleTools().getJARPath();
        componentTools.setIcons(new JButton[]{alterButton, deleteButton}, new String[]{imagePath+"/images/edit.png",
                imagePath+"/images/delete.png"});
//        componentTools.setIcons(new JButton[]{alterButton, deleteButton}, new String[]{"src/bookManageSystem/images/edit.png", "src/bookManageSystem/images/delete.png"});
        checkButton.addActionListener(this::actionPerformed);
        alterButton.addActionListener(this::actionPerformed);
        deleteButton.addActionListener(this::actionPerformed);
        table.getSelectionModel().addListSelectionListener(this::valueChanged);
    }

    public Box createBookTypeManageVBox() {
        totalVBox = Box.createVerticalBox();

        funcationHBox = Box.createHorizontalBox();
        bookTypeManageLabel = new JLabel("图书类别维护功能");
        bookTypeManageLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        funcationHBox.add(bookTypeManageLabel);
        totalVBox.add(funcationHBox);
        totalVBox.add(Box.createVerticalStrut(20));

        bookTypeCheckHBox = Box.createHorizontalBox();
        bookTypeNameLabel1 = new JLabel("图书类别名称：");
        bookTypeNameTextField1 = new JTextField(10);
        checkButton = new JButton("查询");
        bookTypeCheckHBox.add(bookTypeNameLabel1);
        bookTypeCheckHBox.add(Box.createHorizontalStrut(30));
        bookTypeCheckHBox.add(bookTypeNameTextField1);
        bookTypeCheckHBox.add(Box.createHorizontalStrut(30));
        bookTypeCheckHBox.add(checkButton);
        totalVBox.add(bookTypeCheckHBox);
        totalVBox.add(Box.createVerticalStrut(20));

        tableHBox = Box.createHorizontalBox();
        tableScrollPanel = new JScrollPane();
        tableScrollPanel.setViewportView(this.createTable("select btId,btName,btDescription from tb_booktype"));
        tableScrollPanel.setPreferredSize(new Dimension(700, 250));
        tableHBox.add(tableScrollPanel);
        totalVBox.add(tableHBox);
        totalVBox.add(Box.createVerticalStrut(20));

        idAndTypeHBox = Box.createHorizontalBox();
        idLabel = new JLabel("编号：");
        idTextField = new JTextField(10);
        idTextField.setEnabled(false);
        bookTypeNameLabel2 = new JLabel("图书类别名称：");
        bookTypeNameTextField2 = new JTextField(10);
        idAndTypeHBox.add(idLabel);
        idAndTypeHBox.add(Box.createHorizontalStrut(20));
        idAndTypeHBox.add(idTextField);
        idAndTypeHBox.add(Box.createHorizontalStrut(20));
        idAndTypeHBox.add(bookTypeNameLabel2);
        idAndTypeHBox.add(Box.createHorizontalStrut(20));
        idAndTypeHBox.add(bookTypeNameTextField2);
        totalVBox.add(idAndTypeHBox);
        totalVBox.add(Box.createVerticalStrut(20));

        descriptionHBox = Box.createHorizontalBox();
        descriptionLabel = new JLabel("描述：");
        descriptionTextArea = new JTextArea(5, 40);
        descriptionTextArea.setLineWrap(true);
        descriptionHBox.add(descriptionLabel);
        descriptionHBox.add(Box.createHorizontalStrut(20));
        descriptionHBox.add(descriptionTextArea);
        totalVBox.add(descriptionHBox);
        totalVBox.add(Box.createVerticalStrut(20));

        buttonHBox = Box.createHorizontalBox();
        alterButton = new JButton("修改");
        deleteButton = new JButton("删除");
        buttonHBox.add(alterButton);
        buttonHBox.add(Box.createHorizontalStrut(80));
        buttonHBox.add(deleteButton);
        totalVBox.add(buttonHBox);

        return totalVBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {
            String sql = "";
            if (!new SimpleTools().isEmpty(bookTypeNameTextField1.getText())) {
                sql = "select * from tb_booktype;";
            } else {
                sql = "select * from tb_booktype where btName='" + bookTypeNameTextField1.getText() + "';";
            }
            String[][] rowdatas = new BookTypeDao().ListToArray(new BookTypeDao().getRecordsDataBySql(sql));
            String[] headers = {"编号", "图书类别名称", "图书类别描述"};
            tableModel.setDataVector(rowdatas, headers);
        }
        if (e.getSource() == alterButton) {
            String id = idTextField.getText();
            String name = bookTypeNameTextField2.getText();
            String description = descriptionTextArea.getText();
            String alterSQL = "update tb_booktype set btName='" + name + "',btDescription='" + description + "' where" +
                    " btId=" + id + ";";
            boolean isOK = new BookTypeDao().dataChange(alterSQL);
            if (isOK) {
                refreshTable();
                new ComponentTools().reset(idTextField, bookTypeNameTextField2, descriptionTextArea);
                JOptionPane.showMessageDialog(null, "修改成功！");
            } else {
                JOptionPane.showMessageDialog(null, "修改失败！");
            }
        }
        if (e.getSource() == deleteButton) {
            String id = idTextField.getText();
            String sql1 = "set FOREIGN_KEY_CHECKS=0;";
            String deleteSQL = "delete from tb_booktype where btId=" + id + ";";
            String sql2 = "set FOREIGN_KEY_CHECKS=1;";
            int isOK = JOptionPane.showConfirmDialog(null, "是否确认删除？");
            if (isOK == JOptionPane.OK_OPTION) {
                new BookTypeDao().dataChange(sql1);
                boolean is = new BookTypeDao().dataChange(deleteSQL);
                new BookTypeDao().dataChange(sql2);
                if (is) {
                    refreshTable();
                    new ComponentTools().reset(idTextField, bookTypeNameTextField2, descriptionTextArea);
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败！");
                }
            } else {
                return;
            }
        }
    }

    public JTable createTable(String sql) {
        String[][] rowdatas = new BookTypeDao().ListToArray(new BookTypeDao().getRecordsDataBySql(sql));
        String[] headers = {"编号", "图书类别名称", "图书类别描述"};
        table = new JTable();
        table.setRowHeight(30);
        tableModel = new DefaultTableModel(rowdatas, headers);
        table.setModel(tableModel);
        return table;
    }

    public void refreshTable() {
        String sql = "select * from tb_booktype;";
        String[][] rowdatas = new BookTypeDao().ListToArray(new BookTypeDao().getRecordsDataBySql(sql));
        String[] headers = {"编号", "图书类别名称", "图书类别描述"};
        tableModel.setDataVector(rowdatas, headers);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int getSelectedRowIndex = table.getSelectedRow();
        if (getSelectedRowIndex == -1) {
            refreshTable();
        } else {
            idTextField.setText((String) table.getValueAt(getSelectedRowIndex, 0));
            bookTypeNameTextField2.setText((String) table.getValueAt(getSelectedRowIndex, 1));
            descriptionTextArea.setText((String) table.getValueAt(getSelectedRowIndex, 2));
        }
    }
}
