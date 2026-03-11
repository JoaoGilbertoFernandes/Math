package br.com.math;

public class MathUtils {

    public static long factorial(long n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n % 2 == 0 && n != 2) return false;
        for (int i = 3; i <= n / i; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static boolean isPerfect(long n) {
        long sum = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum == n;
    }
}
