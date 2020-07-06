package com.company;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkWithFile {
    File newFolder;

    public String getPath() {
        return newFolder.getAbsolutePath().substring(0, newFolder.getAbsolutePath().lastIndexOf(File.separator));
    }

    public void setNewFolder(File newFolder) {
        this.newFolder = newFolder;
    }

    public String[] fileToString(String nameFile) throws FileNotFoundException {
        FileReader fileReader = new FileReader(nameFile);
        Scanner scanFile = new Scanner(fileReader);
        List<String> listArray = new ArrayList<>();
        while (scanFile.hasNextLine()) {
            listArray.add(scanFile.nextLine());
        }
        return listArray.toArray(new String[0]);
    }


    public void saveArrayToFile(String[][] arr, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (String[] ints : arr) {
                for (String j : ints) {
                    bw.write(j + " ");
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveListToFile(List<List<Integer>> list, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));

            for (List<Integer> integers : list) {
                for (Integer number : integers) {
                    bw.write(number + " ");
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
