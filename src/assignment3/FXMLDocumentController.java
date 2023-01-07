
package assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Tab order;
    @FXML
    private CheckBox chkChicken;
    @FXML
    private CheckBox chkHawaiian;
    @FXML
    private CheckBox chkSeafood;
    @FXML
    private CheckBox chkVeggie;
    @FXML
    private RadioButton rbPSmall;
    @FXML
    private RadioButton rbPMedium;
    @FXML
    private ToggleGroup tgPizza;
    @FXML
    private RadioButton rbPLarge;
    @FXML
    private Slider sidPamount;
    @FXML
    private TextArea taOutput;
    @FXML
    private Button btnOrder;

    //Creating Observable list for Drinks
    @FXML
    private ComboBox<String> cboDrink;
    @FXML
    private Slider sidDAmount;
    @FXML
    private ChoiceBox<String> cbSalad;
    @FXML
    private ToggleGroup tgSalad;
    @FXML
    private RadioButton rbSsmall;

    @FXML
    private RadioButton rbSmedium;
    @FXML
    private RadioButton rbSlarge;
    @FXML
    private Slider sidSAmount;
    @FXML
    private Button btnPreset;
    @FXML
    private Button btnDreset;
    @FXML
    private Button btnSreset;
    @FXML
    private Button btnResetAll;
    @FXML
    private Button btnConfirm;
    @FXML
    private ListView<String> lvCoupon;
    @FXML
    private TextField tfPayment;

    //creating two arraylist that will store content 
    private ArrayList<String> contentToAppend = new ArrayList<>();
    private ArrayList<String> contentToAppend1 = new ArrayList<>();
    //setting paths for Summary.txt and sales.txt
    private static String salesFilePath = "D:\\java files\\Assignment3\\src\\assignment3\\Sales.txt";
    private static String summaryFilePath = "D:\\java files\\Assignment3\\src\\assignment3\\Summary.txt";

    @FXML
    private TextArea taSummary;
    @FXML
    private Button btnShowSummary;
    @FXML
    private Button btnShowSales;
    @FXML
    private TextArea taSales;

//initalising code before runing    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // creating drink items
        cboDrink.getItems().addAll("Coke", "Pepsi", "Sprite");

        //creating salad items
        cbSalad.getItems().addAll("Caeser", "Chicken", "Garden");

        //creating listeview items
        lvCoupon.getItems().addAll("$2", "$5", "$10");
    }

