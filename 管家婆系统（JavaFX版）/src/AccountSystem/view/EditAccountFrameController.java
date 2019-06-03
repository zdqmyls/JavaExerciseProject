package AccountSystem.view;

import AccountSystem.bean.Property;
import AccountSystem.bean.Records;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class EditAccountFrameController {

    private Stage dialogStage;
    private String selectedCoboboxItem=null;
    private String selectedRadioButton=null;
    private Records records;

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records records) {
        this.records = records;
    }

    @FXML
    private TextField idTextField;

    @FXML
    private RadioButton outputRadioButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField moneyTextField;

    @FXML
    private DatePicker datePickerText;

    @FXML
    private RadioButton inputRadioButton;

    @FXML
    private TextArea memoTextArea;

    @FXML
    private Button okButton;

    @FXML
    private ComboBox<?> classificationComboBox;

    @FXML
    private ToggleGroup group;
    
    public void outputRadioButtonEvent(ActionEvent actionEvent) {
        // 清除下列列表框中的所有选项
        classificationComboBox.getItems().clear();
        // 添加下拉列表项
        ObservableList options= FXCollections.observableArrayList(new Property().getOutputClassificationList());
        classificationComboBox.setItems(options);
        // 获取单选按钮项
        selectedRadioButton=outputRadioButton.getText();
    }

    public void inputRadioButtonEvent(ActionEvent actionEvent) {
        // 清除下列列表框中的所有选项
        classificationComboBox.getItems().clear();
        // 添加下拉列表项
        ObservableList options= FXCollections.observableArrayList(new Property().getInputClassificationList());
        classificationComboBox.setItems(options);
        // 获取单选按钮项
        selectedRadioButton=inputRadioButton.getText();
    }

    public void classificationComboBoxEvent(ActionEvent actionEvent) {
        //只处理选中的状态
        selectedCoboboxItem=(String)classificationComboBox.getSelectionModel().selectedItemProperty().getValue();
    }

    // ”OK“按钮的事件监听器
    public void okButtonEvent(ActionEvent actionEvent) {

    }

    // ”cancel“按钮的事件监听器
    public void cancelButtonEvent(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public void initialize(){
        setEditValue(new EditAccountFrameController().getRecords());
    }

    public void setEditValue(Records records){
        // 设置分类
//        if (records.getRecordType().equals("支出")) { //设置类型
//            outputRadioButton.setSelected(true);
//        } else {
//            inputRadioButton.setSelected(true);
//        }
//
//        if (outputRadioButton.isSelected()) {
//            classificationComboBox.setItems(FXCollections.observableArrayList(new Property().getOutputClassificationList()));
//        }
//        if (inputRadioButton.isSelected()) {
//            classificationComboBox.setItems(FXCollections.observableArrayList(new Property().getInputClassificationList()));
//        }

        // 设置分类
        String str = records.getRecordClassification();
        int index=0;
        if(records.getRecordType().equals("支出")){
            List outputList=FXCollections.observableArrayList(new Property().getOutputClassificationList());
            for(int i=0;i<outputList.size();i++){
                if(str.equals(outputList.get(i))){
                    index=i;
                }
            }
            classificationComboBox.getSelectionModel().select(index);//设置分类
        }
        if(records.getRecordType().equals("收入")){
            List inputList=FXCollections.observableArrayList(new Property().getInputClassificationList());
            for(int i=0;i<inputList.size();i++){
                if(str.equals(inputList.get(i))){
                    index=i;
                }
            }
            classificationComboBox.getSelectionModel().select(index);//设置分类
        }
        // 设置序号
        idTextField.setText(String.valueOf(records.getRecordId()));
        // 设置金额
        moneyTextField.setText(String.valueOf(records.getRecordMoney()));
        // 设置备注
        memoTextArea.setText(records.getRecordMemo());
        // 设置日期
        datePickerText.setValue(LocalDate.parse(records.getRecordDate()));
    }

}
