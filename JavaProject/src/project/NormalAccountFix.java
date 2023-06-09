package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; 

import org.json.JSONObject;

public class NormalAccountFix {
	
	private Employee employee;
	private JSONObject root;
	private JSONObject employeeData;
	private Scanner sc = new Scanner(System.in);
	private BufferedWriter bw;
	private String rootPath = "C://Temp/AllEmployees.json";
	
	public NormalAccountFix(Employee employee) {
		this.employee = employee;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(rootPath));
			root = new JSONObject(br.readLine());
			employeeData = root.getJSONObject(this.employee.getId());
			br.close();
			bw = new BufferedWriter(new FileWriter(rootPath));
		} catch(IOException e) {e.printStackTrace();}
		finally {
			try{
				br.close();
			} catch(IOException e) {e.printStackTrace();}
		}
	}
	
	// 메소드
	public void normalAccountFix() {
		
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
						bw.close(); return;
					} catch(IOException e) {}
				}
			}
		
		}
		
	}
	
	// Employee 객체 필드, AllEmployees.json 업데이트
	public void setName(String name) {
		employeeData.put("이름", name);
		employee.setName(name);
		writeData();
	}
	public void setBirthDate(String birthDate) {
		employeeData.put("birthdate", birthDate);
		employee.setBirthDate(birthDate);
		writeData();
	}
	public void setPhoneNumber(String phoneNumber) {
		employeeData.put("전화번호", phoneNumber);
		employee.setPhoneNumber(phoneNumber);
		writeData();
	}
	public void setPassword(String password) {
		employeeData.put("password", password);
		employee.setPw(password);
		writeData();
	}
	
	// AllEmployees.json 업데이트
	public void writeData() {
		try{
//			root.put(employee.getId(), employeeData); // *유사시 주석 해제
			bw.write(root.toString());
		}
		catch(IOException e) { e.printStackTrace(); }
		finally { try {
					bw.flush(); bw.close();
				} catch(IOException e) { e.printStackTrace();}
		}
	}
	
}
