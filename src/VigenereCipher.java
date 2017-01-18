/*this encrypts and decrypts strings using the vigenere cipher
 * this only outputs uppercase strings though it can intake lowercase strings
 * and can't deal with punctuation
 */

import java.util.Scanner;
public class VigenereCipher {
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        VigenereCipher cip = new VigenereCipher();
        String ab = "attackAtDawN", ac = "Lemon";
        System.out.println(cip.encrypt(ab, ac));
        System.out.println(cip.decrypt(cip.encrypt(ab, ac), ac));
    }


    private static boolean isValidKey(String message, String key) {
        //boolean denotes whether the string meets all requirements or not

        if (key.length() != message.length()) {
            return false;
        }

        for (int j = 0; j < key.length(); j++) {
            if (key.charAt(j) < 'A' || key.charAt(j) > 'Z') {
                return false;
            }
        }

        return true;
    }

    //encrypt the given string using the formula C = (M + K) mod 26
    public static String encrypt(String message, String key) {

        String cipher = "";

        key = generateKey(message,key);

        for (int i = 0; i < message.length(); i++) {

            int shift = alphabet.indexOf(key.charAt(i));

            char character = message.charAt(i);
            char encryptedCharacter = Caesar.encrypt(character, shift);

            cipher += encryptedCharacter;

        }

        return cipher;
    }


    //encrypt the given string using the formula C = (M + K) mod 26
    public static String decrypt(String message, String key) {

        key = generateKey(message,key);

        String plaintextMessage = "";


        for (int i = 0; i < message.length(); i++) {

            int shift = alphabet.indexOf(key.charAt(i));

            char character = message.charAt(i);
            char encryptedCharacter = Caesar.decrypt(character, shift);

            plaintextMessage += encryptedCharacter;

        }

        return plaintextMessage;
    }


    private static String generateKey(String message, String key) {

        key = key.toUpperCase();

        if (key.length() < message.length()) {
            int i = 0;
            while (key.length() < message.length()) {
                key = key.concat(String.valueOf(key.charAt(i)));
                i++;
            }
        } else if (key.length() > message.length()) {
            key = key.substring(0,message.length());
        }

        return key;

    }




}