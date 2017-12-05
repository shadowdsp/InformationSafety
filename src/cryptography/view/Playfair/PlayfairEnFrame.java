package cryptography.view.Playfair;

import cryptography.model.Playfair;
import cryptography.model.regExCheck;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayfairEnFrame {
    private JPanel playfairen;
    private JTextArea plaintextArea;
    private JTextArea ciptextArea;
    private JButton begin;
    private JLabel plainLabel;
    private JLabel cipLabel;
    private JTextField keytextField;
    private JLabel keyLabel;

    private PlayfairEnFrame() {
        begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plain = plaintextArea.getText();
                if(!regExCheck.checkCh(plain)) { // 明文必须是纯字母
                    JOptionPane.showMessageDialog(null, "明文必须是纯字母", "明文警告", JOptionPane.ERROR_MESSAGE);
                } else {
                    Playfair playfair = new Playfair();
                    String cipher = playfair.encrypt(plain);
                    ciptextArea.setText(cipher);
                    String key = playfair.key1;
                    keytextField.setText(key);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PlayfairEnFrame");
        frame.setLocation(550, 400);
        frame.setSize(700, 500);
        frame.setContentPane(new PlayfairEnFrame().playfairen);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        frame.pack();
        frame.setVisible(true);
    }
}
