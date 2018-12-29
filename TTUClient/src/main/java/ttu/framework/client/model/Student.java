package ttu.framework.client.model;

import java.sql.Date;

import ttuframework.annotation.Column;
import ttuframework.annotation.PrimaryKey;
import ttuframework.annotation.Table;
import ttuframework.common.DataType;

@Table(name="student")
public class Student {

	@PrimaryKey(name="id", autoId=false)
	private int id;
	
	@Column(name="name",type=DataType.NVARCHAR)
	private String name;
	
	@Column(name="gender",type=DataType.VARCHAR)
	private String gender;
	
//	@Column(name="dob", type=DataType.DATE)
//	private Date dob;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

//	public Date getDob() {
//		return dob;
//	}
//
//	public void setDob(Date dob) {
//		this.dob = dob;
//	}

	public Student() {
		super();
	}
}
