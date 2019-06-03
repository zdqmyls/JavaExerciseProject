package AccountSystem.view;

import AccountSystem.bean.Records;
import AccountSystem.bean.Session;
import AccountSystem.bean.TableData;
import AccountSystem.dao.RecordsDao;
import AccountSystem.tools.PublicTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class dateCheckFrameController {
    private RecordsDao recordsDao=new RecordsDao();
    private PublicTools publicTools=new PublicTools();

    @FXML // fx:id="check_classificationColumn"
    private TableColumn<TableData,String> check_classificationColumn; // Value injected by FXMLLoader

    @FXML // fx:id="endDatePicker"
    private DatePicker endDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="check_idColumn"
    private TableColumn<TableData,String> check_idColumn; // Value injected by FXMLLoader

    @FXML // fx:id="check_typeColumn"
    private TableColumn<TableData,String> check_typeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="check_memoColumn"
    private TableColumn<TableData,String> check_memoColumn; // Value injected by FXMLLoader

    @FXML // fx:id="dateCheckButton"
    private Button dateCheckButton; // Value injected by FXMLLoader

    @FXML // fx:id="check_tableView"
    private TableView<TableData> check_tableView; // Value injected by FXMLLoader

    @FXML // fx:id="startDatePicker"
    private DatePicker startDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="check_moneyColumn"
    private TableColumn<TableData,String> check_moneyColumn; // Value injected by FXMLLoader

    @FXML // fx:id="check_dateColumn"
    private TableColumn<TableData,String> check_dateColumn; // Value injected by FXMLLoader

    @FXML // ”按日期查询“按钮的事件监听器
    public void dateCheckButtonEvent(ActionEvent event) {
        do_dateCheckButton_Event();
    }

    /**
     * 操作结果”按日期查询“按钮的事件方法
     */
    public void do_dateCheckButton_Event(){
        String startDate= String.valueOf(startDatePicker.getValue());
        String endDate= String.valueOf(endDatePicker.getValue());
        String sql="select rId,rType,rMoney,rClassification,rMemo,rDate from tb_records where rDate between '"+startDate+"' and '"+endDate+"';";
        ObservableList<TableData> data=publicTools.public_tableViewData(sql,check_idColumn,check_typeColumn,check_moneyColumn,check_classificationColumn,check_memoColumn,check_dateColumn);
        check_tableView.setItems(data);
    }
}
