package org.genesiscode.practicethree.utils;

import java.util.ArrayList;
import java.util.List;

public class Tool {

    public static String addZeros(String number, int d) {
        int sizeExpected = (d % 2 == 0) ? d * 2 : d * 2 - 1;
        int sizeActual = number.length();
        StringBuilder builder = new StringBuilder(number);
        while (sizeActual < sizeExpected) {
            builder.insert(0, "0");
            sizeActual++;
        }
        return builder.toString();
    }

    public static String extractNumber(String number, int d) {
        int countToExtract = d / 2;
        return number.substring(countToExtract, number.length() - countToExtract);
    }

    public static Double addZeroAndPoint(String number) {
        return Double.valueOf(String.format("0.%s", number));
    }

    public static boolean areRelativelyPrime(int numberOne, int numberTwo) {
        boolean isRelativelyPrime;
        if (isNumberPrime(numberOne) || isNumberPrime(numberTwo)) {
            isRelativelyPrime = true;
        } else if (areConsecutiveNumbers(numberOne, numberTwo)) {
            isRelativelyPrime = true;
        } else {
            List<Integer> multiplesNumberOne = foundMultiples(numberOne);
            List<Integer> multiplesNumberTwo = foundMultiples(numberTwo);
            long count = multiplesNumberOne.stream()
                    .filter(multiplesNumberTwo::contains)
                    .count();
            isRelativelyPrime = count == 0;
        }
        return isRelativelyPrime;
    }

    private static boolean isNumberPrime(int number) {
        boolean isPrime = true;
        int i = 2;
        while (i < number / 2 && isPrime) {
            if (number % i == 0) {
                isPrime = false;
            }
            i++;
        }
        return isPrime;
    }

    private static boolean areConsecutiveNumbers(int numberOne, int numberTwo) {
        return numberOne + 1 == numberTwo || numberOne - 1 == numberTwo;
    }

    private static List<Integer> foundMultiples(int number) {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 2;
        int numberClone = number;
        while (i <= number) {
            if (number == i || number % i == 0) {
                if (! list.contains(i)) {
                    list.add(i);
                }
                number /= i;
            } else {
                i++;
            }
        }
        if (list.contains(numberClone)) {
            list.remove(numberClone);
        }
        return list;
    }
}
