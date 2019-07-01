package AccountSystem.dao;

import AccountSystem.bean.Classification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassificationDao {
    /**
     * 操作结果：根据参数sql获取数据库记录数据
     *
     * @param sql SQL语句
     * @return List 返回包含记录Users对象的集合
     */
    public List getTableData(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            //获得数据的连接
            conn = JDBCUtils.getConnection();
            //获得Statement对象
            stmt = conn.createStatement();
            //发送SQL语句
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Classification classification = new Classification();
                classification.setcId(rs.getInt(1));
                classification.setcName(rs.getString(2));
                classification.setcType(rs.getString(3));
                list.add(classification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        return list;
    }

    /**
     * 操作结果：根据SQL语句实现对数据库表的增删改
     *
     * @param sql SQL语句
     * @return boolean 如果更改成功则返回true，否则返回false
     */
    public boolean setTableData(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //获得数据的连接
            conn = JDBCUtils.getConnection();
            //获得Statement对象
            stmt = conn.createStatement();
            //发送SQL语句
            int num = stmt.executeUpdate(sql);
            //判断是否更改成功
            if (num > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        return false;
    }
}
