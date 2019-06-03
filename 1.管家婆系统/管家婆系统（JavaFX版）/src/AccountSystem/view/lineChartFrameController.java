package AccountSystem.view;

import AccountSystem.dao.RecordsDao;
import AccountSystem.tools.PublicTools;
import AccountSystem.tools.SimpleTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class lineChartFrameController {
    private PublicTools publicTools=new PublicTools();
    private RecordsDao recordsDao=new RecordsDao();
    private SimpleTools simpleTools=new SimpleTools();

    @FXML // fx:id="categoryAxis"
    private CategoryAxis categoryAxis; // Value injected by FXMLLoader

    @FXML // fx:id="lineChart_comboBox"
    private ComboBox<?> lineChart_comboBox; // Value injected by FXMLLoader

    @FXML // fx:id="lineChart"
    private LineChart<?,?> lineChart; // Value injected by FXMLLoader

    @FXML // fx:id="numberAxis"
    private NumberAxis numberAxis; // Value injected by FXMLLoader

    @FXML // 初始化界面
    public void initialize() {
        initComboBox();// 初始化下拉列表框
    }

    /**
     * 操作结果：初始化下拉列表框
     */
    public void initComboBox() {
        // 清除下列列表框中的所有选项
        lineChart_comboBox.getItems().clear();
        // 添加下拉列表项
        ObservableList options = FXCollections.observableArrayList(  "最近3天", "最近7天", "最近30天", "最近1年（12月）", "最近1年（4季度）");
        lineChart_comboBox.setItems(options);
    }

    // 折线图界面下拉列表框事件方法
    public void lineChart_comboBoxEvent(ActionEvent event) {
        //只处理选中的状态
        String selectedCoboboxItem = (String) lineChart_comboBox.getSelectionModel().selectedItemProperty().getValue();
        if (selectedCoboboxItem.equals("最近3天")) {
            publicTools.public_setLineChartData(3,lineChart,categoryAxis,numberAxis);
        }else if(selectedCoboboxItem.equals("最近7天")){
            publicTools.public_setLineChartData(7,lineChart,categoryAxis,numberAxis);
        }else if(selectedCoboboxItem.equals("最近30天")){
            publicTools.public_setLineChartData(30,lineChart,categoryAxis,numberAxis);
        }else if(selectedCoboboxItem.equals("最近1年（12月）")){
            Calendar calendar=Calendar.getInstance();
            int month=calendar.get(Calendar.MONDAY)+1;
            publicTools.public_setMonthLineChartData(month,lineChart,categoryAxis,numberAxis);
        }else if(selectedCoboboxItem.equals("最近1年（4季度）")){
            categoryAxis.setLabel("日期");
            numberAxis.setLabel("金额");
            XYChart.Series seasonInputSeries = new XYChart.Series();
            seasonInputSeries.setName("收入");

            XYChart.Series seasonOutputSeries = new XYChart.Series();
            seasonOutputSeries.setName("支出");

            List<Date[]> list = simpleTools.yearQuarterList(new Date());
            Date[] date1 = list.get(0);
            Date[] date2 = list.get(1);
            Date[] date3 = list.get(2);
            Date[] date4 = list.get(3);

            String strdate1 = simpleTools.dateFormat(date1[0], "yyyy-MM-dd") + "-" + simpleTools.dateFormat(date1[1], "yyyy-MM-dd");
            String strdate2 = simpleTools.dateFormat(date2[0], "yyyy-MM-dd") + "-" + simpleTools.dateFormat(date2[1], "yyyy-MM-dd");
            String strdate3 = simpleTools.dateFormat(date3[0], "yyyy-MM-dd") + "-" + simpleTools.dateFormat(date3[1], "yyyy-MM-dd");
            String strdate4 = simpleTools.dateFormat(date4[0], "yyyy-MM-dd") + "-" + simpleTools.dateFormat(date4[1], "yyyy-MM-dd");

            categoryAxis.setCategories(FXCollections.observableArrayList(
                    strdate1,strdate2,strdate3,strdate4
            ));

            String seasonInputSql1 = "select SUM(rMoney) from tb_records where rType='收入' and rDate between '" + simpleTools.dateFormat(date1[0], "yyyy-MM-dd") + "' and '" + simpleTools.dateFormat(date1[1], "yyyy-MM-dd") + "'";
            String seasonOutputSql1 = "select SUM(rMoney) from tb_records where rType='支出' and rDate between '" + simpleTools.dateFormat(date1[0], "yyyy-MM-dd") + "' and '" + simpleTools.dateFormat(date1[1], "yyyy-MM-dd") + "'";
            Object seasonInput1 = recordsDao.getResultValue(seasonInputSql1).get(0);
            Object seasonOutput1 = recordsDao.getResultValue(seasonOutputSql1).get(0);
            String seasonInputSql2 = "select SUM(rMoney) from tb_records where rType='收入' and rDate between '" + simpleTools.dateFormat(date2[0], "yyyy-MM-dd") + "' and '" + simpleTools.dateFormat(date2[1], "yyyy-MM-dd") + "'";
            String seasonOutputSql2 = "select SUM(rMoney) from tb_records where rType='支出' and rDate between '" + simpleTools.dateFormat(date2[0], "yyyy-MM-dd") + "' and '" + simpleTools.dateFormat(date2[1], "yyyy-MM-dd") + "'";
            Object seasonInput2 = recordsDao.getResultValue(seasonInputSql2).get(0);
            Object seasonOutput2 = recordsDao.getResultValue(seasonOutputSql2).get(0);
            String seasonInputSql3 = "select SUM(rMoney) from tb_records where rType='收入' and rDate between '" + simpleTools.dateFormat(date3[0], "yyyy-MM-dd") + "' and '" + simpleTools.dateFormat(date3[1], "yyyy-MM-dd") + "'";
            String seasonOutputSql3 = "select SUM(rMoney) from tb_records where rType='支出' and rDate between '" + simpleTools.dateFormat(date3[0], "yyyy-MM-dd") + "' and '" + simpleTools.dateFormat(date3[1], "yyyy-MM-dd") + "'";
            Object seasonInput3 = recordsDao.getResultValue(seasonInputSql3).get(0);
            Object seasonOutput3 = recordsDao.getResultValue(seasonOutputSql3).get(0);
            String seasonInputSql4 = "select SUM(rMoney) from tb_records where rType='收入' and rDate between '" + simpleTools.dateFormat(date4[0], "yyyy-MM-dd") + "' and '" + simpleTools.dateFormat(date4[1], "yyyy-MM-dd") + "'";
            String seasonOutputSql4 = "select SUM(rMoney) from tb_records where rType='支出' and rDate between '" + simpleTools.dateFormat(date4[0], "yyyy-MM-dd") + "' and '" + simpleTools.dateFormat(date4[1], "yyyy-MM-dd") + "'";
            Object seasonInput4 = recordsDao.getResultValue(seasonInputSql4).get(0);
            Object seasonOutput4= recordsDao.getResultValue(seasonOutputSql4).get(0);

            seasonInputSeries.getData().add(new XYChart.Data<>(strdate1, seasonInput1));
            seasonInputSeries.getData().add(new XYChart.Data<>(strdate2, seasonInput2));
            seasonInputSeries.getData().add(new XYChart.Data<>(strdate3, seasonInput3));
            seasonInputSeries.getData().add(new XYChart.Data<>(strdate4, seasonInput4));
            seasonOutputSeries.getData().add(new XYChart.Data<>(strdate1, seasonOutput1));
            seasonOutputSeries.getData().add(new XYChart.Data<>(strdate2, seasonOutput2));
            seasonOutputSeries.getData().add(new XYChart.Data<>(strdate3, seasonOutput3));
            seasonOutputSeries.getData().add(new XYChart.Data<>(strdate4, seasonOutput4));

            lineChart.getData().clear();
            lineChart.getData().addAll(seasonInputSeries, seasonOutputSeries);
        }
    }

}
