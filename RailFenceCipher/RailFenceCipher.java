package RailFenceCipher;

public class RailFenceCipher {

    // Method to encrypt plaintext using the Rail Fence Cipher
    public static String encrypt(String plaintextMessage, int numRails) {
        if (numRails <= 1) {
            return plaintextMessage; // No encryption needed for 1 rail
        }

        StringBuilder[] rails = new StringBuilder[numRails];
        for (int i = 0; i < numRails; i++) {
            rails[i] = new StringBuilder();
        }

        int currentRail = 0;
        boolean directionDown = true;

        for (char currentCharacter : plaintextMessage.toCharArray()) {
            rails[currentRail].append(currentCharacter);

            if (directionDown) {
                currentRail++;
                if (currentRail == numRails) {
                    currentRail -= 2;
                    directionDown = false;
                }
            } else {
                currentRail--;
                if (currentRail == -1) {
                    currentRail += 2;
                    directionDown = true;
                }
            }
        }

        StringBuilder encryptedMessage = new StringBuilder();
        for (StringBuilder rail : rails) {
            encryptedMessage.append(rail);
        }

        return encryptedMessage.toString();
    }

    // Method to decrypt ciphertext using the Rail Fence Cipher
    public static String decrypt(String ciphertextMessage, int numRails) {
        if (numRails <= 1) {
            return ciphertextMessage; // No decryption needed for 1 rail
        }

        int[] railLengths = new int[numRails];
        int currentRail = 0;
        boolean directionDown = true;

        for (int i = 0; i < ciphertextMessage.length(); i++) {
            railLengths[currentRail]++;
            if (directionDown) {
                currentRail++;
                if (currentRail == numRails) {
                    currentRail -= 2;
                    directionDown = false;
                }
            } else {
                currentRail--;
                if (currentRail == -1) {
                    currentRail += 2;
                    directionDown = true;
                }
            }
        }

        String[] rails = new String[numRails];
        int currentIndex = 0;
        for (int i = 0; i < numRails; i++) {
            rails[i] = ciphertextMessage.substring(currentIndex, currentIndex + railLengths[i]);
            currentIndex += railLengths[i];
        }

        StringBuilder decryptedMessage = new StringBuilder();
        currentRail = 0;
        directionDown = true;

        int[] railIndices = new int[numRails];
        for (int i = 0; i < ciphertextMessage.length(); i++) {
            decryptedMessage.append(rails[currentRail].charAt(railIndices[currentRail]));
            railIndices[currentRail]++;

            if (directionDown) {
                currentRail++;
                if (currentRail == numRails) {
                    currentRail -= 2;
                    directionDown = false;
                }
            } else {
                currentRail--;
                if (currentRail == -1) {
                    currentRail += 2;
                    directionDown = true;
                }
            }
        }

        return decryptedMessage.toString();
    }
}