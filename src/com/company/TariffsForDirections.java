package com.company;

import java.util.List;

public class TariffsForDirections {
    private int directionCode;//код направления
    private String directionsTitle;//название направления
    private double pricePerMinute;//цена минуты разговора

    public TariffsForDirections(int directionCode, String directionsTitle, double pricePerMinute) {
        this.directionCode = directionCode;
        this.directionsTitle = directionsTitle;
        this.pricePerMinute = pricePerMinute;
    }

    public int getDirectionCode() {
        return directionCode;
    }

    public String getDirectionsTitle() {
        return directionsTitle;
    }

    public double getPricePerMinute() {
        return pricePerMinute;
    }

    public TariffsForDirections getTariffsForDirections(int directionCode, String directionsTitle, double pricePerMinute) {
        TariffsForDirections TariffsForDirections = new TariffsForDirections(directionCode, directionsTitle, pricePerMinute);
        return TariffsForDirections;
    }

    public static void printTariffsForDirections(TariffsForDirections tariffsForDirections) {
        System.out.print(tariffsForDirections.getDirectionCode());
        System.out.print(" ");
        System.out.print(tariffsForDirections.getDirectionsTitle());
        System.out.print(" ");
        System.out.println(tariffsForDirections.getPricePerMinute());

    }


}
