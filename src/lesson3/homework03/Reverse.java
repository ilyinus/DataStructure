package lesson3.homework03;

import lesson3.stack.IStack;
import lesson3.stack.StackImpl;

public class Reverse {
    private final static String[] arr = {
            "Компьютер",
            "Слон",
            "Стул",
            "Стакан"
    };

    public static void main(String[] args) {
        IStack<Character> stack = new StackImpl<>(1000);

        for (String string : arr) {
            for (char ch : string.toCharArray()) {
                stack.push(ch);
            }
            while (!stack.isEmpty()) {
                System.out.print(stack.pop());
            }
            System.out.println();
        }
        
    }

}
