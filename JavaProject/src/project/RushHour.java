package project;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class RushHour {
	private Employee employee;

	public RushHour(Employee employee) {
		this.employee = employee;
	}

	public RushHour(String employeeId) {
		this.employee = new Employee(employeeId);
	}

	public JSONObject rushHour(String action) {
		JSONObject result = new JSONObject();

		switch (action) {
		case "1":
			String attendanceDateTime = getCurrentDateTime();
			recordAttendance(attendanceDateTime);
			// result.put("status", "success");
			// result.put("message", "출근 기록이 성공적으로 저장되었습니다.");
			break;

		case "2":
			String leavingDateTime = getCurrentDateTime();
			recordLeaving(leavingDateTime);
			// result.put("status", "success");
			// result.put("message", "퇴근 기록이 성공적으로 저장되었습니다.");
			break;

		default:
			// result.put("status", "failure");
			// result.put("message", "유효하지 않은 액션입니다.");
			break;
		}

		return result;
	}

	private void recordAttendance(String attendanceDateTime) {
		Map<String, Object> attendanceRecord = new HashMap<>();
		String employeeId = employee.getId();
		String employeeName = employee.getName();
		String employeePosition = employee.getPosition();
		String employeeDepartment = employee.getDepartment();

		attendanceRecord.put("이름", employeeName);
		attendanceRecord.put("직급", employeePosition);
		attendanceRecord.put("소속", employeeDepartment);
		attendanceRecord.put("출근", attendanceDateTime);

		String fileName = employeeId + ".json";
		writeToFile(fileName, new JSONObject(attendanceRecord).toString());
	}

	private void recordLeaving(String leavingDateTime) {
		Map<String, Object> leavingRecord = new HashMap<>();
		String employeeId = employee.getId();
		String employeeName = employee.getName();
		String employeePosition = employee.getPosition();
		String employeeDepartment = employee.getDepartment();

		leavingRecord.put("이름", employeeName);
		leavingRecord.put("직급", employeePosition);
		leavingRecord.put("소속", employeeDepartment);
		leavingRecord.put("퇴근", leavingDateTime);

		String fileName = employeeId + ".json";
		writeToFile(fileName, new JSONObject(leavingRecord).toString());
	}

	private void writeToFile(String fileName, String data) {
		try (FileWriter fileWriter = new FileWriter(fileName)) {
			fileWriter.write(data);
			fileWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}
}