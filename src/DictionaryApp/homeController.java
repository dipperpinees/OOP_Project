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

    private List<String> listEng = new ArrayList<String>();

    private List<String> listVie = new ArrayList<String>();

    private DictionaryManagement dic = new DictionaryManagement();
    private ActionEvent event;
//    private String currentWord;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addWordFile();
        listEng = showAllWord();
//        showAllWord();
        listView.getItems().setAll(listEng);

        text.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() != 0) {
                listView.getItems().clear();
                vieLabel.setText("");
                listView.getItems().setAll(LookUpWord(newValue));
            } else {
                listView.getItems().setAll(listEng);
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String currentWord = listView.getSelectionModel().getSelectedItem();
                engLabel.setText(currentWord);
                vieLabel.setText(getExplain(currentWord));
            }
        });
    }

    public String getExplain (String target) {
        if(target == null || target.length() == 0) return "";
        for (int i = 0; i < dic.getDictionnary().getWordsList().size(); i++) {
            if (dic.getDictionnary().getWordsList().get(i).getWordTarget().equals(target)) {
                return dic.getDictionnary().getWordsList().get(i).getWordExplain();
            }
        }
        return "";
    }

    public void addWordFile() {
        dic.insertFromFile();
        for (int i = 0; i<dic.getDictionnary().getWordsList().size(); i++) {
            listVie.add(dic.getDictionnary().getWordsList().get(i).getWordExplain());
        }
    }

    public List<String> showAllWord() {
        List<String> newList = new ArrayList<String>();
        for (int i = 0; i<dic.getDictionnary().getWordsList().size(); i++) {
            newList.add(dic.getDictionnary().getWordsList().get(i).getWordTarget());
        }
        return newList;
    }

    public List<String> LookUpWord (String word) {
        List<String> listWord = new ArrayList<String>();

        boolean check = false;
        for(int i = 0; i <dic.getDictionnary().getWordsList().size(); i++) {
            if(dic.getDictionnary().getWordsList().get(i).getWordTarget().indexOf(word) == 0) {

                listWord.add(dic.getDictionnary().getWordsList().get(i).getWordTarget());
                check = true;
            }
        }
        if(!check) {
            listWord.add("Not Found");
        }
        return listWord;
    }


//    public void LookUpWord(ActionEvent event) {
//        String eng = text.getText();
//        ObservableList<String> newList =FXCollections.observableArrayList();
//        boolean check = false;
//        for (int i = 0; i < dic.getDictionnary().getWordsList().size(); i++) {
//            if (dic.getDictionnary().getWordsList().get(i).getWordTarget().indexOf(eng) == 0 ) {
//                newList.add(dic.getDictionnary().getWordsList().get(i).getWordTarget());
//                check = true;
//            }
//        }
//        if (!check) {
//            newList.add("Not Found!");
//        }
//        listView.setItems(newList);
//    }
}

