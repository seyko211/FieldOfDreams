import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Random;

public class Main {
        static String word; // Загадываемое слово
        static String quest; // Вопрос к слову
        static ArrayList<String> words = new ArrayList<>();// Список возможных загадываемых слов
        static ArrayList<String> questes = new ArrayList<>(); // Список вопросов к загадываемым словам
        public static void main(String[] args) throws IOException {
            Scanner wordsFile = new Scanner(new File("C:/Users/Seyko/IdeaProjects/FieldOfDreams/src/wordsList.txt"));
            do{
                String[] pair;
                pair = wordsFile.nextLine().split(";");
                words.add(pair[0]);
                questes.add(pair[1]);
            } while(wordsFile.hasNextLine());
            int index = new Random().nextInt(words.size());
            word = words.get(index);
            quest = questes.get(index);
            Scanner input = new Scanner(System.in);

            System.out.printf("Угадай слово:\n%s\n", quest);
            int lengthWord = word.length();
            int tryCount = 3;
            String maskWord = "-".repeat(lengthWord);
            System.out.println(maskWord);

            do {
                System.out.printf("\nВведите букву\t[Осталось %d попыток]\n", tryCount);
                char c = input.next().charAt(0);
                if (c < 'А' || c > 'я') System.out.println("Используй только русские буквы!");
                if (word.indexOf(c) >= 0) {
                    System.out.println("Удача");
                    for (char elem : word.toCharArray()) {
                        if (elem == c) {
                            maskWord = replaceMaskLetter(c, maskWord);
                        }
                    }
                    System.out.print(maskWord);
                } else {
                    tryCount--;
                    System.out.println("Промах, поробуй еще раз");
                    System.out.println(maskWord);
                }
            } while (tryCount > 0 && maskWord.contains("-"));
            System.out.println(tryCount == 0 ? "\nТы проиграл. Попробуй еще раз." : "\nПоздравляем, ты выйграл!");
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