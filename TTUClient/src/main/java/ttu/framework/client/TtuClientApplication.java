package ttu.framework.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ttu.framework.client.model.Student;
import ttuframework.connection.TTUConnection;
import ttuframework.mapper.Mapper;
import ttuframework.mapper.SQLMapper;
import ttuframework.query.QueryWhere;

@SpringBootApplication
public class TtuClientApplication {

	//test mapper by thainn2
	public static void main(String[] args) throws SQLException {
//		SpringApplication.run(TtuClientApplication.class, args);
		
		String url = "jdbc:mysql://localhost:3306/ttuframework";
		String username = "root";
		String password = "123456";
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement sttm = conn.createStatement();
		ResultSet rs = sttm.executeQuery("select * from student");
		while(rs.next()){
			System.out.println(rs.getString(1) + "  "+ rs.getString(2) + "  " + rs.getString(3)+ "  " + rs.getString(5));
			Mapper m = new SQLMapper(Student.class);
			try {
				Student s = m.mapWithOutRelationship(rs);
				System.out.println(s.getName());
				System.out.println(s.getDob());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

