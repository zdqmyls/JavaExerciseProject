package AccountSystem.controller;

import AccountSystem.dao.ClassificationDao;
import AccountSystem.tools.PublicTools;
import AccountSystem.tools.SimpleTools;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;

public class AddClassificationFrameController {
    private SimpleTools simpleTools = new SimpleTools();
    private PublicTools publicTools = new PublicTools();

    private Stage dialogStage;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML // fx:id="inputClassificationNameTextField"
    private TextField inputClassificationNameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="inputClassificationListView"
    private ListView<String> inputClassificationListView; // Value injected by FXMLLoader

    @FXML // fx:id="outputClassificationNameTextField"
    private TextField outputClassificationNameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="outputClassificationListView"
    private ListView<String> outputClassificationListView; // Value injected by FXMLLoader

    @FXML // 初始化界面
    public void initialize() {
        inputClassificationListView.setItems(FXCollections.observableArrayList(publicTools.public_getInputClassification()));
        outputClassificationListView.setItems(FXCollections.observableArrayList(publicTools.public_getOutputClassification()));
    }

    // ”添加支出“按钮的事件监听器
    public void addOutputButtonEvent(ActionEvent event) {
        String output = outputClassificationNameTextField.getText();
        if (null == output || "".equals(output)) {
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "文本框内容不能为空！");
        } else {
            outputClassificationListView.getItems().add(output);
            String addOutputClassificationSql = "insert into tb_classification(cName,cType) values('" + output + "'," +
                    "'支出');";
            new ClassificationDao().setTableData(addOutputClassificationSql);
            outputClassificationNameTextField.setText("");
        }

    }

    // “支出”右键菜单“编辑”的事件监听器
    public void output_editContextMenuEvent(ActionEvent event) {
        outputClassificationListView.setCellFactory(TextFieldListCell.forListView());
        outputClassificationListView.setEditable(true);
        outputClassificationListView.setFocusTraversable(true);
    }

    // “支出”右键菜单“删除”的事件监听器
    public void output_deleteMenuItemEvent(ActionEvent event) {
        String outputItem = (String) outputClassificationListView.getSelectionModel().getSelectedItem();
        outputClassificationListView.getItems().remove(outputItem);
        String deleteOutputClassificationSql = "delete from tb_classification where cName='" + outputItem + "';";
        new ClassificationDao().setTableData(deleteOutputClassificationSql);
    }

    // 支出分类界面”返回“按钮的事件监听器
    public void outputClassificationReturnButtonEvent(ActionEvent event) {
        dialogStage.close();
    }

    // ”添加收入“按钮的事件监听器
    public void addInputButtonEvent(ActionEvent event) {
        String input = inputClassificationNameTextField.getText();
        if (null == input || "".equals(input)) {
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "文本框内容不能为空！");
        } else {
            inputClassificationListView.getItems().add(input);
            String addInputClassificationSql = "insert into tb_classification(cName,cType) values('" + input + "'," +
                    "'收入');";
            new ClassificationDao().setTableData(addInputClassificationSql);
            inputClassificationNameTextField.setText("");
        }
    }

    // “收入”右键菜单“编辑”的事件监听器
    public void input_editContextMenuEvent(ActionEvent event) {
        inputClassificationListView.setCellFactory(TextFieldListCell.forListView());
        inputClassificationListView.setEditable(true);
        inputClassificationListView.setFocusTraversable(true);
    }

    // “收入”右键菜单“删除”的事件监听器
    public void input_deleteContextMenuEvent(ActionEvent event) {
        String inputItem = (String) inputClassificationListView.getSelectionModel().getSelectedItem();
        inputClassificationListView.getItems().remove(inputItem);
        String deleteInputClassificationSql = "delete from tb_classification where cName='" + inputItem + "';";
        new ClassificationDao().setTableData(deleteInputClassificationSql);
    }

    // 收入分类界面”返回“按钮的事件监听器
    public void inputClassificationReturnButtonEvent(ActionEvent event) {
        dialogStage.close();
    }

    // 支出分类列表视图编辑完成的事件监听器
    public void outputClassificationListViewCommitEvent(ListView.EditEvent<String> stringEditEvent) {
        String sourceValue = stringEditEvent.getSource().getSelectionModel().getSelectedItem();
        String newValue = stringEditEvent.getNewValue();
        outputClassificationListView.getItems().remove(sourceValue);
        outputClassificationListView.getItems().add(newValue);
        String alterOutputClassificationSql =
                "update tb_classification set cName='" + newValue + "' where cName='" + sourceValue +
                "';";
        new ClassificationDao().setTableData(alterOutputClassificationSql);
    }

    // 收入分类列表视图编辑完成的事件监听器
    // 按回车键完成对事件的响应
    public void inputClassificationListViewCommitEvent(ListView.EditEvent<String> stringEditEvent) {
        String sourceValue = stringEditEvent.getSource().getSelectionModel().getSelectedItem();
        String newValue = stringEditEvent.getNewValue();
        inputClassificationListView.getItems().remove(sourceValue);
        inputClassificationListView.getItems().add(newValue);
        String alterInputClassificationSql =
                "update tb_classification set cName='" + newValue + "' where cName='" + sourceValue +
                        "';";
        new ClassificationDao().setTableData(alterInputClassificationSql);
    }

}
