package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

import org.json.JSONObject;
import org.json.JSONException;
/*
 * print 메소드
 * printToday() throws IOException
 * printStatus(String id) throws IOException
 * printLate(String id) throws IOException
 * printTodayWorkHour(String id) throws IOException
 * printThisWeekWorkHour(String id) throws IOException
 * 	-> 전부 출력 후 줄바꿈 필요
 * get 메소드
 * LocalDateTime getTodayAttendTime(String id) throws JSONException, IOException
 * LocalDateTime getTodayLeaveTime(String id) throws JSONException, IOException
 * String getStatus(String id) throws IOException
 * boolean getLate(String id) throws IOException
 * Duration getTodayWorkHour(String id) throws IOException
 * Duration getThisWeekWorkHour(String id) throws IOException
 */
public class WorkingTimeCalc {
	
	private static LocalDateTime todayDateTime;
	private static String todayDateStr;
	
	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static {
		todayDateTime = LocalDateTime.now();
//		todayDateTime = LocalDateTime.of(2023, 6, 2, 22, 0); //test
		todayDateStr = todayDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public WorkingTimeCalc() {}
	
	// 파일 읽기 ----------------------------------------------------------
	public static BufferedReader buildBufferedReader(String id) throws FileNotFoundException {
		return new BufferedReader(new FileReader("C:/Temp/" + id + ".json"));
	}
	
	public static JSONObject getCommuteData(String id) throws IOException{
		JSONObject commuteData = null;
		BufferedReader br = buildBufferedReader(id);
		commuteData = new JSONObject(br.readLine());
		return commuteData;
	}
	public static JSONObject getTodayCommuteData(String id) throws JSONException, IOException {
		JSONObject commuteData = getCommuteData(id);
		JSONObject todayCommuteData = commuteData.getJSONObject(todayDateStr);
		return todayCommuteData;
	}
	
	// 메소드 ------------------------------------------------------
	public static void printToday() {
		System.out.print(todayDateTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
	}
	
	// 출퇴근 시간 얻기
	public static LocalDateTime getTodayAttendTime(String id) throws JSONException, IOException {
		JSONObject todayCommuteData = getTodayCommuteData(id);
		LocalDateTime todayAttendTime = LocalDateTime.parse(todayCommuteData.getString("출근"), df);
		return todayAttendTime;
	}
	public static LocalDateTime getTodayLeaveTime(String id) throws JSONException, IOException {
		JSONObject todayCommuteData = getTodayCommuteData(id);
		LocalDateTime todayLeaveTime = LocalDateTime.parse(todayCommuteData.getString("퇴근"), df);
		return todayLeaveTime;
	}
	
	// 현재 상태 얻기
	public static String getStatus(String id) throws IOException {
		LocalDateTime todayAttendTime = null;
		String nowStatus = null;
		
		//출근 데이터 존재하는지 확인, 없으면 "출근 전"
		try {
			todayAttendTime = getTodayAttendTime(id);
		} catch(JSONException e) {
			nowStatus = "출근 전";
		}
		
		//출근 존재하면 퇴근 존재하는 지 확인, 퇴근 있으면 "퇴근", 출근 있고 퇴근 없으면 "근무 중"
		try {
			if(todayAttendTime != null) {
				getTodayLeaveTime(id);
				nowStatus = "퇴근";
			}
		} catch(JSONException e) {
			nowStatus = "근무 중";
		}
		return nowStatus;
	}
	
	public static void printStatus(String id) throws IOException {
		String nowStatus = getStatus(id);
		System.out.print("현재 상태 : " + nowStatus);
	}
	
	// 금일 지각 여부 얻기
	public static boolean getLate(String id) throws IOException {
		LocalDateTime todayAttendTime = null;;
		
		boolean lateBool = false;
		try {
			todayAttendTime = getTodayAttendTime(id);
			lateBool = todayAttendTime.isAfter(LocalDateTime.parse(todayDateStr + " 09:00:00", df));
		}
		catch(JSONException e) {}
		return lateBool;
	}

	public static void printLate(String id) throws IOException {
		boolean lateBool = getLate(id);
		try {
			getTodayAttendTime(id);
			
			System.out.print("지각 여부 : ");
			if(lateBool) System.out.print("O");
			else System.out.print("X");
			
		} catch(JSONException e) {}
	}
	
	// 금일 근무 시간 얻기
	public static Duration getTodayWorkHour(String id) throws IOException {
		Duration todayWorkHour = null;
		LocalDateTime todayAttendTime = null;
		LocalDateTime todayLeaveTime = null;
		
		boolean lateBool = getLate(id);
		try {
			todayAttendTime = getTodayAttendTime(id);	//json예외 -> catch
			//지각O
			if(lateBool) {
				try {
					//퇴근 후
					todayLeaveTime = getTodayLeaveTime(id);
					todayWorkHour = Duration.between(todayAttendTime, todayLeaveTime);
				} catch(JSONException e) {
					//근무 중
					todayWorkHour = Duration.between(todayAttendTime, LocalDateTime.now());
				}
			}
			//지각X
			else {
				try {
					//퇴근 후
					todayLeaveTime = getTodayLeaveTime(id);
					todayWorkHour = Duration.between(LocalDateTime.parse(todayDateStr + " 09:00:00", df), todayLeaveTime);
				} catch(JSONException e) {
					//근무 중
					todayWorkHour = Duration.between(LocalDateTime.parse(todayDateStr + " 09:00:00", df), LocalDateTime.now());
				}
			}
		} catch(NullPointerException e) {}
		catch(JSONException e) {}
		return todayWorkHour;
	}
	
	public static void printTodayWorkHour(String id) throws IOException {
		Duration todayWorkHour = getTodayWorkHour(id);
		try {
			System.out.print("금일 근무 시간 : "
								+ todayWorkHour.toHoursPart()+ "시간 "
								+ todayWorkHour.toMinutesPart() + "분 "
								+ todayWorkHour.toSecondsPart() + "초");
		} catch(NullPointerException e) {}
	}
	
	// 금주 첫날(월) 0시0분0초 날짜시간 얻기
	public static LocalDateTime getWeekStart() {
		Calendar weekStartCal = Calendar.getInstance();
 		weekStartCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
 		weekStartCal.set(Calendar.HOUR_OF_DAY, 0);
 		weekStartCal.set(Calendar.MINUTE, 0);
 		weekStartCal.set(Calendar.SECOND, 0);
 		String weekStartStr = sdf.format(weekStartCal.getTime());
 		return LocalDateTime.parse(weekStartStr, df);
	}
	
	// 금주 마지막날(금) 토요일 0시0분0초 날짜시간 얻기
	public static LocalDateTime getWeekEnd() {
		Calendar weekEndCal = Calendar.getInstance();
 		weekEndCal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
 		weekEndCal.set(Calendar.HOUR_OF_DAY, 24);
 		weekEndCal.set(Calendar.MINUTE, 0);
 		weekEndCal.set(Calendar.SECOND, 0);
 		String weekEndStr = sdf.format(weekEndCal.getTime());
 		return LocalDateTime.parse(weekEndStr, df);
	}
	
	// 금주 근무 시간 얻기
	public static Duration getThisWeekWorkHour(String id) throws IOException {
		LocalDateTime weekStart = getWeekStart();
		LocalDateTime weekEnd = getWeekEnd();
		
		JSONObject commuteData = getCommuteData(id);
		
		// 날짜 배열 entry 변수 오늘 기준 생성
		Calendar dateCal = Calendar.getInstance();
		dateCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String dateStr = sdf.format(dateCal.getTime());
		LocalDateTime date = LocalDateTime.parse(dateStr, df);
		
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
				JSONObject attendanceData = commuteData.getJSONObject(dateEntry);
				
				attendTimeStr = attendanceData.getString("출근");
				LocalDateTime attendTime = LocalDateTime.parse(attendTimeStr, df);
				try {
					leaveTimeStr = attendanceData.getString("퇴근");
					LocalDateTime leaveTime = LocalDateTime.parse(leaveTimeStr, df);
					
					thisWeekWorkHour = thisWeekWorkHour.plus(Duration.between(attendTime, leaveTime));
				} catch(JSONException e) {
					thisWeekWorkHour = thisWeekWorkHour.plus(Duration.between(attendTime, todayDateTime));
				}
				
			} catch(JSONException e) { break; }
			
		}
		
		return thisWeekWorkHour;
	}
	
	public static void printThisWeekWorkHour(String id) throws IOException {
		Duration thisWeekWorkHour = getThisWeekWorkHour(id);
		
		System.out.print("금주 근무 시간 : "
							+ thisWeekWorkHour.toHours()+ "시간 "
							+ thisWeekWorkHour.toMinutesPart() + "분 "
							+ thisWeekWorkHour.toSecondsPart() + "초");
	}
	
}