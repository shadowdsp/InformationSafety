package cryptography.view.Hill;

import cryptography.model.Hill;
import cryptography.model.regExCheck;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HillEnFrame {
    private JPanel hillen;
    private JTextField plaintextField;
    private JTextField ciptextField;
    private JButton beginbutton;
    private JTextField mat00textField;
    private JTextField mat10textField;
    private JTextField mat20textField;
    private JTextField mat01textField;
    private JTextField mat02textField;
    private JTextField mat11textField;
    private JTextField mat12textField;
    private JTextField mat22textField;
    private JTextField mat21textField;
    private JLabel plainLabel;
    private JLabel cipLabel;
    private JLabel keytextField;
    private JPanel matPanel;
    private JPanel keyPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JLabel invmatLabel;
    private JPanel invmatPanel;
    private JTextField invmat00textField;
    private JTextField invmat10textField;
    private JTextField invmat20textField;
    private JTextField invmat01textField;
    private JTextField invmat02textField;
    private JTextField invmat11textField;
    private JTextField invmat12textField;
    private JTextField invmat22textField;
    private JTextField invmat21textField;

    private HillEnFrame() {
        beginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plain = plaintextField.getText();
                // 明文必须是纯字母
                if(!regExCheck.checkCh(plain)) {
                    JOptionPane.showMessageDialog(null, "明文必须是纯字母", "明文警告", JOptionPane.ERROR_MESSAGE);
                } else {
                    Hill hill = new Hill();
                    // 加密算法
                    String cipher = hill.encrypt(plain);

                    ciptextField.setText(cipher);

                    // 得到加密的矩阵
                    double [][]mat = hill.ansmat;
                    mat00textField.setText(String.valueOf((int)((mat[0][0] % 26 + 26)) % 26));
                    mat01textField.setText(String.valueOf((int)((mat[0][1] % 26 + 26)) % 26));
                    mat02textField.setText(String.valueOf((int)((mat[0][2] % 26 + 26)) % 26));
                    mat10textField.setText(String.valueOf((int)((mat[1][0] % 26 + 26)) % 26));
                    mat11textField.setText(String.valueOf((int)((mat[1][1] % 26 + 26)) % 26));
                    mat12textField.setText(String.valueOf((int)((mat[1][2] % 26 + 26)) % 26));
                    mat20textField.setText(String.valueOf((int)((mat[2][0] % 26 + 26)) % 26));
                    mat21textField.setText(String.valueOf((int)((mat[2][1] % 26 + 26)) % 26));
                    mat22textField.setText(String.valueOf((int)((mat[2][2] % 26 + 26)) % 26));

                    // 得到加密矩阵的逆矩阵，方便验证
                    mat = hill.invmat;
                    invmat00textField.setText(String.valueOf((int)((mat[0][0] % 26 + 26)) % 26));
                    invmat01textField.setText(String.valueOf((int)((mat[0][1] % 26 + 26)) % 26));
                    invmat02textField.setText(String.valueOf((int)((mat[0][2] % 26 + 26)) % 26));
                    invmat10textField.setText(String.valueOf((int)((mat[1][0] % 26 + 26)) % 26));
                    invmat11textField.setText(String.valueOf((int)((mat[1][1] % 26 + 26)) % 26));
                    invmat12textField.setText(String.valueOf((int)((mat[1][2] % 26 + 26)) % 26));
                    invmat20textField.setText(String.valueOf((int)((mat[2][0] % 26 + 26)) % 26));
                    invmat21textField.setText(String.valueOf((int)((mat[2][1] % 26 + 26)) % 26));
                    invmat22textField.setText(String.valueOf((int)((mat[2][2] % 26 + 26)) % 26));
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("HillEnFrame");
        frame.setLocation(400, 400);
        frame.setContentPane(new HillEnFrame().hillen);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
