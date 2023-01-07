
package assignment3;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Item {
    //variables
    private int type;
    private int amount;
    private double price;
    private int size;
    private String name;

    
//constructors
    public Item() {
    }

    public Item(int size, int amount, double price, String name){
        this.amount= amount;
        this.size= size;
        this.price= price;
        this.name= name;
    }
    public Item( int type,double price, String name) {
        this.type = type;
        this.price = price;
        this.name= name;
    }

    public Item(int amount, double price, int type, int size,String name) {
        this.amount = amount;
        this.price = price;
        this.type = type;
        this.size = size;
        this.name= name;

    }
    

//getters and setters
    public int getAmount() {
        return amount;
    }
    public double getPrice() {
        return price;
    }
    public int getType() {
        return type;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    //writing a receipt
    public void writeReceipt(Pizza pizza, Drink drink, Salad salad, Coupon coupon, Payment payment) {
        FileWriter file = null;
        PrintWriter output = null;
        
        try{
            file = new FileWriter("src\\assignment3\\sales.txt",true);
            output = new PrintWriter(file);
            
            output.println(pizza.getName());
        }catch(FileNotFoundException ex1){
            ex1.toString();
        } catch (IOException ex2){
            ex2.toString();
        } finally{
            if(file !=null){
                output.close();
            }
        }
    }
//Override Tostring

    @Override
    public String toString() {
        return "Item{" + "type=" + type + ", size=" + size +", amount=" + amount + '}';
    }



}
