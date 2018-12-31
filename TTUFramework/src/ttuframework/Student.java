/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework;

import java.util.Date;
import ttuframework.annotation.Column;
import ttuframework.annotation.ForeignKey;
import ttuframework.annotation.ManyToOne;
import ttuframework.annotation.PrimaryKey;
import ttuframework.annotation.Table;
import ttuframework.common.DataType;

/**
 *
 * @author Tu Nguyen
 */
@Table(name="student")
public class Student {

	@PrimaryKey(name="id", autoId=false)
	private int id;
	
	@Column(name="name",type=DataType.NVARCHAR)
	private String name;
	
	@Column(name="gender",type=DataType.VARCHAR)
	private String gender;
	
	@Column(name="dob", type=DataType.DATE)
	private Date dob;
	
	@ForeignKey(name="group", references="sclass", relationshipId="id")
	@ManyToOne(tableName="sclass",relationshipId="id")
	private String group;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Student() {
		super();
	}
	
	public String toString(){
		return String.format("{id=%s, name=%s, gender=%s, dob=%s, class=%s}", id,name, gender, dob, group);
	}
	
}
