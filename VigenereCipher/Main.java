package VigenereCipher;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        // Input the encryption key
        System.out.println("Enter the key: ");
        String encryptionKey = inputScanner.nextLine();

        // Input the plaintext message to encrypt
        System.out.println("Enter the message to encrypt: ");
        String plaintextMessage = inputScanner.nextLine();

        // Encrypt the plaintext message
        String encryptedMessage = VigenereCipher.encrypt(plaintextMessage, encryptionKey);
        System.out.println("Encrypted message: " + encryptedMessage);

        // Input the ciphertext message to decrypt
        System.out.println("Enter the message to decrypt: ");
        String ciphertextMessage = inputScanner.nextLine();

        // Decrypt the ciphertext message
        String decryptedMessage = VigenereCipher.decrypt(ciphertextMessage, encryptionKey);
        System.out.println("Decrypted message: " + decryptedMessage);

        inputScanner.close();
    }
}