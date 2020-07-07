package com.company;

import ru.vsu.cs.util.JTableUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private InputArray inputArray = new InputArray("window");
    private WorkWithFile workWithFile = new WorkWithFile();
    private List<TariffsForDirections> tariffsPlan = new ArrayList<>();
    private JFrame Panel;
    private JPanel mainPanel;
    private JTable inputTable;
    private JTable tableInt;
    private JTable tableString;
    private JTable tableDouble;
    private JTable searchTable;
    private JTable callDurationTable;
    private JButton loadButton;
    private JButton saveButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton setButton;
    private JButton searchButton;
    private JButton calculateCallCostButton;
    private JTable tableCallCost;


    public MainFrame() {
        this.setTitle("Tariffs");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        JTableUtils.initJTableForArray(inputTable, 140, false, false, false, false);
        JTableUtils.initJTableForArray(searchTable, 140, false, false, false, false);
        JTableUtils.initJTableForArray(tableInt, 140, false, false, false, false);
        JTableUtils.initJTableForArray(tableString, 140, false, false, false, false);
        JTableUtils.initJTableForArray(tableDouble, 140, false, false, false, false);
        JTableUtils.initJTableForArray(callDurationTable, 140, false, false, false, false);
        JTableUtils.initJTableForArray(tableCallCost, 140, false, false, false, false);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("File(.txt)", "txt");
                fileOpen.addChoosableFileFilter(filter);
                int ret = fileOpen.showDialog(null, "Открыть файл");

                try {
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file = fileOpen.getSelectedFile();
                        String nameFile = file.getPath();
                        List<List<String>> workingListString = inputArray.toTwoDimensionalListString(workWithFile.fileToString(nameFile));
                        String[][] workingArrayString = InputArray.converterListStringToArrayString(workingListString);
                        JTableUtils.writeArrayToJTable(inputTable, workingArrayString);

                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } /*catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
                }*/ catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                }
            }

        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooserSave = new JFileChooser();
                if (fileChooserSave.showSaveDialog(Panel) == JFileChooser.APPROVE_OPTION) {
                    try {
                        String[][] arrayString = JTableUtils.readStringMatrixFromJTable(inputTable);

                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        workWithFile.saveArrayToFile(arrayString, file);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String[][] arrayString = JTableUtils.readStringMatrixFromJTable(inputTable);
                    int[][] searchElement = JTableUtils.readIntMatrixFromJTable(searchTable);
                    tariffsPlan = inputArray.converterListStringToTariffs(inputArray.ArrayToList(arrayString));
                    TariffsForDirections tariffsForDirections = Tariffs.searchElementToTariffsPlan(tariffsPlan, searchElement[0][0]);
                    if (tariffsForDirections.getDirectionCode() != -1) {
                        JTableUtils.writeArrayToJTable(tableInt, new int[]{tariffsForDirections.getDirectionCode()});
                        JTableUtils.writeArrayToJTable(tableString, new String[]{tariffsForDirections.getDirectionsTitle()});
                        JTableUtils.writeArrayToJTable(tableDouble, new double[]{tariffsForDirections.getPricePerMinute()});

                    } else {
                        JOptionPane.showMessageDialog(null, "Ошибка! Такого направления не существует", "Output", JOptionPane.PLAIN_MESSAGE);

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                }


            }
        });
        calculateCallCostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String[][] arrayString = JTableUtils.readStringMatrixFromJTable(inputTable);
                    int[][] searchElement = JTableUtils.readIntMatrixFromJTable(searchTable);
                    tariffsPlan = inputArray.converterListStringToTariffs(inputArray.ArrayToList(arrayString));
                    TariffsForDirections tariffsForDirections = Tariffs.searchElementToTariffsPlan(tariffsPlan, searchElement[0][0]);

                    if (tariffsForDirections.getDirectionCode() != -1) {
                        int[] callCost = JTableUtils.readIntArrayFromJTable(callDurationTable);
                        JTableUtils.writeArrayToJTable(tableCallCost, new double[]{Tariffs.callCostToTariffsForDirections(tariffsForDirections, callCost)});

                    } else {
                        JOptionPane.showMessageDialog(null, "Ошибка! Такого направления не существует", "Output", JOptionPane.PLAIN_MESSAGE);

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                }


            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String[][] arrayString = JTableUtils.readStringMatrixFromJTable(inputTable);
                    tariffsPlan = inputArray.converterListStringToTariffs(inputArray.ArrayToList(arrayString));
                    int[] directionCode = JTableUtils.readIntArrayFromJTable(tableInt);
                    String[] directionsTitle = JTableUtils.readStringArrayFromJTable(tableString);
                    double[] pricePerMinute = JTableUtils.readDoubleArrayFromJTable(tableDouble);

                    TariffsForDirections tariffsForDirections = new TariffsForDirections(directionCode[0], directionsTitle[0], pricePerMinute[0]);

                    if (!Tariffs.searchElement(tariffsPlan, tariffsForDirections)) {
                        Tariffs.addTariffs(tariffsForDirections, tariffsPlan);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ошибка! Код направления уже существует", "Output", JOptionPane.PLAIN_MESSAGE);
                    }
                    JTableUtils.writeArrayToJTable(inputTable, InputArray.converterListStringToArrayString(InputArray.converterTariffsForDirectionsToListString(tariffsPlan)));


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String[][] arrayString = JTableUtils.readStringMatrixFromJTable(inputTable);
                    tariffsPlan = inputArray.converterListStringToTariffs(inputArray.ArrayToList(arrayString));
                    int[] directionCode = JTableUtils.readIntArrayFromJTable(tableInt);
                    String[] directionsTitle = JTableUtils.readStringArrayFromJTable(tableString);
                    double[] pricePerMinute = JTableUtils.readDoubleArrayFromJTable(tableDouble);

                    TariffsForDirections tariffsForDirections = new TariffsForDirections(directionCode[0], directionsTitle[0], pricePerMinute[0]);
                    if (Tariffs.searchElement(tariffsPlan, tariffsForDirections)) {
                        tariffsPlan = Tariffs.removeTariffs(tariffsPlan, Tariffs.searchNumberToTariffsForDirections(tariffsPlan, tariffsForDirections));
                    }
                    JTableUtils.writeArrayToJTable(inputTable, InputArray.converterListStringToArrayString(InputArray.converterTariffsForDirectionsToListString(tariffsPlan)));


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String[][] arrayString = JTableUtils.readStringMatrixFromJTable(inputTable);
                    tariffsPlan = inputArray.converterListStringToTariffs(inputArray.ArrayToList(arrayString));
                    int[] directionCode = JTableUtils.readIntArrayFromJTable(tableInt);
                    String[] directionsTitle = JTableUtils.readStringArrayFromJTable(tableString);
                    double[] pricePerMinute = JTableUtils.readDoubleArrayFromJTable(tableDouble);

                    TariffsForDirections tariffsForDirections = new TariffsForDirections(directionCode[0], directionsTitle[0], pricePerMinute[0]);

                    if (Tariffs.searchElement(tariffsPlan, tariffsForDirections)) {
                        tariffsPlan = Tariffs.setTariffs(tariffsForDirections, tariffsPlan, Tariffs.searchNumberToTariffsForDirections(tariffsPlan, tariffsForDirections));
                    } else {
                        JOptionPane.showMessageDialog(null, "Ошибка! Такого направления не существует", "Output", JOptionPane.PLAIN_MESSAGE);

                    }
                    JTableUtils.writeArrayToJTable(inputTable, InputArray.converterListStringToArrayString(InputArray.converterTariffsForDirectionsToListString(tariffsPlan)));


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });

    }


}
