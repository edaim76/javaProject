package project;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Employee {
	
	private String Id;
    private String name;
    private String pw;
    private String birthDate;
    private String position;
    private String department;
    private String joinCompanyDate;
    private String phoneNumber;

	public Employee(String employeeId) {
		try {	
			String rootPath = "C:/Temp/AllEmployees.json";
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(rootPath));
			
			JSONObject employeeObject = (JSONObject) jsonObject.get(employeeId);

			if (employeeObject != null) {
				this.Id = employeeId;
				this.name = (String) employeeObject.get("이름");
				this.pw = (String) employeeObject.get("password");
				this.birthDate = (String) employeeObject.get("birthdate");
				this.position = (String) employeeObject.get("직급");
				this.department = (String) employeeObject.get("소속");
				this.joinCompanyDate = (String) employeeObject.get("입사일");
				this.phoneNumber = (String) employeeObject.get("전화번호");
			} else {
				throw new IllegalArgumentException("유효하지 않은 사번입니다.");
			}
		}catch(Exception e) {e.printStackTrace();}
	}

	// Getters & Setter 메소드

	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getJoinCompanyDate() {
		return joinCompanyDate;
	}
	public void setJoinCompanyDate(String joinCompanyDate) {
		this.joinCompanyDate = joinCompanyDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
