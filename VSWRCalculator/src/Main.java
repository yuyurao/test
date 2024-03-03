//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.lang.Math;

public class Main {

    public static void main(String[] args) {
        double returnLoss = 6.4;

        // Calculate the exponent part
        double exponentValue = Math.pow(10, -returnLoss / 20);

        // Calculate the whole formula
        double result = (1 + exponentValue) / (1 - exponentValue);

        // Print the result
        System.out.println(result);
    }
}