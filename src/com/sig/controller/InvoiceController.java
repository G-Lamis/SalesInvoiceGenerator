/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.controller;

import com.sig.models.Invoice_Header;
import com.sig.models.Invoice_Header_Table;
import com.sig.models.Invoice_Line;
import com.sig.models.Invoice_Lines_Table;
import com.sig.views.Invoice_Dialog_Header;
import com.sig.views.InvoiceDialog;
import com.sig.views.InvoiceForm;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileWriter;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class InvoiceController implements ActionListener{
    private Invoice_Dialog_Header dialogHeader;
    private InvoiceDialog dialogLine;
    private InvoiceForm form;
   
    
    public InvoiceController(InvoiceForm form){
        this.form=form;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      switch ( e.getActionCommand()){
      
          case "Load File":
              loadFile();
              break;
              
          case "Save File":
              saveFile();
              break;
              
          case "Create New Invoice":
              createInvoice();
              break;   
          
          case "Delete Invoice":
              deleteInvoice();
              break;
              
          case "Save":
              NewLine();
              break;
              
          case "Cancel":
              deleteLine();
              break;   
              
          case "applyNewInv" :
              OkNewInvDialog();
              break;
              
          case "cancelNewInv" :
              CancelInvoice();
              break;
              
          case "insertNewLineOk" :
              Insert_New_Line();
              break;
          
           case "cancelNewLine" :
              Cancel_New_Line();
              break;
      }
          
    }
    private void loadFile(){
        JFileChooser file =new JFileChooser();
       try{ 
        int result=file.showOpenDialog(form);
         ArrayList<Invoice_Header> invoiceHeaders=new ArrayList<>();  //create an empty array of invoice headers to fill it when reading data from the file
        if(result==JFileChooser.APPROVE_OPTION){
        File headerFile= file.getSelectedFile();
//        FileReader FileRead=new FileReader(headerFile);
//        BufferedReader bufferReader=new BufferedReader(FileRead);
        Path headerPath= Paths.get(headerFile.getAbsolutePath());
        List <String> headerLines =Files.readAllLines(headerPath);
        for(String headerLine :headerLines){
           String[] segment=headerLine.split(",");
           String Segment0=segment[0];
           String Segment1=segment[1];
           String Segment2=segment[2];
           int InvoiceCode=Integer.parseInt(Segment0); //convert invoice id from string to integer
           Date invoiceDate=InvoiceForm.date.parse(Segment1);  //convert the string of the date into dateformat
            
            Invoice_Header header=new Invoice_Header(InvoiceCode,Segment2,invoiceDate); //create object header from class invoiceheader
            invoiceHeaders.add(header);
        }
        form.setInvoicesArray(invoiceHeaders);
        }
        
        result=file.showOpenDialog(form);
        if(result==JFileChooser.APPROVE_OPTION){
            File TheLineFile =file.getSelectedFile();
            Path TheLinePath =Paths.get(TheLineFile.getAbsolutePath());
            List<String> LineLines=Files.readAllLines(TheLinePath);
            ArrayList<Invoice_Line> invoiceLines=new ArrayList<>();
            for(String LineLine:LineLines){
                String[] LineSegment=LineLine.split(",");
                String LineSegment_0=LineSegment[0]; //invoice id int
                String LineSegment_1=LineSegment[1]; //item string
                String LineSegment_2 =LineSegment[2]; //price double
                String LineSegment_3=LineSegment[3]; //count int
                int InvoiceCode_1=Integer.parseInt(LineSegment_0);
                double itemPrice=Double.parseDouble(LineSegment_2);
                int itemCount=Integer.parseInt(LineSegment_3);
                Invoice_Header Invoice=form.getInvCode(InvoiceCode_1);
                Invoice_Line invoiceLine=new Invoice_Line(LineSegment_2,itemPrice,itemCount,Invoice);
                Invoice.getLines().add(invoiceLine);
                   
            } 
        Invoice_Header_Table headerTableModel=new Invoice_Header_Table(invoiceHeaders);
        form.setHeaderTableModel(headerTableModel);
        form.getI_invoices().setModel(headerTableModel);
        System.out.println("show result");
        ArrayList<Invoice_Header> invHeaderArray=form.getInvoicesArray();
        ArrayList<Invoice_Line> invLineArray=form.getInvoiceLinesArray();
       // System.out.println(invHeaderArray.toString());
        String InvoiceHeader="";
        String InvoiceLine="";
       for(com.sig.models.Invoice_Header Invoice_Header : invHeaderArray){
            InvoiceHeader+=Invoice_Header.toString();
            InvoiceHeader+="\n";
            for(com.sig.models.Invoice_Line Invoice_Line:Invoice_Header.getLines()){
            InvoiceLine+=Invoice_Line.toString();
            InvoiceLine+="\n";  //to write the lines sequential
           
            }
           }
        System.out.println("the content of the invoices table ");
        System.out.println(InvoiceHeader);
        System.out.println("the content of the invoicelines table ");
        System.out.println(InvoiceLine);
       }}catch(IOException e){
           
           JOptionPane.showMessageDialog(form, e, "Error", JOptionPane.ERROR_MESSAGE);
           
           }catch (ParseException ex) {
               // Logger.getLogger(InvoiceListener.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(form, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    

    private void saveFile() {
       ArrayList<Invoice_Header> InvoiceHeaderArray=form.getInvoicesArray();
       JFileChooser New_File=new JFileChooser();
      try{ int result=New_File.showOpenDialog(form);    //open the dialog once pressed on the save file button
       if(result==JFileChooser.APPROVE_OPTION){
           File invoicesFile = New_File.getSelectedFile();  //select the file to store data
           FileWriter headerFileWriter=new FileWriter(invoicesFile);
           String invoiceheader ="";
           String invoicelines="";
           // nested for loop to write each row in the invoice table in the new file  
           for(Invoice_Header invHead : InvoiceHeaderArray){
            invoiceheader +=invHead.toString();
            invoiceheader +="\n";
            //each invoice has an array of lines so loop on them to write each line of invoices in the new lines file
            for(Invoice_Line invLine:invHead.getLines()){
            invoicelines+=invLine.toString();
            invoicelines+="\n";  //to write the lines sequential
            }
           }
           result=New_File.showOpenDialog(form);
           File invoice_Lines_File = New_File.getSelectedFile();
           FileWriter Line_File_Writer=new FileWriter(invoice_Lines_File);
           // write all headers in the header file
           headerFileWriter.write(invoiceheader);
           Line_File_Writer.write(invoicelines);
           headerFileWriter.close();
           Line_File_Writer.close();
           
       }
        }catch(IOException e){
        
          JOptionPane.showMessageDialog(form, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

     private void createInvoice() {
        dialogHeader= new Invoice_Dialog_Header(form);
        dialogHeader.setVisible(true);
        
    }
     
    private void deleteInvoice() {
       int RowIndex=form.getI_invoices().getSelectedRow();
       if(RowIndex >= 0){
        form.getInvoicesArray().remove(RowIndex);    //to remove the selected line from the invoice table
        form.getHeaderTableModel().fireTableDataChanged();   //redraw the invoice table after removing the line
        form.getInvoice_Details().setModel(new Invoice_Lines_Table(null));
        form.setInvoiceLinesArray(null);
        form.getCustomer_button().setText("");
        form.getI_invoive_Number().setText("");
        form.getInvoice_Total().setText("");
        form.getInvoicedate().setText("");
       }
    } 
    
    private void NewLine() {
       dialogLine= new InvoiceDialog(form);
       dialogLine.setVisible(true);
    }
    
    private void deleteLine() {
        int Row_Index =form.getInvoice_Details().getSelectedRow();
         int InvoiceHeaderIndex=form.getI_invoices().getSelectedRow();
        if(Row_Index >= 0){
           form.getInvoiceLinesArray().remove(Row_Index);
           form.getLinesTableModel().fireTableDataChanged();      
           form.getInvoice_Total().setText(""+form.getInvoicesArray().get(InvoiceHeaderIndex).Sum_of_Lines());  //update the total cost in the lable above the item details table
           form.getHeaderTableModel().fireTableDataChanged();        
        }
        form.getI_invoices().setRowSelectionInterval(InvoiceHeaderIndex, InvoiceHeaderIndex);  //to can delete more lines and updating the total cost in parallel withit
       
    }

    private void CancelInvoice() {
        dialogHeader.setVisible(false);
        dialogHeader.dispose();    //to hide the dialog completely
        dialogHeader=null;       
    } 

    private void OkNewInvDialog() {
        dialogHeader.setVisible(false);
        String customerName=dialogHeader.getCustomerName().getText();
        String strDate=dialogHeader.getInvoiceDate().getText();
        Date date=new Date();
        try{
             date = InvoiceForm.date.parse(strDate);
        }catch(Exception e){
            JOptionPane.showMessageDialog(form, strDate, "parse error in the date", JOptionPane.ERROR_MESSAGE);
        }

// finding the last id number to increment it for getting the new id number
      int New_ID=form.getInvoicesArray().size();
      int ID= New_ID+1;
      System.out.println(New_ID);
        Invoice_Header inv = new Invoice_Header(ID,customerName,date);
        form.getInvoicesArray().add(inv);
        form.getHeaderTableModel().fireTableDataChanged(); //to redraw the table with the new data
        dialogHeader.dispose();
        dialogHeader=null;
    }

    private void Insert_New_Line() {
        dialogLine.setVisible(false);
        String itemName=dialogLine.getProduct_Name().getText();
        String itemPrice =dialogLine.getProduct_Price().getText();
        String itemCount =dialogLine.getProduct_Number().getText();
        int Count=0;
        double Price=0;
        try{Count=Integer.parseInt(itemCount);
            Price=Double.parseDouble(itemPrice);
        }catch(NumberFormatException e){
        JOptionPane.showMessageDialog(form,"cannot convert number","Invalid Format" ,JOptionPane.ERROR_MESSAGE);
        }
        int selectedRowIndex=form.getI_invoices().getSelectedRow();
        if(selectedRowIndex >= 0){
            Invoice_Header invoiceheader_1=form.getInvoicesArray().get(selectedRowIndex);
            Invoice_Line invoiceline_1 = new Invoice_Line(itemName,Price,Count,invoiceheader_1);
            form.getInvoiceLinesArray().add(invoiceline_1);         //adding the new line of item details to the arraylines
            form.getLinesTableModel().fireTableDataChanged(); //redraw the lines table
            form.getHeaderTableModel().fireTableDataChanged(); //to redraw the invoices table with the new data  
        }
         form.getI_invoices().setRowSelectionInterval(selectedRowIndex, selectedRowIndex); // to keep the invoice header row selected even after refreshing the tables to add more lines
        
        dialogLine.dispose();    //to hide the dialog completely
        dialogLine=null;  
    }

    private void Cancel_New_Line() {
        dialogLine.setVisible(false);
        dialogLine.dispose();    //to hide the dialog completely
        dialogLine=null;
    }



}
