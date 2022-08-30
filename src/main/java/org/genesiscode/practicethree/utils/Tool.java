package org.genesiscode.practicethree.utils;

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

    public static boolean isRelativePrime(int number, int module) {
        return true;
    }
}
