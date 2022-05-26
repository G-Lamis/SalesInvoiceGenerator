/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class Invoice_Lines_Table extends AbstractTableModel{
    private ArrayList<Invoice_Line> InvoiceLines;
    private String[] columns={"Item","Price","Count","total"};
    public Invoice_Lines_Table(ArrayList<Invoice_Line> InvoiceLines) {
        this.InvoiceLines = InvoiceLines;
    }
    
    @Override
    public int getRowCount() {
       return InvoiceLines ==null ? 0 : InvoiceLines.size(); //return all rows in the invoiceline array
    }

    @Override
    public int getColumnCount() {
        return columns.length;        //return all columns of the invoice line array
    }

     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {   //return content of the invoice array
        Invoice_Line inv= InvoiceLines.get(rowIndex);
        switch(columnIndex){
            case 0: return inv.getItem();
            case 1:return inv.getPrice();
            case 2:return inv.getCount();
            case 3: return inv.CalcLinetotal();
            
        }
        return "";
    }
    
    @Override
    public String getColumnName(int column) {                //return columns name
        return columns[column];
    }
    
}
