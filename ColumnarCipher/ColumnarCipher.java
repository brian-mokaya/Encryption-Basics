package ColumnarCipher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColumnarCipher {

    public static String encrypt(String plaintext, String key) {
        plaintext = plaintext.replace(" ", ""); 
        int numColumns = key.length();
        int numRows = (int) Math.ceil((double) plaintext.length() / numColumns);

        char[][] matrix = new char[numRows][numColumns];
        int index = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                if (index < plaintext.length()) {
                    matrix[row][col] = plaintext.charAt(index++);
                } else {
                    matrix[row][col] = '\0'; 
                }
            }
        }

        List<Integer> columnOrder = getColumnOrder(key);

        StringBuilder ciphertext = new StringBuilder();
        for (int col : columnOrder) {
            for (int row = 0; row < numRows; row++) {
                if (matrix[row][col] != '\0') {
                    ciphertext.append(matrix[row][col]);
                }
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        int numColumns = key.length();
        int numRows = (int) Math.ceil((double) ciphertext.length() / numColumns);

        List<Integer> columnOrder = getColumnOrder(key);

        char[][] matrix = new char[numRows][numColumns];
        int index = 0;
        for (int col : columnOrder) {
            for (int row = 0; row < numRows; row++) {
                if (index < ciphertext.length()) {
                    matrix[row][col] = ciphertext.charAt(index++);
                } else {
                    matrix[row][col] = '\0'; 
                }
            }
        }

        StringBuilder plaintext = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                if (matrix[row][col] != '\0') {
                    plaintext.append(matrix[row][col]);
                }
            }
        }

        return plaintext.toString();
    }

    private static List<Integer> getColumnOrder(String key) {
        List<Character> sortedKey = new ArrayList<>();
        for (char c : key.toCharArray()) {
            sortedKey.add(c);
        }
        Collections.sort(sortedKey);

        List<Integer> columnOrder = new ArrayList<>();
        boolean[] used = new boolean[key.length()];

        for (char c : key.toCharArray()) {
            for (int i = 0; i < sortedKey.size(); i++) {
                if (sortedKey.get(i) == c && !used[i]) {
                    columnOrder.add(i);
                    used[i] = true;
                    break;
                }
            }
        }

        return columnOrder;
    }

    public static void main(String[] args) {
        String key = "HACKRIC";
        String plaintext = "mokaya is out for a date";

        System.out.println("Key: " + key);
        System.out.println("Plaintext: " + plaintext);

        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        // Decrypt the ciphertext
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}