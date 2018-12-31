package ttuframework;


import java.util.Date;

import ttuframework.annotation.Column;
import ttuframework.annotation.ManyToOne;
import ttuframework.annotation.PrimaryKey;
import ttuframework.annotation.Table;
import ttuframework.common.DataType;

@Table(name="employee")
public class Employee {

	@PrimaryKey(name="id", autoId=false)
	@Column(name="id", type=DataType.VARCHAR)
	private String id;
	
	@Column(name="name", type=DataType.VARCHAR)
	private String name;
	
	@Column(name="dob", type=DataType.DATE)
	private Date dob;
	
	@Column(name="gender", type=DataType.VARCHAR)
	private String gender;
	
	@Column(name="id", type=DataType.VARCHAR)
	@ManyToOne(tableName="company", relationshipId="id")
	private Company company;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String toString(){
		return String.format("Student:[id=%s, name=%s, dob=%s, gender=%s, company=%s]", id, name, dob, gender, company.getName());
	}
}
