package project.test;

import java.io.BufferedReader;
import java.io.FileReader;

import org.json.JSONObject;

public class Employee {
	
	private String id;
	private String name;
	private String birthDate;
	private String phoneNumber;
	private String password;
	
	
	public Employee() { }
	public Employee(String id) throws Exception {
		
		this.id = id;
		
		BufferedReader br = new BufferedReader(new FileReader("C://Temp/AllEmployees.json"));
		JSONObject root = new JSONObject(br.readLine());
		br.close();
		JSONObject data = root.getJSONObject(this.id);
		
		this.name = data.getString("이름");
		this.birthDate = data.getString("birthdate");
		this.phoneNumber = data.getString("전화번호");
		this.password = data.getString("password");
		
	}
	public Employee(String id, JSONObject data) throws Exception {
		
		this.id = id;
		
		this.name = data.getString("이름");
		this.birthDate = data.getString("birthdate");
		this.phoneNumber = data.getString("전화번호");
		this.password = data.getString("password");
		
	}
	
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
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void printInfo() {
		System.out.println(id + " / " + name + " / " + birthDate + " / " + phoneNumber + " / " + password);
	}
	
}
