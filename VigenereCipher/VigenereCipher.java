package VigenereCipher;

public class VigenereCipher {

    public static String encrypt(String plaintextMessage, String encryptionKey) {
        StringBuilder encryptedMessage = new StringBuilder();
        encryptionKey = encryptionKey.toUpperCase();
        int encryptionKeyIndex = 0;

        for (char currentCharacter : plaintextMessage.toCharArray()) {
            if (Character.isLetter(currentCharacter)) {
                int shiftValue = encryptionKey.charAt(encryptionKeyIndex) - 'A';
                if (Character.isUpperCase(currentCharacter)) {
                    char encryptedCharacter = (char) ((currentCharacter - 'A' + shiftValue) % 26 + 'A');
                    encryptedMessage.append(encryptedCharacter);
                } else {
                    char encryptedCharacter = (char) ((currentCharacter - 'a' + shiftValue) % 26 + 'a');
                    encryptedMessage.append(encryptedCharacter);
                }
                encryptionKeyIndex = (encryptionKeyIndex + 1) % encryptionKey.length();
            } else {
                encryptedMessage.append(currentCharacter);
            }
        }

        return encryptedMessage.toString();
    }

    public static String decrypt(String ciphertextMessage, String decryptionKey) {
        StringBuilder decryptedMessage = new StringBuilder();
        decryptionKey = decryptionKey.toUpperCase();
        int decryptionKeyIndex = 0;

        for (char currentCharacter : ciphertextMessage.toCharArray()) {
            if (Character.isLetter(currentCharacter)) {
                int shiftValue = decryptionKey.charAt(decryptionKeyIndex) - 'A';
                if (Character.isUpperCase(currentCharacter)) {
                    char decryptedCharacter = (char) ((currentCharacter - 'A' - shiftValue + 26) % 26 + 'A');
                    decryptedMessage.append(decryptedCharacter);
                } else {
                    char decryptedCharacter = (char) ((currentCharacter - 'a' - shiftValue + 26) % 26 + 'a');
                    decryptedMessage.append(decryptedCharacter);
                }
                decryptionKeyIndex = (decryptionKeyIndex + 1) % decryptionKey.length();
            } else {
                decryptedMessage.append(currentCharacter);
            }
        }

        return decryptedMessage.toString();
    }
}