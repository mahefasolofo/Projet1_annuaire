/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1_annuaire;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Inclusiv
 */
public class LoginController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private Button connect;
    @FXML
    private Label message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
//Déclaration des variables
    HashMap<String,String> loginInfo = new HashMap<>();
    File file = new File("src\\projet1_annuaire\\data\\data.csv");
    Hashage hash = new Hashage();
    
//Actions & methodes
    @FXML
    private void connectClick(MouseEvent event) throws IOException {
        String username = user.getText();
        String pass = password.getText();
        
        try {
            updateInfo();
            if (loginInfo.containsKey(username)){
            String passwordHash = loginInfo.get(username);
       
            if (hash.getHash(pass.getBytes()).matches(passwordHash)) {
           
                    Parent source = FXMLLoader.load(getClass().getResource("Home.fxml"));
                    Scene scene = new Scene(source);
                    Stage stage = new Stage();
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setX(50);
                    stage.setY(50);
                    stage.setScene(scene);
                    stage.show();
                    
                  
            } else {
                message.setText("Erreur");
            }
        } else{message.setText("Utilisateur non enregistré ");System.out.println(System.getProperty("user.dir")) ;}
        }catch (FileNotFoundException fileNotFoundException) {
            message.setText("Utilisateur non enregistré");
            System.out.println(System.getProperty("user.dir")) ;
        }
    }
    
    
    private void updateInfo() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        loginInfo.clear();
        loginInfo = new HashMap<>();
        
        while(scanner.hasNext()){
            String[] usernameAndPassword = scanner.nextLine().split(",");
            loginInfo.put(usernameAndPassword[0],usernameAndPassword[1]);
        }
        
    }
    
}
