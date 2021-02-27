package lesson5.homework05;

public class Power {

    private static long elevate(long arg, int pwd) {
        if (pwd == 0)
            return 1;
        else
            return arg * (elevate(arg, --pwd));
    }

    public static void main(String[] args) {
        System.out.println(elevate(2, 3));
    }

}
