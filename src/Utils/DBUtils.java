package Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.beanutils.BeanUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

public class DBUtils {

    public static Connection conn;

    static {
        try {
            conn = getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //连接数据库
    public static Connection getConnection() throws Exception {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/db.properties");
        Properties properties = new Properties();
        properties.load(in);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        return connection;
    }

    //查询数据并封装在一个类中
    public static <T> T getSingleObj(Class<T> clazz, String sql, Object... args) {
//        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        T bean = null;
        ResultSetMetaData rsmd = null;
        try {
//            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            // 设置SQL语句参数
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            rs = ps.executeQuery();
            //获取元数据
            rsmd = rs.getMetaData();
            //获取结果集列数
            int colsnum = rsmd.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowMap = new HashMap<String, Object>();
                for (int i = 1; i <= colsnum; i++) {
                    String columnName = rsmd.getColumnLabel(i);
                    Object columnValue = rs.getObject(columnName);

                    //判断类型Data
                    if (columnValue instanceof java.sql.Date) {
                        java.sql.Date date = (java.sql.Date) columnValue;
                        columnValue = new java.util.Date(date.getTime());
                    }
                    rowMap.put(columnName, columnValue);
                }
                bean = clazz.newInstance();

                for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    BeanUtils.setProperty(bean, propertyName, propertyValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return bean;
    }

    //查询数据并封装在一个类中，并把类装在对象集中
    public static<T> List<T> getList(Class<T> clazz, String sql, Object...args ){
//        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<T> userList=null;
        ResultSetMetaData rsmd=null;
        try{
//            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if(args!=null && args.length>0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i+1,args[i]);
                }
            }
            rs= ps.executeQuery();
            //获取元数据
            rsmd=rs.getMetaData();
            //获取结果集列数
            int colsnum = rsmd.getColumnCount();
            userList = new ArrayList<>();
            while (rs.next()) {
                Map<String,Object> rowMap = new HashMap<String,Object>();
                for (int i=1;i<=colsnum;i++){
                    String columnName  = rsmd.getColumnLabel(i);
                    Object columnValue = rs.getObject(columnName);
                    //判断类型Data
                    if (columnValue instanceof java.sql.Date){
                        java.sql.Date date=(java.sql.Date)columnValue;
                        columnValue=new java.util.Date(date.getTime());
                    }
                    rowMap.put(columnName,columnValue);
                }
                T bean=clazz.newInstance();

                for(Map.Entry<String, Object> entry:rowMap.entrySet()) {
                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    //根据列名，id==给User对象ID属性赋值的方法名，setId，“set"+Id
//                String methodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
//                Method method = clazz.getMethod(methodName, propertyValue.getClass());
//                  method.invoke(bean, propertyValue);
                    BeanUtils.setProperty(bean,propertyName,propertyValue);
                }
                userList.add(bean);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(conn, ps, rs);
        }
        return userList;
    }

    //插入保存数据库
    public static boolean save(String sql, Object... args) {
//        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = null;
        try {
//            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            // 设置SQL语句参数
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
        return count != null && count > 0;
    }

    //查询满足条件的数量
    public static Integer getCount(String sql, Object... args) {
//        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        Integer count = null;
        try {
//            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return count;
    }

    //更新数据并返回数据的主键（主键得是自增的）
    public static Integer updataForPrimary(String sql, Object... args) {
//        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = -1;
        ResultSetMetaData rsmd = null;
        Integer primaryKey = null;
        ResultSet rs = null;
        try {
//            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof java.util.Date) {
                        java.util.Date date = (java.util.Date) args[i];
                        args[i] = new java.sql.Date(date.getTime());
                    }
                    ps.setObject(i + 1, args[i]);
                }
            }
            count = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                primaryKey = rs.getInt(1);
            }
            //获取元数据
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return primaryKey;
    }

    //更新数据（根据满足条件的元数据）
    public static boolean updata(String sql, Object... args) {
//        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = -1;
        ResultSetMetaData rsmd = null;
        try {
//            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof java.util.Date) {
                        java.util.Date date = (java.util.Date) args[i];
                        args[i] = new java.sql.Date(date.getTime());
                    }
                    ps.setObject(i + 1, args[i]);
                }
            }
            count = ps.executeUpdate();
            //获取元数据
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
        return count > 0;
    }

    // 关闭数据库连接
    public static void close(Connection conn, Statement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
//            try {
////                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
    }

    public static void closeConn(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
