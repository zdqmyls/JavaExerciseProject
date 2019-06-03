package AccountSystem.view;

import AccountSystem.bean.Records;
import AccountSystem.bean.Session;
import AccountSystem.bean.TableData;
import AccountSystem.dao.RecordsDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class TableViewController {
    private RecordsDao recordsDao=new RecordsDao();

    @FXML
    private TableColumn<TableData,String> check_classificationColumn;

    @FXML
    private TableColumn<TableData,String> check_typeColumn;

    @FXML
    private TableColumn<TableData,String> check_memoColumn;

    @FXML
    private TableView<TableData> check_tableView;

    @FXML
    private TableColumn<TableData,String> check_idColumn;

    @FXML
    private TableColumn<TableData,String> check_moneyColumn;

    @FXML
    private TableColumn<TableData,String> check_dateColumn;

    public void initialize(){
        addDataToTableView();// 初始化表格
    }

    /**
     * 操作结果：初始化数据表视图
     */
    public void addDataToTableView() {

        check_idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        check_typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        check_moneyColumn.setCellValueFactory(cellData -> cellData.getValue().moneyProperty());
        check_classificationColumn.setCellValueFactory(cellData -> cellData.getValue().classificationProperty());
        check_memoColumn.setCellValueFactory(cellData -> cellData.getValue().memoProperty());
        check_dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        List li = recordsDao.getAllTableDataByUser(new Session());
        ObservableList<TableData> data = FXCollections.observableArrayList();
        for (int i = 0; i < li.size(); i++) {
            Records r = (Records) li.get(i);
            TableData td = new TableData(String.valueOf(r.getRecordId()), r.getRecordType(), String.valueOf(r.getRecordMoney()), r.getRecordClassification(), r.getRecordMemo(), r.getRecordDate());
            data.add(td);
//            System.out.println("id="+r.getRecordId()+"\t\ttype="+r.getRecordType()+"\t\tmoney"+r.getRecordMoney()+"\t\tclassification="+r.getRecordClassification()+"\t\tmemo="+r.getRecordMemo()+"\t\tdate="+r.getRecordDate());
        }

        check_tableView.setItems(data);
        // 向表格中添加列
//        table.getColumns().addAll(idColumn, typeColumn, moneyColumn, classificationColumn, memoColumn, dateColumn);
    }
}
