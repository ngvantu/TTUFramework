package com.ttu.client.model;

import java.util.List;

import ttuframework.annotation.Column;
import ttuframework.annotation.OneToMany;
import ttuframework.annotation.PrimaryKey;
import ttuframework.annotation.Table;
import ttuframework.common.DataType;

@Table(name="school")
public class School {

	@PrimaryKey(name="idSchool", autoId=false)
	@Column(name="idSchool",type=DataType.VARCHAR)
	private String idSchool;
	
	@Column(name="name",type=DataType.VARCHAR)
	private String name;
	
	@Column(name="address",type=DataType.VARCHAR)
	private String address;
	
	@OneToMany(tableName="student",relationshipId="idSchool")
	private List<Student> listStudent;

	public String getIdSchool() {
		return idSchool;
	}

	public void setIdSchool(String idSchool) {
		this.idSchool = idSchool;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Student> getListStudent() {
		return listStudent;
	}

	public void setListStudent(List<Student> listStudent) {
		this.listStudent = listStudent;
	}
	
	public String toString(){
		return this.idSchool;
	}
	
}
