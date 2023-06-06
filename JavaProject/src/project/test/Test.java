package project.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner; 

import org.json.JSONObject;

public class Test {

	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader("C://Temp/AllEmployees.json"));
		JSONObject root = new JSONObject(br.readLine());
		br.close();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("id 입력 > ");
		String scanId = sc.nextLine();
		
		JSONObject data = root.getJSONObject(scanId);
		System.out.println(data.toString());
		Employee employee = new Employee(scanId, data);
		
		exc exc = new exc(employee, root, data);
		exc.normalAccountFix();
		
	}

}
