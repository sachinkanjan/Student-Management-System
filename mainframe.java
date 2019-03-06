import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;


class mainframe extends JFrame
{
Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete;
Font font = new Font("Times new roman", Font.PLAIN, 40);

mainframe(){
c= getContentPane();
c.setLayout (new BoxLayout (c, BoxLayout.Y_AXIS));  
//c.setLayout(new FlowLayout());

btnAdd = new JButton("Add");
btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
btnAdd.setFont(font);
btnAdd.setBackground(Color.BLACK);
    btnAdd.setForeground(Color.GRAY);
btnView = new JButton("View");
btnView.setBackground(Color.BLACK);
    btnView.setForeground(Color.GRAY);
btnView.setAlignmentX(Component.CENTER_ALIGNMENT);
btnView.setFont(font);
btnUpdate = new JButton("Update");
btnUpdate.setBackground(Color.BLACK);
    btnUpdate.setForeground(Color.GRAY);
btnUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
btnUpdate.setFont(font);
btnDelete = new JButton("Delete");
btnDelete.setBackground(Color.BLACK);
    btnDelete.setForeground(Color.GRAY);
btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
btnDelete.setFont(font);

c.add(Box.createVerticalGlue());
c.add(btnAdd);
c.add(Box.createVerticalGlue());
c.add(btnView);
c.add(Box.createVerticalGlue());
c.add(btnUpdate);
c.add(Box.createVerticalGlue());
c.add(btnDelete);
c.add(Box.createVerticalGlue());

btnAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
AddFrame a = new AddFrame();
dispose();
}
});


btnView.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{

ViewFrame a = new ViewFrame();
dispose();
}
});

btnUpdate.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
UpdateFrame a = new UpdateFrame();
dispose();
}
});

btnDelete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
DeleteFrame d = new DeleteFrame();
dispose();
}
});

setTitle("S.M.S");
setSize(450,450);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
getContentPane().setBackground(Color.BLUE);
getContentPane().setForeground(Color.YELLOW);
//setBackground(Color.RED);

}


public static void main(String args[])
{
mainframe m = new mainframe();

}
}

class DbHandler
{
    public void addStudent(int rno,String name)
    {
        try
        {
         DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection
            ("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
            String sql ="insert into student values (?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,rno);
            pst.setString(2,name);
            int r = pst.executeUpdate();
            JOptionPane.showMessageDialog(new JDialog(),r + "records inserted");
            con.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(new JDialog(),"issue--->" + e);
        }
    }
     public String viewStudent()
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection
            ("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
            String sql ="select * from student order by rno";
           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery(sql);
           while(rs.next())
           {
               sb.append("Rno :" + rs.getInt(1) + "Name :"+ rs.getString(2)+ "\n");
           }
           rs.close();
            con.close();
        }
        catch(SQLException e)
        {
        
            JOptionPane.showMessageDialog(new JDialog(),"issue--->" + e);
           
        }

 return sb.toString();
    }

    public void updateStudent(int rno,String name){
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			String sql = "update student set name = ? where rno = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(2,rno);
			ps.setString(1,name);
			int r = ps.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(),r+" Records Updated");
			con.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(new JDialog(),"issue ----> "+e);
		}

    }
    public void deleteStudent(int rno){
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			String sql = "delete from student where rno  = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,rno);
			int r = ps.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(),r+" Records Deleted");
			con.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(new JDialog(),"issue ----> "+e);
		}
	}
	public void deleteAll(){
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			String sql = "delete from student";
			Statement ps = con.createStatement();
			int r = ps.executeUpdate(sql);
			JOptionPane.showMessageDialog(new JDialog(),r+" Records Deleted");
			con.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(new JDialog(),"issue ----> "+e);
		}
    }
}