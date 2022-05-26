/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.models;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public class Invoice_Header {
    private int Invoice_Number;
    private String Customer_Name;
    private Date Invoice_Date;
    private ArrayList<Invoice_Line> lines;
    private DateFormat Date =new SimpleDateFormat("DD-MM-YYYY");

    public Invoice_Header() {
    }

    public Invoice_Header(int Invoice_Number, String Customer_Name, Date Invoice_Date) {
        this.Invoice_Number = Invoice_Number;
        this.Customer_Name = Customer_Name;
        this.Invoice_Date = Invoice_Date;
    }

    public Date getInvoice_Date() {
        return Invoice_Date;
    }

    public void setInvoice_Date(Date invoice_Date) {
        this.Invoice_Date = invoice_Date;
    }

    public int getInvoice_Number() {
        return Invoice_Number;
    }

    public void setInvoice_Number(int invoice_Number) {
        this.Invoice_Number = invoice_Number;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String CustomerName) {
        this.Customer_Name = CustomerName;
    }

    public void setLines(ArrayList<Invoice_Line> lines) {
        this.lines = lines;
    }
    
    public ArrayList<Invoice_Line> getLines() {
        if(lines==null){
           lines=new ArrayList<>();
        }
        return lines;
    }
     public double Sum_of_Lines(){
        double total=0.0;
        for(int i=0;i<getLines().size();i++){
        total += getLines().get(i).CalcLinetotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return Invoice_Number + "," + Date.format(Invoice_Date) + "," + Customer_Name;
    }
    
}
