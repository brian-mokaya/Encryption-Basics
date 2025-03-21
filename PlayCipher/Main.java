package PlayCipher;

import java.util.Scanner;
d
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the key: ");
        String key = scanner.nextLine();
        PlayCipher playCipher = new PlayCipher(key);
        playCipher.printMatrix();
        System.out.println("Enter the message to encrypt: ");
        String plainText = scanner.nextLine();
        String cipherText = playCipher.encrypt(plainText);
        System.out.println("Encrypted message: " + cipherText);

    }
}