//Outputing the Order
    @FXML
    private void handleOrderAction(ActionEvent event) {

        //Creating Pizza, drink, salad and coupon object
        Drink drink = new Drink();
        Pizza pizza = new Pizza();
        Salad salad = new Salad();
        Coupon coupon = new Coupon();
        Payment payment = new Payment();
        double subTotal = 0.0;

        //Code for Pizza order
        //initialising variable
        //setting selected radio button to a variable + assigning pizza size
        RadioButton selectedPizzaRadioButton = (RadioButton) tgPizza.getSelectedToggle();
        if (rbPSmall.isSelected()) {
            pizza.setSize(1);
        } else if (rbPMedium.isSelected()) {
            pizza.setSize(2);
        } else if (rbPLarge.isSelected()) {
            pizza.setSize(3);
        }
        //setting selected pizza amount from slider to variable
        String Pamount = String.valueOf((int) sidPamount.getValue());
        pizza.setAmount(Integer.valueOf(Pamount));

        //Setting pizza type + pizza Name + Pizza price 
        int Psize = pizza.getSize();
        if (chkChicken.isSelected()) {
            pizza.setType(1);
            if (Psize == 1) {
                pizza.setPrice(5.95);
            } else if (Psize == 2) {
                pizza.setPrice(6.95);
            } else if (Psize == 3) {
                pizza.setPrice(7.95);
            }
            pizza.setName(chkChicken.getText());
        }
        if (chkHawaiian.isSelected()) {
            pizza.setType(2);
            if (Psize == 1) {
                pizza.setPrice(5.95);
            } else if (Psize == 2) {
                pizza.setPrice(6.95);
            } else if (Psize == 3) {
                pizza.setPrice(7.95);
            }
            pizza.setName(chkHawaiian.getText());
        }
        if (chkSeafood.isSelected()) {
            pizza.setType(3);
            if (Psize == 1) {
                pizza.setPrice(7.95);
            } else if (Psize == 2) {
                pizza.setPrice(8.95);
            } else if (Psize == 3) {
                pizza.setPrice(9.95);
            }
            pizza.setName(chkSeafood.getText());
        }
        if (chkVeggie.isSelected()) {
            pizza.setType(4);
            if (Psize == 1) {
                pizza.setPrice(4.95);
            } else if (Psize == 2) {
                pizza.setPrice(5.95);
            } else if (Psize == 3) {
                pizza.setPrice(6.95);
            }
            pizza.setName(chkVeggie.getText());
        }

        //Code for Drink Order     
        //creating variables 
        drink.setName(cboDrink.getSelectionModel().getSelectedItem());
        int Dindex = cboDrink.getSelectionModel().getSelectedIndex();
        drink.setPrice(1.20);
        drink.setType(Dindex + 1);
        String Damount = String.valueOf((int) sidDAmount.getValue());
        drink.setAmount(Integer.valueOf(Damount));
        //output

        //Code for Salad
        //setting salad size 
        RadioButton selectedSaladRadioButton = (RadioButton) tgSalad.getSelectedToggle();
        if (rbSsmall.isSelected()) {
            salad.setSize(1);
        } else if (rbSmedium.isSelected()) {
            salad.setSize(2);
        } else if (rbSlarge.isSelected()) {
            salad.setSize(3);
        }

        //setting salad type + Name
        int Ssize = salad.getSize();
        int Stype = cbSalad.getSelectionModel().getSelectedIndex();
        if (Stype == 0) {
            salad.setType(1);
            salad.setName(cbSalad.getSelectionModel().getSelectedItem());
        } else if (Stype == 1) {
            salad.setType(2);
            salad.setName(cbSalad.getSelectionModel().getSelectedItem());
        } else if (Stype == 2) {
            salad.setType(3);
            salad.setName(cbSalad.getSelectionModel().getSelectedItem());
        }
        //setting salad price 
        if (Ssize == 1) {
            salad.setPrice(1.50);
        } else if (Ssize == 2) {
            salad.setPrice(2.00);
        } else if (Ssize == 3) {
            salad.setPrice(2.50);
        }

        //setting salad amount
        String Samount = String.valueOf((int) sidSAmount.getValue());
        salad.setAmount(Integer.valueOf(Samount));

        //Code for Coupon
        int Ctype = lvCoupon.getSelectionModel().getSelectedIndex();
        //set coupon type and coupon amount
        if (Ctype == 0) {
            coupon.setType(1);
            coupon.setCouponAmount(2.0);
        } else if (Ctype == 1) {
            coupon.setType(2);
            coupon.setCouponAmount(5.0);
        } else if (Ctype == 2) {
            coupon.setType(3);
            coupon.setCouponAmount(10.0);
        }

        //getting the subtotal
        subTotal = (pizza.getAmount() * pizza.getPrice()) + (drink.getAmount() * drink.getPrice()) + (salad.getAmount() * salad.getPrice());
        payment.setSubTotal(subTotal);

        //Outputting the Order
        taOutput.appendText("Chiang Mai Pizza Shop\n");
        taOutput.appendText("\tOrder Details\n");
        //output Pizza order
        if (pizza.getAmount() != 0) {
            for (int i = 0; i < pizza.getAmount(); i++) {
                taOutput.appendText(pizza.getName() + "\t" + selectedPizzaRadioButton.getText() + "\t\t" + pizza.getPrice() + "\n");
            }
        }
        //Output Drink Order
        if (drink.getAmount() != 0) {
            for (int j = 0; j < drink.getAmount(); j++) {
                taOutput.appendText(drink.getName() + "\t" + "\t\t\t" + drink.getPrice() + "\n");
            }
        }
        //Output Salad Order
        if (salad.getAmount() != 0) {
            for (int k = 0; k < salad.getAmount(); k++) {
                taOutput.appendText(salad.getName() + "\t" + selectedSaladRadioButton.getText() + "\t\t" + salad.getPrice() + "\n");
            }
        }
        //Output Coupon Amount
        taOutput.appendText("COUPON\t\t\t\t" + coupon.getCouponAmount() + "\n");
        //Output the rest of the info
        taOutput.appendText("----------------------------------------------------------\n");
        taOutput.appendText("SUBTOTAL\t\t\t\t" + subTotal + "\n\n");

        taOutput.appendText("Input Payment Amount:\n");
        taOutput.appendText("PAYMENT:\t\t\t\t" + tfPayment.getText());
        payment.setPaymentAmount(Double.parseDouble(tfPayment.getText()));

        //getting the values for the final receipt, Summary.txt and Sales.txt
        outputReceipt(pizza, drink, salad, coupon, payment);
        makeSummary(pizza, drink, salad, coupon, payment);
        outputSales(pizza, drink, salad, coupon);

    }

    @FXML
    //resetting Pizza order
    private void handlePizzaResetAction(ActionEvent event) {
        chkChicken.setSelected(false);
        chkHawaiian.setSelected(false);
        chkSeafood.setSelected(false);
        chkVeggie.setSelected(false);
        tgPizza.selectToggle(null);
        sidPamount.setValue(0);
    }

    @FXML
    //resetting drink order
    private void handleDrinkResetAction(ActionEvent event) {
        cboDrink.getSelectionModel().clearSelection();
        sidDAmount.setValue(0);
    }

    @FXML
    //resetting salad order
    private void handleSaladResetAction(ActionEvent event) {
        cbSalad.getSelectionModel().clearSelection();
        tgSalad.selectToggle(null);
        sidSAmount.setValue(0);
    }

    @FXML
    //resetting the whole order
    private void handleResetAllButtonAction(ActionEvent event) {
        chkChicken.setSelected(false);
        chkHawaiian.setSelected(false);
        chkSeafood.setSelected(false);
        chkVeggie.setSelected(false);
        tgPizza.selectToggle(null);
        sidPamount.setValue(0);
        cboDrink.getSelectionModel().clearSelection();
        sidDAmount.setValue(0);
        cbSalad.getSelectionModel().clearSelection();
        tgSalad.selectToggle(null);
        sidSAmount.setValue(0);
        taOutput.setText("");
    }

