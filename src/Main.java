import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение (Римские числа принимаются только в верхнем регистре): ");
        String exp = scn.nextLine();
        String result = calc(exp);
        System.out.println(result);
    }

    public static String calc(String input) throws Exception {
        ConverterRoman converter = new ConverterRoman();
        Character[] actions = {'+', '-', '/', '*'};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        int actionIndex=-1;
        int operatorCount=0;
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < actions.length; j++) {
                if (input.charAt(i) == actions[j]) {
                    actionIndex = j;
                    operatorCount++;
                    break;
                }
            }
        }
        if(actionIndex==-1){
            throw new Exception("Некорректное выражение");
        }
        if(operatorCount>1){
            throw new Exception("Некорректное выражение");
        }


        String[] data = input.split(regexActions[actionIndex]);
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a,b;
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            }else{
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            if(a<1 || a>10){
                throw new Exception("Ошибка! Принимаются числа от 1 до 10 включительно.");
            }
            if(b<1 || b>10){
                throw new Exception("Ошибка! Принимаются числа от 1 до 10 включительно.");
            }
            int result;
            switch (actions[actionIndex]){
                case '+':
                    result = a+b;
                    break;
                case '-':
                    result = a-b;
                    break;
                case '*':
                    result = a*b;
                    break;
                default:
                    result = a/b;
                    break;
            }
            if(isRoman){
                if(result<1){
                    throw new Exception("Ошибка! Римские числа могут быть только положительными.");
                }
                return converter.intToRoman(result);
            }
            else{
                return String.valueOf(result);
            }
        }else{
            throw new Exception("Ошибка! Формат чисел должен быть одинаковым.");
        }


    }
}