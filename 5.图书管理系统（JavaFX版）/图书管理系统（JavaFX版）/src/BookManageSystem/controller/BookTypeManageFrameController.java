package BookManageSystem.controller;

import BookManageSystem.beans.BookTypeBeanTableData;
import BookManageSystem.dao.BookTypeDao;
import BookManageSystem.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BookTypeManageFrameController {
    private SimpleTools simpleTools = new SimpleTools();
    private BookTypeDao bookTypeDao = new BookTypeDao();

    @FXML
    private TextField idTextField;

    @FXML
    private Button alterButton;

    @FXML
    private TableColumn<BookTypeBeanTableData, String> idTableColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<BookTypeBeanTableData> bookTypeManageTableView;

    @FXML
    private TextField bookTypeNameTextField;

    @FXML
    private TableColumn<BookTypeBeanTableData, String> bookTypeNameColumn;

    @FXML
    private TextField bookTypeNameTextField2;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TableColumn<BookTypeBeanTableData, String> bookTypeDescriptionTableColumn;

    public void initialize() {
        /*--打包JAR包路径--*/
        String imagePath=simpleTools.getJARPath();
        simpleTools.setLabeledImage(new Labeled[]{alterButton, deleteButton}, new String[]{imagePath+"src" +
                "/images/edit.png", imagePath+"/images/delete.png"});
//        simpleTools.setLabeledImage(new Labeled[]{alterButton, deleteButton}, new String[]{"src/BookManageSystem/images/edit.png", "src/BookManageSystem/images/delete.png"});
        idTextField.setEditable(false);
        String sql = "select * from tb_booktype;";
        simpleTools.setBookTypeTableViewData(bookTypeManageTableView
                , simpleTools.getBookTypeTableViewData(sql)
                , idTableColumn
                , bookTypeNameColumn
                , bookTypeDescriptionTableColumn
        );
        bookTypeManageTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBookTypeDetails(newValue));
    }

    // 【查询】按钮的事件监听器
    public void do_checkButton_event(ActionEvent event) {
        String bookTypeName = bookTypeNameTextField.getText();
        String checkSQL = "";
        if (simpleTools.isEmpty(bookTypeName)) {
            checkSQL = "select * from tb_booktype";
        } else {
            checkSQL = "select * from tb_booktype where btName='" + bookTypeName + "';";
        }
        simpleTools.setBookTypeTableViewData(bookTypeManageTableView
                , simpleTools.getBookTypeTableViewData(checkSQL)
                , idTableColumn
                , bookTypeNameColumn
                , bookTypeDescriptionTableColumn
        );
    }

    // 【修改】按钮的事件监听器
    public void do_alterButton_event(ActionEvent event) {
        String id = idTextField.getText();
        String name = bookTypeNameTextField2.getText();
        String description = descriptionTextArea.getText();
        String alterSQL = "update tb_booktype set btName='" + name + "',btDescription='" + description + "' where " +
                "btId=" + id + ";";
        boolean isOK = bookTypeDao.dataChange(alterSQL);
        if (isOK) {
            initialize();
            simpleTools.clearTextField(idTextField, bookTypeNameTextField2, descriptionTextArea);
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "修改成功！");
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "修改失败！");
        }
    }

    // 【删除】按钮的事件监听器
    public void do_deleteButton_event(ActionEvent event) {
        String id = idTextField.getText();
        String sql1 = "set FOREIGN_KEY_CHECKS=0;";
        String deleteSQL = "delete from tb_booktype where btId=" + id + ";";
        String sql2 = "set FOREIGN_KEY_CHECKS=1;";
        boolean is = simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "是否删除？");
        if (is) {
            bookTypeDao.dataChange(sql1);
            boolean isOK = bookTypeDao.dataChange(deleteSQL);
            bookTypeDao.dataChange(sql2);
            if (isOK) {
                initialize();
                idTextField.setText("");
                bookTypeNameTextField2.setText("");
                descriptionTextArea.setText("");
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "删除成功！");
            } else {
                simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "删除失败！");
            }
        } else {
            return;
        }

    }

    // 选中行后将选中行内容显示在下面的文本框中
    public void showBookTypeDetails(BookTypeBeanTableData bookTypeBeanTableData) {
        if (bookTypeBeanTableData == null) {
            return;
        }
        {
            idTextField.setText(bookTypeBeanTableData.getBookTypeId());
            bookTypeNameTextField2.setText(bookTypeBeanTableData.getBookTypeName());
            descriptionTextArea.setText(bookTypeBeanTableData.getBookTypeDesciption());
        }
    }
}
