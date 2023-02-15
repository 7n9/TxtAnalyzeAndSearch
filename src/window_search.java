import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class window_search extends JFrame{
    private JPanel panel1;
    private JButton searchButton;
    private JLabel Label1;
    private JLabel Label2;
    private JLabel Label3;
    private JTextField textField1;


    public window_search(File f){

        String file_contents = "";
        
        try {
            byte[] bytes = Files.readAllBytes(f.toPath());
            file_contents = new String(bytes);


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.setSize(500, 400);
        this.setTitle("File: " + f.getName());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.add(panel1);
        panel1.setVisible(true);




        Label1.setText("Lowercase letters: " + countlowercase(file_contents));
        Label1.setVisible(true);

        Label2.setText("Uppercase letters: " + countUppercase(file_contents));
        Label2.setVisible(true);

        Label3.setText("Digit count: " + countDigits(file_contents));
        Label3.setVisible(true);

        this.setVisible(true);
        panel1.repaint();


        String final_fileContents = file_contents;
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StringBuilder search_result = new StringBuilder();
                List results_arr = search(final_fileContents, textField1.getText());

                for (int i = 0; i < results_arr.size() ; i++) {
                    search_result.append(results_arr.get(i));
                    if((i+1)%5 == 0){
                        search_result.append("\n");
                    }else{
                        search_result.append(", ");
                    }
                }
                JOptionPane.showMessageDialog(null, "Expressions containing \"" + textField1.getText() + "\":\n" + search_result,"Search Results", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }



    private int countlowercase(String s){

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                count++;
            }
        }

        return count;
    }

    private int countUppercase(String s){

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                count++;
            }
        }

        return count;
    }

    private int countDigits(String s){

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                count++;
            }
        }

        return count;
    }

    private List<String> search(String s, String search_val){

        List<String> resu = new ArrayList<>();
        String[] contents = s.replaceAll("\n", " ").replaceAll(",", "").replaceAll("\\.", "").split(" ");

        for(String analyzed_part : contents){
            if(analyzed_part.contains(search_val)){
                resu.add(analyzed_part);
            }
        }

        return resu;
    }
}
