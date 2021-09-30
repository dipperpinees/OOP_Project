package DictionaryApp;

import Project.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class homeController implements Initializable {
    @FXML
    private TextField text;
    @FXML
    private TextField textEng;
    @FXML
    private TextField textVie;
    @FXML
    private ListView<String> listView;

    private ObservableList<String> listEng;
    private List<String> listVie = new ArrayList<String>();

    private DictionaryManagement dic = new DictionaryManagement();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listEng = FXCollections.observableArrayList();
        showAllWord();
        listView.setItems(listEng);
    }

    public void showAllWord() {
        dic.insertFromFile();
        for (int i = 0; i<dic.getDictionnary().getWordsList().size(); i++) {
            listEng.add(dic.getDictionnary().getWordsList().get(i).getWordTarget());
        }
    }
    public void LookUpWord(ActionEvent event) {
        String eng = text.getText();
        ObservableList<String> newList =FXCollections.observableArrayList();
        listVie = new ArrayList<String>();
//        if (eng.equals("")) {
//            listEng.add("Not Found!");
//            return ;
//        }
        boolean check = false;
        for (int i = 0; i < dic.getDictionnary().getWordsList().size(); i++) {
            if (dic.getDictionnary().getWordsList().get(i).getWordTarget().indexOf(eng) == 0 ) {
                newList.add(dic.getDictionnary().getWordsList().get(i).getWordTarget());
                listVie.add(dic.getDictionnary().getWordsList().get(i).getWordExplain());
                check = true;
            }
        }
        if (!check) {
            newList.add("Not Found!");
        }
        listView.setItems(newList);
    }
    public void showInfoWord(ActionEvent event) {
        String eng = listView.getSelectionModel().getSelectedItem();
        textEng.setText(eng);

    }
}

