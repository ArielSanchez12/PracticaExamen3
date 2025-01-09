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
                String password = "1234";


                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "SELECT * FROM Usuarios WHERE usuario = ? AND contrasenia = ?";
                    PreparedStatement BDD = conn.prepareStatement(query);
                    BDD.setString(1, textField1.getText());
                    BDD.setString(2, passwordField1.getText());

                    ResultSet busquedaMySQL = BDD.executeQuery();
                    if (busquedaMySQL.next()) {
                        JOptionPane.showMessageDialog(null, "Credenciales correctas, ingresando...");

                        JFrame frame = new JFrame("Iniciar Sesion");
                        frame.setContentPane(new login().Plogin);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(500, 300);
                        frame.setPreferredSize(new Dimension(500, 300));
                        frame.setLocationRelativeTo(null);
                        frame.pack();
                        frame.setVisible(true);

                        JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(Plogin);
                        loginFrame.dispose();

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
