public class CeaserMain {
    public static void main(String[] args) {
        String message = "I promise to attend all lectures so that I can pass my exams";
        int key = 17;
        String encryptedMessage = CaesarCipher.encrypt(message, key);
        System.out.println("Encrypted message: " + encryptedMessage);
        String decryptedMessage = CaesarCipher.decrypt(encryptedMessage, key);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
    
}
