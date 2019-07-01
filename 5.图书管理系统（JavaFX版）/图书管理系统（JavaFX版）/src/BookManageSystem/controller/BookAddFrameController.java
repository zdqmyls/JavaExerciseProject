package BookManageSystem.controller;

import BookManageSystem.beans.BookTypeBean;
import BookManageSystem.dao.BookDao;
import BookManageSystem.dao.BookTypeDao;
import BookManageSystem.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class BookAddFrameController {
    private SimpleTools simpleTools = new SimpleTools();

    @FXML
    private TextField bookAuthorTextField;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private TextField bookPriceTextField;

    @FXML
    private ComboBox<?> bookTypeComboBox;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private Button addButton;

    @FXML
    private Button resetButton;

    @FXML
    private TextField bookNameTextField;

    @FXML
    private TextArea bookDescriptionTextArea;

    // 初始化
    public void initialize() {
//        simpleTools.setLabeledImage(new Labeled[]{addButton, resetButton}, new String[]{"src/BookManageSystem/images/add.png",
//                "src/BookManageSystem/images/reset.png"});
        /*--打包JAR包路径--*/
        String imagePath=simpleTools.getJARPath();
        simpleTools.setLabeledImage(new Labeled[]{addButton,resetButton},new String[]{imagePath+"/images/add.png",
                imagePath+"/images/reset.png"});
        String getBookTypeSQL = "select * from tb_booktype";
        List bookTypeList = new BookTypeDao().getRecordsDataBySql(getBookTypeSQL);
        String[] typeNames = new String[bookTypeList.size()];
        for (int i = 0; i < bookTypeList.size(); i++) {
            BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(i);
            typeNames[i] = bookTypeBean.getBookTypeName();
        }
        simpleTools.addComboBoxItems(bookTypeComboBox, typeNames);
    }

    // 【添加】按钮的事件监听器
    public void do_addButton_event(ActionEvent event) {
        String name = bookNameTextField.getText();
        String author = bookAuthorTextField.getText();
        String sex = "";
        if (maleRadioButton.isSelected()) {
            sex = maleRadioButton.getText();
        } else if (femaleRadioButton.isSelected()) {
            sex = femaleRadioButton.getText();
        }
        String price = bookPriceTextField.getText();
        String type = (String) bookTypeComboBox.getSelectionModel().selectedItemProperty().getValue();
        String description = bookDescriptionTextArea.getText();

        String bookTypeSQL = "select * from tb_booktype where btName='" + type + "';";
        List bookTypeList = new BookTypeDao().getRecordsDataBySql(bookTypeSQL);
        BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(0);
        int bookTypeId = bookTypeBean.getBookTypeId();
        String sql =
                "insert into tb_book(bBookName, bAuthor, bSex, bPrice, bBookDescription, btId) values('" + name + "'," +
                        "'" + author + "','" + sex + "'," + price + ",'" + description + "'," + bookTypeId + ");";
        boolean isOK = new BookDao().dataChange(sql);
        if (isOK) {
            resetContent();
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "添加成功！");
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "添加失败！");
        }
    }

    // 【重置】按钮的事件监听器
    public void do_resetButton_event(ActionEvent event) {
        resetContent();
    }

    // 重置文本框、单选按钮和下拉列表框
    public void resetContent() {
        simpleTools.clearTextField(bookNameTextField, bookAuthorTextField, bookPriceTextField, bookDescriptionTextArea);
        simpleTools.clearSelectedRadioButton(maleRadioButton, femaleRadioButton);
        simpleTools.clearSelectedComboBox(bookTypeComboBox);
    }

}
