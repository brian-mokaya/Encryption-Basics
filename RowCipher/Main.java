package RowCipher;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Enter the key (number of columns): ");
        int key = inputScanner.nextInt();
        inputScanner.nextLine();

        System.out.println("Enter the message to encrypt: ");
        String plaintextMessage = inputScanner.nextLine();

        // Remove spaces from the plaintext message
        String processedPlaintext = plaintextMessage.replaceAll("\\s", "");

        String encryptedMessage = RowCipher.encrypt(processedPlaintext, key);
        System.out.println("Encrypted message: " + encryptedMessage);

        System.out.println("Enter the message to decrypt: ");
        String ciphertextMessage = inputScanner.nextLine();

        String decryptedMessage = RowCipher.decrypt(ciphertextMessage, key);

        // Optionally, reintroduce spaces (if you know their positions)
        System.out.println("Decrypted message: " + decryptedMessage);

        inputScanner.close();
    }
}