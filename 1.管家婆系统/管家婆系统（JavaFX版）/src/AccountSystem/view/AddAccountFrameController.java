/**
 * Sample Skeleton for 'addAccountFrame.fxml' Controller Class
 */

package AccountSystem.view;

import AccountSystem.bean.Property;
import AccountSystem.bean.Records;
import AccountSystem.bean.Session;
import AccountSystem.dao.RecordsDao;
import AccountSystem.tools.SimpleTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.concurrent.locks.ReentrantLock;

public class AddAccountFrameController {

    @FXML
    private String selectedRadioButton=null;

    @FXML
    private String selectedCoboboxItem=null;

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

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // fx:id="classificationComboBox"
    private ComboBox<?> classificationComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="group"
    private ToggleGroup group; // Value injected by FXMLLoader

    @FXML
    // “添加”按钮的事件监听器
    public void addButtonEvent(ActionEvent actionEvent) {
        //类型
        String type=selectedRadioButton;
        //金额
        float money=Float.parseFloat(moneyTextField.getText());//把从文本框得到的string类型数据转换为float类型
        //分类
        String classification=selectedCoboboxItem;
        //备注
        String memo=memoTextArea.getText();
        //日期
        String date=datePickerTextField.getValue().toString();
        SimpleTools simpleTools=new SimpleTools();
        Records records=new Records();
        records.setRecordType(type);
        records.setRecordMoney(money);
        records.setRecordClassification(classification);
        records.setRecordMemo(memo);
        records.setRecordDate(date);
        RecordsDao recordsDao=new RecordsDao();
//        System.out.println("session="+new Session().getUsers().getUserName()+"\t\ttype="+records.getRecordType()+"\t\tmoney="+records.getRecordMoney()+"\t\tclassification="+records.getRecordClassification()+"\t\tmemo="+ records.getRecordMemo()+"\t\tdate="+records.getRecordDate());
//        System.out.println(records.getRecordDate().getClass());
        boolean b=recordsDao.addAccountRecords(new Session(),records);
        if(b){
            new SimpleTools().informationDialog(Alert.AlertType.INFORMATION,"提示","信息","添加账目成功！");
        }else{
            new SimpleTools().informationDialog(Alert.AlertType.ERROR,"提示","错误","添加账目失败！");
        }
    }

    // “支出”单选按钮的事件监听器
    public void outputRadioButtonEvent(ActionEvent actionEvent) {
        // 清除下列列表框中的所有选项
        classificationComboBox.getItems().clear();
        // 添加下拉列表项
        ObservableList options= FXCollections.observableArrayList(new Property().getOutputClassificationList());
        classificationComboBox.setItems(options);
        // 获取单选按钮项
        selectedRadioButton=outputRadioButton.getText();
        // 设置descriptionLabel文本内容
        descriptionLabel.setText("添加"+outputRadioButton.getText());
    }

    // “收入”单选按钮的事件监听器
    public void inputRadioButtonEvent(ActionEvent actionEvent) {
        // 清除下列列表框中的所有选项
        classificationComboBox.getItems().clear();
        // 添加下拉列表项
        ObservableList options= FXCollections.observableArrayList(new Property().getInputClassificationList());
        classificationComboBox.setItems(options);
        // 获取单选按钮项
        selectedRadioButton=inputRadioButton.getText();
        //设置descriptionLabel文本内容
        descriptionLabel.setText("添加"+inputRadioButton.getText());
    }

    // ”分类“下拉列表框的事件监听器
    public void classificationComboBoxEvent(ActionEvent actionEvent) {
        //只处理选中的状态
        selectedCoboboxItem=(String)classificationComboBox.getSelectionModel().selectedItemProperty().getValue();
    }
}
