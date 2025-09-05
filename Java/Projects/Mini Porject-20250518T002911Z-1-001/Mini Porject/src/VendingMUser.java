import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VendingMUser extends JFrame {

    // Panels
    private JPanel vendingMachinePanel, ItemsPanel, UserSide, TotalItemlist, Numpad,
            PayAndChange, AcceptCancel, FirstRow, SecondRow, ThirdRow;

    // Buttons
    private JButton insertButton, cancelButton, acceptButton, enterButton, clearButton1;
    private JButton a0Button, a1Button, a2Button, a3Button, a4Button, a5Button, a6Button,
            a7Button, a8Button, a9Button;
    // Text Fields

    private JTextField PaymentTextField;

    // Labels
    private JLabel itemBrand, itemPackage, numBrandOrig;
    private JLabel ItemEnter;
    // Table
    private JTable itemTable;


    private JLabel imageItem1;
    private JPanel imgItem1;
    private JLabel imageItem2;
    private JPanel imageItem2panel;
    private JPanel imageItem3panel;
    private JLabel imageItem3;
    private JLabel imageItem4;
    private JPanel imageItem4panel;
    private JLabel imageItem5;
    private JPanel imageItem5panel;
    private JLabel imageItem6;
    private JPanel imageItem6panel;
    private JLabel imageItem7;
    private JPanel imageItem7panel;
    private JLabel imageItem8;
    private JPanel imageItem8panel;
    private JLabel imageItem9;
    private JPanel imageItem9panel;
    private JLabel imageItem10;
    private JPanel imageItem10panel;
    private JLabel imageItem11;
    private JPanel imageItem11panel;
    private JLabel imageItem12;
    private JPanel imageItem12panel;
    private JLabel imageItem13;
    private JPanel imageItem13panel;
    private JLabel imageItem14;
    private JPanel imageItem14panel;
    private JLabel imageItem15;
    private JPanel imageItem15panel;

    private JLabel itemQuantity1;
    private JLabel itemQuantity2;
    private JLabel itemQuantity3;
    private JLabel itemQuantity4;
    private JLabel itemQuantity5;
    private JLabel itemQuantity6;

    private JLabel itemQuantity7;
    private JLabel itemQuantity8;
    private JLabel itemQuantity9;
    private JLabel itemQuantity10;
    private JLabel itemQuantity11;
    private JLabel itemQuantity12;
    private JLabel itemQuantity13;
    private JLabel itemQuantity14;
    private JLabel itemQuantity15;
    private JButton refreshButton;
    private JButton cashButton;
    private JButton creditDebitButton;
    private JButton QRCodeButton;
    private JPanel CardPanel;
    private JPanel QrPanel;
    private JPanel PaymentMethod;
    private JLabel paymentMethodText;
    private JPanel selectPaymentMethod;
    private JButton insertCardButton;
    private JLabel QRTextLabelImage;
    private JButton scanQRButton;
    private JLabel totalTextJlabel;
    private JButton goBackButtonCash;
    private JButton goBackButtonQR;
    private JButton goBackButtonCard;
    private JPanel CardPanelLine;
    private static String ItemEnterString = "";
    private final int locationX = 150;
    private final int locationY = 25;
    private final int sizeWidth = 950+(19*5);
    private final int sizeHeight = 600+(12*5);
    private String userAccount = "";
    private boolean isUserAGuest = true;
    private final String[][] productName =   {{"Coca-Cola", "Pepsi", "Sprite", "Royal", "Mountain Dew"},
                                        {"Seafood", "Spicy Seafood", "Creamy Seafood", "Spicy Hot Beef", "Beef"},
                                        {"Jjampong", "Mini Bulalo", "Mini Batchoy", "Mini", "Mini Seafood"}};
    private final String[] brand = {"Nissin", "Lucky Me!"};
    private final String itemPackageString = "Cup Noodles";
    private static final String[] columnNames = {"Item Name", "Quantity", "Price"};
    private static final DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    ///////////////////////////////Product Quantity and price/////////////
    private static final int[] itemsPrices = {
        //first Row
            15, 12, 12, 12, 15,
        //Second Row
            23, 23, 23, 23, 23,
        //Third Row
            28, 23, 23, 23, 23
    };
    private static final int[] itemsQuantity = {
            //first Row
            12, 10, 8, 9, 11,
            //Second Row
            8, 9, 8, 5, 4,
            //Third Row
            6, 6, 8, 7, 9
    };




    private static int previousRowCount = 0;
    /// ///////////////////////////////for Table values rows //////////////////
    private static int quantityValue = 0;
    private static int rowTotalPrice = 0;
    private static int itemsTotalPrice = 0;
    private static int itemsTotalPricePayment = 0;
    private static int initialRowCount = 0;
    private String rowStringProductName = "";
    private static String rowQuantity = "";
    private static int tempForGetQuantity = 0;
    private final static String[] prodName = {
            // No brand
            "Coke", "Pepsi", "Sprite", "Royal", "Mtn Dew",

            // Brand = Nissin, itemPackageString = Cup Noodles
            "Nissin Sfd Cup", "Nissin Spicy Sfd", "Nissin Creamy Sfd",
            "Nissin Spicy Beef", "Nissin Beef",

            // Brand = Lucky Me!, itemPackageString = Cup Noodles
            "LM Jjampong Cup", "LM Mini Bulalo", "LM Mini Batchoy",
            "LM Mini spcy Bulalo", "LM Mini Sfd"
    };

    /// //////////////////////////////////////////////////////////


    /// ///////////////IconImage/////////////////////////
    final int itemIconHeight = (int)(216*.3);
    final int itemIconWidth = (int)(216*.3);
    /// ///////////////IconImage/////////////////////////

private static int item = 0;
    private static String  updateJlabel = "";
    private final Boolean needToUpdateLabelQuantity = false;

    private String update = "";













    public VendingMUser(String title) {

        super(title);
        this.setContentPane(vendingMachinePanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(sizeWidth,sizeHeight);
//        this.setLocation(locationX,locationY);
        this.setLocationRelativeTo(null);
        vendingMachinePanel.setFocusable(true);
        vendingMachinePanel.requestFocusInWindow();

//         disables the input field for cash unless selected cash as mode of payment
        PaymentMethod.setVisible(true);
        QrPanel.setVisible(false);
        CardPanel.setVisible(false);
        PayAndChange.setVisible(false);
        refreshButton.setVisible(false);
        selectPaymentMethod.setVisible(false);

//        this.pack();
            setTable();
            setItemQuantities(true);
            //Load images
        if(ItemsPanel.isVisible()) {
            if (FirstRow.isVisible()) {
                Image scaledImage1 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\cocacola-removebg-preview.png",
                        65,
                        65
                );

                Image scaledImage2 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\pepsi.png",
                        itemIconWidth,
                        itemIconHeight
                );
                Image scaledImage3 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\Sprite.png",
                        itemIconWidth,
                        itemIconHeight
                );
                Image scaledImage4 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\royal-Photoroom.png",
                        itemIconWidth,
                        itemIconHeight
                );
                Image scaledImage5 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\Mountain Dew-Photoroom.png",
                        itemIconWidth,
                        itemIconHeight
                );

                imageItem1.setIcon(new ImageIcon(scaledImage1));
                imageItem2.setIcon(new ImageIcon(scaledImage2));
                imageItem3.setIcon(new ImageIcon(scaledImage3));
                imageItem4.setIcon(new ImageIcon(scaledImage4));
                imageItem5.setIcon(new ImageIcon(scaledImage5));
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Error Loading First Row Images",
                        "Cannot load Image",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            if (SecondRow.isVisible()){
                Image scaledImage1 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\secondRow\\Nissin seafood.png",
                        65,
                        65
                );
                Image scaledImage2 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\secondRow\\Nissin Spicy Seafood.png",
                        itemIconWidth,
                        itemIconHeight
                );
                Image scaledImage3 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\secondRow\\Nissin creamy seafood.png",
                        itemIconWidth,
                        itemIconHeight
                );
                Image scaledImage4 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\secondRow\\NIssin Spicy Hot beef.png",
                        itemIconWidth,
                        itemIconHeight
                );
                Image scaledImage5 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\secondRow\\Nissin Cup Mini Noodles Beef.png",
                        itemIconWidth,
                        itemIconHeight
                );

                imageItem6.setIcon(new ImageIcon(scaledImage1));
                imageItem7.setIcon(new ImageIcon(scaledImage2));
                imageItem8.setIcon(new ImageIcon(scaledImage3));
                imageItem9.setIcon(new ImageIcon(scaledImage4));
                imageItem10.setIcon(new ImageIcon(scaledImage5));
            }else {
                JOptionPane.showMessageDialog(
                        null,
                        "Error Loading Second Row Images",
                        "Cannot load Image",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            if (ThirdRow.isVisible()){

                Image scaledImage1 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\thirdRow\\jjampong.png",
                        65,
                        65
                );
                Image scaledImage2 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\thirdRow\\nissin MIni bulalo.png",
                        itemIconWidth,
                        itemIconHeight
                );
                Image scaledImage3 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\thirdRow\\Nissin Batchoy.png",
                        itemIconWidth,
                        itemIconHeight
                );
                Image scaledImage4 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\thirdRow\\Nissin Spicy Bulalo.png",
                        itemIconWidth,
                        itemIconHeight
                );
                Image scaledImage5 = ImageScaler.getScaledImage(
                        "C:\\Coding practice and progress VScode\\Intermediate-programming-Activity\\Mini Porject\\src\\icons\\thirdRow\\Nissin mini Seafood.png",
                        itemIconWidth,
                        itemIconHeight
                );

                imageItem11.setIcon(new ImageIcon(scaledImage1));
                imageItem12.setIcon(new ImageIcon(scaledImage2));
                imageItem13.setIcon(new ImageIcon(scaledImage3));
                imageItem14.setIcon(new ImageIcon(scaledImage4));
                imageItem15.setIcon(new ImageIcon(scaledImage5));
            }else {
                JOptionPane.showMessageDialog(
                        null,
                        "Error Loading Third Row Images",
                        "Cannot load Image",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }








        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachinePanel.requestFocusInWindow();
            }
        });
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                  Numpad.setVisible(false);
                int total = itemsTotalPrice;
                if (total != 0){
                    AcceptCancel.setVisible(false);
                    selectPaymentMethod.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Cart is empty!",
                            "",
                            JOptionPane.OK_OPTION);
                }

                vendingMachinePanel.requestFocusInWindow();
            }
        });

        if(PaymentMethod.isVisible()) {
            insertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    isPaymentSufficient("cash");

                    vendingMachinePanel.requestFocusInWindow();
                }
            });
            cashButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectPaymentMethod.setVisible(false);
                    PayAndChange.setVisible(true);
                    vendingMachinePanel.requestFocusInWindow();
                }
            });
            creditDebitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectPaymentMethod.setVisible(false);
                    CardPanel.setVisible(true);
                    vendingMachinePanel.requestFocusInWindow();
                }
            });
            insertCardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardPanel.setVisible(false);
                    AcceptCancel.setVisible(true);
                    int choice = JOptionPane.showConfirmDialog(
                            null,
                            "Payment successful!",
                            null,
                            JOptionPane.CLOSED_OPTION
                    );
                    int a = JOptionPane.showConfirmDialog(
                            null,
                            "Payment successful!",
                            null,
                            JOptionPane.CLOSED_OPTION
                    );
                    paymentIsSufficient();
                }
            });
            QRCodeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectPaymentMethod.setVisible(false);
                    QrPanel.setVisible(true);
                    if (QrPanel.isVisible()) {
                        if (QRTextLabelImage.isVisible()) {
                            Image scaledImageqr = ImageScaler.getScaledImage(
                                    "C:\\Programming Projects\\Mini Porject-20250518T002911Z-1-001\\Mini Porject\\src\\icons\\QRGcashLegit.jpg",
                                    160,
                                    160
                            );

                            QRTextLabelImage.setIcon(new ImageIcon(scaledImageqr));
                        }
                    }

                    vendingMachinePanel.requestFocusInWindow();
                }
            });
            scanQRButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    QrPanel.setVisible(false);
                    AcceptCancel.setVisible(true);
                    int choice = JOptionPane.showConfirmDialog(
                            null,
                            "Payment successful!",
                            null,
                            JOptionPane.CLOSED_OPTION
                    );
                    paymentIsSufficient();

                }
            });

        }
        if(Numpad.isVisible()) {
            enterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!ItemEnterString.isEmpty()){
                        int a = Integer.parseInt(ItemEnterString);
                        System.out.println("Enter Run successfully");
                        if(a > 0 && a <= 15){
                            System.out.println("Entered item is available 0-15: " + a);

                            if(isItemAvailable(a)){
                                boolean pass = true;
                                while (pass) {
                                    String quantity = JOptionPane.showInputDialog(
                                            null,
                                            "Enter Quantity",
                                            null,
                                            JOptionPane.QUESTION_MESSAGE
                                    );
                                    if(quantity == null){
                                        pass = false;
                                    } else if (quantity.isEmpty()){
                                        pass = false;
                                    }
                                    try {


                                        if (Integer.parseInt(quantity) <= itemsQuantity[Integer.parseInt(ItemEnterString)-1]) {
                                            quantityValue = Integer.parseInt(quantity);
                                            tempForGetQuantity = Integer.parseInt(quantity);
                                            pass = false;
                                            setRowStringProductName(ItemEnterString);

                                            quantityArrChange();
                                            setRowTotalPrice();
                                            addRowinTable(rowStringProductName, rowQuantity, "₱" + Integer.toString(rowTotalPrice));
                                            System.out.println("QUAAA: " + itemsQuantity[Integer.parseInt(ItemEnterString) - 1]);
                                            updateJlabel = Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString)-1]);
                                            System.out.println("REMAINING:::::"+ updateJlabel);
                                            setItemQuantities(true);
                                            getTotalPrice();
                                            refreshButton.doClick();
                                        } else {
                                            JOptionPane.showMessageDialog(
                                                    null,
                                                    "Available Item: " + itemsQuantity[Integer.parseInt(ItemEnterString)-1] +
                                                    "\nEntered Quantity: " + quantity,
                                                    null,
                                                    JOptionPane.ERROR_MESSAGE
                                            );
                                        }
                                    } catch (NumberFormatException ex) {
                                        clearALl();
                                        ItemEnterString = "";
                                        if(quantity == null){
                                            pass = false;
                                        } else if (quantity.isEmpty()) {
                                            pass = false;
                                        } else {
                                            JOptionPane.showMessageDialog(
                                                    null,
                                                    "Please Enter Integer Value only!",
                                                    null,
                                                    JOptionPane.ERROR_MESSAGE
                                            );
                                        }

                                    }
                                }

                            }

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Item unavailable!", "Empty",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    vendingMachinePanel.requestFocusInWindow();
                }
            });

            clearButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeItemEnter();
                    vendingMachinePanel.requestFocusInWindow();
                }
            });

            a7Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addItemEnter(7);

                    itemQuantity5.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 3]));
                    vendingMachinePanel.requestFocusInWindow();
                }
            });
            a8Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addItemEnter(8);
                    vendingMachinePanel.requestFocusInWindow();

                }
            });
            a9Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addItemEnter(9);
                    vendingMachinePanel.requestFocusInWindow();
                }
            });
            a4Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addItemEnter(4);
                    vendingMachinePanel.requestFocusInWindow();
                }
            });
            a5Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addItemEnter(5);
                    vendingMachinePanel.requestFocusInWindow();
                }
            });
            a6Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addItemEnter(6);
                    vendingMachinePanel.requestFocusInWindow();
                }
            });
            a1Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addItemEnter(1);
                    vendingMachinePanel.requestFocusInWindow();
                }
            });
            a2Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addItemEnter(2);
                    vendingMachinePanel.requestFocusInWindow();
                }
            });
            a3Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addItemEnter(3);
                    vendingMachinePanel.requestFocusInWindow();
                }
            });
            a0Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addItemEnter(0);
                    vendingMachinePanel.requestFocusInWindow();
                }
            });

        }
        if(vendingMachinePanel.isVisible()) {
            vendingMachinePanel.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    super.keyTyped(e);
                    char key = e.getKeyChar();
                    switch (key) {
                        case '0':
                            a0Button.doClick();
                            break;
                        case '1':
                            a1Button.doClick();
                            break;
                        case '2':
                            a2Button.doClick();
                            break;
                        case '3':
                            a3Button.doClick();
                            break;
                        case '4':
                            a4Button.doClick();
                            break;
                        case '5':
                            a5Button.doClick();
                            break;
                        case '6':
                            a6Button.doClick();
                            break;
                        case '7':
                            a7Button.doClick();
                            break;
                        case '8':
                            a8Button.doClick();
                            break;
                        case '9':
                            a9Button.doClick();
                            break;
                        case '\b':
                            clearButton1.doClick();
                            break;
                        case '\n':
                            enterButton.doClick();
                            break;
                        case ' ':
                            refreshButton.doClick();
                            break;
                        default:
                            System.out.println(key + " is pressed");
                            break;
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    super.keyPressed(e);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                }
            });
        }



        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ItemEnterString.isEmpty()){}else {
                    switch (Integer.parseInt(ItemEnterString) - 1) {
                        case 0:
                            itemQuantity1.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 1:
                            itemQuantity2.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 2:
                            itemQuantity3.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 3:
                            itemQuantity4.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) -1]));
                            break;
                        case 4:
                            itemQuantity5.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 5:
                            itemQuantity6.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 6:
                            itemQuantity7.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 7:
                            itemQuantity8.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 8:
                            itemQuantity9.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 9:
                            itemQuantity10.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 10:
                            itemQuantity11.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 11:
                            itemQuantity12.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 12:
                            itemQuantity13.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 13:
                            itemQuantity14.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        case 14:
                            itemQuantity15.setText(Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]));
                            break;
                        default:
                            System.out.println("Invalid item index: " + (Integer.parseInt(ItemEnterString) - 1));
                    }
                }
                removeItemEnter();
                vendingMachinePanel.requestFocusInWindow();
            }
        });
        goBackButtonCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AcceptCancel.setVisible(true);
                PayAndChange.setVisible(false);
            }
        });
        goBackButtonCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardPanel.setVisible(false);
                AcceptCancel.setVisible(true);

            }
        });
        goBackButtonQR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QrPanel.setVisible(false);
                AcceptCancel.setVisible(true);

            }
        });
    }
    public void setIsUserAGuest(boolean a){
        isUserAGuest = a;
    }
    public void setUserAccount(String a){
        userAccount = a;
    }
    public void getTotalPrice(){
        int aInt = 0;
        String a = totalTextJlabel.getText();
        a = a.replace("Total: ₱", "").trim();
        try {

             aInt = Integer.parseInt(a);
             itemsTotalPricePayment = aInt;
        } catch (Exception e) {
        }
    }
    public void isPaymentSufficient(String paymentMethod){
        getTotalPrice();
        if(paymentMethod.trim().equalsIgnoreCase("cash")){
            int paymentInt = 0;
            try {
                String payment = PaymentTextField.getText();
                paymentInt = Integer.parseInt(payment);
                if(paymentInt >= itemsTotalPricePayment){
                    int change = paymentInt - itemsTotalPricePayment;
                    int choice = JOptionPane.showConfirmDialog(
                            null,
                            "Payment successful!\nChange: ₱"+change,
                            null,
                            JOptionPane.CLOSED_OPTION
                    );
                    paymentIsSufficient();
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Insufficient Balance.",
                            null,
                            JOptionPane.ERROR_MESSAGE
                    );
                    PaymentTextField.setText("");
                }
            } catch (Exception e){
                PaymentTextField.setText("");
                JOptionPane.showMessageDialog(null,
                        "Please enter cash!",
                        "",
                        JOptionPane.WARNING_MESSAGE);
            }





        } else if (paymentMethod.trim().equalsIgnoreCase("card")) {

        } else if (paymentMethod.trim().equalsIgnoreCase("qr")) {

        }
    }
    public void paymentIsSufficient(){

        if (tableModel.getRowCount() > 0) {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Thank you for you purchase!\n\nDo you want to make another purchase?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
            );
            initialRowCount = 0;
            if (choice == JOptionPane.YES_OPTION){
                for (int i = 0 ; 0 < tableModel.getRowCount(); i++ ){
                    System.out.println("Row count: "+tableModel.getRowCount());
                    tableModel.removeRow(0);
                    System.out.println("Row" + i + " Deleted");
                }
                ItemEnterString = "";
                quantityValue = 0;
                AcceptCancel.setVisible(true);
                PayAndChange.setVisible(false);
                totalTextJlabel.setText("Total: ");
            } else if (choice == JOptionPane.NO_OPTION) {
                dispose();
                Login.main(null);
                Login run = new Login(null);
                run.setVisible(true);
                for (int i = 0 ; 0 < tableModel.getRowCount(); i++ ){
                    tableModel.removeRow(0);
                    System.out.println("Row count: "+tableModel.getRowCount());
                    System.out.println("Row" + i + " Deleted");
                }
                ItemEnterString = "";
                quantityValue = 0;
                totalTextJlabel.setText("Total: ");

            } else {
                for (int i = 0 ; 0 < tableModel.getRowCount(); i++ ){
                    tableModel.removeRow(0);
                    System.out.println("Row count: "+tableModel.getRowCount());
                    System.out.println("Row" + i + " Deleted");
                }
                ItemEnterString = "";
                quantityValue = 0;
                AcceptCancel.setVisible(true);
                PayAndChange.setVisible(false);
                totalTextJlabel.setText("Total: ");
            }
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Cart is empty.",
                    null,
                    JOptionPane.ERROR_MESSAGE
            );
        }
        itemsTotalPrice = 0;
        itemsTotalPricePayment = 0;
        ItemEnterString = "";
    }
    public void setItemQuantities(boolean isASetup){
        System.out.println("1297381723912731737129731923719273912737913");
        if(isASetup) {
            for (int a = 0; a < itemsQuantity.length; a++) {
                int s = itemsQuantity[a];
                String o = Integer.toString(s);

//                System.out.println("Current index: " + a + "  " + itemsQuantity[a] + "    Item: " + item); // Debugging output
               if(!ItemEnterString.isEmpty()) {
                   int quantity = itemsQuantity[Integer.parseInt(ItemEnterString)-1];
                   update = Integer.toString(quantity);
//                   System.out.println("QUAAA: " + itemsQuantity[Integer.parseInt(ItemEnterString) - 1]);
                   updateJlabel = Integer.toString(itemsQuantity[Integer.parseInt(ItemEnterString) - 1]);
//                   System.out.println("REMAINING:::::" + updateJlabel);
               }
                switch (a) {
                    case 0:
                        itemQuantity1.setText(o);
//                        System.out.println(itemQuantity1.getText());
                        break;
                    case 1:
                        itemQuantity2.setText(o);
//                        System.out.println(itemQuantity2.getText());
                        break;
                    case 2:
                        itemQuantity3.setText(o);
//                        System.out.println(itemQuantity3.getText());
                        break;
                    case 3:
                        itemQuantity4.setText(o);
//                        System.out.println(itemQuantity4.getText());
                        break;
                    case 4:
                        itemQuantity5.setText(o);
//                        itemQuantity5.revalidate();
//                        itemQuantity5.repaint();
//                        System.out.println(itemQuantity5.getText());
                        break;
                    case 5:
                        itemQuantity6.setText(o);

//                        System.out.println(itemQuantity6.getText());
                        break;
                    case 6:
                        itemQuantity7.setText(o);
//                        System.out.println(itemQuantity7.getText());
                        break;
                    case 7:
                        itemQuantity8.setText(o);
//                        System.out.println(itemQuantity8.getText());
                        break;
                    case 8:
                        itemQuantity9.setText(o);
//                        System.out.println(itemQuantity9.getText());
                        break;
                    case 9:
                        itemQuantity10.setText(o);
//                        System.out.println(itemQuantity10.getText());
                        break;
                    case 10:
                        itemQuantity11.setText(o);
//                        System.out.println(itemQuantity11.getText());
                        break;
                    case 11:
                        itemQuantity12.setText(o);
//                        System.out.println(itemQuantity12.getText());
                        break;
                    case 12:
                        itemQuantity13.setText(o);
//                        System.out.println(itemQuantity13.getText());
                        break;
                    case 13:
                        itemQuantity14.setText(o);
//                        System.out.println(itemQuantity14.getText());
                        break;
                    case 14:
                        itemQuantity15.setText(o);
//                        System.out.println(itemQuantity15.getText());
                        break;
                    default:
                        System.out.println("Invalid index: " + a);
                }
                if (!(ItemEnterString.isEmpty())) {
                    switch (Integer.parseInt(ItemEnterString) - 1) {
                        case 0:
                            itemQuantity1.setText(update);
//                                System.out.println(itemQuantity1.getText());
//                                Thread.sleep(50);
                            break;
                        case 1:
//                                Thread.sleep(50);
                            itemQuantity2.setText(update);
//                                System.out.println(itemQuantity2.getText());
                            break;
                        case 2:
//                                Thread.sleep(50);
                            itemQuantity3.setText(update);
//                                System.out.println(itemQuantity3.getText());
                            break;
                        case 3:
//                                Thread.sleep(50);
                            itemQuantity4.setText(update);
//                                System.out.println(itemQuantity4.getText());
                            break;
                        case 4:
//                                Thread.sleep(50);
                            itemQuantity5.setText(update);
//                                SwingUtilities.invokeLater(() -> {
//                                    itemQuantity5.revalidate();
//                                    itemQuantity5.repaint();
//                                    itemQuantity5.getParent().revalidate();
//                                    itemQuantity5.getParent().repaint();
//                                    itemQuantity5.setVisible(true);
//                                });
//                                System.out.println(itemQuantity5.getText());
                            break;
                        case 5:
//                                Thread.sleep(50);
                            itemQuantity6.setText(update);
//                                System.out.println(itemQuantity6.getText());
                            break;
                        case 6:
//                                Thread.sleep(50);
                            itemQuantity7.setText(update);
//                                System.out.println(itemQuantity7.getText());
                            break;
                        case 7:
//                                Thread.sleep(50);
                            itemQuantity8.setText(update);
//                                System.out.println(itemQuantity8.getText());
                            break;
                        case 8:
//                                Thread.sleep(50);
                            itemQuantity9.setText(update);
//                                System.out.println(itemQuantity9.getText());
                            break;
                        case 9:
//                                Thread.sleep(50);
                            itemQuantity10.setText(update);
//                                System.out.println(itemQuantity10.getText());
                            break;
                        case 10:
//                                Thread.sleep(50);
                            itemQuantity11.setText(update);
//                                System.out.println(itemQuantity11.getText());
                            break;
                        case 11:
//                                Thread.sleep(50);
                            itemQuantity12.setText(update);
//                                System.out.println(itemQuantity12.getText());
                            break;
                        case 12:
//                                Thread.sleep(50);
                            itemQuantity13.setText(update);
//                                System.out.println(itemQuantity13.getText());
                            break;
                        case 13:
//                                Thread.sleep(50);
                            itemQuantity14.setText(update);
//                                System.out.println(itemQuantity14.getText());
                            break;
                        case 14:
//                                Thread.sleep(50);
                            itemQuantity15.setText(update);
//                                System.out.println(itemQuantity15.getText());
                            break;
                        default:
                            System.out.println("Invalid index: " + (Integer.parseInt(ItemEnterString) - 1));
                    }
                }
            }

        } else {
            for (int a = 0; a < itemsQuantity.length; a++) {
                int s = Integer.parseInt(ItemEnterString);
                s = s - 1;
                System.out.println("ITEMMMMMMMASDASD :::" + ItemEnterString + updateJlabel);
                String o = Integer.toString(s);

                System.out.println("Current index: " + a); // Debugging output

                switch (s) {
                    case 0:
                        itemQuantity1.setText(updateJlabel);
                        System.out.println(itemQuantity1.getText());
                        break;
                    case 1:
                        itemQuantity2.setText(updateJlabel);
                        break;
                    case 2:
                        itemQuantity3.setText(updateJlabel);
                        break;
                    case 3:
                        itemQuantity4.setText(updateJlabel);
                        break;
                    case 4:
                        itemQuantity5.setText(updateJlabel);
                        break;
                    case 5:
                        itemQuantity6.setText(updateJlabel);
                        break;
                    case 6:
                        itemQuantity7.setText(updateJlabel);
                        break;
                    case 7:
                        itemQuantity8.setText(updateJlabel);
                        break;
                    case 8:
                        itemQuantity9.setText(updateJlabel);
                        break;
                    case 9:
                        itemQuantity10.setText(updateJlabel);
                        break;
                    case 10:
                        itemQuantity11.setText(updateJlabel);
                        break;
                    case 11:
                        itemQuantity12.setText(updateJlabel);
                        break;
                    case 12:
                        itemQuantity13.setText(updateJlabel);
                        break;
                    case 13:
                        itemQuantity14.setText(updateJlabel);
                        break;
                    case 14:
                        itemQuantity15.setText(updateJlabel);
                        break;
                }
            }
        }
    }
    public  boolean isItemAvailable(int itemEntered){
        int whatItem = Integer.parseInt(ItemEnterString) - 1;
        if(!(itemsQuantity[whatItem] > 0) ){
            VendingMUser name = new VendingMUser(null);
            name.setRowStringProductName(ItemEnterString);
            JOptionPane.showMessageDialog(
                    null,
                    "Item " + name.rowStringProductName + " is out of stock",
                    null,
                    JOptionPane.ERROR_MESSAGE
            );

            clearALl();
            ItemEnterString = "";
            return false;
        } else {

        }
        return true;
    }
    public void addItemEnter(int num){
        String numToString = Integer.toString(num);
        if(ItemEnterString.length() < 2) {
            if (ItemEnterString.isEmpty()) {
                ItemEnterString = numToString;
            } else {
                ItemEnterString += num;
            }
            updateText();

        }
    }
    public void clearALl(){
        ItemEnter.setText("");
        itemPackage.setText("");
        itemBrand.setText("");
        numBrandOrig.setText("");
    }
    public void removeItemEnter(){
        if(ItemEnterString.isEmpty()){
            System.out.println("the text is empty");
            clearALl();
            updateText();
        }else{
            ItemEnterString = ItemEnterString.substring(0, 0);
            updateText();
        }

    }
    public void updateText(){
         setTotalPrice();
//        updateItemQuantity(false);
        if(!ItemEnterString.isEmpty()) {
            int stringToNum = Integer.parseInt(ItemEnterString);
            String updateText = "";
            String updateTXTbelow = "";
            switch (stringToNum) {
                case 1:
                    updateText = ItemEnterString + ". " + productName[0][0];
                    break;
                case 2:
                    updateText = ItemEnterString + ". " + productName[0][1];
                    break;
                case 3:
                    updateText = ItemEnterString + ". " + productName[0][2];
                    break;
                case 4:
                    updateText = ItemEnterString + ". " + productName[0][3];
                    break;
                case 5:
                    updateText = ItemEnterString + ". " + productName[0][4];
                    break;
                case 6:
                    updateText = productName[1][0];
                    break;
                case 7:
                    updateText = productName[1][1];
                    break;
                case 8:
                    updateText = productName[1][2];
                    break;
                case 9:
                    updateText = productName[1][3];
                    break;
                case 10:
                    updateText = productName[1][4];
                    break;
                case 11:
                    updateText = productName[2][0];
                    break;
                case 12:
                    updateText = productName[2][1];
                    break;
                case 13:
                    updateText =  productName[2][2];
                    break;
                case 14:
                    updateText = productName[2][3];
                    break;
                case 15:
                    updateText = productName[2][4];
                    break;
                default:
                    updateText ="Does not exist!" ;
                    updateTXTbelow = "Item: " + ItemEnterString;
            }

            if(ItemEnterString.isEmpty()){
                clearALl();
            } else {
                ItemEnter.setText(updateText);
            }

            if( stringToNum > 5 && stringToNum <= 10){
                numBrandOrig.setText(ItemEnterString + ". ");
                itemBrand.setText(brand[0]);
                itemPackage.setText(itemPackageString);
            } else if ( stringToNum > 10 && stringToNum <= 15) {
                numBrandOrig.setText(ItemEnterString + ". ");
                itemBrand.setText(brand[1]);
                itemPackage.setText(itemPackageString);
            } else if (stringToNum > 15) {
                clearALl();
                itemBrand.setText(updateTXTbelow);
                ItemEnter.setText(updateText);

            } else {


            }


        } else {
            System.out.println("It is empty!");
           clearALl();
        }

        if (ItemEnterString.isEmpty()){

        } else {
            System.out.println("Updated ItemEnterString: " + ItemEnterString);
            System.out.println(quantityValue);
            System.out.println(quantityValue);

        }

    }
    public void setTable(){
        itemTable.setModel(tableModel);
        itemTable.setPreferredScrollableViewportSize(new Dimension(50, 50)); // width, height
        itemTable.setFillsViewportHeight(true);
        itemTable.getColumnModel().getColumn(0).setPreferredWidth(40); // Item Name
        itemTable.getColumnModel().getColumn(1).setPreferredWidth(5);  // Quantity
        itemTable.getColumnModel().getColumn(2).setPreferredWidth(5);  // Price

    }
    public void setTotalPrice(){
        int rowCount = tableModel.getRowCount();
        if(rowCount > initialRowCount) {
            itemsTotalPrice = 0;
            String priceString = "";
            int priceInt = 0;
            for (int i = 0; i < rowCount; i++) {
                Object valueOfPriceFromRow = tableModel.getValueAt(i , 2);
                priceString = valueOfPriceFromRow.toString().replace("₱", "");
                priceInt = Integer.parseInt(priceString);
                itemsTotalPrice += priceInt;
                priceString = "";
                priceInt = 0;
            }
            totalTextJlabel.setText("Total: ₱" + Integer.toString(itemsTotalPrice));
            initialRowCount = rowCount;
        }
    }
    public void addRowinTable(String name, String quantity, String totalPrice){
        tableModel.addRow(new Object[]{name, quantity, totalPrice});
        System.out.println("It has run");
//        itemTable.setModel(tableModel);
//        itemTable.revalidate();
//        itemTable.repaint();
    }
    public void quantityArrChange() {
        rowQuantity = quantityValue + " x ₱" + itemsPrices[Integer.parseInt(ItemEnterString)-1];
        itemsQuantity[Integer.parseInt(ItemEnterString)-1] -= quantityValue;

        // Call the method to update the JLabel quantities
    }
    public static void setRowTotalPrice(){
        int a = Integer.parseInt(ItemEnterString);
        System.out.println("ItemEnterString is " + ItemEnterString);
        int price = itemsPrices[a-1];

        rowTotalPrice = quantityValue * price;
    }
    public void setRowStringProductName(String a){
        int bb = Integer.parseInt(a);
        byte b = (byte) bb;
        switch (b){
            case 1:
                rowStringProductName = prodName[b-1];
                break;

            case 2:
                rowStringProductName = prodName[b-1];
                break;


            case 3:
                rowStringProductName = prodName[b-1];
                break;

            case 4:
                rowStringProductName = prodName[b-1];
                break;

            case 5:
                rowStringProductName = prodName[b-1];
                break;

            case 6:
                rowStringProductName = prodName[b-1];
                break;

            case 7:
                rowStringProductName = prodName[b-1];
                break;

            case 8:
                rowStringProductName = prodName[b-1];
                break;

            case 9:
                rowStringProductName = prodName[b-1];
                break;

            case 10:
                rowStringProductName = prodName[b-1];
                break;

            case 11:
                rowStringProductName = prodName[b-1];
                break;

            case 12:
                rowStringProductName = prodName[b-1];
                break;

            case 13:
                rowStringProductName = prodName[b-1];
                break;

            case 14:
                rowStringProductName = prodName[b-1];
                break;

            case 15:
                rowStringProductName = prodName[b-1];
                break;
        }
    }

public void resetValues(){
        tempForGetQuantity = 0;
        quantityValue = 0;
        rowQuantity = "";
        rowTotalPrice = 0;
        rowStringProductName = "";

}
    public static void main(String[] args) {
        JFrame myFrame = new VendingMUser("Vending Machine");
        myFrame.setVisible(true);

}

}
