package project;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class NormalIndividualSearch {
    private String employeeId;
    private Employee employee;

    public NormalIndividualSearch(Employee employee) {
        this.employee = employee;
        this.employeeId = employee.getId();
    }

    public void normalIndividualSearch() {
        String filePath = "C:/Temp/" + employeeId + ".json";

        try {
            // JSON 파일 읽기
            FileReader fileReader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

            // JSON 객체 순회
            Iterator<?> keys = jsonObject.keySet().iterator();
            if (keys.hasNext()) {
                String date = (String) keys.next();
                JSONObject attendanceData = (JSONObject) jsonObject.get(date);

                String position = (String) attendanceData.get("직급");
                String department = (String) attendanceData.get("소속");
                String name = (String) attendanceData.get("이름");

                System.out.println("직급: " + position);
                System.out.println("부서: " + department);
                System.out.println("이름: " + name);
                System.out.println();

                System.out.println("날짜: " + date);
                System.out.println("출근시간: " + attendanceData.get("출근"));
                System.out.println("퇴근시간: " + attendanceData.get("퇴근"));
                System.out.println();
            }

            while (keys.hasNext()) {
                String date = (String) keys.next();
                JSONObject attendanceData = (JSONObject) jsonObject.get(date);

                System.out.println("날짜: " + date);
                System.out.println("출근시간: " + attendanceData.get("출근"));
                System.out.println("퇴근시간: " + attendanceData.get("퇴근"));
                System.out.println();
            }

            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}