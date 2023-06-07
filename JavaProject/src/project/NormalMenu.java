package project;

import java.util.Scanner;

public class NormalMenu {
	private String employeeId;
	private Employee employee;
	
//	public NormalMenu(Employee employee) {
//		this.employee = employee;
//	}
	public NormalMenu(String employeeId) {
		this.employee = new Employee(employeeId);
		this.employeeId = employeeId;
	}
	
	public void normalMenu() {
		Scanner sc = new Scanner(System.in);
		while(true)
			{
				System.out.println("\n---------- 현재 출퇴근 상태 ----------");
				NormalStatus ns = new NormalStatus(employee);
				ns.normalStatus();
				
				System.out.println("\n------- 임직원 메뉴 --------");
				System.out.println("1. 출근/퇴근 기록하기");
				System.out.println("2.개인 정보 조회/수정 ");
				System.out.println("3.로그아웃");
				String select = sc.nextLine();
				switch(select) {
				
				case"1":
					RushHour rh = new RushHour(employee);
					rh.rushHour();
					break;
				
				case "2":
//					NormalIndividualMenu normalIndividual = new NormalIndividualMenu(employee);
//					normalIndividual.normalIndiviualMenu();
					break;
					
				case "3":
					System.out.println("로그인 페이지로 돌아갑니다.");
					System.out.println();
					return;
				}
			
			}
		
	}

}
