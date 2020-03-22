package code.jdbc;

import code.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 22:15 2020/3/7
 * @Description:
 * @Modifyed_By:
 */
public class StudentJdbc {
    public static List<Student> selectAllStudent() throws ClassNotFoundException {
        String url="jdbc:mysql://127.0.0.1:3306/javaee?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";

        String drivername = "com.mysql.cj.jdbc.Driver";

        String sqlString = "select * from student ";

        List<Student>list=new ArrayList<>();
        Class.forName(drivername);//可以省略
        try (Connection connection = DriverManager.getConnection(url, "root", "0016")) {
            //通过连接获取statement
            try (Statement statement = connection.createStatement()) {
                //statement （增、删、改、查）
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setStudentId(resultSet.getLong("student_id"));
                        student.setStudentName(resultSet.getString("student_name"));
                        list.add(student);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean addStudent(Student newStudent) throws ClassNotFoundException {
        String url="jdbc:mysql://127.0.0.1:3306/javaee?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String drivername = "com.mysql.cj.jdbc.Driver";
        List<Student>list=new ArrayList<>();
        Class.forName(drivername);//可以省略
        boolean isSuccess = true;
        try (Connection connection = DriverManager.getConnection(url, "root", "0016")) {
            //通过连接获取statement

                //Preparestatement （增、删、改、查）
                String sqlString = "insert into student(student_id,student_name) values (?,?)";
                PreparedStatement ps = connection.prepareStatement(sqlString);
                ps.setLong(1,newStudent.getStudentId());
                ps.setString(2,newStudent.getStudentName());
                //成功返回false 失败返回true
                isSuccess = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //为了方便后面操作 返回相反的结果
        return !isSuccess;
    }
    public static void main(String[] args) throws ClassNotFoundException {

        List<Student> list=selectAllStudent();
        for(Student student:list){
            System.out.println(student.getStudentId());
        }
    }
}
