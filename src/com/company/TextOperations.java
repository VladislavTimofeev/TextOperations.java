package com.company;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Cоздать приложение, разбирающее текст (текст хранится в строке) и позволяющее выполнять с текстом три различных
 * операции: отсортировать абзацы по количеству предложений; в каждом предложении отсортировать слова по длине;
 * отсортировать лексемы в предложении по убыванию количества вхождений заданного символа, а в случае равенства – по
 * алфавиту.
 */

public class TextOperations {

    public static void main(String[] args) {
        String text = "Первый абзац. Три предложения. Понял?!\n" +
                "Второй абзац. Пять предложений. Это третье предложение. Это четвертое. И это пятое...\n" +
                "Третий абзац и всего одно предложение.\n" +
                "Четвертый абзац и семь предложений. Второе. Третье. Четвертое. Пятое. Шестое? Седьмое.\n" +
                "Пятый абзац и снова одно предложение." +
                "Шестой абзац. Привет, приветы, приветули.";
        makeOperation(text);
    }

    public static void makeOperation(String text) {
        Scanner scanner = new Scanner(System.in);
        String start = "введите \"1\" для сортировки абзацев по количеству предложений. \n" +
                "введите \"2\" для сортировки в каждом предложении слов по длине. \n" +
                "Для завершения работы приложения введите \"exit\"\n";
        String choice = "";
        System.out.println(start);
        while (!choice.equalsIgnoreCase("exit")) {
            System.out.println("Введите команду: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    sortParagraphs(text);
                    break;
                case "2":
                    sortWords(text);
                    break;
                case "exit":
                    System.out.println("\nЗавершение работы");
                    break;
                default:
                    System.out.println("Неверная команда. Попробуйте еще раз.\n");
            }
        }
    }

    private static void sortParagraphs(String text) {
        String[] paragraphs = text.split("\n");
        int[] counterSentences = new int[paragraphs.length];
        int maxLengthParagraph = 0;
        for (int i = 0; i < paragraphs.length; i++) {
            String[] sentences = splitSentences(paragraphs[i]);
            counterSentences[i] = sentences.length;
            if (maxLengthParagraph < sentences.length) {
                maxLengthParagraph = sentences.length;
            }
        }
        for (int i = 1; i <= maxLengthParagraph; i++) {
            for (int j = 0; j < counterSentences.length; j++) {
                if (i == counterSentences[j]) {
                    System.out.println(paragraphs[j]);
                }
            }
        }
        System.out.println();
    }

    private static void sortWords(String text) {
        String[] paragraphs = text.split("\n");
        for (String paragraph : paragraphs) {
            String[] sentences = splitSentences(paragraph);
            for (String sentence : sentences) {
                String[] words = splitWords(sentence);

                //сортировка слов
                for (int k = words.length - 1; k >= 0; k--) {
                    for (int m = 0; m < k; m++) {
                        if (words[m].length() > words[m + 1].length()) {
                            String tmp = words[m];
                            words[m] = words[m + 1];
                            words[m + 1] = tmp;
                        }
                    }
                }

                //вывод слов
                for (String word : words) {
                    System.out.print(word + " ");
                }
                System.out.print("\b. ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static String [] splitSentences(String text) {
        Pattern pattern = Pattern.compile("\\.*[.!?]\\s*");
        return pattern.split(text);
    }
    private static String [] splitWords(String sentence) {
        Pattern pattern = Pattern.compile("\\s*(\\s|,|;|:)\\s*");
        return pattern.split(sentence);
    }
}
