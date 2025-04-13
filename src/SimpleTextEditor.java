import java.io.*;
import java.util.Scanner;

public class SimpleTextEditor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Записати в файл");
            System.out.println("2. Прочитати файл");
            System.out.println("3. Вихід");
            System.out.print("Виберіть дію: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                writeToFile(scanner);
            } else if (choice == 2) {
                readFromFile();
            } else if (choice == 3) {
                System.out.println("Вихід із програми.");
                break;
            } else {
                System.out.println("Невірний ввід. Спробуйте ще раз.");
            }
        }
        scanner.close();
    }

    public static void writeToFile(Scanner scanner) {
        System.out.print("Введіть строку для запису в файл: ");
        String text = scanner.nextLine();

        try (FileWriter writer = new FileWriter("data.txt", true)) {
            writer.write(text + "\n");
            System.out.println("Запис виконано успішно.");
        } catch (IOException e) {
            System.out.println("Помилка при запису в файл.");
        }
    }

    public static void readFromFile() {
        try (FileReader reader = new FileReader("data.txt")) {
            System.out.println("Вміст файлу:");
            int ch;
            char[] line = new char[1000];
            int index = 0;
            while ((ch = reader.read()) != -1) {
                if ((char) ch == '\n') {
                    for (int i = 0; i < index; i++) {
                        System.out.print(line[i]);
                    }
                    System.out.println();
                    index = 0;
                    if (index < line.length) {
                        line[index] = (char) ch;
                        index++;
                    }
                }
            }
            if (index > 0) {
                for (int i = 0; i < index; i++) {
                    System.out.print(line[i]);
                }
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено. Спочатку додайте дані.");
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу.");
        }
    }
}
