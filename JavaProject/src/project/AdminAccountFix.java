package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;


public class AdminAccountFix {
//   테스트 main
//	public static void main(String[] args){
//		
////		menuChange("B2", "직급");
//		adminAccountFix();
//	}

	public void adminAccountFix() {
		Scanner sc = new Scanner(System.in);
		
		// 잘못 입력했을때 항목은 추후 추가
		System.out.print("수정할 계정의 사번을 입력해 주세요 : ");
		String companyId = sc.next();
		System.out.println("1. 이름");
		System.out.println("2. password");
		System.out.println("3. birthdate");
		System.out.println("4. 직급");
		System.out.println("5. 소속");
		System.out.println("6. 입사일");
		System.out.println("7. 전화번호");
		
		// 한글로 입력해서 넘기는 코드
		System.out.print("어떤 항목을 수정하실지 입력해주세요 : ");
		String change = sc.next();
		System.out.println("변경사항을 입력해 주세요 : ");
		String a = sc.next();
		menuChange(companyId, change, a);
		
//		번호 입력 코드
//		System.out.print("어떤 항목을 수정하실 건가요? : ");
//		int what = sc.nextInt();
		
//		switch(what) {
//		case 1:
//			menuChange(companyId, "이름");
//			break;
//		case 2:
//			menuChange(companyId, "password");
//			break;
//		case 3:
//			menuChange(companyId, "birthdate");
//			break;
//		case 4:
//			menuChange(companyId, "직급");
//			break;
//		case 5:
//			menuChange(companyId, "소속");
//			break;
//		case 6:
//			menuChange(companyId, "입사일");
//			break;
//		case 7:
//			menuChange(companyId, "전화번호");
//			break;
		
			
		}
	
	
	public void menuChange(String companyId, String change, String a) {

		try {
			BufferedReader br = new BufferedReader(new FileReader("C://Temp/AllEmployees.json", Charset.forName("UTF-8")));
			String json = br.readLine();
			br.close();
			JSONObject root = new JSONObject(json);
			ArrayList<String> list = new ArrayList<>();	//

			JSONObject jp = new JSONObject(root.getJSONObject(companyId).toString());
			JSONObject ja = new JSONObject();
			root.remove(companyId);

			
			for(String s : jp.keySet()) {
				if(s.equals(change)) {
					ja.put(s, a);
				}
				else {
					ja.put(s, jp.get(s));
				}
			}
			root.put(companyId, ja);

			Writer writer = new FileWriter("C://Temp/AllEmployees.json", Charset.forName("UTF-8"));
			writer.write(root.toString());
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
