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
        String result = getResult(a, b);
        writeResult(a, b, result);
    }

    public static void generateData() {
        Random r = new Random();
        int a = r.nextInt(MAX_NUMBER + 1);
        int b = r.nextInt(MAX_POW * 2 + 1) - MAX_POW;
        System.out.printf("a: %s, b: %s \n", a, b);

        boolean isAFirst = r.nextInt(2) == 0;
        String data;
        if (isAFirst) data = "a " + a + "\nb " + b;
        else data = "b " + b + "\na " + a;

        try (FileWriter fw = new FileWriter(FILE_INPUT, false)) {
            fw.write(data);
            fw.append("\n");
            fw.flush();
            System.out.println("Файл с исходными значениями успешно записан");
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
                if (line.startsWith("a")) data[0] = number;
                else data[1] = number;
            }
            br.close();
            System.out.println("Файл успешно прочитан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public static String getResult(int a, int b) {
        if (a == 0 && b == 0) return "не определён";
        if (b == 0) return "1";
        if (b > 0) return String.valueOf(getPow(a, b));
        else return String.valueOf(1 / getPow(a, b * -1));
    }

    public static double getPow(int a, int b) {
        if (b == 1) return a;
        if (b % 2 == 0) return getPow(a * a, b / 2);
        else return a *= getPow(a * a, (b - 1) / 2);
    }

    public static void writeResult(int a, int b, String result) {
        try (FileWriter fw = new FileWriter(FILE_OUTPUT, false)) {
            String message = "Результат возведения числа " + a + " в степень " + b + "\n";
            fw.write(message);
            fw.write(result);
            fw.append("\n");
            fw.flush();
            System.out.println(message + "записан в файл " + FILE_OUTPUT);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int[] readFile(String name) {
        int[] num = new int[2];
        try {
            BufferedReader br = new BufferedReader(new FileReader(name));
            String str;
            while ((str = br.readLine()) != null) {
                if (str.charAt(0) == 'a') {
                    num[0] = Integer.parseInt(str.substring(3, str.length()));
                }
                if (str.charAt(0) == 'b') {
                    num[1] = Integer.parseInt(str.substring(3, str.length()));
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
        return num;
    }
}