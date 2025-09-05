import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class UnitConverGUI extends JFrame {
    private JPanel mainPanel;
    private String[] optionsLength = {"Millimeter", "Centimeter" ,"Meter", "Kilometer", "Inch", "Foot", "Yard", "Mile" };

    private JButton button1;
    private JButton button2;
    private JButton lengthButton;
    private JButton weightButton;
    private JButton temperatureButton;
    private JComboBox<String> FromLengthComboBox;
    private JComboBox<String> ToLengthComboBox;
    private JTextField amountTextField;
    private JPanel FromLengthPanel;
    private JPanel ToLengthPanel;
    private JPanel calculatorPanel;
    private JPanel unitConverterPanel;
    private JPanel lengthPanel;
    private JButton enterButton;
    private JPanel enterPanel;
    private JPanel weightPanel;
    private JLabel totalJlabel;
    private JPanel temperaturePanel;
    private JComboBox fromWeightJComboBox;
    private JComboBox toWeightJComboBox;
    private JComboBox fromTempJComboBox;
    private JComboBox toTempJComboBox;
    private JButton timeButton;
    private JPanel timePanel;
    private JComboBox fromTimeJComboBox;
    private JComboBox toTimeJComboBox;

    public UnitConverGUI(){
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.pack();
        this.setSize(405,225);
        this.setLocationRelativeTo(null);
        calculatorPanel.setVisible(false);
        weightPanel.setVisible(false);
        temperaturePanel.setVisible(false);
        timePanel.setVisible(false);



        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItemFrom;
                String selectedItemTo;

                if(lengthPanel.isVisible()){
                    String amount = amountTextField.getText().trim();
                    selectedItemFrom = (String) FromLengthComboBox.getSelectedItem();
                    selectedItemTo = (String) ToLengthComboBox.getSelectedItem();
                    System.out.println(getConvertedLength(selectedItemFrom, selectedItemTo, amount));
                    totalJlabel.setText("Total: " + getConvertedLength(selectedItemFrom, selectedItemTo, amount));
                    System.out.println("LengthPanel is visible!");
                } else if (weightPanel.isVisible()) {
                    String amount = amountTextField.getText().trim();
                    selectedItemFrom = (String) fromWeightJComboBox.getSelectedItem();
                    selectedItemTo = (String) toWeightJComboBox.getSelectedItem();

//                    System.out.println(selectedItemFrom + "-from  " + selectedItemTo + "--to");
//                    getConvertedMass(
//                            fromWeightJComboBox.getSelectedItem().toString(),
//                            toWeightJComboBox.getSelectedItem().toString(),
//                            amountTextField.getText()
//                    );
                    System.out.println("Total: " + getConvertedMass(selectedItemFrom, selectedItemTo, amount));
                    totalJlabel.setText("Total: " + getConvertedMass(selectedItemFrom, selectedItemTo, amount));
                    System.out.println("WeightPanel is visible!");
                } else if (temperaturePanel.isVisible()) {
                    String amount = amountTextField.getText().trim();
                    selectedItemFrom = (String) fromTempJComboBox.getSelectedItem();
                    selectedItemTo = (String) toTempJComboBox.getSelectedItem();

//                    System.out.println(selectedItemFrom + "-from  " + selectedItemTo + "--to");
//                    getConvertedMass(
//                            fromWeightJComboBox.getSelectedItem().toString(),
//                            toWeightJComboBox.getSelectedItem().toString(),
//                            amountTextField.getText()
//                    );
                    System.out.println("Total: " + getConvertedTemperature(selectedItemFrom, selectedItemTo, amount));
                    totalJlabel.setText("Total: " + getConvertedTemperature(selectedItemFrom, selectedItemTo, amount));
                    System.out.println("temperaturePanel is visible!");
                } else if (timePanel.isVisible()) {
                    String amount = amountTextField.getText().trim();
                    selectedItemFrom = (String) fromTimeJComboBox.getSelectedItem();
                    selectedItemTo = (String) toTimeJComboBox.getSelectedItem();
                    System.out.println(getConvertedTime(selectedItemFrom, selectedItemTo, amount));
                    totalJlabel.setText("Total: " + getConvertedTime(selectedItemFrom, selectedItemTo, amount));
                    System.out.println("timePanel is visible!");
                }

            }
        });

        lengthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                weightPanel.setVisible(false);
                temperaturePanel.setVisible(false);
                lengthPanel.setVisible(true);
                timePanel.setVisible(false);
            }
        });
        weightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                weightPanel.setVisible(true);
                temperaturePanel.setVisible(false);
                lengthPanel.setVisible(false);
                timePanel.setVisible(false);
            }
        });
        temperatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                weightPanel.setVisible(false);
                temperaturePanel.setVisible(true);
                lengthPanel.setVisible(false);
                timePanel.setVisible(false);
            }
        });
        timeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                weightPanel.setVisible(false);
                temperaturePanel.setVisible(false);
                lengthPanel.setVisible(false);
                timePanel.setVisible(true);
            }
        });
    }


    public String getConvertedLength(String from, String to, String value) {
        try {
            double inputValue = Double.parseDouble(value);
            double valueInMeters = convertToMeters(from, inputValue);
            return convertFromMetersWithLabel(to, valueInMeters);
        } catch (NumberFormatException e) {
            return "Invalid input value: " + value;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
    private double convertToMeters(String fromUnit, double value) {
        switch (fromUnit) {
            case "Millimeter(mm)":
                return value / 1000;
            case "Centimeter(cm)":
                return value / 100;
            case "Meter(m)":
                return value;
            case "Kilometer(km)":
                return value * 1000;
            case "Inch(in)":
                return value / 39.3701;
            case "Foot(ft)":
                return value / 3.28084;
            case "Yard(yd)":
                return value / 1.09361;
            case "Mile(mi)":
                return value / 0.000621371;
            default:
                throw new IllegalArgumentException("Unknown source unit: " + fromUnit);
        }
    }
    private String convertFromMetersWithLabel(String toUnit, double valueInMeters) {
        double result;
        String unitLabel;

        switch (toUnit) {
            case "Millimeter(mm)":
                result = valueInMeters * 1000;
                unitLabel = "mm";
                break;
            case "Centimeter(cm)":
                result = valueInMeters * 100;
                unitLabel = "cm";
                break;
            case "Meter(m)":
                result = valueInMeters;
                unitLabel = "m";
                break;
            case "Kilometer(km)":
                result = valueInMeters * 0.001;
                unitLabel = "km";
                break;
            case "Inch(in)":
                result = valueInMeters * 39.3701;
                unitLabel = "in";
                break;
            case "Foot(ft)":
                result = valueInMeters * 3.28084;
                unitLabel = "ft";
                break;
            case "Yard(yd)":
                result = valueInMeters * 1.09361;
                unitLabel = "yd";
                break;
            case "Mile(mi)":
                result = valueInMeters * 0.000621371;
                unitLabel = "mi";
                break;
            default:
                throw new IllegalArgumentException("Unknown target unit: " + toUnit);
        }

        if (toUnit.matches(".*(mm|cm|m|km).*")) {
            return String.format("%.6f %s", result, unitLabel);
        } else {
            return String.format("%.4f %s", result, unitLabel);
        }
    }

    public String getConvertedMass(String from, String to, String value) {
        try {
            double inputValue = Double.parseDouble(value);
            double valueInKilograms = convertToKilograms(from, inputValue);
            return convertFromKilogramsWithLabel(to, valueInKilograms);
        } catch (NumberFormatException e) {
            return "Invalid input value: " + value;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
    private double convertToKilograms(String fromUnit, double value) {
        switch (fromUnit) {
            case "Milligram(mg)":
//                System.out.println("milligram from");
                return value / 1000000.0;  // 1 kg = 1,000,000 mg
            case "Gram(g)":
                return value / 1000.0;       //  1 kg = 1,000 g
            case "Kilogram(kg)":
                return value;               // Base unit
            case "Metric Ton(tonne)":
                return value * 1000.0;      //  1 tonne = 1,000 kg
            case "Ounce(oz)":
                return value * 0.028349523125; // 1 oz = 0.028349523125 kg
            case "Pound(lb)":
                return value * 0.45359237;   // 1 lb = 0.45359237 kg
            case "Stone(st)":
                return value * 6.35029318;   // 1 st = 6.35029318 kg
            case "US Ton(short ton)":
                return value * 907.18474;    // 1 short ton = 907.18474 kg
            default:
                throw new IllegalArgumentException("Unknown source unit: " + fromUnit);
        }
    }
    private String convertFromKilogramsWithLabel(String toUnit, double valueInKilograms) {
        double result;
        String unitLabel;

        switch (toUnit) {
            case "Milligram(mg)":
                result = valueInKilograms * 1_000_000.0;
                unitLabel = "mg";
                break;
            case "Gram(g)":
                result = valueInKilograms * 1000.0;
                unitLabel = "g";
                break;
            case "Kilogram(kg)":
                result = valueInKilograms;
                unitLabel = "kg";
                break;
            case "Metric Ton(tonne)":
                result = valueInKilograms / 1000.0;
                unitLabel = "tonne";
                break;
            case "Ounce(oz)":
                result = valueInKilograms / 0.028349523125;
                unitLabel = "oz";
                break;
            case "Pound(lb)":
                result = valueInKilograms / 0.45359237;
                unitLabel = "lb";
                break;
            case "Stone(st)":
                result = valueInKilograms / 6.35029318;
                unitLabel = "st";
                break;
            case "US Ton(short ton)":
                result = valueInKilograms / 907.18474;
                unitLabel = "short ton";
                break;
            default:
                throw new IllegalArgumentException("Unknown target unit: " + toUnit);
        }

//        return result + unitLabel;
        // Format with 6 decimal places for metric, 4 for imperial
        if (toUnit.matches(".*(mg|g|kg|tonne).*")) {
            return String.format("%.6f %s", result, unitLabel);
        } else {
            return String.format("%.4f %s", result, unitLabel);
        }
    }


    public String getConvertedTemperature(String from, String to, String value) {
        try {
            // Parse input value
            double inputValue = Double.parseDouble(value);

            // First convert input value to Celsius (base unit)
            double valueInCelsius = 0;

            // Convert from source unit to Celsius
            switch (from) {
                case "Celsius (°C)":
                    valueInCelsius = inputValue;
                    break;
                case "Fahrenheit (°F)":
                    valueInCelsius = (inputValue - 32) * 5/9;
                    break;
                case "Kelvin (K)":
                    valueInCelsius = inputValue - 273.15;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown source unit: " + from);
            }
            String resultString = "";
            // Then convert from Celsius to target unit
            double result = 0;
            switch (to) {
                case "Celsius (°C)":
                    result = valueInCelsius;
                    resultString += String.valueOf(result) + "°C";
                    break;
                case "Fahrenheit (°F)":
                    result = (valueInCelsius * 9/5) + 32;
                    resultString += String.valueOf(result) + "°F";
                    break;
                case "Kelvin (K)":
                    result = valueInCelsius + 273.15;
                    resultString += String.valueOf(result) + "K";
                    break;
                default:
                    throw new IllegalArgumentException("Unknown target unit: " + to);
            }
            return resultString;
            // Format the result with 2 decimal places for temperature



        } catch (NumberFormatException e) {
            return "Invalid input value: " + value;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }



    public String getConvertedTime(String from, String to, String value) {
        try {
            double inputValue = Double.parseDouble(value);
            double valueInSeconds = convertToSeconds(from, inputValue);
            return convertFromSecondsWithLabel(to, valueInSeconds);
        } catch (NumberFormatException e) {
            return "Invalid input value: " + value;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
    private double convertToSeconds(String fromUnit, double value) {
        switch (fromUnit) {
            case "Millisecond (ms)":
                return value / 1000;
            case "Microsecond (µs)":
                return value / 1000000;
            case "Nanosecond (ns)":
                return value / 1000000000;
            case "Picosecond (ps)":
                return value / 1000000000000L;
            case "Second (s)":
                return value;
            case "Minute (min)":
                return value * 60;
            case "Hour (h)":
                return value * 3600;
            case "Day (d)":
                return value * 86400;
            case "Week (wk)":
                return value * 604800;
            case "Month (mo)":
                return value * 2629746;
            case "Year (yr)":
                return value * 31556952;
            default:
                throw new IllegalArgumentException("Unknown source unit: " + fromUnit);
        }
    }
    private String convertFromSecondsWithLabel(String toUnit, double valueInSeconds) {
        double result;
        String unitLabel;

        switch (toUnit) {
            case "Millisecond (ms)":
                result = valueInSeconds * 1000;
                unitLabel = "ms";
                break;
            case "Microsecond (µs)":
                result = valueInSeconds * 1000000;
                unitLabel = "µs";
                break;
            case "Nanosecond (ns)":
                result = valueInSeconds * 1000000000;
                unitLabel = "ns";
                break;
            case "Picosecond (ps)":
                result = valueInSeconds * 1000000000000L;
                unitLabel = "ps";
                break;
            case "Second (s)":
                result = valueInSeconds;
                unitLabel = "s";
                break;
            case "Minute (min)":
                result = valueInSeconds / 60;
                unitLabel = "min";
                break;
            case "Hour (h)":
                result = valueInSeconds / 3600;
                unitLabel = "h";
                break;
            case "Day (d)":
                result = valueInSeconds / 86400;
                unitLabel = "d";
                break;
            case "Week (wk)":
                result = valueInSeconds / 604800;
                unitLabel = "wk";
                break;
            case "Month (mo)":
                result = valueInSeconds / 2629746;
                unitLabel = "mo";
                break;
            case "Year (yr)":
                result = valueInSeconds / 31556952;
                unitLabel = "yr";
                break;
            default:
                throw new IllegalArgumentException("Unknown target unit: " + toUnit);
        }

        if (toUnit.matches(".*(ms|µs|ns|ps|s).*")) {
            return String.format("%.6f %s", result, unitLabel);
        } else {
            return String.format("%.4f %s", result, unitLabel);
        }
    }



    public static void main(String[] args) {
        UnitConverGUI myFrame = new UnitConverGUI();
        myFrame.setVisible(true);
    }
}
