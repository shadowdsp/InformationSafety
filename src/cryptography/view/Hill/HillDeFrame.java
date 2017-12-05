package cryptography.view.Hill;

import cryptography.model.Hill;
import cryptography.model.regExCheck;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HillDeFrame {
    private JPanel hillde;
    private JTextField ciptextField;
    private JTextField plaintextField;
    private JLabel cipLabel;
    private JLabel plainLabel;
    private JPanel middlePanel;
    private JButton beginbutton;
    private JPanel keyPanel;
    private JLabel keytextField;
    private JPanel matPanel;
    private JTextField mat00textField;
    private JTextField mat10textField;
    private JTextField mat20textField;
    private JTextField mat01textField;
    private JTextField mat02textField;
    private JTextField mat11textField;
    private JTextField mat12textField;
    private JTextField mat22textField;
    private JTextField mat21textField;
    private JPanel buttomPanel;
    private JPanel topPanel;

    private HillDeFrame() {
        beginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cipher = ciptextField.getText();
                // 验证密文是否合法，必须是纯字母
                if(!regExCheck.checkCh(cipher)) {
                    JOptionPane.showMessageDialog(null, "密文必须是纯字母", "密文警告", JOptionPane.ERROR_MESSAGE);
                } else {
                    double [][]mat = new double[3][3];
                    String mat00 = mat00textField.getText();
                    String mat01 = mat01textField.getText();
                    String mat02 = mat02textField.getText();
                    String mat10 = mat10textField.getText();
                    String mat11 = mat11textField.getText();
                    String mat12 = mat12textField.getText();
                    String mat20 = mat20textField.getText();
                    String mat21 = mat21textField.getText();
                    String mat22 = mat22textField.getText();
                    // 验证密钥矩阵是否合法，必须是纯数字
                    if(!regExCheck.checkNum(mat00) || !regExCheck.checkNum(mat01) || !regExCheck.checkNum(mat02) ||
                            !regExCheck.checkNum(mat10) || !regExCheck.checkNum(mat11) || !regExCheck.checkNum(mat12) ||
                            !regExCheck.checkNum(mat20) || !regExCheck.checkNum(mat21) || !regExCheck.checkNum(mat22)) {
                        JOptionPane.showMessageDialog(null, "密钥必须是纯数字", "密钥警告", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // 对矩阵进行操作
                        mat[0][0] = Double.parseDouble(mat00);
                        mat[0][1] = Double.parseDouble(mat01);
                        mat[0][2] = Double.parseDouble(mat02);
                        mat[1][0] = Double.parseDouble(mat10);
                        mat[1][1] = Double.parseDouble(mat11);
                        mat[1][2] = Double.parseDouble(mat12);
                        mat[2][0] = Double.parseDouble(mat20);
                        mat[2][1] = Double.parseDouble(mat21);
                        mat[2][2] = Double.parseDouble(mat22);
                        Hill hill = new Hill();
                        String plain = hill.decrypt(cipher, mat);
                        plaintextField.setText(plain);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("HillDeFrame");
        frame.setLocation(550, 400);
        frame.setContentPane(new HillDeFrame().hillde);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
