package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import org.json.JSONObject;

public class AdminAllPrintInfo {

//	// 테스트 main 코드
//	public static void main(String[] args) {
//		adminAllPrintInfo();
//	}

	public void adminAllPrintInfo() {

		try {
			BufferedReader br = new BufferedReader(
					new FileReader("C://Temp/AllEmployees.json", Charset.forName("UTF-8")));
			String json = br.readLine();
			br.close();
			JSONObject root = new JSONObject(json);

			int number = 0;

			for (String s : root.keySet()) {
				//s = 키값 = id
				if (!s.equals("admin")) {
					JSONObject jp = new JSONObject(root.getJSONObject(s).toString());

					System.out.println((number + 1) + "사번 : " + s + "|이름 : " + jp.get("이름") + "|직급 : " + jp.get("직급")
							+ "|소속 : " + jp.get("소속") + "|현재 상태 : " + " " + "|지각현황? : " + " ");
					number++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
