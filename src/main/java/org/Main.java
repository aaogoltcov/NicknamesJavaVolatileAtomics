package org;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static final int incrementSize = 1;
    static AtomicInteger niceNickname3 = new AtomicInteger(0);
    static AtomicInteger niceNickname4 = new AtomicInteger(0);
    static AtomicInteger niceNickname5 = new AtomicInteger(0);
    static List<Thread> niceNicknameThreads = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        final Thread palindromeThread = new Thread(() -> {
            for (String text : texts) {
                if (NicknameService.isPalindrome(text)) {
                    nickNicknameIncrementor(text);
                }
            }
        });

        palindromeThread.start();
        niceNicknameThreads.add(palindromeThread);

        final Thread lonelyLetter = new Thread(() -> {
            for (String text : texts) {
                if (NicknameService.isLonelyLetter(text)) {
                    nickNicknameIncrementor(text);
                }
            }
        });

        lonelyLetter.start();
        niceNicknameThreads.add(lonelyLetter);

        final Thread increaseLetter = new Thread(() -> {
            for (String text : texts) {
                if (NicknameService.isIncreaseLetter(text)) {
                    nickNicknameIncrementor(text);
                }
            }
        });

        increaseLetter.start();
        niceNicknameThreads.add(increaseLetter);

        for (Thread niceNicknameThread : niceNicknameThreads) {
            niceNicknameThread.join();
        }

        System.out.println("Красивых слов с длиной 3: " + niceNickname3 + " шт");
        System.out.println("Красивых слов с длиной 4: " + niceNickname4 + " шт");
        System.out.println("Красивых слов с длиной 5: " + niceNickname5 + " шт");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void nickNicknameIncrementor(String niceNickname) {
        switch (niceNickname.length()) {
            case 3 -> niceNickname3.addAndGet(incrementSize);
            case 4 -> niceNickname4.addAndGet(incrementSize);
            case 5 -> niceNickname5.addAndGet(incrementSize);
            default -> {
            }
        }
    }
}