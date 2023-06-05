package project;

import org.json.JSONObject;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class IdPwSearch {
//	테스트 코드
//	
//	public static void main(String[] args) throws IOException {
//		
//		System.out.println(idPwSearch("admin", "1q2w3e4r"));
//		System.out.println(idPwSearch("A1", "670104"));
//		
//	}
	
	public int idPwSearch(String id, String pw) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("C://Temp/AllEmployees.json", Charset.forName("UTF-8")));
		String json = br.readLine();
		JSONObject root = new JSONObject(json);
		
		try {
			
			JSONObject s = root.getJSONObject(id);
		
			if(id.equals("admin") && s.getString("password").equals(pw)) {
				System.out.println("관리자 계정으로 로그인 성공했습니다.");
				return 1;
			}
			else if(s.getString("password").equals(pw)) {
				System.out.println("임직원 계정으로 로그인 성공했습니다.");
				return 0;
			} else return -1;
			
		} catch(JSONException e) { return -1; }
		finally {
			br.close();
		}
		
	}
}
