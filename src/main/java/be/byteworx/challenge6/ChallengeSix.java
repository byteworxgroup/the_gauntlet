package be.byteworx.challenge6;

public class ChallengeSix {
    public boolean palindrome(int number) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(number));
        return stringBuilder.toString().contentEquals(stringBuilder.reverse());
    }
}
