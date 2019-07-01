package BookManageSystem.controller;

import BookManageSystem.dao.BookTypeDao;
import BookManageSystem.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BookTypeAddFrameController {
    private SimpleTools simpleTools = new SimpleTools();

    @FXML
    private TextField bookTypeNameTextField;

    @FXML
    private Button addButton;

    @FXML
    private TextArea bookTypeDescriptionTextArea;

    @FXML
    private Button resetButton;

    public void initialize() {
        // 初始化按钮的图标
        /*--打包JAR包路径--*/
        String imagePath=simpleTools.getJARPath();
        simpleTools.setLabeledImage(new Labeled[]{addButton,resetButton},new String[]{imagePath+"/images/add.png",
                imagePath+"/images/reset.png"});
//        simpleTools.setLabeledImage(new Labeled[]{addButton, resetButton}, new String[]{"src/BookManageSystem/images/add.png",
//                "src/BookManageSystem/images/reset.png"});
    }

    // “添加”按钮的事件监听器方法
    public void do_addButton_event(ActionEvent event) {
        String bookTypeName = bookTypeNameTextField.getText();
        String bookTypeDescription = bookTypeDescriptionTextArea.getText();
        String sql =
                "insert into tb_bookType (btName, btDescription) values ('" + bookTypeName + "','" + bookTypeDescription + "');";
        boolean isOK = new BookTypeDao().dataChange(sql);
        if (isOK) {
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "添加成功！");
            bookTypeNameTextField.setText("");
            bookTypeDescriptionTextArea.setText("");
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "添加失败！");
        }
    }

    // “重置”按钮的事件监听器方法
    public void do_resetButton_event(ActionEvent event) {
        simpleTools.clearTextField(bookTypeNameTextField,bookTypeDescriptionTextArea);
    }
}
