import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Practice12 {

    private static final String FILE_NAME = "document.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    writeToFile();
                    break;
                case "2":
                    readFromFile();
                    break;
                case "3":
                    System.out.println("Вихід з редактора. Бувай!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Помилка: Некоректний вибір. Спробуйте ще раз.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("--- Меню текстового редактора ---");
        System.out.println("1. Записати до файлу");
        System.out.println("2. Прочитати увесь вміст файлу");
        System.out.println("3. Вийти з редактора");
        System.out.print("Оберіть опцію: ");
    }

    private static void writeToFile() {
        System.out.print("Введіть рядок для запису: ");
        String line = scanner.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(line + System.lineSeparator());
            System.out.println("Рядок успішно записано!");
        } catch (IOException e) {
            System.out.println("Помилка при записі у файл: " + e.getMessage());
        }
    }

    private static void readFromFile() {
        System.out.println("--- Вміст файлу ---");

        try (FileReader reader = new FileReader(FILE_NAME)) {
            int character;
            String currentLine = "";
            boolean hasContent = false;

            while ((character = reader.read()) != -1) {
                hasContent = true;
                char c = (char) character;

                if (c == '\n') {
                    System.out.print(currentLine + "\n");
                    currentLine = "";
                } else if (c != '\r') {
                    currentLine += c;
                }
            }

            if (!currentLine.isEmpty()) {
                System.out.println(currentLine);
            }

            if (!hasContent) {
                System.out.println("[Файл порожній]");
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу (можливо, він ще не створений): " + e.getMessage());
        }
        System.out.println("-------------------");
    }
}