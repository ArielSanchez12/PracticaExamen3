import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class registro {
    public JPanel PRegistro;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registrarmeButton;
    private JButton menuButton;

    public registro() {
        registrarmeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/RegistrosNatacion";
                String user = "root";
                String password = "root";

                String nombre = textField1.getText();
                int edadTexto = Integer.parseInt(textField2.getText());

                if (edadTexto < 18 || edadTexto > 40) {
                    JOptionPane.showMessageDialog(null, "La edad permitida para el registro va de los 18 hasta los 40 años.");
                    return;
                }

                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "INSERT INTO UsuariosNatacion (nombre, edad) VALUES (?, ?)";
                    PreparedStatement BDD = conn.prepareStatement(query); {
                        BDD.setString(1, nombre);
                        BDD.setInt(2, edadTexto);
                        BDD.execute();
                        JOptionPane.showMessageDialog(null, "Registro exitoso");
                        textField1.setText("");
                        textField2.setText("");
                }
            } catch (SQLException ex) {
                    System.out.println("mal" + ex.getMessage());
                }
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame registroFrame = (JFrame) SwingUtilities.getWindowAncestor(PRegistro);
                if (registroFrame != null) {
                    registroFrame.dispose();
                }

                JFrame frame = new JFrame("Menu");
                frame.setContentPane(new menu().PMenu);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(500, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
