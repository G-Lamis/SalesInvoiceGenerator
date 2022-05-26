/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.models;

import com.sig.views.InvoiceForm;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class Invoice_Header_Table extends AbstractTableModel{
    private ArrayList<Invoice_Header> Invoices;
    private String[] columns={"Invoice ID","Invoice Date","Customer Name","Total Cost"};

    public Invoice_Header_Table(ArrayList<Invoice_Header> invoicesArray) {
        this.Invoices = invoicesArray;
    }
    

    @Override
    public int getRowCount() {
        return Invoices.size();
    }

    @Override
    public int getColumnCount() {
       return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoice_Header inv= Invoices.get(rowIndex);
        switch(columnIndex){
            case 0: return inv.getInvoice_Number();
            case 1: return InvoiceForm.date.format(inv.getInvoice_Date());
            case 2:return inv.getCustomer_Name();
            case 3: return inv.Sum_of_Lines();
        }
        return "";
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public ArrayList<Invoice_Header> getInvoices() {
        return Invoices;
    }
    
}
