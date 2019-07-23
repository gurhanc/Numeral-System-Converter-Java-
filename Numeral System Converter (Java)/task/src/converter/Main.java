package converter;


import java.util.Scanner;

public class Main {

    public static boolean checkDecimalPoint(String number) {
        if (number.contains(".")) {
            return true;
        }
        return false;
    }

    public static long convertToDecimalNoFraction(String number, int baseFrom) {
        if (baseFrom == 1) {
            return Long.parseLong(Integer.toString(number.length()));
        }
        return Long.parseLong(number, baseFrom);
    }

    public static String convertToBaseNoFraction(long number, int baseTo) {
        if (baseTo == 1) {
            String num = "";
            int i;
            for (i=0;i<number;i++) {
                num += "1";
            }
            return num;
        }
        return Long.toString(number, baseTo);
    }

    public static String fractionConvertedToDecimal(String fraction, int base) {
        double result = 0;
        int i;
        String bases = "0123456789abcdefghijklmnopqrstuvwxyz";
        for (i=0;i<fraction.length();i++) {
            int number = bases.indexOf(fraction.charAt(i));
            result += (number * (Math.pow(base, -(i+1))));
        }
        return Double.toString(result);
    }

    public static String fractionConvertedToBase(String fraction, int base) {
        String result = "";
        String bases = "0123456789abcdefghijklmnopqrstuvwxyz";
        double num = Double.parseDouble(fraction);
        int i;
        for (i=0;i<5;i++) {
            result += bases.charAt(((int) Math.floor(num*base)));
            num = num*base - (int) Math.floor(num*base);
        }
        return result;
    }

    public static double convertToDecimalWithFraction(String number, int baseFrom) {
        int indexOfDecimal = number.indexOf(".");
        String integer = number.substring(0,indexOfDecimal);
        String fraction = number.substring(indexOfDecimal+1);
        String integerPart = Long.toString(convertToDecimalNoFraction(integer, baseFrom));   /* convert integer part to decimal */
        String fractionPart =  fractionConvertedToDecimal(fraction, baseFrom);
        return Double.parseDouble(integerPart) + Double.parseDouble(fractionPart);
    }

    public static String convertToBaseWithFraction(double number, int baseTo) {
        String num = Double.toString(number);
        int indexOfDecimal = num.indexOf(".");
        String integer = num.substring(0,indexOfDecimal);
        String fraction = num.substring(indexOfDecimal+1);
        fraction = "0." + fraction;
        String integerPart = convertToBaseNoFraction(Long.parseLong(integer), baseTo);
        String fractionPart = fractionConvertedToBase(fraction, baseTo);
        return integerPart+"."+fractionPart;
    }

    public static void main(String[] args) {
        double output;
        String result;
        Scanner scanner = new Scanner(System.in);
        try {
            int sourceRadix = Integer.parseInt(scanner.nextLine());
            String sourceNumber = scanner.nextLine();
            int targetRadix = Integer.parseInt(scanner.nextLine());
            if (sourceRadix < 1 || sourceRadix > 36 || targetRadix < 1 || targetRadix > 36) {
                throw new Exception();
            }
            if (checkDecimalPoint(sourceNumber) == true) {
                output = convertToDecimalWithFraction(sourceNumber, sourceRadix);
                result = convertToBaseWithFraction(output, targetRadix);
            }
            else {
                output = convertToDecimalNoFraction(sourceNumber, sourceRadix);
                result = convertToBaseNoFraction((long) (output), targetRadix);
            }
            System.out.println(result);
        }
        catch (Exception e) {
            System.out.println("error "+e);
        }
    }
}