package RowCipher;

public class RowCipher {

    // Method to encrypt plaintext using the Row Cipher
    public static String encrypt(String plaintextMessage, int key) {
        int numRows = (int) Math.ceil((double) plaintextMessage.length() / key);
        char[][] grid = new char[numRows][key];

        // Fill the grid row by row
        int index = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < key; col++) {
                if (index < plaintextMessage.length()) {
                    grid[row][col] = plaintextMessage.charAt(index++);
                } else {
                    grid[row][col] = '\0'; // Use null character for empty spaces
                }
            }
        }

        // Read the grid column by column to form the ciphertext
        StringBuilder encryptedMessage = new StringBuilder();
        for (int col = 0; col < key; col++) {
            for (int row = 0; row < numRows; row++) {
                if (grid[row][col] != '\0') { // Skip null characters
                    encryptedMessage.append(grid[row][col]);
                }
            }
        }

        return encryptedMessage.toString();
    }

    // Method to decrypt ciphertext using the Row Cipher
    public static String decrypt(String ciphertextMessage, int key) {
        int numRows = (int) Math.ceil((double) ciphertextMessage.length() / key);
        char[][] grid = new char[numRows][key];

        // Fill the grid column by column
        int index = 0;
        for (int col = 0; col < key; col++) {
            for (int row = 0; row < numRows; row++) {
                if (index < ciphertextMessage.length()) {
                    grid[row][col] = ciphertextMessage.charAt(index++);
                }
            }
        }

        // Read the grid row by row to form the plaintext
        StringBuilder decryptedMessage = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < key; col++) {
                if (grid[row][col] != '\0') { // Skip null characters
                    decryptedMessage.append(grid[row][col]);
                }
            }
        }

        return decryptedMessage.toString();
    }
}