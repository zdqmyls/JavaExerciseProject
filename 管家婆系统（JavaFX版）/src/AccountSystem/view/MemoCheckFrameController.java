package AccountSystem.view;

import AccountSystem.bean.TableData;
import AccountSystem.tools.PublicTools;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MemoCheckFrameController {
    private PublicTools publicTools=new PublicTools();

    @FXML // fx:id="memo_checkButton"
    private Button memo_checkButton; // Value injected by FXMLLoader

    @FXML // fx:id="memo_classificationColumn"
    private TableColumn<TableData,String> memo_classificationColumn; // Value injected by FXMLLoader

    @FXML // fx:id="memo_moneyColumn"
    private TableColumn<TableData,String> memo_moneyColumn; // Value injected by FXMLLoader

    @FXML // fx:id="memo_dateColumn"
    private TableColumn<TableData,String> memo_dateColumn; // Value injected by FXMLLoader

    @FXML // fx:id="memo_memoTextField"
    private TextField memo_memoTextField; // Value injected by FXMLLoader

    @FXML // fx:id="memo_tableView"
    private TableView<TableData> memo_tableView; // Value injected by FXMLLoader

    @FXML // fx:id="memo_addContextMenu"
    private MenuItem memo_addContextMenu; // Value injected by FXMLLoader

    @FXML // fx:id="memo_deleteContextMenu"
    private MenuItem memo_deleteContextMenu; // Value injected by FXMLLoader

    @FXML // fx:id="memo_alterContextMenu"
    private MenuItem memo_alterContextMenu; // Value injected by FXMLLoader

    @FXML // fx:id="memo_idColumn"
    private TableColumn<TableData,String> memo_idColumn; // Value injected by FXMLLoader

    @FXML // fx:id="memo_typeColumn"
    private TableColumn<TableData,String> memo_typeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="memo_memoColumn"
    private TableColumn<TableData,String> memo_memoColumn; // Value injected by FXMLLoader

    @FXML // ”查询“按钮的事件监听方法
    public void memo_checkButtonEvent(ActionEvent event) {
        String memo=memo_memoTextField.getText();
        String sql="select rId,rType,rMoney,rClassification,rMemo,rDate from tb_records where rMemo like '%"+memo+"%';";
        ObservableList<TableData> data=publicTools.public_tableViewData(sql,
                memo_idColumn,
                memo_typeColumn,
                memo_moneyColumn,
                memo_classificationColumn,
                memo_memoColumn,
                memo_dateColumn);
        memo_tableView.setItems(data);
    }

    /**
     * 操作结果：”删除“右键菜单的事件方法
     */
    public void deleteContextMenuEvent(ActionEvent event) {
        publicTools.public_do_deleteContextMenu_Event(memo_tableView);
    }

    /**
     * 操作结果：”添加“右键菜单的事件方法
     */
    public void addContextMenuEvent(ActionEvent event) {
        publicTools.public_do_addContextMenu_Event(memo_tableView);
    }

    /**
     * 操作结果：”修改“右键菜单的事件方法
     */
    public void alterContextMenuEvent(ActionEvent event) {
        publicTools.public_do_alterContextMenu_Event(memo_tableView);
    }

}
