import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookManager {

	public static final int NORMAL = 1;
	public static final int UNIV = 2;
	public static final int COMPANY = 3;
	public static Scanner keyboard = new Scanner(System.in);

	private final File dataFile = new File("c:\\tmp\\PhoneBook.txt");
	// �ش� ������ ������ �����߻�
	Set<PhoneInfo> infoStorage = new HashSet<PhoneInfo>();

	static PhoneBookManager inst = null;

	public static PhoneBookManager createManagerInst() {
		if (inst == null)
			inst = new PhoneBookManager();
		return inst;
	}

	private PhoneBookManager() {
		readFromFile();
		System.out.println("���Ϸκ��� �����͸� �о�Խ��ϴ�. \n");
	}

	// �Ϲ� ģ�� �Է�
	private PhoneInfo readFriendInfo(String name) {
		System.out.print("��ȭ��ȣ: ");
		String phone = keyboard.nextLine();
		return new PhoneInfo(name, phone);
	}

	// ����ģ�� �Է�
	private PhoneInfo readUnivFriendInfo(String name) {
		System.out.print("��ȭ��ȣ: ");
		String phone = keyboard.nextLine();
		System.out.print("����: ");
		String major = keyboard.nextLine();
		System.out.print("�г�: ");
		int year = keyboard.nextInt();
		keyboard.nextLine();
		return new PhoneUnivInfo(name, phone, major, year);
	}

	// ȸ��ģ�� �Է�
	private PhoneInfo readCompanyFriendInfo(String name) {
		System.out.print("��ȭ��ȣ: ");
		String phone = keyboard.nextLine();
		System.out.print("ȸ��: ");
		String company = keyboard.nextLine();
		return new PhoneCompanyInfo(name, phone, company);
	}

	// ������ �Է�
	public void inputData() throws MenuChoiceException {
		System.out.println("������ �Է��� �����մϴ�..");
		System.out.println("1. �Ϲ�, 2. ����, 3. ȸ��");
		System.out.print("����>> ");
		int choice = keyboard.nextInt();
		keyboard.nextLine();
		PhoneInfo info = null;

		if (choice < NORMAL || choice > COMPANY)
			throw new MenuChoiceException(choice);

		System.out.print("�̸�: ");
		String name = keyboard.nextLine();
		/*
		 * Iterator<PhoneInfo> itr = infoStorage.iterator();
		 * while(itr.hasNext()){ if(name.equals(itr.next().getName())){
		 * System.out.println("�ߺ��Ǵ� ����� �ֽ��ϴ�.\n �ٽ� �Է����ּ���.\n"); return; } }
		 */
		switch (choice) {
		case NORMAL:
			info = readFriendInfo(name);
			break;
		case UNIV:
			info = readUnivFriendInfo(name);
			break;
		case COMPANY:
			info = readCompanyFriendInfo(name);
			break;
		}
		boolean isAdded = infoStorage.add(info);
		if (isAdded == true)
			System.out.println("������ �Է��� �Ϸ�Ǿ����ϴ�.\n");
		else
			System.out.println("�̹� ����� �������Դϴ�. \n");

		System.out.println("��ϵ� �ο� �� : " + infoStorage.size());
	}

	// ������ �˻�
	public String searchData(String name) {
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while (itr.hasNext()) {
			PhoneInfo curInfo = itr.next();
			if (name.equals(curInfo.getName()))
				return curInfo.toString();
		}
		return null;
	}

	// ������ ����
	public boolean deleteData(String name) {
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while (itr.hasNext()) {
			PhoneInfo curInfo = itr.next();
			if (name.equals(curInfo.getName())) {
				itr.remove();
				return true;
			}
		}
		return false;
	}
	// ���� Ŭ������ �׻� ���� �ؿ� ���д�
	// �ڽĺ��� if�� ���� �θ�� ���� �ؿ� ���´�

	public void updateData() {
		System.out.println("������ ��� �̸��� �Է��ϼ���");
		System.out.print("�Է� >> ");
		String name = keyboard.nextLine();
		PhoneInfo info = null;

		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while (itr.hasNext()) {
			// if (searchData(name) == null) {
			// System.out.println("������ ����� �����ϴ�.");
			// System.out.println("�ٽ� �Է����ּ���\n");
			// return;
			// }
			info = itr.next();
			if (name.equals(info.getName())) {
				itr.remove();
				// System.out.println("�̸�: ");
				// name = keyboard.nextLine();
				System.out.print("��ȭ��ȣ: ");
				String phonenumber = keyboard.nextLine();

				if (info instanceof PhoneUnivInfo) {
					System.out.print("����: ");
					String major = keyboard.nextLine();
					System.out.print("�г�: ");
					int year = keyboard.nextInt();
					keyboard.nextLine();
					info = new PhoneUnivInfo(name, phonenumber, major, year);
					infoStorage.add(info);
					System.out.println("������ �Ϸ�Ǿ����ϴ�. \n");
				} else if (info instanceof PhoneCompanyInfo) {
					System.out.print("ȸ��: ");
					String company = keyboard.nextLine();
					info = new PhoneCompanyInfo(name, phonenumber, company);
					infoStorage.add(info);
					System.out.println("������ �Ϸ�Ǿ����ϴ�. \n");
				} else if (info instanceof PhoneInfo) {
					info = new PhoneInfo(name, phonenumber);
					infoStorage.add(info);
					System.out.println("������ �Ϸ�Ǿ����ϴ�. \n");
				}
				break;
			}
		}
		// infoStorage.add(info);
	}

	// ���� § �ڵ�
	// public void upData() throws MenuChoiceException {
	// System.out.println("������ ��� �̸��� �Է��ϼ���.");
	// System.out.print("�Է�>>");
	// String name = keyboard.nextLine();
	// if (searchData(name) != null) {
	// deleteData(name);
	// inputData();
	// } else{
	// System.out.println("�׷� ��� �����ϴ�. �ٽ� �Է����ּ���\n");
	// }
	// }

	// HashSet�� �ִ� �����͸� ���Ϸ� ����
	public void saveToFile() {
		try {
			FileOutputStream file = new FileOutputStream(dataFile);
			ObjectOutputStream out = new ObjectOutputStream(file);

			Iterator<PhoneInfo> itr = infoStorage.iterator();
			while (itr.hasNext())
				out.writeObject(itr.next());

			System.out.println("�����Ͱ� ����Ǿ����ϴ�.");
			out.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ������ ���
	public void printData() {
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while (itr.hasNext()) {
			PhoneInfo curInfo = itr.next();
			System.out.println(curInfo);
		}
	}

	// ���Ͽ� �ִ� �����͸� HashSet���� �ҷ���
	public void readFromFile() {
		if (dataFile.exists() == false)
			return;
		try {
			FileInputStream file = new FileInputStream(dataFile);
			ObjectInputStream in = new ObjectInputStream(file);

			while (true) {
				PhoneInfo info = (PhoneInfo) in.readObject();
				if (info == null)
					break;
				infoStorage.add(info);
			}
			in.close();
			file.close();
		} catch (IOException e) {
			return;
		} catch (ClassCastException e) {
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
