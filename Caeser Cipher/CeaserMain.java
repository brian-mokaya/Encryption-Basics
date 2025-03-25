public class CeaserMain {
    public static void main(String[] args) {
        String message = "I will pass my exams";
        int key = 7;
        String encryptedMessage = CaesarCipher.encrypt(message, key);
        System.out.println("Encrypted message: " + encryptedMessage);
        String decryptedMessage = CaesarCipher.decrypt(encryptedMessage, key);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
    
}
