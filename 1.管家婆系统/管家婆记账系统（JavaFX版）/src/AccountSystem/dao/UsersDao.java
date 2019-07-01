package AccountSystem.dao;

import AccountSystem.bean.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
    JDBCUtils jdbcUtils = new JDBCUtils();
    Connection connection = null;

    /**
     * 操作结果：实现按用户名与密码查询用户的方法
     *
     * @param userName 用户名
     * @param password 用户密码
     * @return Users Users对象
     */
    public Users logupUser(String userName, String password) {
        Users users = new Users();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtils.getConnection();
            String sql = "select * from tb_users where uName=? and uPassword=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.setUserId(resultSet.getInt(1));
                users.setUserName(resultSet.getString(2));
                users.setUserPassword(resultSet.getString(3));
                users.setUserImagePath(resultSet.getString(4));
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.release(resultSet, preparedStatement, connection);
        }
        return users;
    }

    /**
     * 操作结果：实现用户注册的方法
     *
     * @param userName      用户名
     * @param password      用户密码
     * @param userImagePath 用户图像路径
     * @return boolean 用户注册成功返回true，否则返回false
     */
    public boolean loginUser(String userName, String password, String userImagePath) {
        PreparedStatement preparedStatement = null;
        int num = 0;
        try {
            connection = jdbcUtils.getConnection();
            String sql = "insert into tb_users(uName,uPassword,uImagePath)" + "values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userImagePath);

            num = preparedStatement.executeUpdate();

            if (num > 0) {
                return true;// 注册成功
            } else {
                return false;// 注册失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.release(preparedStatement, connection);
        }
        return false;
    }

    /**
     * 操作结果：根据参数sql获取数据库记录数据
     *
     * @param sql SQL语句
     * @return List 返回包含记录Users对象的集合
     */
    public List getUsersDataBySql(String sql) {
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
                Users users = new Users();
                users.setUserId(rs.getInt(1));
                users.setUserName(rs.getString(2));
                users.setUserPassword(rs.getString(3));
                users.setUserImagePath(rs.getString(4));
                list.add(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        return list;
    }

    /**
     * 操作结果：根据给定的SQL语句更改数据
     *
     * @param sql SQL语句
     * @return boolean 如果更改成功则返回true，否则返回false
     */
    public boolean alterUsersDataItem(String sql) {
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
