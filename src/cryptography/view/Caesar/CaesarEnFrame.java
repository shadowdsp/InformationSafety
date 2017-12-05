package cryptography.view.Caesar;

import cryptography.model.Caesar;
import cryptography.model.regExCheck;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaesarEnFrame {
    private JTextArea plaintextArea;
    private JTextArea ciptextArea;
    private JLabel cipLable;
    private JLabel plaintextLable;
    private JButton checkButton;
    private JPanel caesaren;
    private JTextField keytextField;
    private JLabel keyLabel;

    private CaesarEnFrame() {
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plain = plaintextArea.getText();
                if(!regExCheck.checkCh(plain)) { // 明文必须是纯字母
                    JOptionPane.showMessageDialog(null, "明文必须是纯字母", "明文警告", JOptionPane.ERROR_MESSAGE);
                } else {
                    Caesar caesar = new Caesar();
                    String cipher = caesar.encrypt(plain);
                    ciptextArea.setText(cipher);
                    keytextField.setText(Integer.toString(caesar.key));
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CaesarEnFrame");
        frame.setLocation(550, 400);
        frame.setSize(700, 500);
        frame.setContentPane(new CaesarEnFrame().caesaren);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
