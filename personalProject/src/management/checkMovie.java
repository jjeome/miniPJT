package management;

public class checkMovie extends Management{ 
	public checkMovie() {
		int menuNo = 0;
		while(true) {
			printMenu();
			
			menuNo = menuSelect();
			if(menuNo==1) {
				//영화 조회
				new SerchMovie();
			} else if(menuNo==2) {
				//영화 카테고리 검색
				new SerchCategory();
			} else if(menuNo==9) {
				//뒤로가기
				back();
				break;
			} else {
				//오류메세지
				showInputError();
			}
		}
	}
	
	
	private void back() {
		System.out.println("메인으로 돌아갑니다.");
	}

	private void printMenu() {
		System.out.println("=================================");
		System.out.println(" 1.영화 조회 | 2.영화 검색 | 3.뒤로 가기 ");
		System.out.println("=================================");
	}

	
}
