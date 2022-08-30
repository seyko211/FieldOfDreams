import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Random;

public class Main {
        static String word;
        static ArrayList<String> words = new ArrayList<>();
        public static void main(String[] args) throws IOException {
            Scanner wordsFile = new Scanner(new File("C:/Users/Seyko/IdeaProjects/FieldOfDreams/src/wordsList.txt"));
            do{
                words.add(wordsFile.nextLine());
            } while(wordsFile.hasNextLine());
            word = words.get(new Random().nextInt(words.size()));
            Scanner input = new Scanner(System.in);

            System.out.println("Угадай слово");
            int lengthWord = word.length();
            int tryCount = 0;
            int score = 0;
            String maskWord = "-".repeat(lengthWord);
            System.out.println(maskWord);

            do {
                System.out.println("\nВведите букву");
                char c = input.next().charAt(0);
                tryCount++;
                if (word.indexOf(c) >= 0) {
                    System.out.println("Удача");
                    for (char elem : word.toCharArray()) {
                        if (elem == c) {
                            maskWord = replaceMaskLetter(c, maskWord);
                            score += 10;
                        }
                    }
                    System.out.print(maskWord);
                } else {
                    System.out.println("Промах, поробуй еще раз");
                    System.out.println(maskWord);
                    score -= 5;
                }
            } while (maskWord.contains("-"));
            System.out.printf("\nПоздравляем ты выйграл со счетом %d! (%d попыток)", score, tryCount);
        }

        public static String replaceMaskLetter(char c, String maskWord) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == c) {
                    stringBuilder.append(c);
                } else if (maskWord.charAt(i) != '-') {
                    stringBuilder.append(maskWord.charAt(i));
                } else {
                    stringBuilder.append("-");
                }
            }
            return stringBuilder.toString();
        }
    }