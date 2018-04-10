package com.meybise.Accounts;

import java.io.File;
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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class AdminPageController implements Initializable {
    @FXML
    private Button loadledgerinfo;
     @FXML
    private Image image;
     @FXML
    private TextField num;
     @FXML
    private TableView<LedgerData> ledgertable;
    @FXML
    private TableColumn<LedgerData,Integer> id;
    @FXML
    private TableColumn<LedgerData,String> name;
    @FXML
    private TableColumn<LedgerData,String> address;
    @FXML
    private TableColumn<LedgerData,String> gstno;
    @FXML
    private TableColumn<LedgerData,String> phoneno;
    @FXML
    private TableColumn<LedgerData,Integer> date;
    private ObservableList<LedgerData>data;
    @FXML
    private Label welcome;
    String AdminID;
    @FXML
    private ImageView adminimage;
    @FXML
    private Label adminname;
    @FXML
    private Label adminid;
    public void GetAdminID(String id) throws SQLException, FileNotFoundException, IOException{
        AdminID = id;
        Connection con = DbConnection.Connection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM admins WHERE id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            adminname.setText(rs.getString("name"));
            adminid.setText(rs.getString("id"));
            InputStream is = rs.getBinaryStream("image");
            OutputStream os = new FileOutputStream(new File("adminimage.jpeg"));
            byte[] content = new byte[1024];
            int s = 0;
            while((s= is.read(content))!= -1){
            os.write(content, 0, s);
            }
            Image image = new Image("file:adminimage.jpeg");
            adminimage.setImage(image);
            adminimage.setFitWidth(248);
            adminimage.setFitHeight(186);
            Circle clip = new Circle(93,93,93);
            adminimage.setClip(clip);
        }
        ps.close();
        rs.close();
        con.close();
    }
    @FXML
    public void LoadLedgerData(ActionEvent event) throws SQLException{
    Connection con = DbConnection.Connection();
    data = FXCollections.observableArrayList();
    PreparedStatement ps = con.prepareStatement("SELECT * FROM ledger");
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
    data.add(new LedgerData(rs.getInt("id"),rs.getString("name"),rs.getString("address"),rs.getString("gstno"),rs.getString("phoneno"),rs.getString("date")));
    }
            id.setCellValueFactory(new PropertyValueFactory<LedgerData,Integer>("LedgerId"));
            name.setCellValueFactory(new PropertyValueFactory<LedgerData,String>("LedgerName"));
            address.setCellValueFactory(new PropertyValueFactory<LedgerData,String>("LedgerAddress"));
            gstno.setCellValueFactory(new PropertyValueFactory<LedgerData,String>("LedgerGstno"));
            phoneno.setCellValueFactory(new PropertyValueFactory<LedgerData,String>("LedgerPhoneno"));
            date.setCellValueFactory(new PropertyValueFactory<LedgerData,Integer>("LedgerDate"));
            
            ledgertable.setItems(data);
            ps.close();
            rs.close();
            con.close();
    }
     @FXML
    public void AddCustomerData(ActionEvent event) throws IOException{
    Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AddLedgers.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/AddLedgers.css");
        Image icon = new Image("/icons/adduser.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Add Ledger Page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void EditCustomerData(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/EditLedgers.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/EditLedgers.css");
        Image icon = new Image("/icons/edituser.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Edit Ledger Page");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void DeleteCustomerData(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/DeleteLedgers.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/DeleteLedgers.css");
        Image icon = new Image("/icons/deleteuser.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Delete Ledger Page");
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
