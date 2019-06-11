package AccountSystem.dao;

import java.io.IOException;
import java.sql.*;

/**
 * 连接JDBC类
 */
public class JDBCUtils {
    //加载驱动，并建立数据库连接
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/db_accountSystem";
        String username = "root";
        String password = "admin";
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    //关闭数据库连接，释放资源
    public static void release(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    //关闭数据库连接，释放资源
    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        release(stmt, conn);
    }

    /**
     * Java代码实现MySQL数据库导出
     *
     * @param userName     进入数据库所需要的用户名
     * @param password     进入数据库所需要的密码
     * @param savePathName 数据库导出文件保存路径加名字
     * @param databaseName 要导出的数据库名
     * @return 返回true表示导出成功，否则返回false。
     */
    public static boolean backup(String userName, String password, String savePathName, String databaseName) throws InterruptedException {
        try {
//            String stmt = "mysql -uroot -padmin myDB < " + "c:/sql.sql";
            String stmt = "mysqldump -u" + userName + " -p" + password + " " + databaseName + " > " + savePathName;
            String[] cmd = {"cmd", "/c", stmt};
            Process process = Runtime.getRuntime().exec(cmd);
            if (process.waitFor() == 0) {
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
     *
     * @param username     用户名
     * @param password     用户数据库密码
     * @param databasename 数据库名字
     * @param filePathName 数据库文件路径及名字加后缀
     * @return boolean 如果恢复成功则返回true，否则返回false
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean recover(String username, String password, String databasename, String filePathName) throws IOException, InterruptedException {

        try {
//            String stmt = "mysql -uroot -padmin myDB < " + "c:/sql.sql";
            String stmt = "mysql -u" + username + " -p" + password + " " + databasename + " < " + filePathName;
            String[] cmd = {"cmd", "/c", stmt};
            Process process = Runtime.getRuntime().exec(cmd);
            if (process.waitFor() == 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
