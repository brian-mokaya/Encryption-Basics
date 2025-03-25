package ColumnarCipher;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        // Input the key (a string)
        System.out.println("Enter the key (a string): ");
        String key = inputScanner.nextLine();

        // Input the plaintext message to encrypt
        System.out.println("Enter the message to encrypt: ");
        String plaintextMessage = inputScanner.nextLine();

        // Encrypt the plaintext message
        String encryptedMessage = ColumnarCipher.encrypt(plaintextMessage, key);
        System.out.println("Encrypted message: " + encryptedMessage);

        // Input the ciphertext message to decrypt
        System.out.println("Enter the message to decrypt: ");
        String ciphertextMessage = inputScanner.nextLine();

        // Decrypt the ciphertext message
        String decryptedMessage = ColumnarCipher.decrypt(ciphertextMessage, key);
        System.out.println("Decrypted message: " + decryptedMessage);

        inputScanner.close();
    }
}