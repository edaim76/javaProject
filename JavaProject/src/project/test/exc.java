package project.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//import java.nio.charset.Charset;
import java.util.Scanner; 

import org.json.JSONObject;

public class exc {

	Employee employee;
	JSONObject root;
	JSONObject data;
	Scanner sc = new Scanner(System.in);
	BufferedWriter bw;
	
	public exc() {}
	public exc(Employee employee) throws IOException {
		this.employee = employee;
		BufferedReader br = new BufferedReader(new FileReader("C://Temp/AllEmployees.json"));
		root = new JSONObject(br.readLine());
		data = root.getJSONObject(this.employee.getId());
		br.close();
		bw = new BufferedWriter(new FileWriter("C://Temp/test.json"));
	}
	public exc(Employee employee, JSONObject root, JSONObject data) throws IOException {
		this.employee = employee;
		this.root = root;
		this.data = data;
		bw = new BufferedWriter(new FileWriter("C://Temp/test.json"));
	}
	
	public Employee normalAccountFix() {
		
		while(true) {
		
			System.out.println("\n---------- 개인 정보 수정 ----------");
			System.out.println("1. 이름 수정");
			System.out.println("2. 생년월일 수정");
			System.out.println("3. 전화번호 수정");
			System.out.println("4. 비밀번호 수정");
			System.out.println("9. 뒤로가기");
			System.out.print("메뉴를 선택하세요 > ");
			String select = sc.nextLine();
		
			switch(select) {
				case "1" :{
					System.out.print("\n바꿀 이름을 입력하세요 > ");
					String changeName = sc.nextLine();
					setName(changeName);
					break;
				}
				case "2" :{
					System.out.print("\n바꿀 생년월일을 입력하세요 > ");
					String changeBirthDate = sc.nextLine();
					setBirthDate(changeBirthDate);
					break;
				}
				case "3" :{
					System.out.print("\n바꿀 전화번호를 입력하세요 > ");
					String changePhoneNumber = sc.nextLine();
					setPhoneNumber(changePhoneNumber);
					break;
				}
				case "4" :{
					System.out.print("\n바꿀 비밀번호를 입력하세요 > ");
					String changePassword = sc.nextLine();
					setPassword(changePassword);
					break;
				}
				case "9" : {
					try {
						bw.close(); return employee;
					} catch(IOException e) {}
					}
			}
		
		}
		
	}
	
	public void setName(String name) {
		data.put("이름", name);
		employee.setName(name);
		writeData();
	}
	public void setBirthDate(String birthDate) {
		data.put("birthdate", birthDate);
		employee.setBirthDate(birthDate);
		writeData();
	}
	public void setPhoneNumber(String phoneNumber) {
		data.put("전화번호", phoneNumber);
		employee.setPhoneNumber(phoneNumber);
		writeData();
	}
	public void setPassword(String password) {
		data.put("password", password);
		employee.setPassword(password);
		writeData();
	}
	
	public void writeData() {
		try{bw.write(root.toString());}
		catch(IOException e) { e.printStackTrace(); }
		finally { try {
					bw.flush();
				} catch(IOException e) { e.printStackTrace();}
		}
	}
	

}
