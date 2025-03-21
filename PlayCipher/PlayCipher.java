package PlayCipher;

public class PlayCipher{
    private String key; // The key used for the cipher.
    private char[][] matrix; // 5x5 grid for encryption.

    public PlayCipher(String key){
        this.key = formatKey(key); // Format and clean the provided key.
        this.matrix =  this.generateMatrix(this.key); // Generate the cipher matrix.
    }

    public String formatKey(String key){
        key = key.toUpperCase(); // Convert key to uppercase.
        key = key.replaceAll("[^A-Z]", ""); // Remove non-alphabetic characters.
        key = key.replace("J", "I"); // Replace 'J' with 'I' for Playfair cipher compatibility.

        StringBuilder formattedKey = new StringBuilder();

        for(char c : key.toCharArray()){
            if(formattedKey.indexOf(String.valueOf(c)) == -1){
                formattedKey.append(c); // Add unique letters only.
            }
        }
        return formattedKey.toString(); // Return formatted key.
    }

    private char[][] generateMatrix(String key){
        char[][] matrix = new char[5][5]; // 5x5 matrix for the cipher.
        boolean[] used = new boolean[26]; // Track used letters in the alphabet.
        int x = 0, y = 0; // Matrix row and column indices.

        // Fill the matrix with letters from the key.
        for(char c : key.toCharArray()){
            matrix[x][y] = c; 
            used[c - 'A'] = true; // Mark letter as used.
            y++;
            if(y == 5){
                x++;
                y = 0; // Move to the next row when column limit is reached.
            }
        }

        // Fill remaining slots in the matrix with unused letters.
        for(char c = 'A'; c <= 'Z'; c++){
            if(c == 'J') continue; // Skip 'J'.
            if(!used[c - 'A']){ // Check if the letter has not been used.
                matrix[x][y] = c; 
                y++;
                if(y == 5){
                    x++;
                    y = 0; // Move to the next row when column limit is reached.
                }
            }
        }
        return matrix; // Return the generated matrix.
    }

    public void printMatrix(){
        for(char row[] : matrix){
            for(char c : row){
                System.out.print(c + " "); // Print each character in the row.
            }
            System.out.println(); // Move to the next row.
        }
    }

    public String encrypt(String plaintext){
        plaintext = plaintext.toUpperCase(); // Convert plaintext to uppercase.
        plaintext = plaintext.replaceAll("[^A-Z]", ""); // Remove non-alphabetic characters.
        plaintext = plaintext.replace("J", "I"); // Replace 'J' with 'I'.

        StringBuilder encryptedText = new StringBuilder();

        for(int i = 0; i < plaintext.length(); i += 2){
            char a = plaintext.charAt(i); // First letter of the pair.
            char b = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X'; // Second letter or filler.

            if(a == b) { 
                b = 'X'; // Avoid duplicate letters in a pair.
                i--; // Retry with 'a' in the next pair.
            }

            encryptedText.append(encryptPair(a, b)); // Encrypt the pair.
        }

        return encryptedText.toString(); // Return the encrypted text.
    }

    private String encryptPair(char a, char b){
        StringBuilder encrypt = new StringBuilder();
        int[] aPos = findPosition(a); // Get the position of 'a' in the matrix.
        int[] bPos = findPosition(b); // Get the position of 'b' in the matrix.

        if(aPos[0] == bPos[0]) { 
            // Same row: Shift right.
            encrypt.append(matrix[aPos[0]][(aPos[1] + 1) % 5]);
            encrypt.append(matrix[bPos[0]][(bPos[1] + 1) % 5]);
        } else if(aPos[1] == bPos[1]) {
            // Same column: Shift down.
            encrypt.append(matrix[(aPos[0] + 1) % 5][aPos[1]]);
            encrypt.append(matrix[(bPos[0] + 1) % 5][bPos[1]]);
        } else {
            // Rectangle rule: Swap columns.
            encrypt.append(matrix[aPos[0]][bPos[1]]);
            encrypt.append(matrix[bPos[0]][aPos[1]]);
        }

        return encrypt.toString(); // Return the encrypted pair.
    }

    private int[] findPosition(char c){
        int[] pos = new int[2]; // Array to store row and column indices.

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(matrix[i][j] == c){
                    pos[0] = i; // Row index.
                    pos[1] = j; // Column index.
                    return pos; // Return the position of the character.
                }
            }
        }

        return pos; // Return the position if found.
    }
}
