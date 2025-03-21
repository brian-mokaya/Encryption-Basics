package PlayCipher;

public class PlayCipher{
    private String key;
    private char[][] matrix;

    public PlayCipher(String key){
        this.key = formatKey(key);
        this.matrix =  this.generateMatrix(this.key);

    }

public  String formatKey(String key){
        key = key.toUpperCase();
        key = key.replaceAll("[^A-Z]", "");
        key = key.replace("J", "I");

        StringBuilder formattedKey = new StringBuilder();

        for(char c : key.toCharArray()){
            if(formattedKey.indexOf(String.valueOf(c)) == -1){
                formattedKey.append(c);
            }
        }

        return formattedKey.toString();
    }

private char[][] generateMatrix(String key){
        char[][] matrix = new char[5][5];
        boolean[] used = new boolean[26];
        int x=0, y=0;

        //fill the matrix with the key
        for(char c : key.toCharArray()){
            matrix[x][y] = c;
            used[c - 'A'] = true;
            y++;
            if(y == 5){
                x++;
                y = 0;
            }
        }

        //fill the matrix with the remaining letters
        for(char c = 'A'; c <= 'Z'; c++){
            if(c == 'J') continue;
            if(!used[c - 'A']){
                matrix[x][y] = c;
                y++;
                if(y == 5){
                    x++;
                    y = 0;
                }
            }
        }
        return matrix;
    }

    public void printMatrix(){
        for(char row[] : matrix){
            for(char c : row){
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    //encryption
    public String encrypt(String plaintext){
        plaintext = plaintext.toUpperCase();
        plaintext = plaintext.replaceAll("[^A-Z]", "");
        plaintext = plaintext.replace("J", "I");

        StringBuilder encryptedText = new StringBuilder();

        for(int i=0; i<plaintext.length(); i+=2){
            char a = plaintext.charAt(i);
            char b = (i+1 < plaintext.length()) ? plaintext.charAt(i+1) : 'X';
            if(a == b) {
                b = 'X';
                i--;
        }

        encryptedText.append(encryptPair(a, b));

        }

        return encryptedText.toString();
    }

    private String encryptPair(char a, char b){
        StringBuilder encrypt = new StringBuilder();
        int[] aPos = findPosition(a);
        int[] bPos = findPosition(b);

        if(aPos[0] == bPos[0]){ 
            encrypt.append(matrix[aPos[0]][(aPos[1] + 1) % 5]);
            encrypt.append(matrix[bPos[0]][(bPos[1] + 1) % 5]);
        } else if(aPos[1] == bPos[1]){
            encrypt.append(matrix[(aPos[0] + 1) % 5][aPos[1]]);
            encrypt.append(matrix[(bPos[0] + 1) % 5][bPos[1]]);
        } else { 
            encrypt.append(matrix[aPos[0]][bPos[1]]);
            encrypt.append(matrix[bPos[0]][aPos[1]]);
        }

        return encrypt.toString();
    }

    private int[] findPosition(char c){
        int[] pos = new int[2];

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(matrix[i][j] == c){
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }

        return pos;
    }
       
}
