package com.company;

import com.company.Errors;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputArray {
    public Errors errors;

    // Window constructor
    public InputArray(JFrame frame, String version) {

        this.errors = new Errors(frame, version);
    }

    //Console constructor
    public InputArray(String version) {
        this.errors = new Errors(version);
    }


    public int checkInt(String line) throws NumberFormatException {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            line = errors.checkIntError(line);
            return Integer.parseInt(line);
        }
    }

    public double checkDouble(String line) throws NumberFormatException {
        try {
            return Double.parseDouble(line);
        } catch (NumberFormatException e) {
            line = errors.checkDoubleError(line);
            return Double.parseDouble(line);
        }
    }

    public String[][] toTwoDimensionalArrayString(String[] array) {
        String[][] array2 = new String[array.length][];
        for (int i = 0; i < array.length; i++) {
            array2[i] = array[i].split("\\s+");
        }
        return array2;
    }

    public List<List<String>> toTwoDimensionalListString(String[] array) {

        String[][] twoDimensionalArray = toTwoDimensionalArrayString(array);
        return ArrayToList(twoDimensionalArray);
    }

    public List<List<String>> ArrayToList(String[][] array) {
        List<String> lineList = new ArrayList<>();
        List<List<String>> mainList = new ArrayList<>();
        for (String[] lineArray : array) {
            for (String s : lineArray) {
                lineList.add(s);
            }
            mainList.add(lineList);
            lineList = new ArrayList<>();
        }
        return mainList;
    }

    public TariffsForDirections converterStringToTariffsForDirections(List<String> stringList) {
        int directionCode;//код направления
        String directionsTitle;//название направления
        double pricePerMinute;//цена минуты разговора

        directionCode = checkInt(stringList.get(0));
        directionsTitle = stringList.get(1);
        pricePerMinute = checkDouble(stringList.get(2));
        return new TariffsForDirections(directionCode, directionsTitle, pricePerMinute);
    }

    public List<TariffsForDirections> converterListStringToTariffs(List<List<String>> lists) {
        List<TariffsForDirections> tariffsPlan = new ArrayList<>();

        for (List<String> strings : lists) {
            tariffsPlan.add(converterStringToTariffsForDirections(strings));
        }
        return tariffsPlan;
    }

    public static String[][] converterListStringToArrayString(List<List<String>> lists) {
        String[][] stringsArray = new String[lists.size()][3];
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                if (Errors.workingListStringRedundant(lists, i)) {
                    return new String[0][0];
                }
                stringsArray[i][j] = lists.get(i).get(j);
            }

        }

        return stringsArray;
    }

    public static List<List<String>> converterTariffsForDirectionsToListString(List<TariffsForDirections> tariffsPlan) {
        List<String> newList = new ArrayList<>();
        List<List<String>> listString = new ArrayList<>();

        for (TariffsForDirections tariffsForDirections : tariffsPlan) {

            newList.add(Integer.toString(tariffsForDirections.getDirectionCode()));
            newList.add(tariffsForDirections.getDirectionsTitle());
            newList.add(Double.toString(tariffsForDirections.getPricePerMinute()));

            listString.add(newList);
            newList = new  ArrayList<>();

        }


        return listString;
    }
}
