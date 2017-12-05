package cryptography.view.Caesar;

import cryptography.model.Caesar;
import cryptography.model.regExCheck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CaesarCrFrame {
    private JTextArea ciptextArea;
    private JButton beginButton;
    private JTextArea crtextArea;
    private JLabel cipLabel;
    private JLabel crLabel;
    private JPanel crPanel;
    private JPanel cipPanel;
    private JPanel caesarcr;

    public CaesarCrFrame() {
        beginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cipher = ciptextArea.getText();
                // 密文必须是纯字母
                if(regExCheck.checkCh(cipher)) {
                    Caesar caesar = new Caesar();
                    List<String> cipherList = caesar.crack(cipher);
                    int len = cipherList.size();
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < len; i++) {
                        sb.append("偏移量为" + i + "的情况：" + cipherList.get(i) + "\n");
                    }
                    crtextArea.setText(sb.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "密文必须是纯字母", "密文警告", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CaesarCrFrame");
        frame.setLocation(550, 400);
        frame.setSize(700, 500);
        frame.setContentPane(new CaesarCrFrame().caesarcr);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
