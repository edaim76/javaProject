package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.ArrayList;

import org.json.JSONObject;

public class AdminSearch {

//	// 테스트 main 코드
//	public static void main(String[] args) {
//		adminSearch();
//	}

	public void adminSearch() {
		Scanner sc = new Scanner(System.in);

		try {

			boolean choice = true;
			while (choice) {
				// 사번, 해당 사번 저장할 ArrayList
				ArrayList<JSONObject> list = new ArrayList<>();
				ArrayList<String> nameList = new ArrayList<>();
				AdminAccountFix af = new AdminAccountFix(); // 수정 메뉴 객체
				String companyId; // 사번 저장
				// json파일 접근
				BufferedReader br = new BufferedReader(new FileReader("C://Temp/AllEmployees.json", Charset.forName("UTF-8")));
				String json = br.readLine();
				br.close();
				JSONObject root = new JSONObject(json.toString());

				System.out.print("검색하실 이름을 입력해 주세요 : ");
				String name = sc.next();

				// ArrayList에 사번, 사번에 해당하는 값 추가
				for (String s : root.keySet()) {
					if (!s.equals("admin")) {
						JSONObject jp = new JSONObject(root.getJSONObject(s).toString());
						if (jp.get("이름").equals(name)) {
							list.add(jp);
							nameList.add(s);
						}
					}
				}

				// ArrayList에 있는 값들 순서대로 출력
				for (int i = 0; i < list.size(); i++) {
					System.out.println((i + 1) + ". : " + list.get(i));
				}
				System.out.println("선택하실 분의 번호를 입력해 주세요 : ");
				int num = sc.nextInt();
				// 번호를 입력하면 ArrayList에 있는 해당 사번의 값을 출력
				System.out.println(list.get(num - 1)); // 해당 항목은 확인을 위해 추가한 것이므로 삭제해도 무방함
				// 번호 입력하면 ArrayList에 있는 해당 사번을 companyId에 입력
				companyId = nameList.get(num - 1);

				System.out.println("=== [수정 메뉴] ===");
				System.out.println("메뉴를 선택해 주세요");
				System.out.println("1. 수정");
				System.out.println("9. 돌아가기");
				int menu = sc.nextInt();

				switch (menu) {
				case 1:
					System.out.println(companyId);
					af.adminAccountFixAndSearch(companyId);
					break;

				case 9:
					System.out.println("검색 기능을 종료합니다.");
					choice = false;
					break;
				}
			}

		} catch (IOException e) {
		}
	}

}
