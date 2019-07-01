package MainSendSystem.controller;

import MainSendSystem.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SendAccountOptionFrameController {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextField addresserTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField serverTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    public void initialize() {
/**
 * 打包使用的路径
 //        String imagePath = new SimpleTools().getJARPath();
 //        new SimpleTools().setLabeledImage(new Labeled[]{saveButton, cancelButton}, new String[]{
 //                imagePath + "\\images\\save.png", imagePath + "\\images\\cancel.png"});
 */
        new SimpleTools().setLabeledImage(new Labeled[]{saveButton, cancelButton}, new String[]{
                "src/MainSendSystem/images/save.png", "src/MainSendSystem/images/cancel.png"});
    }

    @FXML
        // 【保存】按钮的事件监听器
    void do_saveButton_event(ActionEvent event) {
        String addresser = addresserTextField.getText();
        String password = passwordField.getText();
        String server = serverTextField.getText();
        Map dataMap = new HashMap();
        dataMap.put("addresser", addresser);
        dataMap.put("password", password);
        dataMap.put("server", server);
/**
 * 打包使用路径
 //        String imagePath = new SimpleTools().getJARPath();
 //        new SimpleTools().dataWriteProperties(imagePath +
 //                "/properties/data.properties", dataMap);
 */
        new SimpleTools().dataWriteProperties("src/MainSendSystem/properties/data.properties",dataMap);
    }

    @FXML
        // 【取消】按钮的事件监听器
    void do_ccancelButton_event(ActionEvent event) {
        stage.close();
    }

}
