package project;

import java.util.Scanner;

public class AdminMenu {

//	// 테스트 main 코드
//	public static void main(String[] args) throws IOException {
//		adminMenu();
//	}

	public void adminMenu(){

		// 계정 검색, 수정, 생성/삭제 객체
		// 전체 근태 정보 추가 및 1번에 입력
		AdminSearch as = new AdminSearch();
		AdminAccountHandling ah = new AdminAccountHandling();
		AdminAccountFix af = new AdminAccountFix();

		boolean choice = true;

		Scanner sc = new Scanner(System.in);
		while (choice) {
			System.out.println("===== [관리자 메뉴] =====");
			System.out.println("1. 전체 근태 정보");
			System.out.println("2. 임직원 검색");
			System.out.println("3. 계정 수정");
			System.out.println("4. 계정 생성/삭제");
			System.out.println("5. 로그아웃");
			System.out.print("메뉴 번호를 입력해 주세요 :");
			String menuChoice = sc.next();

			switch (menuChoice) {
			case "1":
				// AdminAllPrintInfo
				break;

			case "2":
				// 계정 검색
				as.adminSearch();
				break;

			case "3":
				// 계정 수정
				af.adminAccountFix();
				break;

			case "4":
				// 계정 생성/삭제
				System.out.println("1. 계정 생성");
				System.out.println("2. 계정 삭제");
				System.out.println("3. 돌아가기");
				int menu = sc.nextInt();

				switch (menu) {

				case 1:
					// 계정 생성
					ah.adminAccountHandlingAdd();
					System.out.println("계정 생성이 완료되었습니다.");
					break;

				case 2:
					// 계정 삭제
					ah.adminAccountHandlingSub();
					System.out.println("계정 삭제가 완료되었습니다.");
					break;

				case 3:
					System.out.println("이전 메뉴로 되돌아갑니다.");
					break;
				}

				break;
			case "5":
				// 로그아웃
				choice = false;
				System.out.println("로그아웃 되었습니다.");
				break;
			}
		}

	}
}
