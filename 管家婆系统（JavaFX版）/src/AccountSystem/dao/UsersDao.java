package AccountSystem.dao;

import AccountSystem.bean.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersDao {
    JDBCUtils jdbcUtils=new JDBCUtils();
    Connection connection=null;

    /**
     * 操作结果：实现按用户名与密码查询用户的方法
     * @param userName 用户名
     * @param password 用户密码
     * @return Users Users对象
     */
    public Users logupUser(String userName, String password) {
        Users users=new Users();
        PreparedStatement preparedStatement=null;
        ResultSet  resultSet=null;
        try{
            connection=jdbcUtils.getConnection();
            String sql="select * from tb_users where uName=? and uPassword=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);

            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                users.setUserId(resultSet.getInt(1));
                users.setUserName(resultSet.getString(2));
                users.setUserPassword(resultSet.getString(3));
                users.setUserImagePath(resultSet.getString(4));
            }
            return users;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jdbcUtils.release(resultSet,preparedStatement,connection);
        }
        return users;
    }

    /**
     * 操作结果：实现用户注册的方法
     * @param userName 用户名
     * @param password 用户密码
     * @param userImagePath 用户图像路径
     * @return boolean 用户注册成功返回true，否则返回false
     */
    public boolean loginUser(String userName,String password,String userImagePath) {
        PreparedStatement preparedStatement=null;
        int num=0;
        try{
            connection=jdbcUtils.getConnection();
            String sql="insert into tb_users(uName,uPassword,userImagePath)"+ "values(?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,userImagePath);

            num=preparedStatement.executeUpdate();

            if(num>0){
                return true;// 注册成功
            }else{
                return false;// 注册失败
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jdbcUtils.release(preparedStatement,connection);
        }
        return false;
    }


}
