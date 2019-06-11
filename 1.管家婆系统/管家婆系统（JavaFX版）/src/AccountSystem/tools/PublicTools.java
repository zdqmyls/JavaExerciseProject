package AccountSystem.tools;

import AccountSystem.MainApp;
import AccountSystem.bean.Records;
import AccountSystem.bean.TableData;
import AccountSystem.bean.Users;
import AccountSystem.dao.RecordsDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PublicTools {
    private DateTools dateTools = new DateTools();

    /**
     * 操作结果：”删除“右键菜单的事件方法
     */
    public void public_do_deleteContextMenu_Event(TableView<TableData> tableView) {
        RecordsDao recordsDao = new RecordsDao();
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        TableData td = tableView.getSelectionModel().getSelectedItem();
        Records records = new Records();
        if (selectedIndex >= 0) {
            tableView.getItems().remove(selectedIndex);
            records.setRecordId(Integer.parseInt(td.getId()));
            boolean b = recordsDao.deleteAccountItem(records);
        }
    }

    /**
     * 操作结果：”添加“右键菜单的事件方法
     */
    public void public_do_addContextMenu_Event(TableView<TableData> tableView) {
        new MainApp().initAddFrame();
    }

    /**
     * 操作结果：”修改“右键菜单的事件方法
     */
    public void public_do_alterContextMenu_Event(TableView<TableData> tableView) {
        new MainApp().initAlterFrame();
    }

    public ObservableList<TableData> public_getTableViewData(String sql) {
        RecordsDao recordsDao = new RecordsDao();
        List list = recordsDao.getRecordsDataBySql(sql);
        ObservableList<TableData> data = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            Records r = (Records) list.get(i);
            TableData td = new TableData(String.valueOf(r.getRecordId()), r.getRecordType(), String.valueOf(r.getRecordMoney()), r.getRecordClassification(), r.getRecordMemo(), r.getRecordDate());
            data.add(td);
        }
        return data;
    }

    /**
     * 操作结果：设置条形图的数据根据给定的天数
     *
     * @param day          给定的天数
     * @param barChart     条形图
     * @param categoryAxis X轴
     * @param numberAxis   Y轴
     */
    public void public_setBarChartData(int day, BarChart<?, ?> barChart, CategoryAxis categoryAxis, NumberAxis numberAxis) {
        RecordsDao recordsDao = new RecordsDao();
        categoryAxis.setLabel("日期");
        numberAxis.setLabel("金额");
        XYChart.Series inputSeries = new XYChart.Series();
        inputSeries.setName("收入");
        XYChart.Series outputSeries = new XYChart.Series();
        outputSeries.setName("支出");

        for (int i = 0; i < day; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -i);
            Date calendarTime = calendar.getTime();
            String date = dateTools.dateFormat(calendarTime, "yyyy-MM-dd");
            String dateInputsql = "select SUM(rMoney) from tb_records where rType='收入' and rDate= '" + date + "';";
            String dateOutputsql = "select SUM(rMoney) from tb_records where rType='支出' and rDate= '" + date + "';";
            Object dateInput = recordsDao.getResultValue(dateInputsql).get(0);
            Object dateOutput = recordsDao.getResultValue(dateOutputsql).get(0);
            categoryAxis.getCategories().add(date);
            inputSeries.getData().add(new XYChart.Data<>(date, dateInput));
            outputSeries.getData().add(new XYChart.Data<>(date, dateOutput));
        }

        barChart.getData().clear();
        barChart.getData().addAll(inputSeries, outputSeries);
    }

    /**
     * 操作结果：设置饼图的数据根据给定的天数
     *
     * @param users    用户对象
     * @param day      给定的天数
     * @param pieChart 饼图
     * @param type     类型
     */
    public void public_setWeekPieChartData(Users users, int day, PieChart pieChart, String type) {
        RecordsDao recordsDao = new RecordsDao();
        ObservableList observableList = FXCollections.observableArrayList();
        for (int i = 0; i < day; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -i);
            Date calendarTime = calendar.getTime();
            String date = dateTools.dateFormat(calendarTime, "yyyy-MM-dd");
            String dateInputsql = "select SUM(rMoney) from tb_records where rType='" + type + "' and rDate= '" + date + "' and uId=" + users.getUserId() + ";";
            Object dateInput = recordsDao.getResultValue(dateInputsql).get(0);
            observableList.add(new PieChart.Data(date, (Float) dateInput));
        }
        pieChart.setTitle("饼图");// 设置饼图的标题
        pieChart.setClockwise(true);// 顺时针设置饼图的切片
        pieChart.setLabelLineLength(50);// 方法设置标签行的长度
        pieChart.setLabelsVisible(true);// 将饼图的标签设置为可见
        pieChart.setStartAngle(180);// 设置饼图的起始角度
        pieChart.getData().clear();
        pieChart.setData(observableList);
    }

    /**
     * 操作结果：设置折线图的数据根据给定的天数
     *
     * @param day       给定的天数
     * @param lineChart 折线图
     */
    public void public_setLineChartData(int day, LineChart<?, ?> lineChart, CategoryAxis categoryAxis, NumberAxis numberAxis) {
        categoryAxis.setLabel("日期");
        numberAxis.setLabel("金额");
        RecordsDao recordsDao = new RecordsDao();

        XYChart.Series inputSeries = new XYChart.Series();
        inputSeries.setName("收入");

        XYChart.Series outputSeries = new XYChart.Series();
        outputSeries.setName("支出");

        for (int i = 0; i < day; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -i);
            Date calendarTime = calendar.getTime();
            String date = dateTools.dateFormat(calendarTime, "yyyy-MM-dd");
            String dateInputsql = "select SUM(rMoney) from tb_records where rType='收入' and rDate= '" + date + "';";
            String dateOutputsql = "select SUM(rMoney) from tb_records where rType='支出' and rDate= '" + date + "';";
            Object dateInput = recordsDao.getResultValue(dateInputsql).get(0);
            Object dateOutput = recordsDao.getResultValue(dateOutputsql).get(0);
            inputSeries.getData().add(new XYChart.Data<>(date, dateInput));
            outputSeries.getData().add(new XYChart.Data<>(date, dateOutput));
        }

        lineChart.getData().clear();
        lineChart.getData().addAll(inputSeries, outputSeries);
    }

    /**
     * 操作结果：设置条形图的数据根据给定的月数
     *
     * @param Month        给定的月数
     * @param barChart     条形图
     * @param categoryAxis X轴
     * @param numberAxis   Y轴
     */
    public void public_setMonthBarChartData(int Month, BarChart<?, ?> barChart, CategoryAxis categoryAxis, NumberAxis numberAxis) {
        RecordsDao recordsDao = new RecordsDao();
        categoryAxis.setLabel("日期");
        numberAxis.setLabel("金额");
        XYChart.Series monthInputSeries = new XYChart.Series();
        monthInputSeries.setName("收入");
        XYChart.Series monthOutputSeries = new XYChart.Series();
        monthOutputSeries.setName("支出");

        for (int i = 0; i < Month; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(calendar.MONDAY, -i);
            Date monthDate = calendar.getTime();
            String monthStringDate = dateTools.dateFormat(monthDate, "yyyy-MM");
            String MonthInputsql = "select SUM(rMoney) from tb_records where rType='收入' and MONTH(rDate)= MONTH('" + monthStringDate + "');";
            String MonthOutputsql = "select SUM(rMoney) from tb_records where rType='支出' and MONTH(rDate)= MONTH('" + monthStringDate + "');";
            Object MonthInput = recordsDao.getResultValue(MonthInputsql).get(0);
            Object MonthOutput = recordsDao.getResultValue(MonthOutputsql).get(0);
            categoryAxis.getCategories().add(monthStringDate);
            monthInputSeries.getData().add(new XYChart.Data<>(monthStringDate, MonthInput));
            monthOutputSeries.getData().add(new XYChart.Data<>(monthStringDate, MonthOutput));
        }

        barChart.getData().clear();
        barChart.getData().addAll(monthInputSeries, monthOutputSeries);
    }

    /**
     * 操作结果：设置折线图的数据根据给定的月数
     *
     * @param Month     给定的月数
     * @param lineChart 折线图
     */
    public void public_setMonthLineChartData(int Month, LineChart lineChart, CategoryAxis categoryAxis, NumberAxis numberAxis) {
        categoryAxis.setLabel("日期");
        numberAxis.setLabel("金额");
        RecordsDao recordsDao = new RecordsDao();

        XYChart.Series monthInputSeries = new XYChart.Series();
        monthInputSeries.setName("收入");

        XYChart.Series monthOutputSeries = new XYChart.Series();
        monthOutputSeries.setName("支出");

        for (int i = 0; i < Month; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(calendar.MONDAY, -i);
            Date monthDate = calendar.getTime();
            String monthStringDate = dateTools.dateFormat(monthDate, "yyyy-MM");
            String MonthInputsql = "select SUM(rMoney) from tb_records where rType='收入' and MONTH(rDate)= MONTH('" + monthStringDate + "');";
            String MonthOutputsql = "select SUM(rMoney) from tb_records where rType='支出' and MONTH(rDate)= MONTH('" + monthStringDate + "');";
            Object MonthInput = recordsDao.getResultValue(MonthInputsql).get(0);
            Object MonthOutput = recordsDao.getResultValue(MonthOutputsql).get(0);
            monthInputSeries.getData().add(new XYChart.Data<>(monthStringDate, MonthInput));
            monthOutputSeries.getData().add(new XYChart.Data<>(monthStringDate, MonthOutput));
        }

        lineChart.getData().clear();
        lineChart.getData().addAll(monthInputSeries, monthOutputSeries);
    }

    /**
     * 操作结果：设置饼图的数据根据给定的月数
     *
     * @param Month    给定的月数
     * @param pieChart 饼图
     * @param type     指定类型
     */
    public void public_setMonthPieChartData(Users users, int Month, PieChart pieChart, String type) {
        RecordsDao recordsDao = new RecordsDao();
        ObservableList observableList = FXCollections.observableArrayList();
        for (int i = 0; i < Month; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(calendar.MONDAY, -i);
            Date monthDate = calendar.getTime();
            String monthStringDate = dateTools.dateFormat(monthDate, "yyyy-MM");
            String monthsql = "select SUM(rMoney) from tb_records where rType='" + type + "' and MONTH(rDate)= MONTH('" + dateTools.dateFormat(monthDate, "yyyy-MM-dd") + "') and uId=" + users.getUserId() + ";";
            Object monthResult = recordsDao.getResultValue(monthsql).get(0);
            observableList.add(new PieChart.Data(monthStringDate, (Float) monthResult));
        }

        pieChart.setTitle("饼图");// 设置饼图的标题
        pieChart.setClockwise(true);// 顺时针设置饼图的切片
        pieChart.setLabelLineLength(50);// 方法设置标签行的长度
        pieChart.setLabelsVisible(true);// 将饼图的标签设置为可见
        pieChart.setStartAngle(180);// 设置饼图的起始角度
        pieChart.getData().clear();
        pieChart.setData(observableList);
    }

    /**
     * 操作结果：设置饼图的数据根据季度
     *
     * @param pieChart 饼图
     * @param type     指定类型
     */
    public void public_setSeasonPieChartData(Users users, PieChart pieChart, String type) {
        RecordsDao recordsDao = new RecordsDao();
        List<Date[]> list = dateTools.getYearQuarterList(new Date());
        Date[] date1 = list.get(0);
        Date[] date2 = list.get(1);
        Date[] date3 = list.get(2);
        Date[] date4 = list.get(3);

        String strdate1 = dateTools.dateFormat(date1[0], "yyyy-MM-dd") + "-" + dateTools.dateFormat(date1[1], "yyyy-MM-dd");
        String strdate2 = dateTools.dateFormat(date2[0], "yyyy-MM-dd") + "-" + dateTools.dateFormat(date2[1], "yyyy-MM-dd");
        String strdate3 = dateTools.dateFormat(date3[0], "yyyy-MM-dd") + "-" + dateTools.dateFormat(date3[1], "yyyy-MM-dd");
        String strdate4 = dateTools.dateFormat(date4[0], "yyyy-MM-dd") + "-" + dateTools.dateFormat(date4[1], "yyyy-MM-dd");

        String seasonOutputSql1 = "select SUM(rMoney) from tb_records where rType='" + type + "' and rDate between '" + dateTools.dateFormat(date1[0], "yyyy-MM-dd") + "' and '" + dateTools.dateFormat(date1[1], "yyyy-MM-dd") + "' and uId=" + users.getUserId() + ";";
        Object seasonOutput1 = recordsDao.getResultValue(seasonOutputSql1).get(0);
        String seasonOutputSql2 = "select SUM(rMoney) from tb_records where rType='" + type + "' and rDate between '" + dateTools.dateFormat(date2[0], "yyyy-MM-dd") + "' and '" + dateTools.dateFormat(date2[1], "yyyy-MM-dd") + "' and uId=" + users.getUserId() + ";";
        Object seasonOutput2 = recordsDao.getResultValue(seasonOutputSql2).get(0);
        String seasonOutputSql3 = "select SUM(rMoney) from tb_records where rType='" + type + "' and rDate between '" + dateTools.dateFormat(date3[0], "yyyy-MM-dd") + "' and '" + dateTools.dateFormat(date3[1], "yyyy-MM-dd") + "' and uId=" + users.getUserId() + ";";
        Object seasonOutput3 = recordsDao.getResultValue(seasonOutputSql3).get(0);
        String seasonOutputSql4 = "select SUM(rMoney) from tb_records where rType='" + type + "' and rDate between '" + dateTools.dateFormat(date4[0], "yyyy-MM-dd") + "' and '" + dateTools.dateFormat(date4[1], "yyyy-MM-dd") + "' and uId=" + users.getUserId() + ";";
        Object seasonOutput4 = recordsDao.getResultValue(seasonOutputSql4).get(0);

        ObservableList observableList = FXCollections.observableArrayList(
                new PieChart.Data(strdate1, (Float) seasonOutput1),
                new PieChart.Data(strdate2, (Float) seasonOutput2),
                new PieChart.Data(strdate3, (Float) seasonOutput3),
                new PieChart.Data(strdate4, (Float) seasonOutput4)
        );

        pieChart.setTitle("饼图");// 设置饼图的标题
        pieChart.setClockwise(true);// 顺时针设置饼图的切片
        pieChart.setLabelLineLength(50);// 方法设置标签行的长度
        pieChart.setLabelsVisible(true);// 将饼图的标签设置为可见
        pieChart.setStartAngle(180);// 设置饼图的起始角度
        pieChart.getData().clear();
        pieChart.getData().addAll(observableList);
    }

    /**
     * 操作结果：读取配置文件信息
     *
     * @return
     */
    public String readStyleData() {
        String s = null;
        List list = new SimpleTools().readPropertiesFile("E:\\GitHub\\LearningSource\\实战\\管家婆系统（JavaFX版）\\src\\AccountSystem\\properties\\properties.properties");
        for (int i = 0; i < list.size(); i++) {
            s = (String) list.get(i);
        }
        return s;
    }


    /**
     * 操作结果：向下拉列表框中添加列表项
     *
     * @param comboBox 下拉列表框
     * @param items    列表项
     */
    public void public_addComboBoxItems(ComboBox comboBox, Object[] items) {
        comboBox.getItems().clear();// 清除下列列表框中的所有选项
        ObservableList options = FXCollections.observableArrayList(items);
        comboBox.setItems(options);// 添加下拉列表项
    }

    /**
     * 操作结果：根据用户计算余额
     *
     * @param users 用户对象
     * @return float 余额
     */
    public float public_getBalanceByUsers(Users users) {
        RecordsDao recordsDao = new RecordsDao();
        String outputSql = "select SUM(rMoney) from tb_records where rType='支出' and uId=" + users.getUserId() + ";";
        String inputSql = "select SUM(rMoney) from tb_records where rType='收入' and uId=" + users.getUserId() + ";";
        float totalOutput = recordsDao.getAccountTotal(outputSql);
        float totalInput = recordsDao.getAccountTotal(inputSql);
        float balance = totalInput - totalOutput;
        return balance;
    }

    /**
     * 操作结果：根据用户计算支出
     *
     * @param users 用户对象
     * @param date  日期对象
     * @return Object 支出
     */
    public Object public_getOutputByUsers(Users users, Date date) {
        RecordsDao recordsDao = new RecordsDao();
        String thisDate = dateTools.dateFormat(date, "yyyy-MM-dd");
        String thisOutputsql = "select SUM(rMoney) from tb_records where rType='支出' and rDate= '" + thisDate + "' and uId=" + users.getUserId() + ";";
        Object thisOutput = recordsDao.getResultValue(thisOutputsql).get(0);
        return thisOutput;
    }

    /**
     * 操作结果：根据用户计算收入
     *
     * @param users 用户对象
     * @param date  日期对象
     * @return Object 收入
     */
    public Object public_getInputByUsers(Users users, Date date) {
        RecordsDao recordsDao = new RecordsDao();
        String thisDate = dateTools.dateFormat(date, "yyyy-MM-dd");
        String thisInputsql = "select SUM(rMoney) from tb_records where rType='收入' and rDate= '" + thisDate + "' and uId=" + users.getUserId() + ";";
        Object thisInput = recordsDao.getResultValue(thisInputsql).get(0);
        return thisInput;
    }


    public void public_initTableViewData(TableView tableView, ObservableList<TableData> data, TableColumn<TableData, String> idColumn, TableColumn<TableData, String> typeColumn, TableColumn<TableData, String> moneyColumn, TableColumn<TableData, String> classificationColumn, TableColumn<TableData, String> memoColumn, TableColumn<TableData, String> dateColumn) {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        moneyColumn.setCellValueFactory(cellData -> cellData.getValue().moneyProperty());
        classificationColumn.setCellValueFactory(cellData -> cellData.getValue().classificationProperty());
        memoColumn.setCellValueFactory(cellData -> cellData.getValue().memoProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        tableView.setItems(data);
    }
}
