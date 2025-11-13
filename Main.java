public class Main {
    public static void main(String[] args) {
        String message = "AABABCDABDABAAA";
        String pattern = "AB";
        int count = 0;
        for (int i = 0; i < message.length() - pattern.length() + 1; i++) {
            for (int j = 0; j < pattern.length(); j++) {
                if (message.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
                if (j == pattern.length() - 1) {
                    count++;
                }
            }
        }
        System.out.println("Count of '" + pattern + "' in the message: " + count);
    }
}
