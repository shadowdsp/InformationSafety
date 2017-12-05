package cryptography.view;

import cryptography.view.Caesar.CaesarCrFrame;
import cryptography.view.Caesar.CaesarDeFrame;
import cryptography.view.Caesar.CaesarEnFrame;
import cryptography.view.Hill.HillDeFrame;
import cryptography.view.Hill.HillEnFrame;
import cryptography.view.Playfair.PlayfairDeFrame;
import cryptography.view.Playfair.PlayfairEnFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    private JPanel mainPanel;
    private JRadioButton playfairRadioButton;
    private JRadioButton hillRadioButton;
    private JRadioButton caesarRadioButton;
    private JRadioButton enRadioButton;
    private JRadioButton deRadioButton;
    private JPanel methodPanel;
    private JPanel casePanel;
    private JButton beginButton;
    private JRadioButton crRadioButton;


    private MainFrame() {
        beginButton.addActionListener(new ActionListener() {
            @Override
            // 总共六种方案，根据单选按钮的选择跳到不同的Frame
            public void actionPerformed(ActionEvent e) {
                if(enRadioButton.isSelected()) {
                    if(caesarRadioButton.isSelected()) {
                        CaesarEnFrame.main(null);
                    } else if(playfairRadioButton.isSelected()) {
                        PlayfairEnFrame.main(null);
                    } else {
                        HillEnFrame.main(null);
                    }
                } else if(deRadioButton.isSelected()) {
                    if(caesarRadioButton.isSelected()) {
                        CaesarDeFrame.main(null);
                    } else if(playfairRadioButton.isSelected()) {
                        PlayfairDeFrame.main(null);
                    } else {
                        HillDeFrame.main(null);
                    }
                } else {
                    if(caesarRadioButton.isSelected()) {
                        CaesarCrFrame.main(null);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrame");
        frame.setLocation(600, 450);
        frame.setContentPane(new MainFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
