/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.views;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;





/**
 *
 * @author HP
 */
public class Invoice_Dialog_Header extends JDialog{
    private JTextField CustomerName;
    private JTextField InvoiceDate;
    private JLabel Customer;
    private JLabel Date;
    private JButton Agree;
    private JButton Cancel;
    
    public Invoice_Dialog_Header(InvoiceForm form){
        Customer = new JLabel("Customer Name");
        CustomerName =new JTextField(20);
        Date = new JLabel("Invoice Date");
        InvoiceDate =new JTextField(20);
        Agree = new JButton("OK");
        Cancel = new JButton("Cancel");
        
        // insert action commands for both buttons to apply methods for them in action listener
        Agree.setActionCommand("applyNewInv");
        Cancel.setActionCommand("cancelNewInv");
        
        // add action listener on both buttons 
         Agree.addActionListener(form.getActionListener());
         Cancel.addActionListener(form.getActionListener());
         
         //add all fields to be displayed in the frame
         add(Customer);
         add(CustomerName);
         add(Date);
         add(InvoiceDate);
         add(Agree);
         add(Cancel);
         setLayout(new GridLayout(3,2));
         pack();
         
        
    }

    public JTextField getCustomerName() {
        return CustomerName;
    }

    public JTextField getInvoiceDate() {
        return InvoiceDate;
    }

    public JLabel getCustomer() {
        return Customer;
    }

    public JLabel getDate() {
        return Date;
    }

    public JButton getAgree() {
        return Agree;
    }

    public JButton getCancel() {
        return Cancel;
    }
    
    
}
