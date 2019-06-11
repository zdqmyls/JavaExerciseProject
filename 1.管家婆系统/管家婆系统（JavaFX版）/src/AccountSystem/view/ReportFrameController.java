package AccountSystem.view;

import AccountSystem.bean.Session;
import AccountSystem.bean.Users;
import AccountSystem.dao.RecordsDao;
import AccountSystem.tools.DateTools;
import AccountSystem.tools.PublicTools;
import AccountSystem.tools.SimpleTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.Date;

import static AccountSystem.tools.SimpleTools.*;

public class ReportFrameController {
    private SimpleTools simpleTools = new SimpleTools();
    private RecordsDao recordsDao = new RecordsDao();
    private PublicTools publicTools = new PublicTools();
    private DateTools dateTools=new DateTools();

    @FXML // fx:id="thisYearOutputShowLabel"
    private Label thisYearOutputShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisMonthComboBox"
    private ComboBox<?> thisMonthComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="thisDayInputShowLabel"
    private Label thisDayInputShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisWeekPieChart"
    private PieChart thisWeekPieChart; // Value injected by FXMLLoader

    @FXML // fx:id="thisDayComboBox"
    private ComboBox<?> thisDayComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="thisYearComboBox"
    private ComboBox<?> thisYearComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="thisMonthBalanceShowLabel"
    private Label thisMonthBalanceShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisSeasonBalanceShowLabel"
    private Label thisSeasonBalanceShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisMonthOutputShowLabel"
    private Label thisMonthOutputShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisDayBalanceShowLabel"
    private Label thisDayBalanceShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisSeasonComboBox"
    private ComboBox<?> thisSeasonComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="thisMonthInputShowLabel"
    private Label thisMonthInputShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisSeasonPieChart"
    private PieChart thisSeasonPieChart; // Value injected by FXMLLoader

    @FXML // fx:id="thisDayOutputShowLabel"
    private Label thisDayOutputShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisWeekInputShowLabel"
    private Label thisWeekInputShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisYearBalanceShowLabel"
    private Label thisYearBalanceShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisYearPieChart"
    private PieChart thisYearPieChart; // Value injected by FXMLLoader

    @FXML // fx:id="thisYearInputShowLabel"
    private Label thisYearInputShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisDayPieChart"
    private PieChart thisDayPieChart; // Value injected by FXMLLoader

    @FXML // fx:id="thisWeekOutputShowLabel"
    private Label thisWeekOutputShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisWeekShowBalanceLabel"
    private Label thisWeekShowBalanceLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisWeekComboBox"
    private ComboBox<?> thisWeekComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="thisSeasonOutputShowLabel"
    private Label thisSeasonOutputShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisSeasonInputShowLabel"
    private Label thisSeasonInputShowLabel; // Value injected by FXMLLoader

    @FXML // fx:id="thisMonthPieChart"
    private PieChart thisMonthPieChart; // Value injected by FXMLLoader

    @FXML // 初始化界面
    public void initialize() {
        publicTools.public_addComboBoxItems(thisDayComboBox, new String[]{"本日"});
        publicTools.public_addComboBoxItems(thisWeekComboBox, new String[]{"本周"});
        publicTools.public_addComboBoxItems(thisMonthComboBox, new String[]{"本月"});
        publicTools.public_addComboBoxItems(thisSeasonComboBox, new String[]{"本季度"});
        publicTools.public_addComboBoxItems(thisYearComboBox, new String[]{"本年"});
    }

    // ”日报告“界面的下拉列表框的事件监听器
    public void thisDayComboBoxEvent(ActionEvent event) {
        String selectedCoboboxItem = (String) thisDayComboBox.getSelectionModel().selectedItemProperty().getValue();
        if (selectedCoboboxItem.equals("本日")) {
            setReportFrame(new Session().getUsers()
                    , new Date()
                    , new Date()
                    , publicTools.public_getBalanceByUsers(new Session().getUsers())
                    , thisDayPieChart
                    , thisDayOutputShowLabel
                    , thisDayInputShowLabel
                    , thisDayBalanceShowLabel);
        }
    }

    // ”周报告“界面的下拉列表框的事件监听器
    public void thisWeekComboBoxEvent(ActionEvent event) {
        String selectedCoboboxItem = (String) thisWeekComboBox.getSelectionModel().selectedItemProperty().getValue();
        if (selectedCoboboxItem.equals("本周")) {
            setReportFrame(new Session().getUsers()
                    , dateTools.getWeekDayStartTime(new Date())
                    , dateTools.getWeekDayEndTime(new Date())
                    , publicTools.public_getBalanceByUsers(new Session().getUsers())
                    , thisWeekPieChart
                    , thisWeekOutputShowLabel
                    , thisWeekInputShowLabel
                    , thisWeekShowBalanceLabel);
        }
    }

