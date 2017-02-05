
public class PhoneUnivInfo extends PhoneInfo {

	private static final long serialVersionUID = 1L;
	
	private String major;
	private int year;

	public PhoneUnivInfo(String name, String phonenumber, String major, int year) {
		super(name, phonenumber);
		this.major = major;
		this.year = year;
	}
	
	public void setMajor(String major){
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setYear(int year){
		this.year = year;
	}
	
	public int getYear() {
		return year;
	}
	
	@Override
	public void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("MAJOR: " + major);
		System.out.println("YEAR: " + year);
	}
	
	@Override
	public String toString() {
		return super.toString() + "MAJOR: " + major + '\n' + "YEAR: " + year + '\n';
	}
}
