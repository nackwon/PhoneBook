//예외처리
public class MenuChoiceException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private int wrongChoice;
	
	public MenuChoiceException(int wrongChice){
		super("잘못된 선택이 이뤄졌습니다.");
		this.wrongChoice = wrongChice;
	}
	
	public void showWrongChice(){
		System.out.println(wrongChoice+"에 해당하는 선택은 존재하지 않습니다.");
	}	
}
