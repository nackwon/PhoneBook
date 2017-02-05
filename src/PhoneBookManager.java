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
	// 해당 폴더가 없으면 에러발생
	Set<PhoneInfo> infoStorage = new HashSet<PhoneInfo>();

	static PhoneBookManager inst = null;

	public static PhoneBookManager createManagerInst() {
		if (inst == null)
			inst = new PhoneBookManager();
		return inst;
	}

	private PhoneBookManager() {
		readFromFile();
		System.out.println("파일로부터 데이터를 읽어왔습니다. \n");
	}

	// 일반 친구 입력
	private PhoneInfo readFriendInfo(String name) {
		System.out.print("전화번호: ");
		String phone = keyboard.nextLine();
		return new PhoneInfo(name, phone);
	}

	// 대학친구 입력
	private PhoneInfo readUnivFriendInfo(String name) {
		System.out.print("전화번호: ");
		String phone = keyboard.nextLine();
		System.out.print("전공: ");
		String major = keyboard.nextLine();
		System.out.print("학년: ");
		int year = keyboard.nextInt();
		keyboard.nextLine();
		return new PhoneUnivInfo(name, phone, major, year);
	}

	// 회사친구 입력
	private PhoneInfo readCompanyFriendInfo(String name) {
		System.out.print("전화번호: ");
		String phone = keyboard.nextLine();
		System.out.print("회사: ");
		String company = keyboard.nextLine();
		return new PhoneCompanyInfo(name, phone, company);
	}

	// 데이터 입력
	public void inputData() throws MenuChoiceException {
		System.out.println("데이터 입력을 시작합니다..");
		System.out.println("1. 일반, 2. 대학, 3. 회사");
		System.out.print("선택>> ");
		int choice = keyboard.nextInt();
		keyboard.nextLine();
		PhoneInfo info = null;

		if (choice < NORMAL || choice > COMPANY)
			throw new MenuChoiceException(choice);

		System.out.print("이름: ");
		String name = keyboard.nextLine();
		/*
		 * Iterator<PhoneInfo> itr = infoStorage.iterator();
		 * while(itr.hasNext()){ if(name.equals(itr.next().getName())){
		 * System.out.println("중복되는 사람이 있습니다.\n 다시 입력해주세요.\n"); return; } }
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
			System.out.println("데이터 입력이 완료되었습니다.\n");
		else
			System.out.println("이미 저장된 데이터입니다. \n");

		System.out.println("등록된 인원 수 : " + infoStorage.size());
	}

	// 데이터 검색
	public String searchData(String name) {
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while (itr.hasNext()) {
			PhoneInfo curInfo = itr.next();
			if (name.equals(curInfo.getName()))
				return curInfo.toString();
		}
		return null;
	}

	// 데이터 삭제
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
	// 상위 클래스는 항상 제일 밑에 놔둔다
	// 자식부터 if문 쓰고 부모는 제일 밑에 적는다

	public void updateData() {
		System.out.println("수정할 사람 이름을 입력하세요");
		System.out.print("입력 >> ");
		String name = keyboard.nextLine();
		PhoneInfo info = null;

		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while (itr.hasNext()) {
			// if (searchData(name) == null) {
			// System.out.println("수정할 사람이 없습니다.");
			// System.out.println("다시 입력해주세요\n");
			// return;
			// }
			info = itr.next();
			if (name.equals(info.getName())) {
				itr.remove();
				// System.out.println("이름: ");
				// name = keyboard.nextLine();
				System.out.print("전화번호: ");
				String phonenumber = keyboard.nextLine();

				if (info instanceof PhoneUnivInfo) {
					System.out.print("전공: ");
					String major = keyboard.nextLine();
					System.out.print("학년: ");
					int year = keyboard.nextInt();
					keyboard.nextLine();
					info = new PhoneUnivInfo(name, phonenumber, major, year);
					infoStorage.add(info);
					System.out.println("수정이 완료되었습니다. \n");
				} else if (info instanceof PhoneCompanyInfo) {
					System.out.print("회사: ");
					String company = keyboard.nextLine();
					info = new PhoneCompanyInfo(name, phonenumber, company);
					infoStorage.add(info);
					System.out.println("수정이 완료되었습니다. \n");
				} else if (info instanceof PhoneInfo) {
					info = new PhoneInfo(name, phonenumber);
					infoStorage.add(info);
					System.out.println("수정이 완료되었습니다. \n");
				}
				break;
			}
		}
		// infoStorage.add(info);
	}

	// 내가 짠 코딩
	// public void upData() throws MenuChoiceException {
	// System.out.println("수정할 사람 이름을 입력하세요.");
	// System.out.print("입력>>");
	// String name = keyboard.nextLine();
	// if (searchData(name) != null) {
	// deleteData(name);
	// inputData();
	// } else{
	// System.out.println("그런 사람 없습니다. 다시 입력해주세요\n");
	// }
	// }

	// HashSet에 있는 데이터를 파일로 저장
	public void saveToFile() {
		try {
			FileOutputStream file = new FileOutputStream(dataFile);
			ObjectOutputStream out = new ObjectOutputStream(file);

			Iterator<PhoneInfo> itr = infoStorage.iterator();
			while (itr.hasNext())
				out.writeObject(itr.next());

			System.out.println("데이터가 저장되었습니다.");
			out.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 데이터 출력
	public void printData() {
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while (itr.hasNext()) {
			PhoneInfo curInfo = itr.next();
			System.out.println(curInfo);
		}
	}

	// 파일에 있는 데이터를 HashSet으로 불러옴
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
