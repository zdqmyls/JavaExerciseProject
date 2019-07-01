package bookManageSystem.dao;

import bookManageSystem.bean.BookTypeBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookTypeDao {

    /**
     * 操作结果：根据SQL语句执行数据库的增删改操作
     *
     * @param sql SQL语句
     * @return boolean 如果操作数据库成功返回true，否则返回false
     */
    public boolean dataChange(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //获得数据的连接
            conn = new JDBCUtils().getConnection();
            //获得Statement对象
            stmt = conn.createStatement();
            //发送SQL语句
            int num = stmt.executeUpdate(sql);
            if (num > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new JDBCUtils().release(rs, stmt, conn);
        }
        return false;
    }

    /**
     * 操作结果：根据参数sql获取数据库记录数据
     *
     * @param sql SQL语句
     * @return List 返回包含记录Records对象的集合
     */
    public List getRecordsDataBySql(String sql) {
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
                BookTypeBean bookTypeBean = new BookTypeBean();
                bookTypeBean.setBookTypeId(rs.getInt(1));
                bookTypeBean.setBookTypeName(rs.getString(2));
                bookTypeBean.setBookTypeDescription(rs.getString(3));
                list.add(bookTypeBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        return list;
    }

    /**
     * 操作结果：将集合转换成数组
     * @param list 集合
     * @return String[][] 二维数组
     */
    public String[][] ListToArray(List<BookTypeBean> list) {
        String[][] array =new String[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            BookTypeBean l = list.get(i);
            array[i][0] = String.valueOf(l.getBookTypeId());
            array[i][1] = l.getBookTypeName();
            array[i][2] = l.getBookTypeDescription();
        }
        return array;
    }
}
