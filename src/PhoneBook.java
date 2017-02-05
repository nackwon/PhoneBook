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
				System.out.println("�����ϼ��� ....");
				System.out.println("1. ������ �Է�");
				System.out.println("2. ������ ����");
				System.out.println("3. ������ ����(����)");
				System.out.println("4. ��ü ������ ���");
				System.out.println("5. ���α׷� ����");
				System.out.print("����: ");
				choice = keyboard.nextInt();
				keyboard.nextLine();

				if (choice < INPUT || choice > EXIT)
					throw new MenuChoiceException(choice);

				switch (choice) {
				case INPUT:
					manager.inputData();
					break;
				case UPDATE:
					// manager.upData();���� �����
					manager.updateData();
					break;
				case SAVE:
					manager.saveToFile();
					break;
				case PRINT:
					manager.printData();
					break;
				case EXIT:
					System.out.println("���α׷��� �����մϴ�.");
					System.exit(0);
					return;
				}
			} catch (MenuChoiceException e) {
				e.showWrongChice();
				System.out.println("�޴� ������ ó������ �ٽ� �����մϴ�./n");
			}
		}
	}
}
