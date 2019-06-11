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

import java.util.Calendar;
import java.util.Date;

public class PieChartFrameController {
    private SimpleTools simpleTools = new SimpleTools();
    private RecordsDao recordsDao = new RecordsDao();
    private PublicTools publicTools = new PublicTools();
    private DateTools dateTools=new DateTools();

    @FXML // fx:id="pieChart_comboBox"
    private ComboBox<?> pieChart_comboBox; // Value injected by FXMLLoader

    @FXML // fx:id="pieChart"
    private PieChart pieChart; // Value injected by FXMLLoader

    @FXML // 初始化界面
    public void initialize() {
        initComboBox();// 初始化下拉列表框
    }

    // 初始化下拉列表框
    public void initComboBox() {
        publicTools.public_addComboBoxItems(pieChart_comboBox, new String[]{"今天", "昨天", "最近1周收入", "最近1周支出", "最近1年支出（按月份）", "最近1年收入（按月份）", "最近1年支出（按季度）", "最近1年收入（按季度）"});
    }

    @FXML // 饼图界面的下拉列表框的监听器方法
    public void pieChart_comboBoxEvent(ActionEvent event) {
        //只处理选中的状态
        String selectedCoboboxItem = (String) pieChart_comboBox.getSelectionModel().selectedItemProperty().getValue();
        if (selectedCoboboxItem.equals("今天")) {
            setReportFrame(new Session().getUsers(), new Date(), new Date(), publicTools.public_getBalanceByUsers(new Session().getUsers()), pieChart);
        } else if (selectedCoboboxItem.equals("昨天")) {
            setReportFrame(new Session().getUsers(), dateTools.getYesterdayDate(), dateTools.getYesterdayDate(), publicTools.public_getBalanceByUsers(new Session().getUsers()), pieChart);
        } else if (selectedCoboboxItem.equals("最近1周支出")) {
            publicTools.public_setWeekPieChartData(new Session().getUsers(), 7, pieChart, "支出");
        } else if (selectedCoboboxItem.equals("最近1周收入")) {
            publicTools.public_setWeekPieChartData(new Session().getUsers(), 7, pieChart, "收入");
        } else if (selectedCoboboxItem.equals("最近1年支出（按月份）")) {
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONDAY) + 1;
            publicTools.public_setMonthPieChartData(new Session().getUsers(), month, pieChart, "支出");
        } else if (selectedCoboboxItem.equals("最近1年收入（按月份）")) {
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONDAY) + 1;
            publicTools.public_setMonthPieChartData(new Session().getUsers(), month, pieChart, "收入");
        } else if (selectedCoboboxItem.equals("最近1年支出（按季度）")) {
            publicTools.public_setSeasonPieChartData(new Session().getUsers(), pieChart, "支出");
        } else if (selectedCoboboxItem.equals("最近1年收入（按季度）")) {
            publicTools.public_setSeasonPieChartData(new Session().getUsers(), pieChart, "收入");
        }

    }

    /**
     * 操作结果：设置报告界面饼图数据
     *
     * @param users     用户对象
     * @param startDate 起始日期
     * @param endDate   终止日期
     * @param balance   余额
     * @param pieChart  饼图
     */
    public void setReportFrame(Users users, Date startDate, Date endDate, float balance, PieChart pieChart) {
        String thisStartDate = dateTools.dateFormat(startDate, "yyyy-MM-dd");
        String thisEndDate = dateTools.dateFormat(endDate, "yyyy-MM-dd");
        String thisInputsql = "select SUM(rMoney) from tb_records where rType='收入' and rDate between '" + thisStartDate + "'" + " and " + "'" + thisEndDate + "' and uId=" + users.getUserId() + ";";
        String thisOutputsql = "select SUM(rMoney) from tb_records where rType='支出' and rDate between '" + thisStartDate + "'" + " and " + "'" + thisEndDate + "' and uId=" + users.getUserId() + ";";
        Object thisInput = recordsDao.getResultValue(thisInputsql).get(0);
        Object thisOutput = recordsDao.getResultValue(thisOutputsql).get(0);

        setPieChartData(pieChart, thisInput, thisOutput);//设置饼图数据
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

}
