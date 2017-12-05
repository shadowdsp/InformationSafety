package cryptography.model;

import java.util.regex.Pattern;

public class regExCheck {
    // 判断字符串是否是纯数字
    public static boolean checkNum(String str) {
        String regEx = "[0-9]+";
        return Pattern.compile(regEx).matcher(str).matches();
    }

    // 判断字符串是否是纯字母(可以带空格)
    public static boolean checkCh(String str) {
        String regEx = "[a-zA-Z\\s]+";
        return Pattern.compile(regEx).matcher(str).matches();
    }
}
