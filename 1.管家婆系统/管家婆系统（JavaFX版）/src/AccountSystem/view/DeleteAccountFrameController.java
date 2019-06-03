package AccountSystem.view;

import AccountSystem.bean.Records;
import AccountSystem.dao.RecordsDao;
import AccountSystem.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteAccountFrameController {
    @FXML
    private TextField idTextField;

    @FXML
    private Button deleteButton;

    @FXML
    private Button checkButton;

    @FXML
    private Label contentLabel;

    @FXML
    // ”查询“按钮的事件监听器
    public void checkButtonEvent(ActionEvent actionEvent) {
        Records records=new Records();
        RecordsDao recordsDao=new RecordsDao();
        records.setRecordId(Integer.parseInt(idTextField.getText()));
        Records resultRecords=recordsDao.checkAccountItem(records);

        String info=
                 "类型：\t\t"+resultRecords.getRecordType()+"\n"
                +"金额：\t\t"+resultRecords.getRecordMoney()+"\n"
                +"分类：\t\t"+resultRecords.getRecordClassification()+"\n"
                +"备注：\t\t"+resultRecords.getRecordMemo()+"\n"
                +"日期：\t\t"+resultRecords.getRecordDate()+"\n";
        contentLabel.setText(info);
    }

    @FXML
    // ”删除“按钮的事件监听器
    public void deleteButtonEvent(ActionEvent actionEvent) {
        int id=Integer.parseInt(idTextField.getText());//将string类型数据转换为int类型数据
        Records records=new Records();
        records.setRecordId(id);
        RecordsDao recordsDao=new RecordsDao();
        SimpleTools simpleTools=new SimpleTools();
        boolean b=recordsDao.deleteAccountItem(records);
        if(b){
            boolean is=simpleTools.informationDialog(Alert.AlertType.INFORMATION,"提示","信息","删除数据成功！");
            // 删除成功后就清除窗体数据
            idTextField.setText("");
            contentLabel.setText("");
            if(is){
                /*
                    【点击”确定“后关闭窗体，打开主窗体浏览表格】
                 */
            }
        }else{
            simpleTools.informationDialog(Alert.AlertType.ERROR,"提示","错误","删除数据失败！");
        }
    }
}
