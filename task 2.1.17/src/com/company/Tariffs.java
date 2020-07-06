package com.company;

import java.util.ArrayList;
import java.util.List;

public class Tariffs {
    //private List<TariffsForDirections> tariffsPlanList;

    public Tariffs(TariffsForDirections tariffsForDirections) {

    }

    /*public Tariffs(List<TariffsForDirections> tariffsPlanList) {
        this.tariffsPlanList = tariffsPlanList;
    }*/

    public static void printTariffs(List<TariffsForDirections> tariffsPlanList, int number) {
        if (tariffsPlanList.size() < 1) {
            System.out.print(-1);
        } else {
            System.out.print(tariffsPlanList.get(number).getDirectionCode());
            System.out.print(" ");
            System.out.print(tariffsPlanList.get(number).getDirectionsTitle());
            System.out.print(" ");
            System.out.print(tariffsPlanList.get(number).getPricePerMinute());
        }
    }


    public static List<TariffsForDirections> addTariffs(TariffsForDirections tariffsForDirections, List<TariffsForDirections> tariffsPlanList) {


        tariffsPlanList.add(tariffsForDirections);
        return tariffsPlanList;
    }


    public static List<TariffsForDirections> removeTariffs(List<TariffsForDirections> tariffsPlanList, int number) {
        if (tariffsPlanList.size() < 1) {
            System.out.print(-1);
            return tariffsPlanList;
        } else {
            tariffsPlanList.remove(number);
            return tariffsPlanList;
        }
    }

    public static List<TariffsForDirections> setTariffs(TariffsForDirections tariffsForDirections, List<TariffsForDirections> tariffsPlanList, int number) {
        if (tariffsPlanList.size() < 1) {
            System.out.print(-1);
            return tariffsPlanList;
        } else {
            tariffsPlanList.set(number, tariffsForDirections);
            return tariffsPlanList;
        }
    }


    public static TariffsForDirections getTariffsForDirections(List<TariffsForDirections> tariffs, int number) {
        TariffsForDirections TariffsForDirections;
        TariffsForDirections = tariffs.get(number);
        return TariffsForDirections;
    }

    public static TariffsForDirections searchElementToTariffsPlan(List<TariffsForDirections> tariffsPlan, int directionCode) {
        for (TariffsForDirections tariffsForDirections : tariffsPlan) {

            if (tariffsForDirections.getDirectionCode() == directionCode) {
                return tariffsForDirections;
            }
        }
        return new TariffsForDirections(-1, "", -1);
    }
    public static int searchNumberToTariffsForDirections(List<TariffsForDirections>tariffsPlan ,TariffsForDirections tariffsForDirections){
        int number=-1;
        for (TariffsForDirections tariffsForDirectionsList : tariffsPlan){
            number++;
            if (tariffsForDirectionsList.getDirectionCode()==tariffsForDirections.getDirectionCode()){return number;}

        }
        return number;
    }

    public static double callCostToTariffsForDirections(TariffsForDirections tariffsForDirections, int[] callDurationArray) {
        int callDuration = callDurationArray[0];
        if (callDuration < 6) {
            return 0;
        }
        int callDurationMinutes;
        if (callDuration % 60 > 0) {
            callDurationMinutes = callDuration / 60 + 1;
        } else {
            callDurationMinutes = callDuration / 60;
        }
        double callCost = tariffsForDirections.getPricePerMinute() * callDurationMinutes;

        return callCost;
    }

    public static boolean searchElement(List<TariffsForDirections> tariffsPlan, TariffsForDirections tariffsForDirections) {
        for (TariffsForDirections tariffsForDirectionsList : tariffsPlan) {

            if (tariffsForDirectionsList.getDirectionCode() == tariffsForDirections.getDirectionCode()) {
                return true;
            }
        }
        return false;
    }

}

