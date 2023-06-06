package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.json.JSONObject;

public class AdminAccountHandling {
	// 테스트 main
//	public static void main(String[] args) throws IOException {
//		adminAccountHandlingSub();
//	}

	// 계정 추가 메소드
	public void adminAccountHandlingAdd() throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new FileReader("C://Temp/AllEmployees.json", Charset.forName("UTF-8")));
		String json = br.readLine();
		br.close();
		JSONObject root = new JSONObject(json);

		JSONObject jp = new JSONObject();
		
		System.out.print("이름을 입력해 주세요 : ");
		String name = sc.next();
		System.out.print("password를 입력해 주세요 : ");
		String pw = sc.next();
		System.out.print("birthdate를 입력해 주세요 : ");
		String bd = sc.next();
		System.out.print("직급을 입력해 주세요 : ");
		String ps = sc.next();
		System.out.print("소속을 입력해 주세요 : ");
		String dt = sc.next();
		System.out.print("입사일을 입력해 주세요 : ");
		String dj = sc.next();
		System.out.println("전화번호를 입력해 주세요 : ");
		String pn = sc.next();
		System.out.println("사번을 입력해 주세요 : ");
		String en = sc.next();
		
		jp.put("이름", name);
		jp.put("password", pw);
		jp.put("birthdate", bd);
		jp.put("직급", ps);
		jp.put("소속", dt);
		jp.put("입사일", dj);
		jp.put("전화번호", pn);
		root.put(en, jp);
		
		
		Writer writer = new FileWriter("C://Temp/AllEmployees.json", Charset.forName("UTF-8"));
		writer.write(root.toString());
		writer.flush();
		writer.close();
	}
	

	// 계정 삭제 메소드
	public static void adminAccountHandlingSub() throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new FileReader("C://Temp/AllEmployees.json", Charset.forName("UTF-8")));
		String json = br.readLine();
		br.close();
		JSONObject root = new JSONObject(json);
		
		
		
		boolean choice = true;
		while(choice) {
			System.out.print("삭제할 인원의 사번을 입력하세요 : ");
			String companyId = sc.next();
			
			for(String s : root.keySet()) {
				if(s.equals(companyId)) {
					root.remove(companyId);
					choice = false;
				}

			}
		}
				
		System.out.println(root.toString());
		
		Writer writer = new FileWriter("C://Temp/AllEmployees.json", Charset.forName("UTF-8"));
		writer.write(root.toString());
		writer.flush();
		writer.close();
	}
}
