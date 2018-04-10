package com.meybise.Accounts;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddLedgerController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextArea address;
    @FXML
    private TextField phone;
    @FXML
    private Button add;
    @FXML
    private Label addledgerconfirm;
    @FXML
    private TextField gsttinNo;
    @FXML
    private TextField date;
   
    @FXML
    public void AddLedgers(ActionEvent event) throws SQLException, FileNotFoundException {
        try {
        	System.out.println("call this");
            if (name.getText().isEmpty() ||  address.getText().isEmpty() || gsttinNo.getText().isEmpty() || phone.getText().isEmpty() || date.getText().isEmpty()) {
            	addledgerconfirm.setText("Please Fill Up Everything");
            }  
             else {
            	
                    try {
                        
                    	 Connection con = DbConnection.Connection();
                        String sql = "INSERT INTO ledger " //
                                + " (name, address, phoneno,gstno,date) " //
                                + " values " //
                                + " (?,?,?,?,?)";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, name.getText());
                        ps.setString(2, address.getText());
                        ps.setString(3, phone.getText());
                        ps.setString(4, gsttinNo.getText());
                        ps.setString(5, date.getText());
                        int i = ps.executeUpdate();
                        
                        System.out.println("i"+i);
                        System.out.println("Name"+name.getText()+"Address"+address.getText()+"Phone"+phone.getText()+"gst"+gsttinNo.getText());
                        if (i > 0) {
                            name.setText("");
                            address.setText("");
                            phone.setText("");
                            gsttinNo.setText("");
                            date.setText("");
                            addledgerconfirm.setText("New Ledger Added Successfully");
                        } else {
                        	addledgerconfirm.setText("Failed To Add New Ledger");
                        }
                        con.commit();
                        ps.close();
                        con.close();
                    } catch ( NumberFormatException | SQLException e) {
                    	e.printStackTrace();
                    	addledgerconfirm.setText("Invalid Ledger ID or ID Is Not Available");
                    }
                }
        } catch (NumberFormatException e) {
        	addledgerconfirm.setText("Please Enter Everything Correctly");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
