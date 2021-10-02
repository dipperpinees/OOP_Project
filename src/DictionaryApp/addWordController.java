package DictionaryApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class addWordController {

    private List<String> listEng;
    private List<String> listVie;
    @FXML
    private TextField engText;
    @FXML
    private TextField vieText;

    public void initData(List<String> listEng, List<String> listVie) {
        this.listEng = listEng;
        this.listVie = listVie;
    }


    public void goHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent homeParent = loader.load();
        Scene homeScene = new Scene(homeParent);
        homeController home = loader.<homeController>getController();
        home.initDataHome(listEng,listVie);
        stage.setScene(homeScene);

    }

    public void addWo(ActionEvent event) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        String eng = engText.getText();
        String vie = vieText.getText();
        Alert alertFalse = new Alert(Alert.AlertType.WARNING);
        alertFalse.setHeaderText("Từ đã có hoặc từ không hợp lệ");
        if (listEng.indexOf(eng) != -1) {
            alertFalse.show();
            return;
        } else {
            if (eng != null && vie != null && eng.length() * vie.length() != 0) {
                listEng.add(eng);
                listVie.add(vie);
                String s = eng + '\t' + vie;
                try {
                    fw = new FileWriter("src/dictionaries.txt", true);
                    bw = new BufferedWriter(fw);
                    bw.write(s);
                    bw.newLine();
                    bw.close();
                    fw.close();
                } catch (Exception ex) {

                }
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setHeaderText("Thêm thành công");
                alert0.show();
                return;
            }
        }
        alertFalse.show();
    }
}
