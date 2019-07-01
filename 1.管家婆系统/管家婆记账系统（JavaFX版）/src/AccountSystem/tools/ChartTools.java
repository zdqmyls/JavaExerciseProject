package AccountSystem.tools;

import AccountSystem.bean.Session;
import AccountSystem.bean.Users;
import AccountSystem.dao.RecordsDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChartTools {
    private DateTools dateTools = new DateTools();

    /**
     * 操作结果：设置条形图的数据根据给定的月数
     *
     * @param Month        给定的月数
     * @param barChart     条形图
     * @param categoryAxis X轴
     * @param numberAxis   Y轴
     */
    public void public_setMonthBarChartData(int Month, BarChart<?, ?> barChart, CategoryAxis categoryAxis,
                                            NumberAxis numberAxis) {
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
            String monthStringDate = dateTools.dateFormat(monthDate, "yyyy-MM-dd");
            String MonthInputsql =
                    "select SUM(rMoney) from tb_records where rType='收入' and MONTH(rDate)= MONTH('" + monthStringDate + "') and uId=" + new Session().getUsers().getUserId() + ";";
            String MonthOutputsql =
                    "select SUM(rMoney) from tb_records where rType='支出' and MONTH(rDate)= MONTH('" + monthStringDate + "') and uId=" + new Session().getUsers().getUserId() + ";";
            Object MonthInput = recordsDao.getResultValue(MonthInputsql).get(0);
            Object MonthOutput = recordsDao.getResultValue(MonthOutputsql).get(0);
            categoryAxis.getCategories().add(monthStringDate);
            monthInputSeries.getData().add(new XYChart.Data<>(dateTools.dateFormat(dateTools.stringToDate(monthStringDate, "yyyy-MM-dd"), "yyyy-MM"), MonthInput));
            monthOutputSeries.getData().add(new XYChart.Data<>(dateTools.dateFormat(dateTools.stringToDate(monthStringDate, "yyyy-MM-dd"), "yyyy-MM"), MonthOutput));
        }

        barChart.getData().clear();
        barChart.getData().addAll(monthInputSeries, monthOutputSeries);
    }

    /**
     * 操作结果：设置条形图的数据根据一年（四季度）
     *
     * @param barChart     条形图
     * @param categoryAxis X轴
     * @param numberAxis   Y轴
     */
    public void public_setSeasonBarChartData(BarChart<?, ?> barChart, CategoryAxis categoryAxis,
                                             NumberAxis numberAxis) {
        categoryAxis.setLabel("日期");
        numberAxis.setLabel("金额");
        XYChart.Series seasonInputSeries = new XYChart.Series();
        seasonInputSeries.setName("收入");

        XYChart.Series seasonOutputSeries = new XYChart.Series();
        seasonOutputSeries.setName("支出");

        List<Date[]> list = dateTools.getYearQuarterList(new Date());
        for (int i = 0; i < list.size(); i++) {
            String strDate =
                    dateTools.dateFormat(list.get(i)[0], "yyyy-MM-dd") + "-" + dateTools.dateFormat(list.get(i)[1],
                            "yyyy" + "-MM-dd");
            categoryAxis.getCategories().add(strDate);
            String seasonInputSql =
                    "select SUM(rMoney) from tb_records where rType='收入' and rDate between '" + dateTools.dateFormat(list.get(i)[0], "yyyy-MM-dd") + "' and '" + dateTools.dateFormat(list.get(i)[1], "yyyy-MM-dd") + "' and uId=" + new Session().getUsers().getUserId();
            String seasonOutpuSql =
                    "select SUM(rMoney) from tb_records where rType='支出' and rDate between '" + dateTools.dateFormat(list.get(i)[0], "yyyy-MM-dd") + "' and '" + dateTools.dateFormat(list.get(i)[1], "yyyy-MM-dd") + "' and uId=" + new Session().getUsers().getUserId();
            Object seasonInput = new RecordsDao().getResultValue(seasonInputSql).get(0);
            Object seasonOutput = new RecordsDao().getResultValue(seasonOutpuSql).get(0);
            seasonInputSeries.getData().add(new XYChart.Data<>(strDate, seasonInput));
            seasonOutputSeries.getData().add(new XYChart.Data<>(strDate, seasonOutput));
        }
        barChart.getData().clear();
        barChart.getData().addAll(seasonInputSeries, seasonOutputSeries);
    }

    /**
     * 操作结果：设置条形图的数据根据给定的天数
     *
     * @param day          给定的天数
     * @param barChart     条形图
     * @param categoryAxis X轴
     * @param numberAxis   Y轴
     */
    public void public_setBarChartData(int day, BarChart<?, ?> barChart, CategoryAxis categoryAxis,
                                       NumberAxis numberAxis) {
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
            String dateInputsql = "select SUM(rMoney) from tb_records where rType='收入' and rDate= '" + date + "' and "
                    + "uId=" + new Session().getUsers().getUserId() + ";";
            String dateOutputsql = "select SUM(rMoney) from tb_records where rType='支出' and rDate= '" + date + "' and" +
                    " " + "uId=" + new Session().getUsers().getUserId() + ";";
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
     * 操作结果：设置折线图的数据根据给定的天数
     *
     * @param day       给定的天数
     * @param lineChart 折线图
     */
    public void public_setDayLineChartData(int day, LineChart<?, ?> lineChart, CategoryAxis categoryAxis,
                                           NumberAxis numberAxis) {
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
            String dateInputsql =
                    "select SUM(rMoney) from tb_records where rType='收入' and rDate= '" + date + "' and uId=" + new Session().getUsers().getUserId() + ";";
            String dateOutputsql = "select SUM(rMoney) from tb_records where rType='支出' and rDate= '" + date + "' and" +
                    " uId=" + new Session().getUsers().getUserId() + ";";
            Object dateInput = recordsDao.getResultValue(dateInputsql).get(0);
            Object dateOutput = recordsDao.getResultValue(dateOutputsql).get(0);
            inputSeries.getData().add(new XYChart.Data<>(date, dateInput));
            outputSeries.getData().add(new XYChart.Data<>(date, dateOutput));
        }

        lineChart.getData().clear();
        lineChart.getData().addAll(inputSeries, outputSeries);
    }

    /**
     * 操作结果：设置折线图的数据根据给定的月数
     *
     * @param Month     给定的月数
     * @param lineChart 折线图
     */
    public void public_setMonthLineChartData(int Month, LineChart lineChart, CategoryAxis categoryAxis,
                                             NumberAxis numberAxis) {
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
            String monthStringDate = dateTools.dateFormat(monthDate, "yyyy-MM-dd");
            String MonthInputsql =
                    "select SUM(rMoney) from tb_records where rType='收入' and MONTH(rDate)= MONTH('" + monthStringDate + "') and uId=" + new Session().getUsers().getUserId() + ";";
            String MonthOutputsql =
                    "select SUM(rMoney) from tb_records where rType='支出' and MONTH(rDate)= MONTH('" + monthStringDate + "') and uId=" + new Session().getUsers().getUserId() + ";";
            Object MonthInput = recordsDao.getResultValue(MonthInputsql).get(0);
            Object MonthOutput = recordsDao.getResultValue(MonthOutputsql).get(0);
            monthInputSeries.getData().add(new XYChart.Data<>(dateTools.dateFormat(monthDate, "yyyy-MM"), MonthInput));
            monthOutputSeries.getData().add(new XYChart.Data<>(dateTools.dateFormat(monthDate, "yyyy-MM"),
                    MonthOutput));
        }

        lineChart.getData().clear();
        lineChart.getData().addAll(monthInputSeries, monthOutputSeries);
    }

    /**
     * 操作结果：设置折线图的数据根据给定的年（四个季度）
     *
     * @param lineChart    折线图
     * @param categoryAxis X轴
     * @param numberAxis   Y轴
     */
    public void public_setSeasonLineChartData(LineChart lineChart, CategoryAxis categoryAxis,
                                              NumberAxis numberAxis) {
        categoryAxis.setLabel("日期");
        numberAxis.setLabel("金额");
        XYChart.Series seasonInputSeries = new XYChart.Series();
        seasonInputSeries.setName("收入");

        XYChart.Series seasonOutputSeries = new XYChart.Series();
        seasonOutputSeries.setName("支出");

        List<Date[]> list = dateTools.getYearQuarterList(new Date());
        for (int i = 0; i < list.size(); i++) {
            String strDate =
                    dateTools.dateFormat(list.get(i)[0], "yyyy-MM-dd") + "-" + dateTools.dateFormat(list.get(i)[1],
                            "yyyy-MM-dd");
            categoryAxis.getCategories().add(strDate);
            String seasonInputSql =
                    "select SUM(rMoney) from tb_records where rType='收入' and rDate between '" + dateTools.dateFormat(list.get(i)[0], "yyyy-MM-dd") + "' and '" + dateTools.dateFormat(list.get(i)[1], "yyyy-MM-dd") + "' and uId=" + new Session().getUsers().getUserId();
            String seasonOutputSql =
                    "select SUM(rMoney) from tb_records where rType='支出' and rDate between '" + dateTools.dateFormat(list.get(i)[0], "yyyy-MM-dd") + "' and '" + dateTools.dateFormat(list.get(i)[1], "yyyy-MM-dd") + "' and uId=" + new Session().getUsers().getUserId();
            Object seasonInput = new RecordsDao().getResultValue(seasonInputSql).get(0);
            Object seasonOutput = new RecordsDao().getResultValue(seasonOutputSql).get(0);
            seasonInputSeries.getData().add(new XYChart.Data<>(strDate, seasonInput));
            seasonOutputSeries.getData().add(new XYChart.Data<>(strDate, seasonOutput));
        }
        lineChart.getData().clear();
        lineChart.getData().addAll(seasonInputSeries, seasonOutputSeries);
    }

    /**
     * 操作结果：设置饼图的数据根据起始日期和终止日期
     *
     * @param users     用户对象
     * @param pieChart  饼图
     * @param startDate 起止日期
     * @param endDate   终止日期
     */
    public void public_setDayPieChartData(Users users, PieChart pieChart, Date startDate, Date endDate) {

        String thisStartDate = dateTools.dateFormat(startDate, "yyyy-MM-dd");
        String thisEndDate = dateTools.dateFormat(endDate, "yyyy-MM-dd");
        String thisInputsql =
                "select SUM(rMoney) from tb_records where rType='收入' and rDate between '" + thisStartDate + "'" + " " +
                        "and " + "'" + thisEndDate + "' and uId=" + users.getUserId() + ";";
        String thisOutputsql =
                "select SUM(rMoney) from tb_records where rType='支出' and rDate between '" + thisStartDate + "'" + " " +
                        "and " + "'" + thisEndDate + "' and uId=" + users.getUserId() + ";";
        Object thisInput = new RecordsDao().getResultValue(thisInputsql).get(0);
        Object thisOutput = new RecordsDao().getResultValue(thisOutputsql).get(0);

        ObservableList observableList = FXCollections.observableArrayList(
                new PieChart.Data("收入", (Float) (thisInput)),
                new PieChart.Data("支出", (Float) thisOutput)
        );

        pieChart.setClockwise(true);// 顺时针设置饼图的切片
        pieChart.setLabelLineLength(50);// 方法设置标签行的长度
        pieChart.setLabelsVisible(true);// 将饼图的标签设置为可见
        pieChart.setStartAngle(180);// 设置饼图的起始角度

        pieChart.setData(observableList);
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
            String dateInputsql =
                    "select SUM(rMoney) from tb_records where rType='" + type + "' and rDate= '" + date + "' and " +
                            "uId=" + users.getUserId() + ";";
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
            String monthsql = "select SUM(rMoney) from tb_records where rType='" + type + "' and MONTH(rDate)= MONTH" +
                    "('" + dateTools.dateFormat(monthDate, "yyyy-MM-dd") + "') and uId=" + users.getUserId() + ";";
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
        ObservableList observableList = FXCollections.observableArrayList();
        List<Date[]> list = dateTools.getYearQuarterList(new Date());
        for (int i = 0; i < list.size(); i++) {
            String strDate =
                    dateTools.dateFormat(list.get(i)[0], "yyyy-MM-dd") + "-" + dateTools.dateFormat(list.get(i)[1],
                            "yyyy-MM-dd");
            String seasonSql =
                    "select SUM(rMoney) from tb_records where rType='" + type + "' and rDate between '" + dateTools.dateFormat(list.get(i)[0], "yyyy-MM-dd") + "' and '" + dateTools.dateFormat(list.get(i)[1], "yyyy-MM-dd") + "' and uId=" + users.getUserId() + ";";
            Object season = new RecordsDao().getResultValue(seasonSql).get(0);
            observableList.add(new PieChart.Data(strDate, (Float) season));
        }
        pieChart.setTitle("饼图");// 设置饼图的标题
        pieChart.setClockwise(true);// 顺时针设置饼图的切片
        pieChart.setLabelLineLength(50);// 方法设置标签行的长度
        pieChart.setLabelsVisible(true);// 将饼图的标签设置为可见
        pieChart.setStartAngle(180);// 设置饼图的起始角度
        pieChart.getData().clear();
        pieChart.getData().addAll(observableList);
    }
}
