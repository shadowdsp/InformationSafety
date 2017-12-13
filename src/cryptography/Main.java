package cryptography;

import cryptography.model.Caesar;
import cryptography.model.Hill;
import cryptography.model.Playfair;

public class Main {

    public static void main(String[] args) {
        Caesar caesar = new Caesar();
        String cipher = caesar.encrypt("gaul is divided into three parts");
        String plain = caesar.decrypt(cipher, 3);
//        System.out.println("Caesar - cipher : " + cipher);
//        System.out.println("Caesar - plain : " + plain);
//
//        Playfair playfair = new Playfair();
//        cipher = playfair.encrypt("we are discovered save yourself");
//        plain = playfair.decrypt(cipher, "monarchy");
//        System.out.println("Playfair - cipher : " + cipher);
//        System.out.println("Playfair - plain : " + plain.toLowerCase());

        Hill hill = new Hill();
        cipher = hill.encrypt("pay more money");
        double [][]mat = {
                {4, 9, 15},
                {15, 17, 6},
                {24, 0, 17}
        };
        plain = hill.decrypt(cipher, mat);
        System.out.println("Hill - cipher : " + cipher);
        System.out.println("Hill - plain : " + plain);
    }
}
