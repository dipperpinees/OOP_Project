package DictionaryApp;

import Project.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class homeController implements Initializable {
    @FXML
    private TextField text;
    @FXML
    private Label engLabel;
    @FXML
    private Label vieLabel;
    @FXML
    private ListView<String> listView;

    private ObservableList<String> listEng;
    private List<String> listVie = new ArrayList<String>();

    private DictionaryManagement dic = new DictionaryManagement();
    private ActionEvent event;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listEng = FXCollections.observableArrayList();
        addWordFile();
        showAllWord();
        listView.setItems(listEng);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String eng = listView.getSelectionModel().getSelectedItem();
                if (eng.equals("Not Found!")) {
                    engLabel.setText("Not Found!");
                    vieLabel.setText("Không tìm thấy");
                    return ;
                }
                int index = listEng.indexOf(eng);
                engLabel.setText(eng + " :");
                vieLabel.setText(listVie.get(index));
            }
        });
    }

    public void addWordFile() {
        dic.insertFromFile();
        for (int i = 0; i<dic.getDictionnary().getWordsList().size(); i++) {
            listVie.add(dic.getDictionnary().getWordsList().get(i).getWordExplain());
        }
    }

    public void showAllWord() {
        for (int i = 0; i<dic.getDictionnary().getWordsList().size(); i++) {
            listEng.add(dic.getDictionnary().getWordsList().get(i).getWordTarget());
        }
    }
    public void LookUpWord(ActionEvent event) {
        String eng = text.getText();
        ObservableList<String> newList =FXCollections.observableArrayList();
        boolean check = false;
        for (int i = 0; i < dic.getDictionnary().getWordsList().size(); i++) {
            if (dic.getDictionnary().getWordsList().get(i).getWordTarget().indexOf(eng) == 0 ) {
                newList.add(dic.getDictionnary().getWordsList().get(i).getWordTarget());
                check = true;
            }
        }
        if (!check) {
            newList.add("Not Found!");
        }
        listView.setItems(newList);
    }
}

