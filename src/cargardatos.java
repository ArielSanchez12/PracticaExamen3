import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class cargardatos {
    public JPanel PCargarDatos;
    public JTextArea textArea1;
    private JButton menuButton;
    private JButton cargarDatosButton;


    public cargardatos() {
        cargarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/RegistrosNatacion";
                String user = "root";
                String password = "root";

                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "SELECT * FROM UsuariosNatacion";
                    try (PreparedStatement BDD = conn.prepareStatement(query);
                         ResultSet resultado = BDD.executeQuery()) {

                        StringBuilder resultados = new StringBuilder();
                        while (resultado.next()) {
                            resultados.append("ID: ").append(resultado.getInt("id"))
                                    .append(", Nombre: ").append(resultado.getString("nombre"))
                                    .append(", Edad: ").append(resultado.getInt("edad")).append("\n");

                        }
                        textArea1.setText(resultados.toString());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos");
                }
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame cargarDFrame = (JFrame) SwingUtilities.getWindowAncestor(PCargarDatos);
                if (cargarDFrame != null) {
                    cargarDFrame.dispose();
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
