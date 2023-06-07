package project;

import java.util.Scanner;
import java.io.IOException;

public class LogIn {
	
	private Scanner sc = new Scanner(System.in);
	
	public void logIn() {
			
		while(true) {
			System.out.println("\n========== 로그인 ==========");
			System.out.println("\nID를 입력하세요.");
			System.out.print("종료하려면 '9'를 입력하세요 > ");
			String id = sc.nextLine();
			
			if(id.equals("9")) {
				System.out.println("프로그램을 종료합니다.");
				return;
			}
			
			System.out.print("비밀번호를 입력하세요 > ");
			String password = sc.nextLine();
			
			try {
			IdPwSearch idPwSearch = new IdPwSearch();
			int searchResult = idPwSearch.idPwSearch(id, password);
			
			switch(searchResult) {
				case 0 : {
					NormalMenu normalMenu = new NormalMenu(id);
					normalMenu.normalMenu();
					break;
				}
//				case 1 : {
//					AdminMenu adminMenu = new AdminMenu();
//					adminMenu.adminMenu();
//					break;
//				}
				case -1 : {
					System.out.println("\nID나 비밀번호가 잘못되었습니다. 다시 입력하세요.");
					break;
				}

			}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
}
