package cryptography.model;

public class Playfair {
    private char []ins = {'k', 'a'};

    private char[][] getKey(String key) {
        char [][]mat = new char[5][5];
        String key1 = key.toUpperCase();
        int []Hash = new int[25];
        int now = 0;
        int len = key1.length();
        // 将X和Z绑定在一起，先将传入的关键词插入到矩阵中
        for(int i = 0; i < len; i++) {
            char c = key1.charAt(i);
            if(c == 'Z') c = 'X';
            if(0 == Hash[c - 'A']) {
                Hash[(int)c - 'A'] = 1;
                mat[now/5][now%5] = c;
                now++;
            }
        }
        // 再将剩余的字母插入到矩阵中
        for(int i = 0; i < 25; i++) {
            char c = (char) (i + 'A');
            if(0 == Hash[c - 'A']) {
                Hash[c - 'A'] = 1;
                mat[now/5][now%5] = c;
                now++;
            }
        }
        return mat;
//        for(int i = 0; i < 5; i++) {
//            for(int j = 0; j < 5; j++) {
//                System.out.print(mat[i][j]);
//            }
//            System.out.println();
//        }
    }

    // 加密
    public String encrypt(String plaintext) {
        char [][]mat = getKey("monarchy");
        // 消除空格
        plaintext = plaintext.replace(" ", "");
        // 转化为大写
        plaintext = plaintext.toUpperCase();
        int len = plaintext.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++) {
            // a为第一个字符，b为第二个字符
            char a = plaintext.charAt(i);
            char b;
            if(i + 1 < len) {
                // 如果b是合法的
                b = plaintext.charAt(i + 1);
                if(b == a) {
                    // 如果a和b相同就要插入规定好的字符，并且b往右移动，这里看作i不增加
                    if(b != ins[0]) b = ins[0];
                    else b = ins[1];
                } else {
                    // 否则说明b可以插入，i++
                    i++;
                }
            } else {
                // 如果b不能取到，说明还需要插入一个字符凑成一组
                if(a != ins[0]) b = ins[0];
                else b = ins[1];
            }
            // Z都看作X
            if(a == 'Z') a = 'X';
            if(b == 'Z') b = 'X';

            // 在预处理好的矩阵中找到a和b的位置
            int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
            for(int k = 0; k < 5; k++) {
                for(int j = 0; j < 5; j++) {
                    if(mat[k][j] == a) {
                        x1 = k;
                        y1 = j;
                    }
                    if(mat[k][j] == b) {
                        x2 = k;
                        y2 = j;
                    }
                }
            }
            // 按照Playfair算法进行加密
            int col1, col2, row1, row2;
            if(x1 == x2) {
                row1 = row2 = x1;
                col1 = (y1 + 1) % 5;
                col2 = (y2 + 1) % 5;
            } else if(y1 == y2) {
                col1 = col2 = y1;
                row1 = (x1 + 1) % 5;
                row2 = (x2 + 1) % 5;
            } else {
                row1 = x1;
                row2 = x2;
                col1 = y2;
                col2 = y1;
            }
            sb.append(mat[row1][col1]);
            sb.append(mat[row2][col2]);
        }
        return sb.toString();
    }

    // 解密
    public String decrypt(String ciphertext, String key) {
        char [][]mat = getKey(key);
        ciphertext = ciphertext.replace(" ", "");
        ciphertext = ciphertext.toUpperCase();
        StringBuilder sb = new StringBuilder();
        int len = ciphertext.length();
        for(int i = 0; i < len; i += 2) {
            char a = ciphertext.charAt(i);
            char b = ciphertext.charAt(i + 1);

            // 找预处理的矩阵中a和b的位置，然后还原
            int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
            for(int k = 0; k < 5; k++) {
                for(int j = 0; j < 5; j++) {
                    if(mat[k][j] == a) {
                        x1 = k;
                        y1 = j;
                    }
                    if(mat[k][j] == b) {
                        x2 = k;
                        y2 = j;
                    }
                }
            }
            int col1, col2, row1, row2;
            if(x1 == x2) {
                row1 = row2 = x1;
                col1 = (y1 - 1 + 5) % 5;
                col2 = (y2 - 1 + 5) % 5;
            } else if(y1 == y2) {
                col1 = col2 = y1;
                row1 = (x1 - 1 + 5) % 5;
                row2 = (x2 - 1 + 5) % 5;
            } else {
                row1 = x1;
                row2 = x2;
                col1 = y2;
                col2 = y1;
            }
            sb.append(mat[row1][col1]);
            sb.append(mat[row2][col2]);
        }
        return sb.toString();
    }

}
