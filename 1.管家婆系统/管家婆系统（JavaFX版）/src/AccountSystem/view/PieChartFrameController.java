package AccountSystem.view;

import AccountSystem.dao.RecordsDao;
import AccountSystem.tools.PublicTools;
import AccountSystem.tools.SimpleTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PieChartFrameController {
    private SimpleTools simpleTools=new SimpleTools();
    private RecordsDao recordsDao=new RecordsDao();
    private PublicTools publicTools=new PublicTools();

    @FXML // fx:id="pieChart_comboBox"
    private ComboBox<?> pieChart_comboBox; // Value injected by FXMLLoader

    @FXML // fx:id="pieChart"
    private PieChart pieChart; // Value injected by FXMLLoader

    @FXML // 初始化界面
    public void initialize() {
        initComboBox();// 初始化下拉列表框
    }

    /**
     * 操作结果：初始化下拉列表框
     */
    public void initComboBox() {
        // 清除下列列表框中的所有选项
        pieChart_comboBox.getItems().clear();
        // 添加下拉列表项
        ObservableList options = FXCollections.observableArrayList(  "今天", "昨天","最近1周收入","最近1周支出", "最近1年支出（按月份）","最近1年收入（按月份）","最近1年支出（按季度）", "最近1年收入（按季度）");
        pieChart_comboBox.setItems(options);
    }

    @FXML // 饼图界面的下拉列表框的监听器方法
    public void pieChart_comboBoxEvent(ActionEvent event) {
        //只处理选中的状态
        String selectedCoboboxItem = (String) pieChart_comboBox.getSelectionModel().selectedItemProperty().getValue();
        if (selectedCoboboxItem.equals("今天")) {
            String todayDate = simpleTools.dateFormat(new Date(), "yyyy-MM-dd");
            String todayInputsql = "select SUM(rMoney) from tb_records where rType='收入' and rDate= '" + todayDate + "';";
            String todayOutputsql = "select SUM(rMoney) from tb_records where rType='支出' and rDate= '" + todayDate + "';";
            Object todayInput = recordsDao.getResultValue(todayInputsql).get(0);
            Object todayOutput = recordsDao.getResultValue(todayOutputsql).get(0);

            ObservableList observableList=FXCollections.observableArrayList(
                    new PieChart.Data("收入", (Float)(todayInput)),
                    new PieChart.Data("支出", (Float) todayOutput)
            );

            pieChart.setTitle("饼图");// 设置饼图的标题
            pieChart.setClockwise(true);// 顺时针设置饼图的切片
            pieChart.setLabelLineLength(50);// 方法设置标签行的长度
            pieChart.setLabelsVisible(true);// 将饼图的标签设置为可见
            pieChart.setStartAngle(180);// 设置饼图的起始角度

            pieChart.setData(observableList);
        }else if(selectedCoboboxItem.equals("昨天")){
            String yesterdayDate = simpleTools.dateFormat(simpleTools.getYesterdayDate(), "yyyy-MM-dd");
            String yesterdayInputsql = "select SUM(rMoney) from tb_records where rType='收入' and rDate= '" + yesterdayDate + "';";
            String yesterdayOutputsql = "select SUM(rMoney) from tb_records where rType='支出' and rDate= '" + yesterdayDate + "';";
            Object yesterdayInput = recordsDao.getResultValue(yesterdayInputsql).get(0);
            Object yesterdayOutput = recordsDao.getResultValue(yesterdayOutputsql).get(0);

            ObservableList observableList=FXCollections.observableArrayList(
                    new PieChart.Data("收入", (Float)(yesterdayInput)),
                    new PieChart.Data("支出", (Float) yesterdayOutput)
            );

            pieChart.setTitle("饼图");// 设置饼图的标题
            pieChart.setClockwise(true);// 顺时针设置饼图的切片
            pieChart.setLabelLineLength(50);// 方法设置标签行的长度
            pieChart.setLabelsVisible(true);// 将饼图的标签设置为可见
            pieChart.setStartAngle(180);// 设置饼图的起始角度

            pieChart.setData(observableList);
        }else if(selectedCoboboxItem.equals("最近1周支出")){
            publicTools.public_setPieChartData(7,pieChart,"支出");
        }else if(selectedCoboboxItem.equals("最近1周收入")){
            publicTools.public_setPieChartData(7,pieChart,"收入");
        } else if(selectedCoboboxItem.equals("最近1年支出（按月份）")){
            Calendar calendar=Calendar.getInstance();
            int month=calendar.get(Calendar.MONDAY)+1;
            publicTools.public_setMonthPieChartData(month, pieChart,"支出");
        }else if(selectedCoboboxItem.equals("最近1年收入（按月份）")){
            Calendar calendar=Calendar.getInstance();
            int month=calendar.get(Calendar.MONDAY)+1;
            publicTools.public_setMonthPieChartData(month, pieChart,"收入");
        }else if(selectedCoboboxItem.equals("最近1年支出（按季度）")){
            publicTools.public_setSeasonPieChartData(pieChart,"支出");
        }else if(selectedCoboboxItem.equals("最近1年收入（按季度）")){
            publicTools.public_setSeasonPieChartData(pieChart,"收入");
        }

    }
}