//will print out the receipt 
    @FXML
    private void handleConfirmButtonAction(ActionEvent event) {
        for (int i = 0; i < contentToAppend.size(); i++) {
            taOutput.appendText(contentToAppend.get(i));
        }
    }
    //preparing the final receipt inside an array list that will be filled with inputs when the method is called upon

    public void outputReceipt(Pizza pizza, Drink drink, Salad salad, Coupon coupon, Payment payment) {
        Date date = new Date();

        //get change
        double change = (coupon.getCouponAmount() + payment.getPaymentAmount()) - payment.getSubTotal();
        //setting new coupon
        double subTotal = payment.getSubTotal();
        if (subTotal > 0 && subTotal <= 20) {
            coupon.setNewCoupon(0.0);
        } else if (subTotal < 50) {
            coupon.setNewCoupon(2.0);
        } else if (subTotal < 100) {
            coupon.setNewCoupon(5.0);
        } else if (subTotal >= 100) {
            coupon.setNewCoupon(10.0);
        }

        RadioButton selectedPizzaRadioButton = (RadioButton) tgPizza.getSelectedToggle();
        RadioButton selectedSaladRadioButton = (RadioButton) tgSalad.getSelectedToggle();
        contentToAppend.add("\n\n\n\tCHIANG MAI PIZZA SHOP\n" + "\n\tTAX INVOICE(CPS)" + "\n\t#CMPS-1\n");
        //output Pizza order
        if (pizza.getAmount() != 0) {
            for (int i = 0; i < pizza.getAmount(); i++) {
                contentToAppend.add(pizza.getName() + "\t" + selectedPizzaRadioButton.getText() + "\t\t" + pizza.getPrice() + "\n");
            }
        }
        //Output Drink Order
        if (drink.getAmount() != 0) {
            for (int j = 0; j < drink.getAmount(); j++) {
                contentToAppend.add(drink.getName() + "\t" + "\t\t\t" + drink.getPrice() + "\n");
            }
        }
        //Output Salad Order
        if (salad.getAmount() != 0) {
            for (int k = 0; k < salad.getAmount(); k++) {
                contentToAppend.add(salad.getName() + "\t" + selectedSaladRadioButton.getText() + "\t\t" + salad.getPrice() + "\n");
            }
        }
        //Output Coupon Amount
        contentToAppend.add("COUPON\t\t\t\t" + coupon.getCouponAmount() + "\n");
        //Output the rest of the info
        contentToAppend.add("----------------------------------------------------------\n");
        contentToAppend.add("SUBTOTAL\t\t\t" + payment.getSubTotal() + "\n");
        contentToAppend.add("PAYMENT\t\t\t" + payment.getPaymentAmount() + "\n");
        contentToAppend.add("CHANGE\t\t\t\t" + change + "\n");
        contentToAppend.add("NEW COUPON\t\t" + coupon.getNewCoupon() + "\n");
        contentToAppend.add("\n" + date.toString() + " VAT INCLUDED\n");
        contentToAppend.add("----------------------------------------------------------");

    }

    //create a summarytxt file by adding to "contentToAppend1" Array list and writing it out
    public void makeSummary(Pizza pizza, Drink drink, Salad salad, Coupon coupon, Payment payment) {
        //creating objects from menu,date and array list classes
        Date date = new Date();

        contentToAppend1.add("\n\tCHIANG MAI PIZZA SHOP\n" + "\n\tTAX INVOICE(CPS)" + "\n\t#CMPS-1\n" + "\tDAILY SUMMARY OF SALES\n\n" + "ITEM\t\tSIZE\tUNITPRICE\t#of Orders\tAmount");

        //getting total prices assuming this order only takes one type of pizza, drink and salad
        double ptotal = pizza.getAmount() * pizza.getPrice();
        double dTotal = drink.getAmount() * drink.getPrice();
        double sTotal = salad.getAmount() * salad.getPrice();
        double salesTotal = ptotal + dTotal + sTotal;

        //outputting Summary
        if (pizza.getType() == 1) {
            if (pizza.getSize() == 1) {
                contentToAppend1.add("\nCHICKEN\t\tS\t5.95\t\t" + pizza.getAmount() + "\t\t" + ptotal);
                contentToAppend1.add("\nCHICKEN\t\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tS\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tM\t8.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tL\t9.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tS\t4.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tM\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tL\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + ptotal);
            } else if (pizza.getSize() == 2) {
                contentToAppend1.add("\nCHICKEN\t\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t6.95\t\t" + pizza.getAmount() + "\t\t" + ptotal);
                contentToAppend1.add("\nCHICKEN\t\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tS\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tM\t8.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tL\t9.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tS\t4.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tM\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tL\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + ptotal);
            } else if (pizza.getSize() == 3) {
                contentToAppend1.add("\nCHICKEN\t\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t7.95\t\t" + pizza.getAmount() + "\t\t" + ptotal);
                contentToAppend1.add("\nHAWAIIAN\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tS\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tM\t8.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tL\t9.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tS\t4.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tM\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tL\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + ptotal);
            }
        } else if (pizza.getType() == 2) {
            if (pizza.getSize() == 1) {
                contentToAppend1.add("\nCHICKEN\t\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tS\t5.95\t\t" + pizza.getAmount() + "\t\t" + ptotal);
                contentToAppend1.add("\nHAWAIIAN\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tS\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tM\t8.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tL\t9.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tS\t4.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tM\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tL\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + ptotal);

            } else if (pizza.getSize() == 2) {
                contentToAppend1.add("\nCHICKEN\t\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tM\t6.95\t\t" + pizza.getAmount() + "\t\t" + ptotal);
                contentToAppend1.add("\nHAWAIIAN\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tS\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tM\t8.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tL\t9.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tS\t4.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tM\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tL\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + ptotal);
            } else if (pizza.getSize() == 3) {
                contentToAppend1.add("\nCHICKEN\t\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tL\t7.95\t\t" + pizza.getAmount() + "\t\t" + ptotal);
                contentToAppend1.add("\nSEAFOOD\t\tS\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tM\t8.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tL\t9.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tS\t4.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tM\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tL\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + ptotal);
            }
        } else if (pizza.getType() == 3) {
            if (pizza.getSize() == 1) {
                contentToAppend1.add("\nCHICKEN\t\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tS\t7.95\t\t" + pizza.getAmount() + "\t\t" + ptotal);
                contentToAppend1.add("\nSEAFOOD\t\tM\t8.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tL\t9.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tS\t4.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tM\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tL\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + ptotal);
            } else if (pizza.getSize() == 2) {
                contentToAppend1.add("\nCHICKEN\t\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tS\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tM\t8.95\t\t" + pizza.getAmount() + "\t\t" + ptotal);
                contentToAppend1.add("\nSEAFOOD\t\tL\t9.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tS\t4.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tM\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tL\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + ptotal);
            } else if (pizza.getSize() == 3) {
                contentToAppend1.add("\nCHICKEN\t\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tS\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tM\t8.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tL\t9.95\t\t" + pizza.getAmount() + "\t\t" + ptotal);
                contentToAppend1.add("\nVEGGIE\t\tS\t4.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tM\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tL\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + ptotal);
            }
        } else if (pizza.getType() == 4) {
            if (pizza.getSize() == 1) {
                contentToAppend1.add("\nCHICKEN\t\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tS\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tM\t8.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tL\t9.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tS\t4.95\t\t" + pizza.getAmount() + "\t\t" + ptotal);
                contentToAppend1.add("\nVEGGIE\t\tM\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tL\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + ptotal);
            } else if (pizza.getSize() == 2) {
                contentToAppend1.add("\nCHICKEN\t\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tS\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tM\t8.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tL\t9.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tS\t4.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tM\t5.95\t\t" + pizza.getAmount() + "\t\t" + ptotal);
                contentToAppend1.add("\nVEGGIE\t\tL\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + ptotal);
            } else if (pizza.getSize() == 3) {
                contentToAppend1.add("\nCHICKEN\t\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tS\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tM\t6.95\t\t0\t\t0.00");
                contentToAppend1.add("\nHAWAIIAN\tL\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tS\t7.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tM\t8.95\t\t0\t\t0.00");
                contentToAppend1.add("\nSEAFOOD\t\tL\t9.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tS\t4.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tM\t5.95\t\t0\t\t0.00");
                contentToAppend1.add("\nVEGGIE\t\tL\t6.95\t\t" + pizza.getAmount() + "\t\t" + ptotal);
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + ptotal);

            }
        }

        contentToAppend1.add("\n---------------------------------------------------------------");
        if (drink.getType() == 1) {
            contentToAppend1.add("\nCOKE\t\t\t1.20\t\t" + drink.getAmount() + "\t\t" + (dTotal * 100) / 100 + "0");
            contentToAppend1.add("\nPEPSI\t\t\t1.20\t\t0\t\t0.00");
            contentToAppend1.add("\nSPRITE\t\t\t1.20\t\t0\t\t0.00");
            contentToAppend1.add("\n---------------------------------------------------------------");
            contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + (dTotal * 100) / 100 + "0");
        } else if (drink.getType() == 2) {
            contentToAppend1.add("\nCOKE\t\t\t1.20\t\t0\t\t0.00");
            contentToAppend1.add("\nPEPSI\t\t\t1.20\t\t" + drink.getAmount() + "\t\t" + (dTotal * 100) / 100 + "0");
            contentToAppend1.add("\nSPRITE\t\t\t1.20\t\t0\t\t0.00");
            contentToAppend1.add("\n---------------------------------------------------------------");
            contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + (dTotal * 100) / 100 + "0");
        } else if (drink.getType() == 3) {
            contentToAppend1.add("\nCOKE\t\t\t1.20\t\t0\t\t0.00");
            contentToAppend1.add("\nPEPSI\t\t\t1.20\t\t0\t\t0.00");
            contentToAppend1.add("\nSPRITE\t\t\t1.20\t\t" + drink.getAmount() + "\t\t" + (dTotal * 100) / 100 + "0");
            contentToAppend1.add("\n---------------------------------------------------------------");
            contentToAppend1.add("\nDRINK TOTAL\t\t\t\t\t\t" + (dTotal * 100) / 100 + "0");
        } else if (drink.getType() == 0) {
            contentToAppend1.add("\nCOKE\t\t\t1.20\t\t0\t\t0.00");
            contentToAppend1.add("\nPEPSI\t\t\t1.20\t\t0\t\t0.00");
            contentToAppend1.add("\nSPRITE\t\t\t1.20\t\t0\t\t0.00");
            contentToAppend1.add("\n---------------------------------------------------------------");
            contentToAppend1.add("\nPIZZA TOTAL\t\t\t\t\t\t" + (dTotal * 100) / 100 + "0");
        }

        contentToAppend1.add("\n---------------------------------------------------------------");
        if (salad.getType() == 0) {
            contentToAppend1.add("\nCAESAR\t\tS\t1.50\t\t0\t\t0.00");
            contentToAppend1.add("\nCAESAR\t\tM\t2.00\t\t0\t\t0.00");
            contentToAppend1.add("\nCAESAR\t\tL\t2.50\t\t0\t\t0.00");
            contentToAppend1.add("\nCHICKEN\t\tS\t1.50\t\t0\t\t0.00");
            contentToAppend1.add("\nCHICKEN\t\tM\t2.00\t\t0\t\t0.00");
            contentToAppend1.add("\nCHICKEN\t\tL\t2.50\t\t0\t\t0.00");
            contentToAppend1.add("\nGARDEN\t\tS\t1.50\t\t0\t\t0.00");
            contentToAppend1.add("\nGARDEN\t\tM\t2.00\t\t0\t\t0.00");
            contentToAppend1.add("\nGARDEN\t\tL\t2.50\t\t0\t\t0.00");
            contentToAppend1.add("\n---------------------------------------------------------------");
            contentToAppend1.add("\nSALAD TOTAL\t\t\t\t\t\t" + sTotal + "0");
        } else if (salad.getType() == 1) {
            if (salad.getSize() == 1) {
                contentToAppend1.add("\nCAESAR\t\tS\t1.50\t\t" + salad.getAmount() + "\t\t" + sTotal + "0");
                contentToAppend1.add("\nCAESAR\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nSALAD TOTAL\t\t\t\t\t\t" + sTotal + "0");
            } else if (salad.getSize() == 2) {
                contentToAppend1.add("\nCAESAR\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tM\t2.00\t\t" + salad.getAmount() + "\t\t" + sTotal + "0");
                contentToAppend1.add("\nCAESAR\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nSALAD TOTAL\t\t\t\t\t\t" + sTotal + "0");
            } else if (salad.getSize() == 3) {
                contentToAppend1.add("\nCAESAR\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tL\t2.50\t\t" + salad.getAmount() + "\t\t" + sTotal + "0");
                contentToAppend1.add("\nCHICKEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nSALAD TOTAL\t\t\t\t\t\t" + sTotal + "0");
            }
        } else if (salad.getType() == 2) {
            if (salad.getSize() == 1) {
                contentToAppend1.add("\nCAESAR\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tS\t1.50\t\t" + salad.getAmount() + "\t\t" + sTotal + "0");
                contentToAppend1.add("\nCHICKEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nSALAD TOTAL\t\t\t\t\t\t" + sTotal + "0");
            } else if (salad.getSize() == 2) {
                contentToAppend1.add("\nCAESAR\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t2.00\t\t" + salad.getAmount() + "\t\t" + sTotal + "0");
                contentToAppend1.add("\nCHICKEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nSALAD TOTAL\t\t\t\t\t\t" + sTotal + "0");
            } else if (salad.getSize() == 3) {
                contentToAppend1.add("\nCAESAR\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t2.50\t\t" + salad.getAmount() + "\t\t" + sTotal + "0");
                contentToAppend1.add("\nGARDEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nSALAD TOTAL\t\t\t\t\t\t" + sTotal + "0");
            }
        } else if (salad.getType() == 3) {
            if (salad.getSize() == 1) {
                contentToAppend1.add("\nCAESAR\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tS\t1.50\t\t" + salad.getAmount() + "\t\t" + sTotal + "0");
                contentToAppend1.add("\nGARDEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nSALAD TOTAL\t\t\t\t\t\t" + sTotal + "0");
            } else if (salad.getSize() == 2) {
                contentToAppend1.add("\nCAESAR\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tM\t2.00\t\t" + salad.getAmount() + "\t\t" + sTotal + "0");
                contentToAppend1.add("\nGARDEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nSALAD TOTAL\t\t\t\t\t\t" + sTotal + "0");
            } else if (salad.getSize() == 3) {
                contentToAppend1.add("\nCAESAR\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCAESAR\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nCHICKEN\t\tL\t2.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tS\t1.50\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tM\t2.00\t\t0\t\t0.00");
                contentToAppend1.add("\nGARDEN\t\tL\t2.50\t\t" + salad.getAmount() + "\t\t" + sTotal + "0");
                contentToAppend1.add("\n---------------------------------------------------------------");
                contentToAppend1.add("\nSALAD TOTAL\t\t\t\t\t\t" + sTotal + "0");
            }
        }
        contentToAppend1.add("\n---------------------------------------------------------------");
        contentToAppend1.add("\nSALES TOTAL\t\t\t\t\t\t" + salesTotal);
        contentToAppend1.add("\nCOUPON PAYMENT\t\t\t\t\t\t" + coupon.getCouponAmount());
        contentToAppend1.add("\n---------------------------------------------------------------");
        contentToAppend1.add("\nCOUPON PAYMENT\t\t\t\t\t\t" + (payment.getSubTotal() - coupon.getCouponAmount()));
        contentToAppend1.add("\n---------------------------------------------------------------");
        contentToAppend1.add("\n---------------------------------------------------------------");
        contentToAppend1.add("\n" + date.toString());

        //Writing on the Summary.txt file
        File file = new File(summaryFilePath);
        try {
            Scanner sc = new Scanner(file);
            BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
            buffer.flush();
            for (int i = 0; i < contentToAppend1.size(); i++) {
                buffer.write(contentToAppend1.get(i));
            }
            buffer.close();

        } catch (Exception e) {
        }
    }

    //Method to read summary.txt
    public void readSummary() {
        File file = new File(summaryFilePath);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                taSummary.appendText(sc.nextLine() + "\n");
            }
        } catch (IOException e) {

        }
    }

    public void outputSales(Pizza pizza, Drink drink, Salad salad, Coupon coupon) {
        String contentToAppendA = "\n" + pizza.getType() + "" + pizza.getSize() + "" + pizza.getAmount() + "" + drink.getType() + "" + drink.getAmount() + "" + salad.getType() + "" + salad.getSize() + "" + salad.getAmount() + "" + coupon.getType();
        File file = new File("D:\\java files\\Assignment3\\src\\assignment3\\Sales.txt");
        try {
            Scanner sc = new Scanner(file);
            try ( BufferedWriter buffer = new BufferedWriter(new FileWriter(file, true))) {
                buffer.flush();
                buffer.write(contentToAppendA);
            }
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (IOException e) {
        }

    }

    //creating creatSales method that will read sales.txt
    public void readSales() {
        File file = new File("D:\\java files\\Assignment3\\src\\assignment3\\Sales.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                taSales.appendText(sc.nextLine() + "\n");
            }
        } catch (IOException e) {
        }
    }

    //use readSummary method to write Summary in textAreaSummary
    @FXML
    private void btnShowSummaryAction(ActionEvent event) {
        readSummary();

    }

    //read sales.txt
    @FXML
    private void btnShowSalesAction(ActionEvent event) {
        readSales();
    }

}
