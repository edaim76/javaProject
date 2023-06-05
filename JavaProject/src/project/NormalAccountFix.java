package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner; 

import org.json.JSONObject;

public class NormalAccountFix {
	
	Employee employee;
	JSONObject employeeData;
	Scanner sc = new Scanner(System.in);
	
	
	public NormalAccountFix() {}
	public NormalAccountFix(Employee employee) {
		this.employee = employee;
		BufferedReader br = new BufferedReader(new FileReader("C://Temp/AllEmployees.json", Charset.forName("UTF-8")));
		String json = br.readLine();
		JSONObject root = new JSONObject(json);
		employeeData = root.get(this.employee.getId());
		br.close();
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
					setName(changeBirthDate);
					break;
				}
				case "3" :{
					System.out.print("\n바꿀 전화번호를 입력하세요 > ");
					String changePhoneNumber = sc.nextLine();
					setName(changePhoneNumber);
					break;
				}
				case "4" :{
					System.out.print("\n바꿀 비밀번호를 입력하세요 > ");
					String changePassword = sc.nextLine();
					setName(changePassword);
					break;
				}
				case "9" : return employee;
			}
		
		}
		
	}
	
	public void setName(String name) {
		employee.setName(name);
		employeeData.get(name);
	}
	public void setBirthDate(String BirthDate) {
		employee.setBirthDate(BirthDate);
	}
	public void setPhoneNumber(String phoneNumber) {
		employee.setPhoneNumber(phoneNumber);
	}
	public void setPassword(String password) {
		employee.setPassword(password);
	}
	
}
