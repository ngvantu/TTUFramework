package ttuframework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import ttuframework.connection.TTUConnection;
import ttuframework.connection.TTUSQLConnection;
import ttuframework.mapper.Mapper;
import ttuframework.mapper.SQLMapper;

public class thainn2Test {

	public static void main(String[] args) throws Exception {
	
		
        String url = "jdbc:mysql://localhost:3306/ttuframework";
		String username = "root";
		String password = "123456";
		
		//test company
		Connection cnn = DriverManager.getConnection(url, username, password);
		Statement stm = cnn.createStatement();
		ResultSet rs = stm.executeQuery("select * from company");
		Mapper mapper = new SQLMapper(Company.class);
		List<Company> listCompanies = mapper.mapWithRelationship(cnn, rs);
		rs.close();
		stm.close();
		for(Company c : listCompanies){
			System.out.println(c.toString());
			System.out.println("Number of employees: " + c.getListEmployees().size());
		}
		
		
		//test employee
		stm = cnn.createStatement();
		rs = stm.executeQuery("select * from employee");
		mapper = new SQLMapper(Employee.class);
		List<Employee> listEmployees = mapper.mapWithRelationship(cnn, rs);
		rs.close();
		stm.close();
		for(Employee e : listEmployees){
			System.out.println(e.toString());
		}
		
		//test TTUConnection
		TTUConnection ttucnn = new TTUSQLConnection(url, username, password);
		ttucnn.open();
		ttucnn.close();
	}
}
