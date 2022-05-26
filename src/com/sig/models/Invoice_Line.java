/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.models;

/**
 *
 * @author HP
 */
public class Invoice_Line {
    private String item;
    private int count;
    private double price;
    Invoice_Header header;

    public Invoice_Line() {
    }

    public Invoice_Line(String item, double price, int count, Invoice_Header header) {
        this.item = item;
        this.count = count;
        this.price = price;
        this.header = header;
    }
    
    
    public Invoice_Header getHeader(){
    return header;
    }
    
    public void setHeader(Invoice_Header invHeader){
    this.header =invHeader;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public double CalcLinetotal(){
    
        return count*price;
    }

    @Override
    public String toString() {
        return  header.getInvoice_Number()+ "," + item + "," + price + "," + count  ;
    }
    
    
}
