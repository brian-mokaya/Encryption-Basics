package PlayCipher;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the key
        System.out.println("Enter the key: ");
        String key = scanner.nextLine();

        // Create PlayCipher instance
        PlayCipher playCipher = new PlayCipher(key);

        // Print the cipher matrix
        System.out.println("Generated Cipher Matrix:");
        playCipher.printMatrix();

        // Input the plaintext to encrypt
        System.out.println("Enter the message to encrypt: ");
        String plainText = scanner.nextLine();

        // Encrypt the plaintext
        String cipherText = playCipher.encrypt(plainText);
        System.out.println("Encrypted message: " + cipherText);

        // Input the ciphertext to decrypt
        System.out.println("Enter the message to decrypt: ");
        String encryptedText = scanner.nextLine();

        // Decrypt the ciphertext
        String decryptedText = playCipher.decrypt(encryptedText);
        System.out.println("Decrypted message: " + decryptedText);

        scanner.close();
    }
}