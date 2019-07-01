package AccountSystem.controller;

import AccountSystem.bean.Records;
import AccountSystem.bean.TableData;
import AccountSystem.dao.RecordsDao;
import AccountSystem.tools.PublicTools;
import AccountSystem.tools.SimpleTools;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class alterAccountFrameController {
    private PublicTools publicTools = new PublicTools();

    @FXML
    private String selectedCoboboxItem = null;

    @FXML
    private String selectedRadioButton = null;

    @FXML
    private TextField idTextField;

    @FXML
    private RadioButton outputRadioButton;

    @FXML
    private Button alterButton;

    @FXML
    private TextField moneyTextField;

    @FXML
    private DatePicker datePickerText;

    @FXML
    private RadioButton inputRadioButton;

    @FXML
    private TextArea memoTextArea;

    @FXML
    private ComboBox<?> classificationComboBox;

    @FXML// “支出”单选按钮的事件监听器
    public void outputRadioButtonEvent(ActionEvent actionEvent) throws IOException {
        publicTools.public_addComboBoxItems(classificationComboBox,
                publicTools.public_getOutputClassification());
        selectedRadioButton = outputRadioButton.getText();// 获取单选按钮项
    }

    @FXML// “收入”单选按钮的事件监听器
    public void inputRadioButtonEvent(ActionEvent actionEvent) throws IOException {
        publicTools.public_addComboBoxItems(classificationComboBox,
                publicTools.public_getInputClassification());
        selectedRadioButton = inputRadioButton.getText();// 获取单选按钮项
    }

    @FXML// ”分类“下拉列表框的事件监听器
    public void classificationComboBoxEvent(ActionEvent actionEvent) {
        //只处理选中的状态
        selectedCoboboxItem = (String) classificationComboBox.getSelectionModel().selectedItemProperty().getValue();
    }

    @FXML // ”查询“按钮的事件监听器
    public void checkButtonEvent(ActionEvent actionEvent) throws IOException {
        Records records = new Records();
        RecordsDao recordsDao = new RecordsDao();
        records.setRecordId(Integer.parseInt(idTextField.getText()));
        Records resultRecords = recordsDao.checkAccountItem(records);

        // 使界面组件可编辑
        inputRadioButton.setDisable(false);
        outputRadioButton.setDisable(false);
        moneyTextField.setDisable(false);
        classificationComboBox.setDisable(false);
        memoTextArea.setDisable(false);
        datePickerText.setDisable(false);
        alterButton.setDisable(false);

        // 设置界面的值
        if (resultRecords.getRecordType().equals("支出")) { //设置类型
            outputRadioButton.setSelected(true);
        } else {
            inputRadioButton.setSelected(true);
        }

        if (outputRadioButton.isSelected()) {
            String[] s = publicTools.public_getOutputClassification();
            List list = Arrays.asList(s);
            classificationComboBox.setItems(FXCollections.observableArrayList(list));
        }
        if (inputRadioButton.isSelected()) {
            String[] s = new SimpleTools().objectOneArrayToOneArrayString(publicTools.public_getInputClassification());
            List list = Arrays.asList(s);
            classificationComboBox.setItems(FXCollections.observableArrayList(list));
        }

        //设置金额
        moneyTextField.setText(String.valueOf(resultRecords.getRecordMoney()));

        // 设置分类
        String str = resultRecords.getRecordClassification();
        int index = 0;
        if (resultRecords.getRecordType().equals("支出")) {
            List outputList = FXCollections.observableArrayList(publicTools.public_getOutputClassification());
            for (int i = 0; i < outputList.size(); i++) {
                if (str.equals(outputList.get(i))) {
                    index = i;
                }
            }
            classificationComboBox.getSelectionModel().select(index);//设置分类
        }
        if (resultRecords.getRecordType().equals("收入")) {
            List inputList = FXCollections.observableArrayList(publicTools.public_getInputClassification());
            for (int i = 0; i < inputList.size(); i++) {
                if (str.equals(inputList.get(i))) {
                    index = i;
                }
            }
            classificationComboBox.getSelectionModel().select(index);//设置分类
        }
        //设置备注
        memoTextArea.setText(resultRecords.getRecordMemo());
        //设置日期
        datePickerText.setValue(LocalDate.parse(resultRecords.getRecordDate()));
    }

    @FXML // ”更改“按钮的事件监听器
    public void alterButtonEvent(ActionEvent actionEvent) {
        // 序号
        int id = Integer.parseInt(idTextField.getText());
        //类型
        String type = null;
        if (inputRadioButton.isSelected()) {
            type = inputRadioButton.getText();
        } else if (outputRadioButton.isSelected()) {
            type = outputRadioButton.getText();
        }
        //金额
        float money = Float.parseFloat(moneyTextField.getText());//把从文本框得到的string类型数据转换为float类型
        //分类
        String classification = classificationComboBox.getSelectionModel().getSelectedItem().toString();
        //备注
        String memo = memoTextArea.getText();
        //日期
        String date = datePickerText.getValue().toString();
        Records records = new Records();
        records.setRecordId(id);
        records.setRecordType(type);
        records.setRecordMoney(money);
        records.setRecordClassification(classification);
        records.setRecordMemo(memo);
        records.setRecordDate(date);
        RecordsDao recordsDao = new RecordsDao();
        boolean b = recordsDao.alterAccountItem(records);
        if (b) {
            new SimpleTools().informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "修改账目成功！");
        } else {
            new SimpleTools().informationDialog(Alert.AlertType.ERROR, "提示", "错误", "修改账目失败！");
        }
    }

    // 设置用户数据当选中某一行后
    public void setTableData(TableData tableData) throws IOException {
        // 使界面组件可编辑
        inputRadioButton.setDisable(false);
        outputRadioButton.setDisable(false);
        moneyTextField.setDisable(false);
        classificationComboBox.setDisable(false);
        memoTextArea.setDisable(false);
        datePickerText.setDisable(false);
        alterButton.setDisable(false);

        idTextField.setText(tableData.getId());
        if (tableData.getType().equals("支出")) {
            outputRadioButton.setSelected(true);
        } else {
            inputRadioButton.setSelected(true);
        }
        if (outputRadioButton.isSelected()) {
            String[] s = publicTools.public_getOutputClassification();
            List list = Arrays.asList(s);
            classificationComboBox.setItems(FXCollections.observableArrayList(list));
        }
        if (inputRadioButton.isSelected()) {
            String[] s = new SimpleTools().objectOneArrayToOneArrayString(publicTools.public_getInputClassification());
            List list = Arrays.asList(s);
            classificationComboBox.setItems(FXCollections.observableArrayList(list));
        }
        moneyTextField.setText(tableData.getMoney());
        // 设置分类
        String str = tableData.getClassification();
        int index = 0;
        if (tableData.getType().equals("支出")) {
            List outputList = FXCollections.observableArrayList(publicTools.public_getOutputClassification());
            for (int i = 0; i < outputList.size(); i++) {
                if (str.equals(outputList.get(i))) {
                    index = i;
                }
            }
            classificationComboBox.getSelectionModel().select(index);//设置分类
        }
        if (tableData.getType().equals("收入")) {
            List inputList = FXCollections.observableArrayList(publicTools.public_getInputClassification());
            for (int i = 0; i < inputList.size(); i++) {
                if (str.equals(inputList.get(i))) {
                    index = i;
                }
            }
            classificationComboBox.getSelectionModel().select(index);//设置分类
        }
        memoTextArea.setText(tableData.getMemo());
        datePickerText.setValue(LocalDate.parse(tableData.getDate()));
    }
}
