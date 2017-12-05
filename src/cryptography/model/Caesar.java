package cryptography.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Caesar {
    // 加密时候随机得到的密钥
    public int key;
    // 加密
    public String encrypt(String plaintext) {
        Random random = new Random();
        this.key = random.nextInt(26);
        return solve(plaintext, this.key, 0);
    }

    // 解密
    public String decrypt(String ciphertext, int key) {
        return solve(ciphertext, key, 1);
    }

    // 破解
    public List<String> crack(String ciphertext) {
        List<String> ciplist = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            ciplist.add(decrypt(ciphertext, i));
        }
        return ciplist;
    }

    private String solve(String text, int key, int type) {
        key %= 26;
        StringBuilder sb = new StringBuilder();
//        text = text.replace(" ", "");
//        text = text.toUpperCase();
        int len = text.length();
        for(int i = 0; i < len; i++) {
            char c = text.charAt(i);
            if(c != ' ') {
                int k;
                if(c < 'a') { // 大写字母
                    k = (int)'A';
                } else { // 小写字母
                    k = (int)'a';
                }
                if(type == 0) {
                    c = (char)(((text.charAt(i) - k + key) % 26) + k);
                } else {
                    c = (char)(((text.charAt(i) - k - key + 26) % 26) + k);
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
