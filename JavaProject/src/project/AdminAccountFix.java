package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.json.JSONObject;

public class AdminAccountFix {
//테스트 main
//	public static void main(String[] args){
//		
////		menuChange("B2", "직급");
//		adminAccountFix();
//	}

	public void adminAccountFix() {
		Scanner sc = new Scanner(System.in);

		boolean choice = true;
		while (choice) {
			
			System.out.print("수정할 계정의 사번을 입력해 주세요 : ");
			String companyId = sc.next();
			// 잘못 입력했을때 항목은 추후 추가
			System.out.println("1. 이름");
			System.out.println("2. password");
			System.out.println("3. birthdate");
			System.out.println("4. 직급");
			System.out.println("5. 소속");
			System.out.println("6. 입사일");
			System.out.println("7. 전화번호");
			System.out.println("9. 돌아가기");

			System.out.print("어떤 항목을 수정하실지 숫자를 입력해주세요 : ");
			int change = sc.nextInt();
			
			// 수정할 항목 선택 및 사번, 수정할 항목, 변경사항 넘기기
			if (change != 9) {
				System.out.println("변경사항을 입력해 주세요 : ");
				String a = sc.next();
				switch (change) {

				case 1:
					menuChange(companyId, "이름", a);
					choice = false;
					break;

				case 2:
					menuChange(companyId, "password", a);
					choice = false;
					break;

				case 3:
					menuChange(companyId, "birthdate", a);
					choice = false;
					break;

				case 4:
					menuChange(companyId, "직급", a);
					choice = false;
					break;

				case 5:
					menuChange(companyId, "소속", a);
					choice = false;
					break;

				case 6:
					menuChange(companyId, "입사일", a);
					choice = false;
					break;

				case 7:
					menuChange(companyId, "전화번호", a);
					choice = false;
					break;

				}

			} else if (change == 9) {
				System.out.println("이전 메뉴로 되돌아갑니다.");
				choice = false;
				break;
			}
		}

	}

	public void adminAccountFixAndSearch(String companyId) { // 사번 받아오는 파라미터
		Scanner sc = new Scanner(System.in);

		boolean choice = true;
		while (choice) {
			
			// 잘못 입력했을때 항목은 추후 추가
			System.out.println("1. 이름");
			System.out.println("2. password");
			System.out.println("3. birthdate");
			System.out.println("4. 직급");
			System.out.println("5. 소속");
			System.out.println("6. 입사일");
			System.out.println("7. 전화번호");
			System.out.println("9. 돌아가기");

			System.out.print("어떤 항목을 수정하실지 숫자를 입력해주세요 : ");
			int change = sc.nextInt();
			
			// 수정할 항목 선택 및 사번, 수정할 항목, 변경사항 넘기기
			if (change != 9) {
				System.out.println("변경사항을 입력해 주세요 : ");
				String a = sc.next();
				switch (change) {

				case 1:
					menuChange(companyId, "이름", a);
					choice = false;
					break;

				case 2:
					menuChange(companyId, "password", a);
					choice = false;
					break;

				case 3:
					menuChange(companyId, "birthdate", a);
					choice = false;
					break;

				case 4:
					menuChange(companyId, "직급", a);
					choice = false;
					break;

				case 5:
					menuChange(companyId, "소속", a);
					choice = false;
					break;

				case 6:
					menuChange(companyId, "입사일", a);
					choice = false;
					break;

				case 7:
					menuChange(companyId, "전화번호", a);
					choice = false;
					break;

				}

			} else if (change == 9) {
				System.out.println("이전 메뉴로 되돌아갑니다.");
				choice = false;
				break;
			}
		}
	}

	public void menuChange(String companyId, String change, String a) {

		try {
			// json 파일 접근
			BufferedReader br = new BufferedReader(new FileReader("C://Temp/AllEmployees.json", Charset.forName("UTF-8")));
			String json = br.readLine();
			br.close();
			JSONObject root = new JSONObject(json); 
			// 사번에 입력된 값 jp JSONObject에 저장
			JSONObject jp = new JSONObject(root.getJSONObject(companyId).toString());

			// jp 항목들과 변경할 항목이 일치하는지 확인하고 일치하면 jp에 새로운 값 a를 추가
			// 일치하지 않으면 기존 값 추가
			for (String s : jp.keySet()) {
				if (s.equals(change)) {
					jp.put(s, a);
				} else {
					jp.put(s, jp.get(s));
				}
			}
			root.put(companyId, jp);

			// json 파일에 root값 저장
			Writer writer = new FileWriter("C://Temp/AllEmployees.json", Charset.forName("UTF-8"));
			writer.write(root.toString());
			writer.flush();
			writer.close();
			System.out.println("변경이 완료되었습니다!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
