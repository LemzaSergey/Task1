package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.company.Tariffs;

public class Main {

    public static void main(String[] args) {


        if (args.length > 0) {
            switch (args[0]) {
                case "-window":
                    new MainFrame();
                    break;

                default:
                    System.exit(-13);
            }
        }else
        {
            System.exit(-15);
        }
    }
}
