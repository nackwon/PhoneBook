//����ó��
public class MenuChoiceException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private int wrongChoice;
	
	public MenuChoiceException(int wrongChice){
		super("�߸��� ������ �̷������ϴ�.");
		this.wrongChoice = wrongChice;
	}
	
	public void showWrongChice(){
		System.out.println(wrongChoice+"�� �ش��ϴ� ������ �������� �ʽ��ϴ�.");
	}	
}
