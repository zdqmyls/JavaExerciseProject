package AccountSystem.dao;

import AccountSystem.bean.Records;
import AccountSystem.bean.Session;
import AccountSystem.tools.SimpleTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordsDao {

    /**
     * 操作结果：添加账目记录到数据库
     * @param session 通信对象
     * @param records 记录对象
     * @return boolean 添加成功则返回true，否则返回false
     */
    public boolean addAccountRecords(Session session,Records records){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        int num = 0;
        SimpleTools simpleTools=new SimpleTools();
        try{
            //获得数据的连接
            conn=JDBCUtils.getConnection();
            //获得Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
            String sql="insert into tb_records(uId,rType,rMoney,rClassification,rMemo,rDate)"+"values("+session.getUsers().getUserId()+",'"+records.getRecordType()+"',"+records.getRecordMoney()+",'"+records.getRecordClassification()+"','"+records.getRecordMemo()+"','"+records.getRecordDate()+"');";
            num=stmt.executeUpdate(sql);
            if(num>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return false;
    }

    /**
     * 操作结果：将获取的数据返回一个二维数组
     * @param sql SQL查询语句
     * @return Object[][] 查询的数据二维数组
     */
    public Object[][] checkAccount(String sql){
        Object[][] info=null;
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try{
            //获得数据的连接
            conn=JDBCUtils.getConnection();
            //获得Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
            PreparedStatement pstm=conn.prepareStatement(sql);
            rs=pstm.executeQuery();
            //获取表有多少列数据
            ResultSetMetaData rsmd=pstm.getMetaData();
            //表列数
            int size=rsmd.getColumnCount();
            //计算有多少条记录
            int count=0;
            while(rs.next()){
                count++;
            }
            rs=pstm.executeQuery();
            //将查询获得的数据，转换成适合生成JTable的数据形式
            info=new Object[count][6];
            count=0;
            while(rs.next()){
                info[count][0]=rs.getInt(1);
                info[count][1]=rs.getString(2);
                info[count][2]=rs.getFloat(3);
                info[count][3]=rs.getString(4);
                info[count][4]=rs.getString(5);
                info[count][5]=rs.getString(6);
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return info;
    }

    /**
     * 操作结果：根据序号在数据库查询数据
     * @param records 实体对象
     * @return Records 实体对象
     */
    public Records checkAccountItem(Records records){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try{
            //获得数据的连接
            conn=JDBCUtils.getConnection();
            //获得Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
            String sql="select rId,rType,rMoney,rClassification,rMemo,rDate from tb_records where rId="+records.getRecordId()+";";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                records.setRecordId(rs.getInt(1));
                records.setRecordType(rs.getString(2));
                records.setRecordMoney(rs.getFloat(3));
                records.setRecordClassification(rs.getString(4));
                records.setRecordMemo(rs.getString(5));
                records.setRecordDate(rs.getString(6));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return records;
    }

    /**
     * 操作结果：删除数据
     * @param records 实体对象
     * @return boolean 如果删除成功则返回true，否则返回false
     */
    public boolean deleteAccountItem(Records records){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try{
            //获得数据的连接
            conn=JDBCUtils.getConnection();
            //获得Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
            String sql="delete from tb_records where rId="+records.getRecordId()+";";
            int num=stmt.executeUpdate(sql);
            if(num>0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return false;
    }

    /**
     * 操作结果：更改数据
     * @param records 实体对象
     * @return boolean 如果更改成功则返回true，否则返回false
     */
    public boolean alterAccountItem(Records records){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try{
            //获得数据的连接
            conn=JDBCUtils.getConnection();
            //获得Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
//            String sql="update ACCOUNTS set ATYPE='"+accountItem.getType()+"',"+"AMONEY="+accountItem.getMoney()+","+"ACLASSIFICATION='"+accountItem.getClassification()+"',"+"AMEMO='"+accountItem.getMemo()+"',"+"ADATE='"+accountItem.getDate()+"'"+" where AID="+accountItem.getId()+";";
            String sql="update tb_records set rType=?,rMoney=?,rClassification=?,rMemo=?,rDate=? where rId=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,records.getRecordType());
            pst.setFloat(2,records.getRecordMoney());
            pst.setString(3,records.getRecordClassification());
            pst.setString(4,records.getRecordMemo());
            pst.setString(5,records.getRecordDate());
            pst.setInt(6,records.getRecordId());
            pst.executeUpdate();
            //判断是否更改成功
            int num=pst.getUpdateCount();
            if(num>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return false;
    }

    /**
     * 操作结果：根据条件求和
     * @param condition 条件
     * @return float 返回求和结果
     */
    public float getAccountTotalOutput(String condition){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try{
            //获得数据的连接
            conn=JDBCUtils.getConnection();
            //获得Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
            String sql="select SUM(rMoney) from tb_records where rType='"+ condition+"';";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                return rs.getFloat(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return 0;
    }

    /**
     * 操作结果：根据SQL语句求值
     * @param sql SQL语句
     * @return List 集合
     */
    public List getResultValue(String sql){
        List list=new ArrayList();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try{
            //获得数据的连接
            conn=JDBCUtils.getConnection();
            //获得Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                list.add(rs.getFloat(1));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return null;
    }

    /**
     * 操作结果：根据用户获取他的所有记录数据
     * @param session 通信对象
     * @return List 返回包含记录Records对象的集合
     */
    public List getAllTableDataByUser(Session session){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        List list=new ArrayList();
        try{
            //获得数据的连接
            conn=JDBCUtils.getConnection();
            //获得Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
            String sql="select rId,rType,rMoney,rClassification,rMemo,rDate from tb_records where uId="+session.getUsers().getUserId()+";";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                Records records=new Records();
                records.setRecordId(rs.getInt(1));
                records.setRecordType(rs.getString(2));
                records.setRecordMoney(rs.getFloat(3));
                records.setRecordClassification(rs.getString(4));
                records.setRecordMemo(rs.getString(5));
                records.setRecordDate(rs.getString(6));
                list.add(records);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return list;
    }

    /**
     * 操作结果：根据参数sql获取数据库记录数据
     * @param sql SQL语句
     * @return List 返回包含记录Records对象的集合
     */
    public List getRecordsDataBySql(String sql){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        List list=new ArrayList();
        try{
            //获得数据的连接
            conn=JDBCUtils.getConnection();
            //获得Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                Records records=new Records();
                records.setRecordId(rs.getInt(1));
                records.setRecordType(rs.getString(2));
                records.setRecordMoney(rs.getFloat(3));
                records.setRecordClassification(rs.getString(4));
                records.setRecordMemo(rs.getString(5));
                records.setRecordDate(rs.getString(6));
                list.add(records);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return list;
    }

    public static void main(String[] args) {

    }
}
