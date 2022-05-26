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
public class InvoiceDialog extends JDialog{
    //declaration of all fields 
    private JTextField Product_Name;
    private JTextField Product_Price;
    private JTextField Product_Number;
    private JLabel P_Name;
    private JLabel P_Price;
    private JLabel P_Number;
    private JButton Approve;
    private JButton Deny;
    
    
    
    public InvoiceDialog(InvoiceForm form){
        
        P_Name =new JLabel("Item");
        Product_Name =new JTextField(15);
        
        P_Price =new JLabel("Price");
        Product_Price =new JTextField(15);
        
        P_Number =new JLabel("Count");
        Product_Number =new JTextField(15);
        
        Approve =new JButton("OK");
        Deny =new JButton("Cancel");
        
        //insert action commands for the buttons
        Approve.setActionCommand("insertNewLineOk");
        Deny.setActionCommand("cancelNewLine");
        
        //apply action listener for both buttons
        Approve.addActionListener(form.getActionListener());
        Deny.addActionListener(form.getActionListener());
        
        add(P_Name);
        add(Product_Name);
        add(P_Price);
        add(Product_Price);
        add(P_Number);
        add(Product_Number);
        add(Approve);
        add(Deny);
        setLayout(new GridLayout(4,3));
        setLocation(200,200);
         pack();
    }

    public JTextField getProduct_Name() {
        return Product_Name;
    }

    public JTextField getProduct_Price() {
        return Product_Price;
    }

    public JTextField getProduct_Number() {
        return Product_Number;
    }
    
    
    
}
