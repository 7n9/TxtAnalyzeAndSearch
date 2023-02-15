import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class window_file extends JFrame{
    private JButton button1;
    private JPanel panel1;

    public window_file() {

    this.setSize(300, 100);
    this.setTitle("Open File");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setLocationRelativeTo(null);


    this.add(panel1);

    panel1.setVisible(true);
    panel1.add(button1);
    button1.setVisible(true);
    //this.pack();
    this.setVisible(true);
    panel1.repaint();

    button1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(panel1);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new window_search(selectedFile);
                    }
                });
            }
        }
    });
}
}
