package cryptography.model;

import Jama.Matrix;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Hill {
    // 最终的矩阵
    public double [][]ansmat = new double[3][3];
    // 最终矩阵的逆
    public double [][]invmat = new double[3][3];

    // 随机得到一个矩阵
    private Matrix getRandomMatrix() {
        double [][]mat = new double[3][3];
        Random random = new Random();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                mat[i][j] = random.nextInt(26);
            }
        }
        return new Matrix(mat);
    }

    // 得到行列式为1或者-1的矩阵，这样的矩阵取逆可以得到整数
    private Matrix getRealMatrix() {
        while(true) {
            Matrix matrix = getRandomMatrix();
            for(int i = 0; i < 26; i++) {
                for(int j = 0; j < 26; j++) {
                    // 调整两个位置的值
                    matrix.set(0, 1, i);
                    matrix.set(0, 2, j);
                    double det = matrix.det();
                    if(det == 1 || det == -1) {
                        return matrix;
                    }
                }
            }
        }
    }

    private String solve(String text, Matrix matrix) {
        text = text.replace(" ", "");
        text = text.toUpperCase();
        int len = text.length();
        // 不是3的整数倍要往后面插入rem个X字符
        int rem = 3 - len % 3;
        // 在后面补X
        if(3 > rem && rem > 0) {
            for(int i = 0; i < rem; i++) {
                text += 'X';
            }
        } else {
            rem = 0;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len + rem; i += 3) {
            // 按照Hill算法计算
            int []p = new int[3];
            p[0] = text.charAt(i) - 'A';
            p[1] = text.charAt(i+1) - 'A';
            p[2] = text.charAt(i+2) - 'A';
            for(int j = 0; j < 3; j++) {
                int c = 0;
                for(int k = 0; k < 3; k++) {
                    DecimalFormat df = new DecimalFormat("######0");
                    c += p[k] * Integer.parseInt(df.format(new Double(matrix.get(j,k))));
                }
                c = (c % 26 + 26) % 26;
                // 插入到答案中
                sb.append((char)(c + 'A'));
            }
        }
        return sb.toString();
    }

    // 加密
    public String encrypt(String plaintext) {
        // 得到一个可逆矩阵
        Matrix matrix = getRealMatrix();
        // 得到最终的矩阵
        this.ansmat = matrix.getArray();
        // 得到最终矩阵的逆
        this.invmat = matrix.inverse().getArray();
        return solve(plaintext, matrix);
    }

    // 解密
    public String decrypt(String ciphertext, double [][]mat) {
        Matrix matrix = new Matrix(mat);
        return solve(ciphertext, matrix.inverse());
    }

    @Test
    public void fun1() {
        int cnt = 0;
        for(int i = 0; i < 100; i++) {
            String pre = "paymoremoney";
            String cipher = encrypt(pre);
            String plain = decrypt(cipher, this.ansmat);
            if(pre.equalsIgnoreCase(plain)) cnt++;
        }
        System.out.println(cnt);
    }
}
