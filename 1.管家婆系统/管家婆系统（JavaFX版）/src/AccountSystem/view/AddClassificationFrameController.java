package AccountSystem.view;

import AccountSystem.bean.Property;
import AccountSystem.tools.PublicTools;
import AccountSystem.tools.SimpleTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AddClassificationFrameController {
    private Property property = new Property();
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
    public void initialize() throws IOException {
        List inputList = property.getInputClassificationList();
        ObservableList inputObservableList = FXCollections.observableList(inputList);
        inputClassificationListView.setItems(inputObservableList);

        List outputList = property.getOutputClassificationList();
        ObservableList outputObservableList = FXCollections.observableList(outputList);
        outputClassificationListView.setItems(outputObservableList);
    }

    // ”添加支出“按钮的事件监听器
    public void addOutputButtonEvent(ActionEvent event) throws IOException {
        String output = outputClassificationNameTextField.getText();
        List outputList = property.getOutputClassificationList();
        outputList.add(output);
        if (null == output || "".equals(output)) {
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "文本框内容不能为空！");
        } else {
            outputClassificationListView.getItems().add(output);
        }

    }

    // “支出”右键菜单“编辑”的事件监听器
    public void output_editContextMenuEvent(ActionEvent event) {
        outputClassificationListView.setCellFactory(TextFieldListCell.forListView());
        outputClassificationListView.setEditable(true);
        outputClassificationListView.setFocusTraversable(true);
    }

    // “支出”右键菜单“删除”的事件监听器
    public void output_deleteMenuItemEvent(ActionEvent event) throws IOException {
        String outputItem = (String) outputClassificationListView.getSelectionModel().getSelectedItem();
        outputClassificationListView.getItems().remove(outputItem);
    }

    // 支出分类界面”返回“按钮的事件监听器
    public void outputClassificationReturnButtonEvent(ActionEvent event) {
        dialogStage.close();
    }

    // ”添加收入“按钮的事件监听器
    public void addInputButtonEvent(ActionEvent event) throws IOException {
        String input = inputClassificationNameTextField.getText();
        List inputList = property.getInputClassificationList();
        inputList.add(input);
        if (null == input || "".equals(input)) {
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "文本框内容不能为空！");
        } else {
            inputClassificationListView.getItems().add(input);
        }
    }

    // “收入”右键菜单“编辑”的事件监听器
    public void input_editContextMenuEvent(ActionEvent event) {
        inputClassificationListView.setCellFactory(TextFieldListCell.forListView());
        inputClassificationListView.setEditable(true);
        inputClassificationListView.setFocusTraversable(true);
    }

    // “收入”右键菜单“删除”的事件监听器
    public void input_deleteContextMenuEvent(ActionEvent event) throws IOException {
        String inputItem = (String) inputClassificationListView.getSelectionModel().getSelectedItem();
        inputClassificationListView.getItems().remove(inputItem);
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
    }

    // 收入分类列表视图编辑完成的事件监听器
    public void inputClassificationListViewCommitEvent(ListView.EditEvent<String> stringEditEvent) {
        String sourceValue = stringEditEvent.getSource().getSelectionModel().getSelectedItem();
        String newValue = stringEditEvent.getNewValue();
        inputClassificationListView.getItems().remove(sourceValue);
        inputClassificationListView.getItems().add(newValue);
    }

}
