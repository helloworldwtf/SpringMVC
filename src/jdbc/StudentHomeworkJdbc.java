package jdbc;

import model.StudentHomework;
import org.springframework.context.annotation.Bean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentHomeworkJdbc {
    public static void main(String[] args) {
        List<StudentHomework> list = selectAll();

        for (StudentHomework sh : list){
            System.out.println(sh.getHomeworkContent());
        }
    }
    @Bean(name = "jdbc1")
    public StudentHomeworkJdbc getJDBC() {
        return new StudentHomeworkJdbc();
    }

    public static List<StudentHomework> selectAll(){

        String url = "jdbc:mysql://127.0.0.1:3306/school";

        String allUrl = url + "?user=root&password=12345678";

        String driverName = "com.mysql.jdbc.Driver";

        String sqlString = "SELECT * FROM s_student_homework";

        try {
            // 加载驱动
            Class.forName(driverName);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<StudentHomework> list = new ArrayList<>();
        try(Connection connection =  DriverManager.getConnection(allUrl)) {
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        StudentHomework sh = new StudentHomework();
                        sh.setId(resultSet.getLong("id"));
                        sh.setStudentId(resultSet.getLong("student_id"));
                        sh.setHomeworkId(resultSet.getLong("homework_id"));
                        sh.setHomeworkTitle(resultSet.getString("homework_title"));
                        sh.setHomeworkContent(resultSet.getString("homework_content"));
                        sh.setCreateTime(resultSet.getTimestamp("create_time"));
                        list.add(sh);

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
