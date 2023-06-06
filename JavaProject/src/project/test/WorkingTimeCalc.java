package project.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

import org.json.JSONObject;
import org.json.JSONException;

public class WorkingTimeCalc {
	
	JSONObject commuteData;
	BufferedReader br;
	BufferedWriter bw;
	LocalDateTime todayDateTime;
	
	DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public WorkingTimeCalc() {}
	public WorkingTimeCalc(String id) throws IOException {
		todayDateTime = LocalDateTime.now();
		
		br = new BufferedReader(new FileReader("C:/Temp/" + id + ".json"));
		commuteData = new JSONObject(br.readLine());
		br.close();
		bw = new BufferedWriter(new FileWriter("C:/Temp/test" + id + ".json"));
	}
	
	
	public void workingTimeCalc() {
		
		System.out.println();
		
		// 오늘 날짜 yyyy-MM-dd
		String todayDateStr = todayDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		System.out.println(todayDateTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
		
		// 오늘 근태 정보
		JSONObject todayCommuteData = commuteData.getJSONObject(todayDateStr);
		
		// 오늘 출퇴근 시간, 현재 상태
		LocalDateTime todayAttendTime = null;
		LocalDateTime todayLeaveTime = null;
		String nowStatus = null;
		
		try {
			todayAttendTime = LocalDateTime.parse(todayCommuteData.getString("출근"), df);
//			String todayAttendTimeStr = todayAttendTime.format(df);	//test
//			System.out.println(todayAttendTimeStr);	//test
		} catch(JSONException e) {
			nowStatus = "출근 전";
		}
		
		try {
			if(todayAttendTime != null) {
				todayLeaveTime = LocalDateTime.parse(todayCommuteData.getString("퇴근"), df);
//				String todayLeaveTimeStr = todayLeaveTime.format(df);	//test
//				System.out.println(todayLeaveTimeStr);	//test
				nowStatus = "퇴근";
			}
		} catch(JSONException e) {
			nowStatus = "근무 중";
		}
		
		// 현재 상태 ------------------------------------------------------------------------------------------------------------------------------------
		System.out.println("현재 상태 : " + nowStatus);
		
		// 지각 여부 ------------------------------------------------------------------------------------------------------------------------------------
		try {
			
			boolean lateBool = todayAttendTime.isAfter(LocalDateTime.parse(todayDateStr + " 09:00:00", df));
			
			System.out.print("지각 여부 : ");
			if(lateBool) System.out.println("O");
			else System.out.println("X");
			
		} catch(NullPointerException e) { }
		
		// 오늘 근무 시간 --------------------------------------------------------------------------------------------------------------------------------
		try {
			Duration todayWorkHour = Duration.between(todayAttendTime, todayLeaveTime);
		
			System.out.println("금일 근무 시간 : "
								+ todayWorkHour.toHoursPart()+ "시간 "
								+ todayWorkHour.toMinutesPart() + "분 "
								+ todayWorkHour.toSecondsPart() + "초");
		} catch(NullPointerException e) {}
		
		// 금주 근무 시간 -------------------------------------------------------------------------------------------------------------------------------
		
		// 금주 첫날(월)
		Calendar weekStartCal = Calendar.getInstance();
 		weekStartCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
 		weekStartCal.set(Calendar.HOUR_OF_DAY, 0);
 		weekStartCal.set(Calendar.MINUTE, 0);
 		weekStartCal.set(Calendar.SECOND, 0);
 		String weekStartStr = sdf.format(weekStartCal.getTime());
 		LocalDateTime weekStart = LocalDateTime.parse(weekStartStr, df);
// 		System.out.println(weekStart.toString()); //test
 		// 2023-06-05 00:00:00
 		
 		// 금주 마지막날(금)
 		Calendar weekEndCal = Calendar.getInstance();
 		weekEndCal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
 		weekEndCal.set(Calendar.HOUR_OF_DAY, 24);
 		weekEndCal.set(Calendar.MINUTE, 0);
 		weekEndCal.set(Calendar.SECOND, 0);
 		String weekEndStr = sdf.format(weekEndCal.getTime());
 		LocalDateTime weekEnd = LocalDateTime.parse(weekEndStr, df);
// 		System.out.println(weekEnd.toString()); //test
 		//2023-06-10 00:00:00
		
 		// 날짜 배열 entry 변수 생성
 		Calendar dateCal = Calendar.getInstance();
		dateCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String dateStr = sdf.format(dateCal.getTime());
	 	LocalDateTime date = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	 	
	 	//test
//	 	LocalDateTime weekStart = LocalDateTime.of(2023, 5, 29, 0, 0);
//	 	LocalDateTime weekEnd = LocalDateTime.of(2023, 6, 3, 0, 0);
//	 	LocalDateTime date = LocalDateTime.of(2023, 5, 29, 20, 0);
	 	
	 	// 금주 날짜 배열 생성 월~금
 		String[] weekDate = new String[5];
 		
	 	// 금주 날짜 배열 대입
	 	int idx=0;
 		while(date.isAfter(weekStart) && date.isBefore(weekEnd)) {
 			weekDate[idx] = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
 			date = date.plusDays(1);
 	 		idx++;
 		}
 		
 		// 금주 근무시간 계산
 		Duration thisWeekWorkHour = Duration.ofSeconds(0);
 		for(String dateEntry : weekDate) {
 			String attendTimeStr = null;
 			String leaveTimeStr = null;
 			try {
 				JSONObject dateData = commuteData.getJSONObject(dateEntry);
 				
 				attendTimeStr = dateData.getString("출근");
 				LocalDateTime attendTime = LocalDateTime.parse(attendTimeStr, df);
 				
 				leaveTimeStr = dateData.getString("퇴근");
 				LocalDateTime leaveTime = LocalDateTime.parse(leaveTimeStr, df);
 				
 				thisWeekWorkHour = thisWeekWorkHour.plus(Duration.between(attendTime, leaveTime));
 				
 			} catch(JSONException e) {
 				break;
 			}
 		}
 		System.out.println("금주 근무 시간 : "
				+ thisWeekWorkHour.toHours()+ "시간 "
				+ thisWeekWorkHour.toMinutesPart() + "분 "
				+ thisWeekWorkHour.toSecondsPart() + "초");
 		
	}
	
}
