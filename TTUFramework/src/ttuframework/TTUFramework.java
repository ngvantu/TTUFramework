/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import ttuframework.annotation.Column;
import ttuframework.annotation.Table;
import ttuframework.common.Converter;
import ttuframework.connection.TTUConnection;
import ttuframework.connection.TTUSQLConnection;
import ttuframework.mapper.Mapper;
import ttuframework.mapper.SQLMapper;
import ttuframework.query.QueryWhere;

/**
 *
 * @author Tu Nguyen
 */
public class TTUFramework {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException  {
        // TODO code application logic here
        String url = "jdbc:mysql://localhost:3306/ttuframework";
		String username = "root";
		String password = "123456";
    }
               
//        TTUConnection cnn = new TTUSQLConnection(url, username, password);
//        cnn.open();
//        List<Student> students = cnn.executeQueryWithOutRelationship("select * from student");
//        for (Student student : students) {
//            System.out.println(student.getId() + "  " + student.getName());
//        }
//
//        // set breakpoint at below line to watch data test
//        System.out.println("Ok");
//		
//		Connection cnn = DriverManager.getConnection(url, username, password);
//		Statement stm = cnn.createStatement();
//		ResultSet rs = stm.executeQuery("select * from student");
//		Mapper m = new SQLMapper(Student.class);
//		try {
//			List<Student> ls = m.map(stm, rs);
//			for (Student s : ls){
//				System.out.println(s.toString());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		rs.close();
//		stm.close();
//    }
    
}