    // ”月报告“界面的下拉列表框的事件监听器
    public void thisMonthComboBoxEvent(ActionEvent event) {
        String selectedCoboboxItem = (String) thisMonthComboBox.getSelectionModel().selectedItemProperty().getValue();
        if (selectedCoboboxItem.equals("本月")) {
            setReportFrame(new Session().getUsers()
                    , dateTools.getMonthStartTime(new Date())
                    , dateTools.getMonthEndTime(new Date())
                    , publicTools.public_getBalanceByUsers(new Session().getUsers())
                    , thisMonthPieChart
                    , thisMonthOutputShowLabel
                    , thisMonthInputShowLabel
                    , thisMonthBalanceShowLabel);
        }
    }

    // ”季度报告“界面的下拉列表框的事件监听器
    public void thisSeasonComboBoxEvent(ActionEvent event) {
        String selectedCoboboxItem = (String) thisSeasonComboBox.getSelectionModel().selectedItemProperty().getValue();
        if (selectedCoboboxItem.equals("本季度")) {
            setReportFrame(new Session().getUsers()
                    , dateTools.getQuarterStartTime(new Date())
                    , dateTools.getQuarterEndTime(new Date())
                    , publicTools.public_getBalanceByUsers(new Session().getUsers())
                    , thisSeasonPieChart
                    , thisSeasonOutputShowLabel
                    , thisSeasonInputShowLabel
                    , thisSeasonBalanceShowLabel);
        }
    }

    // ”年报告“界面的下拉列表框的事件监听器
    public void thisYearComboBoxEvent(ActionEvent event) {
        String selectedCoboboxItem = (String) thisYearComboBox.getSelectionModel().selectedItemProperty().getValue();
        if (selectedCoboboxItem.equals("本年")) {
            setReportFrame(new Session().getUsers()
                    , dateTools.getYearStartTime(new Date())
                    , dateTools.getYearEndTime(new Date())
                    , publicTools.public_getBalanceByUsers(new Session().getUsers())
                    , thisYearPieChart
                    , thisYearOutputShowLabel
                    , thisYearInputShowLabel
                    , thisYearBalanceShowLabel);
        }
    }

    /**
     * 操作结果：设置饼图
     *
     * @param pieChart 饼图
     * @param input    收入
     * @param output   支出
     */
    public void setPieChartData(PieChart pieChart, Object input, Object output) {
        ObservableList observableList = FXCollections.observableArrayList(
                new PieChart.Data("收入", (Float) (input)),
                new PieChart.Data("支出", (Float) output)
        );

        pieChart.setClockwise(true);// 顺时针设置饼图的切片
        pieChart.setLabelLineLength(50);// 方法设置标签行的长度
        pieChart.setLabelsVisible(true);// 将饼图的标签设置为可见
        pieChart.setStartAngle(180);// 设置饼图的起始角度

        pieChart.setData(observableList);
    }

    /**
     * 操作结果：设置报告界面饼图数据
     *
     * @param users           用户对象
     * @param startDate       起始日期
     * @param endDate         终止日期
     * @param balance         余额
     * @param pieChart        饼图
     * @param outputShowLabel 支出显示标签
     * @param inputShowLabel  收入显示标签
     * @param balanceLabel    余额显示标签
     */
    public void setReportFrame(Users users, Date startDate, Date endDate, float balance, PieChart pieChart, Label outputShowLabel, Label inputShowLabel, Label balanceLabel) {
        String thisStartDate = dateTools.dateFormat(startDate, "yyyy-MM-dd");
        String thisEndDate = dateTools.dateFormat(endDate, "yyyy-MM-dd");
        String thisInputsql = "select SUM(rMoney) from tb_records where rType='收入' and rDate between '" + thisStartDate + "'" + " and " + "'" + thisEndDate + "' and uId=" + users.getUserId() + ";";
        String thisOutputsql = "select SUM(rMoney) from tb_records where rType='支出' and rDate between '" + thisStartDate + "'" + " and " + "'" + thisEndDate + "' and uId=" + users.getUserId() + ";";
        Object thisInput = recordsDao.getResultValue(thisInputsql).get(0);
        Object thisOutput = recordsDao.getResultValue(thisOutputsql).get(0);

        outputShowLabel.setText(String.valueOf(thisOutput));
        inputShowLabel.setText(String.valueOf(thisInput));
        balanceLabel.setText(String.valueOf(balance));
        setPieChartData(pieChart, thisInput, thisOutput);//设置饼图数据
    }

}
