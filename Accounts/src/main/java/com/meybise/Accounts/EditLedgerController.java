package com.meybise.Accounts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditLedgerController implements Initializable {
   
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextArea address;
    @FXML
    private TextField gsttinNo;
    @FXML
    private TextField phone;
    @FXML
    private TextField date;
    private File file;
    private FileInputStream fis;
    @FXML
    private Button chosepic;
    int cp;
    @FXML
    private Label confirmation;
    @FXML
    private Button load;
   
    @FXML
    public void LoadLedgerInfo(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        try {
            Connection con = DbConnection.Connection();
            PreparedStatement ps = con.prepareStatement("Select * from users where id = ?");
            ps.setInt(1, Integer.parseInt(id.getText()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                gsttinNo.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                date.setText(Integer.toString(rs.getInt("balance")));
                InputStream img = rs.getBinaryStream("image");
                OutputStream os = new FileOutputStream(new File("userimage.jpg"));
                byte[] content = new byte[1024];
                int s = 0;
                while ((s = img.read(content)) != -1) {
                    os.write(content, 0, s);
                }
            } else {
                confirmation.setText("Ledger Not Found");
            }
            ps.close();
            rs.close();
            con.close();
        } catch (IOException | NumberFormatException | SQLException e) {
            confirmation.setText("Please Enter The ID Correctly");
        }
    }
    public void EditLedgerInfo(ActionEvent event) throws SQLException, FileNotFoundException {
        try {
            if (name.getText().isEmpty() || address.getText().isEmpty() || gsttinNo.getText().isEmpty() || phone.getText().isEmpty() || date.getText().isEmpty()) {
                confirmation.setText("Please Fill Up Everything");
            } else if (Integer.parseInt(date.getText()) < 0) {
                confirmation.setText("Please Enter The Balance Correctly");
            } else {
                if (cp == 1) {
                    Connection con = DbConnection.Connection();
                    PreparedStatement ps = con.prepareStatement("UPDATE users SET name = ? ,address = ? ,email = ? , phone = ?,balance = ? , image = ? WHERE id = '" + Integer.parseInt(id.getText()) + "'");
                    ps.setString(1, name.getText());
                    ps.setString(2, address.getText());
                    ps.setString(3, gsttinNo.getText());
                    ps.setString(4, phone.getText());
                    ps.setInt(5, Integer.parseInt(date.getText()));
                    fis = new FileInputStream(file);
                    ps.setBinaryStream(6, (InputStream) fis, (int) file.length());
                    int i = ps.executeUpdate();
                    if (i > 0) {
                        name.setText("");
                        address.setText("");
                        gsttinNo.setText("");
                        phone.setText("");
                        date.setText("");
                        confirmation.setText("Ledgers Info Updated Successfully");
                    } else {
                        confirmation.setText("Failed To Update Ledgers Info");
                    }
                    cp = 0;
                    ps.close();
                    con.close();
                } else if (cp != 1) {
                    Connection con = DbConnection.Connection();
                    PreparedStatement ps = con.prepareStatement("UPDATE users SET name = ? ,address = ? ,email = ? , phone = ?,balance = ? WHERE id = '" + Integer.parseInt(id.getText()) + "'");
                    ps.setString(1, name.getText());
                    ps.setString(2, address.getText());
                    ps.setString(3, gsttinNo.getText());
                    ps.setString(4, phone.getText());
                    ps.setInt(5, Integer.parseInt(date.getText()));
                    int j = ps.executeUpdate();
                    if (j > 0) {
                        name.setText("");
                        address.setText("");
                        gsttinNo.setText("");
                        phone.setText("");
                        date.setText("");
                       confirmation.setText("Ledgers Info Updated Successfully");
                    } else {
                        confirmation.setText("Failed To Update Ledgers Info");
                    }
                    ps.close();
                    con.close();
                }
            }
        } catch (FileNotFoundException | NumberFormatException | SQLException e) {
            confirmation.setText("Please Enter Everything Correctly");
        }
    }
    public void DeleteLedgerInfo(ActionEvent event) throws SQLException {
        try {
            Connection con = DbConnection.Connection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setInt(1, Integer.parseInt(id.getText()));
            int k = ps.executeUpdate();
            if (k > 0) {
                name.setText("");
                address.setText("");
                gsttinNo.setText("");
                phone.setText("");
                date.setText("");
                confirmation.setText("Successfully Removed The Customer");
            } else {
                confirmation.setText("Failed To Find The Customer");
            }
            ps.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            confirmation.setText("Please Enter The ID Correctly");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
