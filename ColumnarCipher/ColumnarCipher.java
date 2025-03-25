package ColumnarCipher;

import java.util.Arrays;

public class ColumnarCipher {

    // Method to encrypt plaintext using the Columnar Cipher
    public static String encrypt(String plaintextMessage, String key) {
        int numRows = (int) Math.ceil((double) plaintextMessage.length() / key.length());
        char[][] grid = new char[numRows][key.length()];

        // Fill the grid row by row
        int index = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < key.length(); col++) {
                if (index < plaintextMessage.length()) {
                    grid[row][col] = plaintextMessage.charAt(index++);
                } else {
                    grid[row][col] = 'X'; // Fill empty spaces with 'X'
                }
            }
        }

        // Sort the columns based on the key
        Integer[] columnOrder = getColumnOrder(key);

        // Read the grid column by column based on the sorted column order
        StringBuilder encryptedMessage = new StringBuilder();
        for (int col : columnOrder) {
            for (int row = 0; row < numRows; row++) {
                encryptedMessage.append(grid[row][col]);
            }
        }

        return encryptedMessage.toString();
    }

    // Method to decrypt ciphertext using the Columnar Cipher
    public static String decrypt(String ciphertextMessage, String key) {
        int numRows = (int) Math.ceil((double) ciphertextMessage.length() / key.length());
        char[][] grid = new char[numRows][key.length()];

        // Sort the columns based on the key
        Integer[] columnOrder = getColumnOrder(key);

        // Fill the grid column by column based on the sorted column order
        int index = 0;
        for (int col : columnOrder) {
            for (int row = 0; row < numRows; row++) {
                if (index < ciphertextMessage.length()) {
                    grid[row][col] = ciphertextMessage.charAt(index++);
                }
            }
        }

        // Read the grid row by row to form the plaintext
        StringBuilder decryptedMessage = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < key.length(); col++) {
                decryptedMessage.append(grid[row][col]);
            }
        }

        // Remove trailing 'X' characters added during encryption
        while (decryptedMessage.charAt(decryptedMessage.length() - 1) == 'X') {
            decryptedMessage.deleteCharAt(decryptedMessage.length() - 1);
        }

        return decryptedMessage.toString();
    }

    // Helper method to determine the column order based on the key
    private static Integer[] getColumnOrder(String key) {
        Character[] keyChars = new Character[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyChars[i] = key.charAt(i);
        }

        Integer[] columnOrder = new Integer[key.length()];
        for (int i = 0; i < key.length(); i++) {
            columnOrder[i] = i;
        }

        Arrays.sort(columnOrder, (a, b) -> keyChars[a].compareTo(keyChars[b]));
        return columnOrder;
    }
}