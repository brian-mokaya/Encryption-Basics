package VigenereCipher;

public class VigenereCipher {

    // Method to encrypt plaintext
    public static String encrypt(String plaintextMessage, String encryptionKey) {
        StringBuilder encryptedMessage = new StringBuilder();
        encryptionKey = encryptionKey.toUpperCase(); // Ensure the key is in uppercase
        int encryptionKeyIndex = 0;

        for (char currentCharacter : plaintextMessage.toCharArray()) {
            if (Character.isLetter(currentCharacter)) { // Only encrypt alphabetic characters
                int shiftValue = encryptionKey.charAt(encryptionKeyIndex) - 'A'; // Calculate the shift from the key
                if (Character.isUpperCase(currentCharacter)) {
                    // Encrypt uppercase letters
                    char encryptedCharacter = (char) ((currentCharacter - 'A' + shiftValue) % 26 + 'A');
                    encryptedMessage.append(encryptedCharacter);
                } else {
                    // Encrypt lowercase letters
                    char encryptedCharacter = (char) ((currentCharacter - 'a' + shiftValue) % 26 + 'a');
                    encryptedMessage.append(encryptedCharacter);
                }
                encryptionKeyIndex = (encryptionKeyIndex + 1) % encryptionKey.length(); // Move to the next key character
            } else {
                // Non-alphabetic characters are added as-is
                encryptedMessage.append(currentCharacter);
            }
        }

        return encryptedMessage.toString();
    }

    // Method to decrypt ciphertext 
    public static String decrypt(String ciphertextMessage, String decryptionKey) {
        StringBuilder decryptedMessage = new StringBuilder();
        decryptionKey = decryptionKey.toUpperCase(); // Ensure the key is in uppercase
        int decryptionKeyIndex = 0;

        for (char currentCharacter : ciphertextMessage.toCharArray()) {
            if (Character.isLetter(currentCharacter)) { // Only decrypt alphabetic characters
                int shiftValue = decryptionKey.charAt(decryptionKeyIndex) - 'A'; // Calculate the shift from the key
                if (Character.isUpperCase(currentCharacter)) {
                    // Decrypt uppercase letters
                    char decryptedCharacter = (char) ((currentCharacter - 'A' - shiftValue + 26) % 26 + 'A');
                    decryptedMessage.append(decryptedCharacter);
                } else {
                    // Decrypt lowercase letters
                    char decryptedCharacter = (char) ((currentCharacter - 'a' - shiftValue + 26) % 26 + 'a');
                    decryptedMessage.append(decryptedCharacter);
                }
                decryptionKeyIndex = (decryptionKeyIndex + 1) % decryptionKey.length(); // Move to the next key character
            } else {
                // Non-alphabetic characters are added as-is
                decryptedMessage.append(currentCharacter);
            }
        }

        return decryptedMessage.toString();
    }
}