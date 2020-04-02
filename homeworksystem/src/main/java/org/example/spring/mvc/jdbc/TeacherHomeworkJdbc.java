package org.example.spring.mvc.jdbc;
import org.example.spring.mvc.model.TeacherHomework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author:GQM
 * @Date:created in 18:10 2020/3/7
 * @Description:
 * @Modifyed_By:
 */
public class TeacherHomeworkJdbc {

    //获取所有教师布置的作业
    public static List<TeacherHomework> selectAllTeacherHomework() throws ClassNotFoundException {
        String url="jdbc:mysql://127.0.0.1:3306/javaee?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String drivername = "com.mysql.cj.jdbc.Driver";
        Class.forName(drivername);//可以省略
        String sqlString = "select * from teacher_homework ";

        List<TeacherHomework>list=new ArrayList<>();


        try (Connection connection = DriverManager.getConnection(url, "root", "030016")) {
            //通过连接获取statement
            try (Statement statement = connection.createStatement()) {
                //statement （增、删、改、查）
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        TeacherHomework th = new TeacherHomework();
                        th.setHomeworkId(resultSet.getLong("homework_id"));
                        th.setHomeworkTitle(resultSet.getString("homework_title"));
                        list.add(th);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static boolean addHomework(TeacherHomework nth) throws ClassNotFoundException {
//        String url="jdbc:mysql://127.0.0.1:3306/javaee?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
//        String drivername = "com.mysql.cj.jdbc.Driver";
//        Class.forName(drivername);//可以省略
        boolean isSuccess = true;
        try (Connection connection = (Connection) DataBasePool.getHikariDataSource()) {
            //通过连接获取statement

            //Preparestatement （增、删、改、查）
            String sqlString = "insert into teacher_homework(homework_id,homework_title) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setLong(1,nth.getHomeworkId());
            ps.setString(2,nth.getHomeworkTitle());
            //成功返回false 失败返回true
            isSuccess = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //为了方便后面操作 返回相反的结果
        return !isSuccess;
    }





    public static void main(String[] args) throws ClassNotFoundException {


        List<TeacherHomework> list1 = selectAllTeacherHomework();
        for(TeacherHomework th:list1){
            System.out.println(th.getHomeworkId());
        }
    }
}
