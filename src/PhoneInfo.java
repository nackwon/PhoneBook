import java.io.Serializable;
//hash set은 hash code라는 것을 가진다
//hash code는 그 안에서 유일한 값을 가지는 것 
//object안에 equals메소드는 그 참조하는 것을 비교
//그러므로 지금 같은 이름을 입력해도 오류가 나지 않는다.
//Hash set은 원래 중복값이 있어선 안된다 p 635 참조

public class PhoneInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String phonenumber;

	public PhoneInfo(String name, String phonenumber) {
		this.name = name;
		this.phonenumber = phonenumber;
	}

	public void showPhoneInfo() {
		System.out.println("NAME: " + name);
		System.out.println("PHONE: " + phonenumber);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPhoneNumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getPhoneNumber() {
		return phonenumber;
	}

	public String toString() {
		return "NAME: " + name + '\n' + "PHONE: " + phonenumber + '\n';
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		PhoneInfo cmp = (PhoneInfo) obj;
		if (name.equals(cmp.getName()))
			return true;
		else
			return false;
	}
}
