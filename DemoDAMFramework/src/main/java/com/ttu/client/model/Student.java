package com.ttu.client.model;


import java.sql.Date;

import ttuframework.annotation.Column;
import ttuframework.annotation.ManyToOne;
import ttuframework.annotation.PrimaryKey;
import ttuframework.annotation.Table;
import ttuframework.common.DataType;

@Table(name="student")
public class Student {
	
	@PrimaryKey(name="idStudent", autoId=false)
	@Column(name="idStudent",type=DataType.VARCHAR)
	private String idStudent;
	
	@Column(name="name",type=DataType.VARCHAR)
	private String name;
	
	@Column(name="gender",type=DataType.VARCHAR)
	private String gender;
	
	@Column(name="dob",type=DataType.DATE)
	private Date dob;
	
	@Column(name="idSchool", type=DataType.VARCHAR)
	@ManyToOne(tableName="school",relationshipId="idSchool")
	private School idSchool;

	public String getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(String idStudent) {
		this.idStudent = idStudent;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public School getIdSchool() {
		return idSchool;
	}

	public void setIdSchool(School idSchool) {
		this.idSchool = idSchool;
	}
	
	public String toString(){
		return this.idStudent;
	}
}
