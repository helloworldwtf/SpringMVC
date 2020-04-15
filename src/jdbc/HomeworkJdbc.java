package jdbc;
import model.Homework;
import model.StudentHomework;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomeworkJdbc {
    private static String url = "jdbc:mysql://127.0.0.1:3306/school";

    private static String allUrl = url + "?user=root&password=12345678";

    private static String driverName = "com.mysql.jdbc.Driver";

    @Bean(name = "jdbc0")
    public HomeworkJdbc getJDBC() {
        return new HomeworkJdbc();
    }
    public static boolean addHomework(Homework homework){

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sqlString = "insert into s_homework (title,content,create_time) values(?,?,?)";

        int resultSet = 0;
        try (Connection connection = DriverManager.getConnection(allUrl, "root", "12345678");) {
            try (PreparedStatement ps = connection.prepareStatement(sqlString)) {
                ps.setString(1,homework.getTitle());
                ps.setString(2,homework.getContent());
                ps.setTimestamp(3,new Timestamp(homework.getCreateTime().getTime()));
                resultSet = ps.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet > 0;
    }
    public static List<Homework> showHomework(){

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sqlString = "SELECT * FROM s_homework";

        List<Homework> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, "root", "12345678")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        Homework homework = new Homework();
                        homework.setId(resultSet.getLong("id"));
                        homework.setTitle(resultSet.getString("title"));
                        homework.setContent(resultSet.getString("content"));
                        homework.setCreateTime(resultSet.getTimestamp("create_time"));
                        list.add(homework);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }
    public static boolean addStudentHomework(StudentHomework studentHomework){

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sqlString = "insert into s_student_homework (student_id,homework_id," +
                "homework_title,homework_content,create_time) values(?,?,?,?,?)";

        int resultSet = 0;

        try (Connection connection = DriverManager.getConnection(url, "root", "12345678")) {
            try (PreparedStatement ps = connection.prepareStatement(sqlString)) {
                ps.setLong(1,studentHomework.getStudentId());
                ps.setLong(2,studentHomework.getHomeworkId());
                ps.setString(3,studentHomework.getHomeworkTitle());
                ps.setString(4,studentHomework.getHomeworkContent());
                ps.setTimestamp(5,new Timestamp(studentHomework.getCreateTime().getTime()));
                resultSet = ps.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet > 0;
    }
    public static Homework showHomeworkDetails(String id){

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sqlString = "SELECT * FROM s_homework WHERE id=" + id;
        System.out.println(id);
        Homework homework = new Homework();
        try (Connection connection = DriverManager.getConnection(url, "root", "12345678")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        homework.setId(resultSet.getLong("id"));
                        homework.setTitle(resultSet.getString("title"));
                        System.out.println(resultSet.getLong("id"));
                        homework.setContent(resultSet.getString("content"));
                        homework.setCreateTime(resultSet.getTimestamp("create_time"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return homework;
    }
}
