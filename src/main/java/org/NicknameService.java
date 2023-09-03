package org;

public class NicknameService {
    public static boolean isPalindrome(String nickname) {
        StringBuilder reverseNickname = new StringBuilder();
        boolean isPalindrome = false;

        for (int i = nickname.length() - 1; i >= 0; i--) {
            reverseNickname.append(nickname.charAt(i));
        }

        if (nickname.contentEquals(reverseNickname)) {
            isPalindrome = true;
        }

        return isPalindrome;
    }

    public static boolean isLonelyLetter(String nickname) {
        char previousLetter = 0;
        boolean isLonelyLetter = true;

        for (int index = 0; index < nickname.length(); index++) {
            if (index != 0) {
                if (nickname.charAt(index) != previousLetter) {
                    isLonelyLetter = false;

                    return isLonelyLetter;
                }
            }

            previousLetter = nickname.charAt(index);
        }

        return isLonelyLetter;
    }

    public static boolean isIncreaseLetter(String nickname) {
        char previousLetter = 0;
        boolean isIncreaseLetter = true;

        for (int index = 0; index < nickname.length(); index++) {
            if (index != 0) {
                if (nickname.charAt(index) < previousLetter) {
                    isIncreaseLetter = false;

                    return isIncreaseLetter;
                }
            }

            previousLetter = nickname.charAt(index);
        }

        return isIncreaseLetter;
    }
}
