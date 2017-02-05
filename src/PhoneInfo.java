import java.io.Serializable;
//hash set�� hash code��� ���� ������
//hash code�� �� �ȿ��� ������ ���� ������ �� 
//object�ȿ� equals�޼ҵ�� �� �����ϴ� ���� ��
//�׷��Ƿ� ���� ���� �̸��� �Է��ص� ������ ���� �ʴ´�.
//Hash set�� ���� �ߺ����� �־ �ȵȴ� p 635 ����

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
