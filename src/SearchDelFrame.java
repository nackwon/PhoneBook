import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class SearchDelFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JTextField srchField = new JTextField(15);
	JButton srchBtn = new JButton("SHEARCH");

	JTextField delField = new JTextField(15);
	JButton delBtn = new JButton("DEL");

	JTextArea textArea = new JTextArea(20, 25);

	public SearchDelFrame(String title) {
		super(title);
		setBounds(100, 200, 330, 450);
		setLayout(new BorderLayout());
		Border border = BorderFactory.createEtchedBorder();

		Border srchBorder = BorderFactory.createTitledBorder(border, "Search");
		JPanel srchPanel = new JPanel();
		srchPanel.setBorder(srchBorder);
		srchPanel.setLayout(new FlowLayout());
		srchPanel.add(srchField);
		srchPanel.add(srchBtn);

		Border delBorder = BorderFactory.createTitledBorder(border, "Delete");
		JPanel delPanel = new JPanel();
		delPanel.setBorder(delBorder);
		delPanel.setLayout(new FlowLayout());
		delPanel.add(delField);
		delPanel.add(delBtn);

		JScrollPane scrollTextArea = new JScrollPane(textArea);
		Border textBorder = BorderFactory.createTitledBorder(border, "Information Board");
		scrollTextArea.setBorder(textBorder);

		add(srchPanel, BorderLayout.NORTH);
		add(delPanel, BorderLayout.SOUTH);
		add(scrollTextArea, BorderLayout.CENTER);

		srchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = srchField.getText();
				PhoneBookManager manager = PhoneBookManager.createManagerInst();
				String srchResult = manager.searchData(name);

				if (srchResult == null) {
					textArea.append("�ش��ϴ� �����Ͱ� �������� �ʽ��ϴ�.\n");
				} else {
					textArea.append("ã���ô� ������ �˷��帳�ϴ�. \n");
					textArea.append(srchResult);
					textArea.append("\n");
				}
			}
		});

		delBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = delField.getText();
				PhoneBookManager manager = PhoneBookManager.createManagerInst();
				boolean isDeleted = manager.deleteData(name);

				if (isDeleted)
					textArea.append("������ ������ �Ϸ��Ͽ����ϴ�.\n");
				else
					textArea.append("�ش��ϴ� �����Ͱ� �������� �ʽ��ϴ�.\n");
			}
		});
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
