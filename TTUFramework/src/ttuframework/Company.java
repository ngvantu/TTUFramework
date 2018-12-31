package ttuframework;

import java.util.List;

import ttuframework.annotation.Column;
import ttuframework.annotation.OneToMany;
import ttuframework.annotation.PrimaryKey;
import ttuframework.annotation.Table;
import ttuframework.common.DataType;

@Table(name="company")
public class Company {

	@PrimaryKey(name="id", autoId=false)
	@Column(name="id", type=DataType.VARCHAR)
	private String id;
	
	@Column(name="name", type= DataType.VARCHAR)
	private String name;
	
	@Column(name="address", type= DataType.VARCHAR)
	private String address;
	
	@OneToMany(tableName="employee", relationshipId="company")
	private List<Employee> listEmployees;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Employee> getListEmployees() {
		return listEmployees;
	}

	public void setListEmployees(List<Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}
	
	public String toString(){
		return String.format("Company:[id=%s, name=%s, address=%s]", id, name, address);
	}
}
