import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
hhhhh

class Login extends JFrame implements ActionListener {
    private JTextField userName;
    private JPasswordField password;
    private JButton login, signup;
    private JLabel label_user, label_pass;

    Login() 
    {
      setTitle("Login portal");
      setBounds(520,200,500,500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      label_user = new JLabel("Enter username:");
      label_user.setForeground(Color.RED);
      label_pass = new JLabel("Password");

      userName=new JTextField(10);
      password = new JPasswordField(10);
      login = new JButton("Log In");
      signup =new JButton("sign up");

      login.addActionListener(this);
      signup.addActionListener(this);

      add(label_user);
      add(userName);
      add(label_pass);
      add(password);
      add(login);
      add(signup);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==login) 
        {
            String userText =userName.getText();
            char[] pass = password.getPassword();
            String passText = new String(pass);
            
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/harry", "root", "Iamindian@13");
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM USERPASS WHERE USERNAME ='"+ userText+"'");
                boolean userFound = false;

                while (rs.next()) {
                    userFound = true;
                    if (userText.equalsIgnoreCase(rs.getString(1)) && passText.equals(rs.getString(2))) 
                    {
                        JOptionPane.showMessageDialog(this,"Login Successful!");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"incorrect Username or Passwor");
                    }
                    
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

}

public class LoginPortal {
    public static void main(String[] args) {
        Login a = new Login();
        a.setVisible(true);
    }
}
