import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu {
    public JPanel PMenu;
    private JButton registroButton;
    private JButton verRegistrosButton;
    private JButton salirButton;

    public menu() {
        registroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame menuFrame = (JFrame) SwingUtilities.getWindowAncestor(PMenu);
                if (menuFrame != null) {
                    menuFrame.dispose();
                }

                JFrame frame = new JFrame("Formulario de Registro");
                frame.setContentPane(new registro().PRegistro);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(500, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

            }
        });
        verRegistrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame menuFrame = (JFrame) SwingUtilities.getWindowAncestor(PMenu);
                if (menuFrame != null) {
                    menuFrame.dispose();
                }

                JFrame frame = new JFrame("Datos");
                frame.setContentPane(new cargardatos().PCargarDatos);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(500, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(salirButton);
                if (frame != null) {
                    frame.dispose();
                }
                System.exit(0);
            }
        });
    }
}
