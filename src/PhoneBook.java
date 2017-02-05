import java.util.Scanner;

public class PhoneBook {

	public final static int INPUT = 1;
	public final static int UPDATE = 2;
	public final static int SAVE = 3;
	public final static int PRINT = 4;
	public final static int EXIT = 5;
	public static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		PhoneBookManager manager = PhoneBookManager.createManagerInst();
		SearchDelFrame winFrame = new SearchDelFrame("PhoneBook");

		int choice;

		while (true) {
			try {
				System.out.println("선택하세요 ....");
				System.out.println("1. 데이터 입력");
				System.out.println("2. 데이터 수정");
				System.out.println("3. 데이터 저장(파일)");
				System.out.println("4. 전체 데이터 출력");
				System.out.println("5. 프로그램 종료");
				System.out.print("선택: ");
				choice = keyboard.nextInt();
				keyboard.nextLine();

				if (choice < INPUT || choice > EXIT)
					throw new MenuChoiceException(choice);

				switch (choice) {
				case INPUT:
					manager.inputData();
					break;
				case UPDATE:
					// manager.upData();내가 만든것
					manager.updateData();
					break;
				case SAVE:
					manager.saveToFile();
					break;
				case PRINT:
					manager.printData();
					break;
				case EXIT:
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
					return;
				}
			} catch (MenuChoiceException e) {
				e.showWrongChice();
				System.out.println("메뉴 선택을 처음부터 다시 진행합니다./n");
			}
		}
	}
}
