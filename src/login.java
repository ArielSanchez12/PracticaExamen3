import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login {
    public JTextField textField1;
    public JPasswordField passwordField1;
    public JButton verificarCredencialesButton;
    public JPanel Plogin;

    public login() {
        verificarCredencialesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/ExamenTres";
                String user = "root";
                String password = "root";


                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "SELECT * FROM Usuarios WHERE usuario = ? AND contrasenia = ?";
                    PreparedStatement BDD = conn.prepareStatement(query);
                    BDD.setString(1, textField1.getText());
                    BDD.setString(2, passwordField1.getText());

                    ResultSet busquedaMySQL = BDD.executeQuery();
                    if (busquedaMySQL.next()) {
                        JOptionPane.showMessageDialog(null, "Credenciales correctas, ingresando...");

                        JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(Plogin);
                        if (loginFrame != null) {
                            loginFrame.dispose();
                        }

                        JFrame menuFrame = new JFrame("Menu Principal");
                        menuFrame.setContentPane(new menu().PMenu);
                        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        menuFrame.setSize(500, 300);
                        menuFrame.setPreferredSize(new Dimension(500, 300));
                        menuFrame.setLocationRelativeTo(null);
                        menuFrame.pack();
                        menuFrame.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales incorrectas, intentalo de nuevo");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en MySQL");
                    ex.printStackTrace();
                }
            }
        });
    }
}
