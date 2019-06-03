package AccountSystem.tools;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SimpleTools {
    /**
     * 操作结果：MD5加密
     * @param str 需要加密的字符串
     * @return String 返回加密成功的字符串
     */
    public String MD5(String str) {//项目中的
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte b[] = md5.digest();

            StringBuffer sb = new StringBuffer("");
            for (int n = 0; n < b.length; n++) {
                int i = b[n];
                if (i < 0) i += 256;
                if (i < 16) sb.append("0");
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();  //32位加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 操作结果：自定义一个JavaFX的对话框
     * @param alterType 对话框类型
     * @param title 对话框标题
     * @param header 对话框头信息
     * @param message 对话框显示内容
     * @return boolean 如果点击了”确定“那么就返回true，否则返回false
     */
    public boolean informationDialog(Alert.AlertType alterType, String title, String header, String message) {
        // 按钮部分可以使用预设的也可以像这样自己 new 一个
        Alert alert=new Alert(alterType,message,new ButtonType("取消",ButtonBar.ButtonData.CANCEL_CLOSE),new ButtonType("确定",ButtonBar.ButtonData.YES));
        // 设置对话框的标题
        alert.setTitle(title);
        alert.setHeaderText(header);
        // showAndWait() 将在对话框消失以前不会执行之后的代码
        Optional<ButtonType> buttonType=alert.showAndWait();
        // 根据点击结果返回
        if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
            return true;// 如果点击了“确定”就返回true
        }else{
            return false;
        }
    }

    /**
     * 操作结果：日期格式化
     * @param date 需要格式化的日期
     * @param format 日期格式
     * @return String 格式化的字符串
     */
    public static String dateFormat(Date date, String format){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
        String str=simpleDateFormat.format(date);
        return str;
    }

    /**
     * 操作结果：获取昨天的日期
     * @return Date 日期对象
     */
    public Date getYesterdayDate(){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        return calendar.getTime();
    }

    /**
     * 操作结果：获取前天的日期
     * @return Date 日期对象
     */
    public Date getBeforeYesterdayDate(){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,-2);
        return calendar.getTime();
    }

    /**
     * 操作结果：获取过去一个月的日期
     * @return Date 日期对象
     */
    public Date getBeforeMonth(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(calendar.MONDAY,-1);
        return calendar.getTime();
    }

    /**
     * 操作结果：将毫秒数换转成日期类型
     * @param msTime 毫秒数
     * @return Date 日期
     */
    public Date msToDate(double msTime){
        return new Date((long) msTime);
    }

    /**
     * 操作结果：将日期字符串转成日期类型
     * @param date 日期字符串
     * @return Date 日期
     */
    public Date stringToDate(String date){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取date所属年的所有季度列表及开始/结束时间 开始时间：date[0]，结束时间：date[1]
     *
     * @param date
     * @return
     */
    public static List<Date[]> yearQuarterList(Date date) {
        List<Date[]> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date starttm = getYearStartTime(date);
        Date endtm = getYearEndTime(date);
        calendar.setTime(starttm);
        while (calendar.getTime().before(endtm)) {
            Date st = getQuarterStartTime(calendar.getTime());
            Date et = getQuarterEndTime(calendar.getTime());
            result.add(new Date[]{st, et});
            calendar.add(Calendar.MONTH, 3);
        }
        return result;
    }

    /**
     * 操作结果：得到当前年的开始时间
     * @param date 日期对象
     * @return Date 当前年的结束日期
     */
    public static Date getYearStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date dt = null;
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            dt = c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 操作结果：得到当前年的结束时间
     * @param date 日期对象
     * @return Date 当前年的结束日期
     */
    public static Date getYearEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date dt = null;
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            dt = c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 操作结果：当前季度的开始时间
     * @param date 日期对象
     * @return Date 当前季度的开始时间
     */
    public static Date getQuarterStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date dt = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 6);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            dt = c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 操作结果：当前季度的结束时间
     * @param date 日期对象
     * @return Date 当前季度的结束时间
     */
    public static Date getQuarterEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date dt = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            dt =c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    public static void main(String[] args) {
        List<Date[]> list = yearQuarterList(new Date());
        Date[] date1 =list.get(0);
        Date[] date2 =list.get(1);
        Date[] date3 =list.get(2);
        Date[] date4 =list.get(3);
        System.out.println(date1[0]+"-"+date1[1]);
        System.out.println(date2[0]+"-"+date2[1]);
        System.out.println(date3[0]+"-"+date3[1]);
        System.out.println(date4[0]+"-"+date4[1]);

    }

    /**
     * Object类型转换为String类型，高阶类型向低阶转会提示错误
     * @param obj
     * @return
     */
    public String[][] objectToString(Object[][] obj){
        String[][] str=new String[obj.length][obj[0].length];
        for(int i=0;i<obj.length;i++){
            for(int j=0;j<obj[i].length;j++){
                str[i][j]=obj[i][j].toString();
            }
        }
        return str;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     * @param cell excel单元格
     * @return String 单元格数据内容
     */
    public String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        //判断单元格的类型
        switch (cell.getCellType()) {
            //字符串类型数据
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            //number类型数据
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            //Boolean类型数据
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            //无数据内容
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            //除上述数据类型以外，均为以下内容
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if(cell==null){
            return "";
        }
        return strCell;
    }

    /**
     * 读取excel数据内容（不包括标题行）
     * @param is 文件输入流
     * @return String[][] 包含单元格数据内容的二维数组
     */
    public String[][] readExcelContentArray(InputStream is){
        HSSFWorkbook wb=null;
        HSSFSheet sheet=null;
        HSSFRow row=null;
        HSSFCellStyle style=null;
        HSSFCell cell=null;
        String str=null;
        String[][] content=null;
        try{
            //实例化一个工作表对象
            wb=new HSSFWorkbook(is);
        }catch (IOException e){
            e.printStackTrace();
        }
        //得到第一张表格
        sheet=wb.getSheetAt(0);
        //得到该表格的总行数（不包括标题行）
        int rowNum=sheet.getLastRowNum();
        //获取该工作表的第一行（下标是从0开始的）
        row=sheet.getRow(0);
        //获取第一行的列数
        int colNum=row.getPhysicalNumberOfCells();
        //正文内容应该从第二行开始，第一行为表头的标题
        //实例化一个数组来保存非标题行下的内容
        content=new String[rowNum][colNum];
        for(int i=0;i<rowNum;i++){
            for(int j=0;j<colNum;j++){
                //将具体行列的单元格内容写入str
                str=getStringCellValue(sheet.getRow(i+1).getCell((short)j)).trim();
                content[i][j]=str;
            }
        }
        return content;
    }


    /**
     * 导出数据内容到excel中
     * @param title String[] 表头数据
     * @param values String[][] 表数据（非表头的）
     */
    public String exportExcel(String[] title, String[][] values, String exportPath)  {
        File exportExcelFile=null;
        HSSFWorkbook wb=null;
        HSSFSheet sheet=null;
        HSSFRow row=null;
        HSSFCellStyle style=null;
        HSSFCell cell=null;
        //第一步，创建一个HSSFWorkbook对象，对应一个excel文件
        wb=new HSSFWorkbook();
        //第二步，在wb添加一个sheet，对应excel文件中的sheet
        sheet=wb.createSheet("Sheet1");
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        row=sheet.createRow(0);
        //第四步，创建单元格，并设置值表头 设置表头居中
        style=wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//创建一个居中格式
        //第五步，添加数据到表头
        for(int i=0;i<title.length;i++){
            cell=row.createCell((short)i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
//            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        }
        //第六步，写入实体数据，实际应用中这些数据从数据库获得
        for(int i=0;i<values.length;i++){
            row=sheet.createRow(i+1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
//                row.createCell((short)j).setEncoding(HSSFCell.ENCODING_UTF_16);
                row.createCell((short)j).setCellValue(values[i][j]);
            }
        }
        //第七步，输出excel文件
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyyMMdd");//定义日期格式
        String name=exportPath+format.format(date)+".xls";//拼接文件名
        //创建一个文件字节输入流
        FileOutputStream outputStream=null;
        try{
            outputStream=new FileOutputStream(name);
            wb.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }


    /**
     * Java代码实现MySQL数据库导出
     *
     * @author GaoHuanjie
     * @param userName 进入数据库所需要的用户名
     * @param password 进入数据库所需要的密码
     * @param savePathName 数据库导出文件保存路径加名字
     * @param databaseName 要导出的数据库名
     * @return 返回true表示导出成功，否则返回false。
     */
    public static boolean backup(String userName, String password, String savePathName, String databaseName) throws InterruptedException {
        try {
//            String stmt = "mysql -uroot -padmin myDB < " + "c:/sql.sql";
            String stmt="mysqldump -u"+userName+" -p"+password+" "+databaseName+" > "+savePathName;
            String[] cmd = {"cmd", "/c", stmt};
            Process process=Runtime.getRuntime().exec(cmd);
            if(process.waitFor() == 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 操作结果：恢复数据库，前提是数据库里有该数据库名字，否则无法恢复（所以应该先创建一个数据库）
     * @param username 用户名
     * @param password 用户数据库密码
     * @param databasename 数据库名字
     * @param filePathName 数据库文件路径及名字加后缀
     * @return boolean 如果恢复成功则返回true，否则返回false
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean recover(String username,String password,String databasename,String filePathName) throws IOException, InterruptedException {

        try {
//            String stmt = "mysql -uroot -padmin myDB < " + "c:/sql.sql";
            String stmt="mysql -u"+username+" -p"+password+" "+databasename+" < "+filePathName;
            String[] cmd = {"cmd", "/c", stmt};
            Process process=Runtime.getRuntime().exec(cmd);
            if(process.waitFor() == 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
