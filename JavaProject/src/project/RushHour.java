package project;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RushHour {
	private Employee employee;

	public RushHour(Employee employee) {
		this.employee = employee;
	}

	public RushHour(String employeeId) {
		this.employee = new Employee(employeeId);
	}

	// rushHour 메소드 출퇴근 기록 메소드
	public void rushHour() {
		Scanner scanner = new Scanner(System.in);
		String option;
		while (true) {
			System.out.println("1. 출근 등록하기");
			System.out.println("2. 퇴근 등록하기");
			System.out.println("0. 종료하기");

			System.out.print("선택: ");
			option = scanner.nextLine();

			switch (option) {
			case "1":
				System.out.println("출근을 체크합니다.");
				String attendanceDateTime = getCurrentDateTime();
				recordAttendance(attendanceDateTime);
				break;

			case "2":
				System.out.println("퇴근을 체크합니다.");
				String leavingDateTime = getCurrentDateTime();
				recordLeaving(leavingDateTime);
				break;

			case "0":
				System.out.println("프로그램을 종료합니다.");
				scanner.close();
				return;

			default:
				System.out.println("올바른 선택지를 입력하세요.");
				break;
			}
		}
	}

	// 출근기록 메소드
	private void recordAttendance(String attendanceDateTime) {
	    JSONObject attendanceRecord = new JSONObject();
	    String employeeId = employee.getId();
	    String employeeName = employee.getName();
	    String employeePosition = employee.getPosition();
	    String employeeDepartment = employee.getDepartment();

	    String date = attendanceDateTime.split(" ")[0]; // 출근일자 추출

	    attendanceRecord.put("이름", employeeName);
	    attendanceRecord.put("직급", employeePosition);
	    attendanceRecord.put("소속", employeeDepartment);
	    attendanceRecord.put("출근", attendanceDateTime);

	    String fileName = employeeId + ".json";
	    String filePath = "C:/Temp/" + fileName;

	    JSONObject existingData = readFromFile(filePath); // 기존 데이터 읽기

	    // 해당 출근일자에 기록이 있는지 확인
	    JSONObject attendanceData = (JSONObject) existingData.get(date);
	    if (attendanceData == null) {
	        // 출근 기록이 없는 경우 새로운 JSON 객체에 출근 기록 저장
	        JSONObject newData = new JSONObject();
	        newData.put("출근", attendanceRecord);
	        existingData.put(date, newData);
	    } else {
	        // 출근 기록이 있는 경우 해당 기록 수정
	        attendanceData.put("출근", attendanceDateTime);
	    }

	    writeToFile(filePath, existingData); // 수정된 데이터 파일에 저장
	}

	// 퇴근기록 메소드
	private void recordLeaving(String leavingDateTime) {
	    JSONObject leavingRecord = new JSONObject();
	    String employeeId = employee.getId();
	    String employeeName = employee.getName();
	    String employeePosition = employee.getPosition();
	    String employeeDepartment = employee.getDepartment();

	    String date = leavingDateTime.split(" ")[0]; // 퇴근일자 데이터 추출

	    leavingRecord.put("이름", employeeName);
	    leavingRecord.put("직급", employeePosition);
	    leavingRecord.put("소속", employeeDepartment);
	    leavingRecord.put("퇴근", leavingDateTime);

	    String fileName = employeeId + ".json";
	    String filePath = "C:/Temp/" + fileName;

	    JSONObject existingData = readFromFile(filePath); // 기존의 존재하는 데이터를 읽기

	    // 해당 퇴근일자에 기록이 있는지 확인
	    JSONObject attendanceData = (JSONObject) existingData.get(date);
	    if (attendanceData == null) {
	        System.out.println("출근 기록이 없습니다. 먼저 출근을 등록하세요.");
	        return;
	    }

	    // 퇴근 기록 추가
	    attendanceData.put("퇴근", leavingDateTime);

	    writeToFile(filePath, existingData); // 수정된 데이터 파일에 저장
	    System.out.println("퇴근이 등록되었습니다.");
	}
	// 현재 날짜 및 시간을 반환하는 메소드
	private String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}

	// 사번.JSON 파일 읽기 메소드
	private JSONObject readFromFile(String fileName) {
		try (FileReader fileReader = new FileReader(fileName)) {
			JSONParser parser = new JSONParser();
			return (JSONObject) parser.parse(fileReader);
		} catch (Exception e) {
			return new JSONObject();
		}
	}

	// 사번.JSON 파일 쓰기 메소드
	private void writeToFile(String fileName, JSONObject data) {
		try (FileWriter fileWriter = new FileWriter(fileName)) {
			fileWriter.write(data.toJSONString());
			fileWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Employee employee = new Employee("C1");
		RushHour rushHour = new RushHour(employee);
		rushHour.rushHour();
	}
}