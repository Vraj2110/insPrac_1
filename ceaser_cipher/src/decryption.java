import java.util.Scanner;

public class decryption {


    public static String decrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {

            if (Character.isUpperCase(ch)) {
                char c = (char) ((ch - 'A' - shift + 26) % 26 + 'A');
                result.append(c);
            }
            else if (Character.isLowerCase(ch)) {
                char c = (char) ((ch - 'a' - shift + 26) % 26 + 'a');
                result.append(c);
            }
            else {
                result.append(ch); // Keep spaces and symbols unchanged
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the encrypted message: ");
        String encryptedMessage = sc.nextLine();

        System.out.print("Enter the shift value: ");
        int shift = sc.nextInt();

        String plainText = decrypt(encryptedMessage, shift);

        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Decrypted Message: " + plainText);

        sc.close();
    }
}