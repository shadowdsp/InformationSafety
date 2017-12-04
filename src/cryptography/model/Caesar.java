package cryptography.model;

public class Caesar {

    // 加密
    public String encrypt(String plaintext) {
        return solve(plaintext, 3, 0);
    }

    // 解密
    public String decrypt(String ciphertext, int key) {
        return solve(ciphertext, key, 1);
    }

    private String solve(String text, int key, int type) {
        StringBuilder sb = new StringBuilder();
        text = text.replace(" ", "");
        text = text.toUpperCase();
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
