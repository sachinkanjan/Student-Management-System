import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ViewFrame extends JFrame{
	Container c;
	TextArea tvData;
	JButton btnBack;
	JPanel p1,p2;
	Font font = new Font("Times new roman", Font.PLAIN, 50);
	public ViewFrame(){
		c = getContentPane();
		c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));

		p1 = new JPanel();
		p1.setLayout(new GridLayout(1,1));
		tvData = new TextArea(4,30);
		p1.add(tvData);
		c.add(p1);

		p2 = new JPanel();
		btnBack = new JButton("Back");
		btnBack.setFont(font);
		p2.add(btnBack);
		c.add(p2);

		DbHandler db = new DbHandler();
		tvData.setText(db.viewStudent());

		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				mainframe m = new mainframe();
				dispose();
			}
		});

		setTitle("View Student");
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GREEN);
		setVisible(true);
	}
}