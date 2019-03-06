import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.*;

class UpdateFrame extends JFrame{
	Container c;
	JLabel lblRno,lblName;
	JTextField txtRno,txtName;
	JButton btnSubmit,btnBack;
	JPanel p1,p2,p3;
	Font font = new Font("Times new roman", Font.PLAIN, 40);

	public static boolean validateLetters(String txt) {
    	String regx = "^[A-Za-z]{1,}$";
		Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(txt);
   		return matcher.find();
	}

	public UpdateFrame(){
		c = getContentPane();
		c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
		//Font font = new Font("Algerian", Font.PLAIN, 40);

		p1 = new JPanel();
		lblRno = new JLabel("Roll No. ");
		txtRno = new JTextField(5);
		lblName = new JLabel("Name ");
		txtName = new JTextField(20);
		p1.add(lblRno);
		p1.add(txtRno);
		p1.add(lblName);
		p1.add(txtName);
		c.add(p1);

		p2 = new JPanel();
		btnSubmit = new JButton("Save");
		btnSubmit.setFont(font);
		btnBack = new JButton("Back");
		btnBack.setFont(font);
		p2.add(btnSubmit);
		p2.add(btnBack);
		c.add(p2);

		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					String r = txtRno.getText();
					if(Integer.parseInt(r) <= 0){
						JOptionPane.showMessageDialog(new JDialog(),"Roll No. Cannot be Zero or Negative");
						txtRno.setText("");
						txtRno.requestFocus();
						return;
					}
					String n = txtName.getText();
					String st = n.trim();
					if(n.length() == 0){
						JOptionPane.showMessageDialog(new JDialog(),"Name is Empty");
						txtName.setText("");
						txtName.requestFocus();
						return;
					}
					if(!validateLetters(st)){
						JOptionPane.showMessageDialog(new JDialog(),"Name is invalid");
						txtName.setText("");
						txtName.requestFocus();
						return;
					}
					DbHandler db = new DbHandler();
					db.updateStudent(Integer.parseInt(r),n);
					txtRno.setText("");
					txtName.setText("");
					txtRno.requestFocus();
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(new JDialog(),"improper entry in fields");
					txtRno.setText("");
					txtRno.requestFocus();
					return;
				}
			}
		});
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				mainframe m = new mainframe();
				dispose();
			}
		});

		setTitle("Update Student");
		setSize(450,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GREEN);
		setVisible(true);
	}
}