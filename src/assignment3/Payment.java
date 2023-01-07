
package assignment3;

import java.util.Date;
import javafx.scene.control.RadioButton;


public class Payment extends Item{
    
    private double PaymentAmount;

    public double getPaymentAmount() {
        return PaymentAmount;
    }

    public void setPaymentAmount(double PaymentAmount) {
        this.PaymentAmount = PaymentAmount;
    }

    private double subTotal;

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }


}
