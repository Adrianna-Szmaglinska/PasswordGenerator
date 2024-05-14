import java.security.SecureRandom;


public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+";

    public static void main(String[] args) {
        int length = 12; 
        String generatedPassword = generatedPassword(length);
        int rating = getPasswordRating(generatedPassword);
        System.out.println("Generated password: " + generatedPassword);
    }

    public static String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Add at least one character from each category
        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        // Add remaining characters randomly
        for (int i = 4; i < length; i++) {
            String charSet = UPPER + LOWER + DIGITS + SPECIAL;
            password.append(charSet.charAt(random.nextInt(charSet.length())));
        }

        // Shuffle the characters to make the password more secure
        String shuffledPassword = shuffle(password.toString());

        return shuffledPassword;
    }

    private static String shuffle(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = (int) (Math.random() * characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    private static int getPasswordRating(String password) {
        int rating = 0;
        if (password.matches(".*[A-Z].*")) {
            rating += 2;
        }
        if (password.matches(".*[a-z].*")) {
            rating += 2;
        }
        if (password.matches(",*[!@#$%^&*()-_=+].*")) {
            rating += 2;
        }
        if (password.length() >=12) {
            rating += 2;
        }
        return rating;
    }
}
