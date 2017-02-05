
public class PhoneCompanyInfo extends PhoneInfo {

	private static final long serialVersionUID = 1L;
	private String company;

	public PhoneCompanyInfo(String name, String phonenumber, String company) {
		super(name, phonenumber);
		this.company = company;
	}
	public void setCompany(String company){
		this.company =company;
	}
	
	public String getCompany(){
		return company;
	}
	
	@Override
	public void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("COMPANY: " + company);
	}
	
	@Override
	public String toString() {
		return super.toString() + "COMPANY: " + company + '\n';
	}
}
