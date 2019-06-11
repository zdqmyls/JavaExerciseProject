package AccountSystem.view;

import AccountSystem.bean.Property;
import AccountSystem.bean.Records;
import AccountSystem.bean.Session;
import AccountSystem.dao.RecordsDao;
import AccountSystem.tools.PublicTools;
import AccountSystem.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AddAccountFrameController {
    private PublicTools publicTools = new PublicTools();

    @FXML
    private String selectedRadioButton = null;

    @FXML
    private String selectedCoboboxItem = null;

    @FXML // fx:id="addLabel"
    private Label descriptionLabel; // Value injected by FXMLLoader

    @FXML // fx:id="outputRadioButton"
    private RadioButton outputRadioButton; // Value injected by FXMLLoader

    @FXML // fx:id="datePickerTextField"
    private DatePicker datePickerTextField; // Value injected by FXMLLoader

    @FXML // fx:id="moneyTextField"
    private TextField moneyTextField; // Value injected by FXMLLoader

    @FXML // fx:id="inputRadioButton"
    private RadioButton inputRadioButton; // Value injected by FXMLLoader

    @FXML // fx:id="memoTextArea"
    private TextArea memoTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="classificationComboBox"
    private ComboBox<?> classificationComboBox; // Value injected by FXMLLoader

    @FXML// “添加”按钮的事件监听器
    public void addButtonEvent(ActionEvent actionEvent) {
        //类型
        String type = selectedRadioButton;
        //金额
        float money = Float.parseFloat(moneyTextField.getText());//把从文本框得到的string类型数据转换为float类型
        //分类
        String classification = selectedCoboboxItem;
        //备注
        String memo = memoTextArea.getText();
        //日期
        String date = datePickerTextField.getValue().toString();
        SimpleTools simpleTools = new SimpleTools();
        Records records = new Records();
        records.setRecordType(type);
        records.setRecordMoney(money);
        records.setRecordClassification(classification);
        records.setRecordMemo(memo);
        records.setRecordDate(date);
        RecordsDao recordsDao = new RecordsDao();
        boolean b = recordsDao.addAccountRecords(new Session(), records);
        if (b) {
            boolean isSuccess = new SimpleTools().informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "添加账目成功！");
        } else {
            new SimpleTools().informationDialog(Alert.AlertType.ERROR, "提示", "错误", "添加账目失败！");
        }
    }

    // “支出”单选按钮的事件监听器
    public void outputRadioButtonEvent(ActionEvent actionEvent) throws IOException {
        publicTools.public_addComboBoxItems(classificationComboBox, new Property().getOutputClassificationList().toArray());
        selectedRadioButton = outputRadioButton.getText();// 获取单选按钮项
        descriptionLabel.setText("添加" + outputRadioButton.getText());// 设置descriptionLabel文本内容
    }

    // “收入”单选按钮的事件监听器
    public void inputRadioButtonEvent(ActionEvent actionEvent) throws IOException {
        publicTools.public_addComboBoxItems(classificationComboBox, new Property().getInputClassificationList().toArray());
        selectedRadioButton = inputRadioButton.getText();// 获取单选按钮项
        descriptionLabel.setText("添加" + inputRadioButton.getText());//设置descriptionLabel文本内容
    }

    // ”分类“下拉列表框的事件监听器
    public void classificationComboBoxEvent(ActionEvent actionEvent) {
        //只处理选中的状态
        selectedCoboboxItem = (String) classificationComboBox.getSelectionModel().selectedItemProperty().getValue();
    }
}
