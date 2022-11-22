import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Program {

    private static final String FILE_INPUT = "input.txt";
    private static final String FILE_OUTPUT = "output.txt";
    private static final int MAX_NUMBER = 10;
    private static final int MAX_POW = 5;

    public static void main(String[] args) {
        generateData();
        int[] data = readData();
        int a = data[0];
        int b = data[1];
        Integer result = getResult(a, b);
        writeResult(a, b, result);
    }

    public static void generateData() {
        Random r = new Random();
        int a = r.nextInt(MAX_NUMBER + 1);
        int b = r.nextInt(MAX_POW + 1);
        System.out.printf("a: %s, b: %s \n", a, b);

        boolean isAFirst = r.nextInt(2) == 0;
        String data;
        if (isAFirst) {
            data = "a " + a + "\nb " + b;
        } else {
            data = "b " + b + "\na " + a; 
        }

        try (FileWriter fw = new FileWriter(FILE_INPUT, false)) {
            fw.write(data);
            fw.append("\n");
            fw.flush();
            System.out.println("Файл успешно записан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int[] readData() {
        int[] data = new int[2];
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_INPUT))) {
            String line;
            while ((line = br.readLine()) != null) {
                int number = Integer.parseInt(line.substring(2, line.length()));
                if (line.startsWith("a")) {
                    data[0] = number;
                } else {
                    data[1] = number;
                }
            }
            br.close();
            System.out.println("Файл успешно прочитан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public static Integer getResult(int a, int b) {
        if (a == 0 && b == 0) return null;
        return (int) Math.pow(a, b);
    }

    public static void writeResult(int a, int b, Integer result) {
        try (FileWriter fw = new FileWriter(FILE_OUTPUT, false)) {
            String message = "Результат возведения числа " + a + " в степень " + b + "\n";
            fw.write(message);
            if (result == null) fw.write("не определён");
            else fw.write(String.valueOf(result));
            fw.append("\n");
            fw.flush();
            System.out.println(message + "записан в файл " + FILE_OUTPUT);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}