package AccountSystem.controller;

import AccountSystem.bean.TableData;
import AccountSystem.tools.PublicTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class dateCheckFrameController {
    private PublicTools publicTools = new PublicTools();

    @FXML // fx:id="check_classificationColumn"
    private TableColumn<TableData, String> check_classificationColumn; // Value injected by FXMLLoader

    @FXML // fx:id="endDatePicker"
    private DatePicker endDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="check_idColumn"
    private TableColumn<TableData, String> check_idColumn; // Value injected by FXMLLoader

    @FXML // fx:id="check_typeColumn"
    private TableColumn<TableData, String> check_typeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="check_memoColumn"
    private TableColumn<TableData, String> check_memoColumn; // Value injected by FXMLLoader

    @FXML // fx:id="check_tableView"
    private TableView<TableData> check_tableView; // Value injected by FXMLLoader

    @FXML // fx:id="startDatePicker"
    private DatePicker startDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="check_moneyColumn"
    private TableColumn<TableData, String> check_moneyColumn; // Value injected by FXMLLoader

    @FXML // fx:id="check_dateColumn"
    private TableColumn<TableData, String> check_dateColumn; // Value injected by FXMLLoader

    @FXML // ”按日期查询“按钮的事件监听器
    public void dateCheckButtonEvent(ActionEvent event) {
        String startDate = String.valueOf(startDatePicker.getValue());
        String endDate = String.valueOf(endDatePicker.getValue());
        String sql = "select rId,rType,rMoney,rClassification,rMemo,rDate from tb_records where rDate between '" + startDate + "' and '" + endDate + "';";
        publicTools.public_initTableViewData(check_tableView
                , publicTools.public_getTableViewData(sql)
                , check_idColumn
                , check_typeColumn
                , check_moneyColumn
                , check_classificationColumn
                , check_memoColumn
                , check_dateColumn);
    }
}
