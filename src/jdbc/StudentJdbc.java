package jdbc;

import model.Student;
import org.springframework.context.annotation.Bean;

import java.sql.*;

public class StudentJdbc {
    private static String url = "jdbc:mysql://127.0.0.1:3306/school";

    private static String allUrl = url + "?user=root&password=12345678";

    private static String driverName = "com.mysql.cj.jdbc.Driver";

    @Bean(name = "jdbc2")
    public StudentJdbc getJDBC() {
        return new StudentJdbc();
    }
    public static boolean addStudent(Student student){

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sqlString = "insert into s_student (id,name,create_time) values(?,?,?)";

        int resultSet = 0;
        try (Connection connection = DriverManager.getConnection(allUrl, "root", "12345678")) {
            try (PreparedStatement ps = connection.prepareStatement(sqlString)) {
                ps.setLong(1,student.getId());
                ps.setString(2,student.getName());
                ps.setTimestamp(3,new Timestamp(student.getCreateTime().getTime()));
                resultSet = ps.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet > 0;
    }
}
