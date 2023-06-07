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

//	// 테스트 main
//	public static void main(String[] args) throws IOException {
//		adminAccountHandlingSub();
//	}

	// 계정 추가 메소드
	public void adminAccountHandlingAdd() {
		Scanner sc = new Scanner(System.in);

		try {
			// 임직원 계정 정보 json 접근
			BufferedReader br = new BufferedReader(new FileReader("C://Temp/AllEmployees.json", Charset.forName("UTF-8")));
			String json = br.readLine();
			br.close();
			JSONObject root = new JSONObject(json);

			// 사번에 해당하는 JSONObject 값 넣을 빈 JSONObject
			JSONObject jp = new JSONObject();

			// 값을 추가할 사번 및 내용들 입력
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

			// jp JSONObject에 값 추가
			jp.put("이름", name);
			jp.put("password", pw);
			jp.put("birthdate", bd);
			jp.put("직급", ps);
			jp.put("소속", dt);
			jp.put("입사일", dj);
			jp.put("전화번호", pn);

			// root JSONObject에 en(사번), jp(사번에 해당하는 JSONObject) 값 추가
			root.put(en, jp);

			// json파일에 root JSONObject 저장
			Writer writer = new FileWriter("C://Temp/AllEmployees.json", Charset.forName("UTF-8"));
			writer.write(root.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 계정 삭제 메소드
	public void adminAccountHandlingSub() {
		Scanner sc = new Scanner(System.in);

		// 임직원 계정 정보 json 접근
		try {
			BufferedReader br = new BufferedReader(new FileReader("C://Temp/AllEmployees.json", Charset.forName("UTF-8")));
			String json = br.readLine();
			br.close();
			JSONObject root = new JSONObject(json);
			JSONObject jp = new JSONObject(json);

			boolean choice = true;

			// 삭제할 사번 입력 및 삭제
			while (choice) {
				System.out.print("삭제할 인원의 사번을 입력하세요 : ");
				String companyId = sc.next();
				
				for (String s : jp.keySet()) {
					if (s.equals(companyId)) {
						root.remove(companyId);
						choice = false;
					}
				}
			}

			// json파일에 root JSONObject 저장
			Writer writer = new FileWriter("C://Temp/AllEmployees.json", Charset.forName("UTF-8"));
			writer.write(root.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
