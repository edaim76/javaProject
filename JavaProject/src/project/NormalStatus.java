package project;

import java.io.IOException;

public class NormalStatus {
	
	private String employeeId;
	private Employee employee;
	
	public NormalStatus(Employee employee) {
		this.employee = employee;
		this.employeeId = employee.getId();
	}
	
	public void normalStatus() {
		
		try {
			WorkingTimeCalc.printToday();
			System.out.println();
			WorkingTimeCalc.printStatus(employeeId);
			System.out.println();
			WorkingTimeCalc.printLate(employeeId);
			System.out.println();
			WorkingTimeCalc.printTodayWorkHour(employeeId);
			System.out.println();
			WorkingTimeCalc.printThisWeekWorkHour(employeeId);
			System.out.println();
		}
		catch(IOException e) {}
		
	}
	
}
