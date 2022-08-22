
import java.util.Arrays;
import java.util.Scanner;

public class StringCalculator {
    static int number;
    static char operation;
    static String result;

    public static void main (String[] args){
        Scanner reader = new Scanner(System.in);
        System.out.println("Калькулятор умеет выполнять операции сложения строк, вычитания строки из строки, " +
                "умножения строки на число и деления строки на число: a + b, a - b, a * b, a / b, " +
                "калькулятор принимает на вход только целые числа от 1 до 10 включительно, не более, и строки длинной не более 10 символов, " +
                "первым аргументом выражения, подаваемого на вход, должна быть строка.");
        String userInput = reader.nextLine();
        char[] userChar = new char[26];
        for (int a =0; a < userInput.length(); a++){
            userChar[a] = userInput.charAt(a);
            if (userChar[a] == '+'){
                operation = '+';
            }
            if (userChar[a] == '-'){
                operation = '-';
            }
            if (userChar[a] == '*'){
                operation = '*';
            }
            if (userChar[a] == '/'){
                operation = '/';
            }
        }
        String userCharString = String.valueOf(userChar);
        String[] block = userCharString.split("[+-/*\"]");
        try {
            block[1] = String.valueOf(block[1]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Строка не является математической операцией");
            return;
        }
        String primitive0 = block[1];
        String primitive1;
        if (block.length > 6){
            throw new ArrayIndexOutOfBoundsException("Неверный формат значений");
        }
        if (block.length == 6) {
            primitive1 = block[4];
            String stringGap = primitive1.trim();
            result = Calc.calcString(primitive0, stringGap, operation);
        } else {
            try {
                block[3] = String.valueOf(block[3]);
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Неверный формат значений");
                return;
            }
            primitive1 = block[3];
            String stringGap = primitive1.trim();
            try {
                number = Integer.parseInt(stringGap);
            } catch (NumberFormatException e){
                System.out.println("Неверный формат значений");
                return;
            }
            result = Calc.calcStrNum(primitive0, number, operation);
        }
        if (result.length() >40){
            String finalResult = result.substring(0,40);
            System.out.println((char)34 + finalResult + "..." + (char)34);
        } else {
            System.out.println((char) 34 + result + (char) 34);
        }
    }
    static class Calc{
        static String calcString (String string1, String string2, char operation1) {
            String exception = "Неверный формат значений";
            if (string1.length() >10 || string2.length() >10) {
                throw new ArrayIndexOutOfBoundsException("Неверный формат значений");
            }
            switch (operation1) {
                case '+' -> result = string1 + string2;
                case '-' -> {
                    String[] subString = string1.split(string2);
                    String result1 = Arrays.toString(subString);
                    String result2 = result1.replaceAll("\\s*,\\s*", "");
                    result = result2.substring(1, result2.length()-1);
                }
                case '*', '/' -> {
                    return exception;
                }
            }
            return result;
        }
        static String calcStrNum (String string2, int number2, char operetion2){
            String exception = "Неверный формат значений";
            if (string2.length() >10 || number2 >10 || number2 <=0){
                throw new ArrayIndexOutOfBoundsException("Неверный формат значений");
            }
            switch (operetion2) {
                case '*' -> result = string2.repeat(number2);
                case '/' -> {
                    int divNum = string2.length() / number2;
                    if (string2.length() == number2) {
                        result = string2.substring(0, 1);
                    } else {
                        result = string2.substring(0, divNum);
                    }
                }
                case '+', '-' -> {
                    return exception;
                }
            }
            return result;
        }
    }
}
