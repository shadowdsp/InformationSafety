package cryptography.view.Caesar;

import cryptography.model.Caesar;
import cryptography.model.regExCheck;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaesarDeFrame {
    private JPanel caesarde;
    private JTextField keytextField;
    private JLabel cipJLabel;
    private JLabel keyJLabel;
    private JLabel plainJLabel;
    private JTextArea ciptextArea;
    private JButton solveButton;
    private JTextArea plaintextArea;

    private CaesarDeFrame() {
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cipher = ciptextArea.getText();
                String key = keytextField.getText();

                if(!regExCheck.checkCh(cipher)) { // 密文必须是纯字母
                    JOptionPane.showMessageDialog(null, "密文必须是纯字母", "密文警告", JOptionPane.ERROR_MESSAGE);
                } else if(!regExCheck.checkNum(key)) { // 密钥必须是纯整数
                    JOptionPane.showMessageDialog(null, "密钥必须是纯整数", "密钥警告", JOptionPane.ERROR_MESSAGE);
                } else {
                    Caesar caesar = new Caesar();
                    String plain = caesar.decrypt(cipher, Integer.parseInt(key));
                    plaintextArea.setText(plain);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CaesarDeFrame");
        frame.setLocation(550, 400);
        frame.setSize(700, 500);
        frame.setContentPane(new CaesarDeFrame().caesarde);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
