package org.gsg;

public class Main {
    public static void main(String[] args) {
        final TaxRateProvider provider = new FileBasedTaxRateProvider();
        final NetCalculator calculator = new DefaultNetCalculator(provider);

        final Double netPrice = calculator.calculateNetPrice(100.0, "DE");

        System.out.println("Net price for gross 100.0 and country Germany is : " + round(netPrice));
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}