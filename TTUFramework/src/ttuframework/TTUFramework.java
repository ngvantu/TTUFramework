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
import ttuframework.mapper.Mapper;
import ttuframework.mapper.SQLMapper;

/**
 *
 * @author Tu Nguyen
 */
public class TTUFramework {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, Exception {
        // TODO code application logic here
        String url = "jdbc:mysql://localhost:3306/ttuframework";
		String username = "root";
		String password = "123456";
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement sttm = conn.createStatement();
		ResultSet rs = sttm.executeQuery("select * from student");
                Student student = new Student();
                Mapper m = new SQLMapper(Student.class);
		while(rs.next()){
                    student = m.mapWithOutRelationship(rs);
		}
               
                System.out.println(student.getId() + "  " + student.getName());
                Field[] fields = Student.class.getDeclaredFields();
                List<Column> w = m.getAllAnnotation(fields, Column.class);
                Method[] methods = Student.class.getMethods();
        
        // set breakpoint at below line to watch data test
        System.out.println("Ok");
    }
    
}
