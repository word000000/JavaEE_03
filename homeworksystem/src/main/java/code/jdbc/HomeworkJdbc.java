package code.jdbc;



import code.model.Student;
import code.model.StudentHomework;
import code.model.TeacherHomework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author:GQM
 * @Date:created in 18:10 2020/3/7
 * @Description:
 * @Modifyed_By:
 */
public class HomeworkJdbc {
    //获取所有学生提交的作业
    public static List<StudentHomework> selectAllStudentHomework() throws ClassNotFoundException {
        String url="jdbc:mysql://127.0.0.1:3306/javaee?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";

        String drivername = "com.mysql.cj.jdbc.Driver";

        String sqlString = "select * from student_homework ";

        List<StudentHomework>list=new ArrayList<>();
        Class.forName(drivername);//可以省略
        try (Connection connection = DriverManager.getConnection(url, "root", "0016")) {
            //通过连接获取statement
            try (Statement statement = connection.createStatement()) {
                //statement （增、删、改、查）
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        StudentHomework sh = new StudentHomework();
                        sh.setId(resultSet.getLong("id"));
                        sh.setStudentId(resultSet.getLong("student_id"));
                        sh.setHomeworkId(resultSet.getLong("homework_id"));
                        sh.setHomeworkTitle(resultSet.getString("homework_title"));
                        sh.setHomeworkContent(resultSet.getString("homework_content"));
                        sh.setCreatTime(resultSet.getTimestamp("create_time"));
                        sh.setUpdateTime(resultSet.getTimestamp("update_time"));
                        list.add(sh);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //获取所有教师布置的作业
    public static List<TeacherHomework> selectAllTeacherHomework() throws ClassNotFoundException {
        String url="jdbc:mysql://127.0.0.1:3306/javaee?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";

        String drivername = "com.mysql.cj.jdbc.Driver";

        String sqlString = "select * from teacher_homework ";

        List<TeacherHomework>list=new ArrayList<>();
        Class.forName(drivername);//可以省略
        try (Connection connection = DriverManager.getConnection(url, "root", "0016")) {
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
        String url="jdbc:mysql://127.0.0.1:3306/javaee?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String drivername = "com.mysql.cj.jdbc.Driver";
        Class.forName(drivername);//可以省略
        boolean isSuccess = true;
        try (Connection connection = DriverManager.getConnection(url, "root", "0016")) {
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


    public static String handHomework(StudentHomework nsh) throws ClassNotFoundException {
        String url="jdbc:mysql://127.0.0.1:3306/javaee?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String drivername = "com.mysql.cj.jdbc.Driver";
        Class.forName(drivername);//可以省略
        String respone = "提交成功";
        List<StudentHomework> shlist = HomeworkJdbc.selectAllStudentHomework();
        List<Student> slist = StudentJdbc.selectAllStudent();
        List<TeacherHomework> thlist = HomeworkJdbc.selectAllTeacherHomework();
        boolean studentExist = false;
        boolean homeworkExist = false;
        //检查学生id
        for(Student s:slist){
            if(s.getStudentId().equals(nsh.getStudentId())){
                studentExist=true;
                break;
            }
        }
        if(!studentExist){
            return  "当前输入的学生id不存在";
        }
        //检查作业是否存在
        for(TeacherHomework th:thlist){
            if(th.getHomeworkId()==nsh.getHomeworkId()){
                homeworkExist=true;
                nsh.setHomeworkTitle(th.getHomeworkTitle());
                break;
            }
        }
        if(!homeworkExist){
            return  "未发现该作业";
        }
        try (Connection connection = DriverManager.getConnection(url, "root", "0016")) {
            //通过连接获取statement
            String sqlString = "insert into student_homework(id,student_id,homework_id,homework_title,homework_content,create_time)values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setLong(1,shlist.size()+1);
            ps.setLong(2,nsh.getStudentId());
            ps.setLong(3,nsh.getHomeworkId());
            ps.setString(4,nsh.getHomeworkTitle());
            ps.setString(5,nsh.getHomeworkContent());
            ps.setTimestamp(6, nsh.getCreatTime());
            //成功返回false 失败返回true
            if(ps.execute()){
                return "提交失败，请检查后再提交";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //为了方便后面操作 返回相反的结果
        return respone;
    }


    public static void main(String[] args) throws ClassNotFoundException {

        List<StudentHomework> list= selectAllStudentHomework();
        List<TeacherHomework> list1 = selectAllTeacherHomework();
        for(TeacherHomework th:list1){
            System.out.println(th.getHomeworkId());
        }
    }
}
