import java.util.Scanner;

public class monoalphabatic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a plain text:");
        String plainText = sc.nextLine();
        char[] cipherChar = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
        String encryptedText = "";

        for (char c : plainText.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                encryptedText += cipherChar[c - 'a'];
            } else {
                encryptedText += c;
            }
        }

        System.out.println("Original:  " + plainText);
        System.out.println("Encrypted: " + encryptedText);
    }
}