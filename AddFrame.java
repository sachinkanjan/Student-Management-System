import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import java.util.*;


class AddFrame extends JFrame
{
Container c;
JLabel lblRno,lblName;
JTextField txtRno,txtName;
JButton btnSave,btnBack;
JPanel p1,p2;
Font font = new Font("Times new roman", Font.PLAIN, 40);
public static boolean validateLetters(String txt) {
    	String regx = "^[A-Za-z]{1,}$";
		Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(txt);
   		return matcher.find();
	}

public AddFrame()
{
c= getContentPane();
c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));

String r,n;
p1= new JPanel();
lblRno = new JLabel("Enter Roll no");
lblName = new JLabel("Enter name");
txtRno = new JTextField(5);
txtName = new JTextField(10);
p1.add(lblName);

p1.add(txtName);
p1.add(lblRno);
p1.add(txtRno);

c.add(p1);

p2 = new JPanel();
btnSave = new JButton("Save");
btnSave.setFont(font);
btnBack = new JButton("Back");
btnBack.setFont(font);
p2.add(btnBack);
p2.add(btnSave);
c.add(p2);

btnSave.addActionListener(new ActionListener()
    {
public void actionPerformed(ActionEvent ae)
{
    try
    {
      String r = txtRno.getText();
					if(Integer.parseInt(r) <= 0){
						JOptionPane.showMessageDialog(new JDialog(),"Roll No. Cannot be Zero or Negative");
						txtRno.setText("");
						txtRno.requestFocus();
						return;
					}
					String n = txtName.getText();
					String st = n.trim();
					if(st.length() == 0){
						JOptionPane.showMessageDialog(new JDialog(),"Name can't be Empty");
						txtName.setText("");
						txtName.requestFocus();
						return;
					}
					if(!validateLetters(st)){
						JOptionPane.showMessageDialog(new JDialog(),"Name is Invalid");
						txtName.setText("");
						txtName.requestFocus();
						return;
					}
    
     r = txtRno.getText();
     n = txtName.getText();
    DbHandler db = new DbHandler();
    db.addStudent(Integer.parseInt(r),n);
    txtName.setText("");
    txtRno.setText("");
    txtRno.requestFocus();
    }
catch(NumberFormatException e){
					JOptionPane.showMessageDialog(new JDialog(),"improper entry in fields");
					txtRno.setText("");
					txtRno.requestFocus();
					return;
}
}
    }
);
btnBack.addActionListener(new ActionListener()
    {
public void actionPerformed(ActionEvent ae)
{
    mainframe a = new mainframe();
    dispose();
}
    });

 setTitle("Add Student");
setSize(350,300);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

setVisible(true);
getContentPane().setBackground(Color.BLUE);

}
}

