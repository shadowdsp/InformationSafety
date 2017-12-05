package cryptography.view.Playfair;

import cryptography.model.Playfair;
import cryptography.model.regExCheck;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayfairDeFrame {
    private JPanel playfairde;
    private JTextArea ciptextArea;
    private JTextArea plaintextArea;
    private JTextField keytextField;
    private JButton beginButton;
    private JLabel cipLabel;
    private JLabel keyLabel;
    private JLabel plainLabel;

    private PlayfairDeFrame() {
        beginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cipher = ciptextArea.getText();
                String key = keytextField.getText();
                if(!regExCheck.checkCh(cipher)) { // 密文必须是纯字母
                    JOptionPane.showMessageDialog(null, "密文必须是纯字母", "密文警告", JOptionPane.ERROR_MESSAGE);
                } else if(!regExCheck.checkCh(key)) { // 密钥必须是纯字母
                    JOptionPane.showMessageDialog(null, "密钥必须是纯字母", "密钥警告", JOptionPane.ERROR_MESSAGE);
                } else {
                    Playfair playfair = new Playfair();
                    String plain = playfair.decrypt(cipher, key);
                    plaintextArea.setText(plain);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PlayfairDeFrame");
        frame.setLocation(550, 400);
        frame.setSize(700, 500);
        frame.setContentPane(new PlayfairDeFrame().playfairde);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        frame.pack();
        frame.setVisible(true);
    }
}
