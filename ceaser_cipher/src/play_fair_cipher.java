import java.util.Scanner;

public class play_fair_cipher {

        static char[][] keyMatrix = new char[5][5];

        // Remove spaces
        static String removeSpace(String text) {
            return text.replaceAll("\\s+", "");
        }

        // Convert to uppercase
        static String toUpperCase(String text) {
            return text.toUpperCase();
        }

        // Generate 5x5 Key Matrix
        static void generateKeyMatrix(String key) {
            boolean[] visited = new boolean[26];

            key = toUpperCase(removeSpace(key));
            key = key.replace('J', 'I');

            StringBuilder sb = new StringBuilder();

            for (char ch : key.toCharArray()) {
                if (Character.isLetter(ch) && !visited[ch - 'A']) {
                    visited[ch - 'A'] = true;
                    sb.append(ch);
                }
            }

            for (char ch = 'A'; ch <= 'Z'; ch++) {
                if (ch == 'J')
                    continue;
                if (!visited[ch - 'A']) {
                    visited[ch - 'A'] = true;
                    sb.append(ch);
                }
            }

            int k = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    keyMatrix[i][j] = sb.charAt(k++);
                }
            }
        }

        // Display Matrix
        static void printMatrix() {
            System.out.println("\nKey Matrix:");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(keyMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }

        // Prepare Plaintext
        static String prepareText(String text) {

            text = toUpperCase(removeSpace(text));
            text = text.replace('J', 'I');

            StringBuilder result = new StringBuilder();

            int i = 0;

            while (i < text.length()) {

                char a = text.charAt(i);

                if (i == text.length() - 1) {
                    result.append(a).append('X');
                    break;
                }

                char b = text.charAt(i + 1);

                if (a == b) {
                    result.append(a).append('X');
                    i++;
                } else {
                    result.append(a).append(b);
                    i += 2;
                }
            }

            return result.toString();
        }

        // Find Position
        static int[] findPosition(char ch) {

            if (ch == 'J')
                ch = 'I';

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (keyMatrix[i][j] == ch)
                        return new int[] { i, j };
                }
            }
            return null;
        }

        // Encrypt
        static String encrypt(String text) {

            StringBuilder cipher = new StringBuilder();

            for (int i = 0; i < text.length(); i += 2) {

                char a = text.charAt(i);
                char b = text.charAt(i + 1);

                int[] p1 = findPosition(a);
                int[] p2 = findPosition(b);

                // Same Row
                if (p1[0] == p2[0]) {

                    cipher.append(keyMatrix[p1[0]][(p1[1] + 1) % 5]);
                    cipher.append(keyMatrix[p2[0]][(p2[1] + 1) % 5]);

                }

                // Same Column
                else if (p1[1] == p2[1]) {

                    cipher.append(keyMatrix[(p1[0] + 1) % 5][p1[1]]);
                    cipher.append(keyMatrix[(p2[0] + 1) % 5][p2[1]]);

                }

                // Rectangle Rule
                else {

                    cipher.append(keyMatrix[p1[0]][p2[1]]);
                    cipher.append(keyMatrix[p2[0]][p1[1]]);
                }
            }

            return cipher.toString();
        }

        // Decrypt
        static String decrypt(String cipher) {

            StringBuilder plain = new StringBuilder();

            for (int i = 0; i < cipher.length(); i += 2) {

                char a = cipher.charAt(i);
                char b = cipher.charAt(i + 1);

                int[] p1 = findPosition(a);
                int[] p2 = findPosition(b);

                // Same Row
                if (p1[0] == p2[0]) {

                    plain.append(keyMatrix[p1[0]][(p1[1] + 4) % 5]);
                    plain.append(keyMatrix[p2[0]][(p2[1] + 4) % 5]);

                }

                // Same Column
                else if (p1[1] == p2[1]) {

                    plain.append(keyMatrix[(p1[0] + 4) % 5][p1[1]]);
                    plain.append(keyMatrix[(p2[0] + 4) % 5][p2[1]]);

                }

                // Rectangle Rule
                else {

                    plain.append(keyMatrix[p1[0]][p2[1]]);
                    plain.append(keyMatrix[p2[0]][p1[1]]);
                }
            }

            return plain.toString();
        }

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Key: ");
            String key = sc.nextLine();

            System.out.print("Enter Plaintext: ");
            String plaintext = sc.nextLine();

            generateKeyMatrix(key);

            printMatrix();

            String prepared = prepareText(plaintext);

            System.out.println("\nPrepared Plaintext : " + prepared);

            String cipher = encrypt(prepared);

            System.out.println("Encrypted Text     : " + cipher);

            String decrypted = decrypt(cipher);

            System.out.println("Decrypted Text     : " + decrypted);

            sc.close();
        }
    }
