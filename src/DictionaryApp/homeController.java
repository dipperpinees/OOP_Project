package DictionaryApp;

import Project.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @FXML
    private Button changeButton = new Button();
    @FXML
    private Button changeButton2 = new Button();

    private List<String> listEng = new ArrayList<String>();

    private List<String> listVie = new ArrayList<String>();

    private DictionaryManagement dic = new DictionaryManagement() ;
    private ActionEvent event;

    public void initDataHome(List<String> listEng, List<String> listVie) {
        this.listEng = listEng;
        this.listVie = listVie;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        changeButton.setVisible(false);
        changeButton2.setVisible(false);
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
            changeButton.setVisible(false);
            changeButton2.setVisible(false);
        });
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String currentWord = listView.getSelectionModel().getSelectedItem();
                engLabel.setText(currentWord);
                vieLabel.setText(getExplain(currentWord));
                changeButton.setVisible(true);
                changeButton2.setVisible(true);
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

    public void gotoAddWord(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addWord.fxml"));
        Parent addWord = loader.load();
        Scene AddScene = new Scene(addWord);
        addWordController addController = loader.<addWordController>getController();
        addController.initData(listEng,listVie);
        stage.setScene(AddScene);
    }

    public void showInputTextDialog() throws IOException  {

        TextInputDialog dialog = new TextInputDialog("");

        dialog.setTitle("Sửa nghĩa từ");
        dialog.setHeaderText("Sửa nghĩa từ: " + engLabel.getText());
        dialog.setContentText("Nghĩa");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            boolean check = dic.changeWordExplain(name, engLabel.getText());
            vieLabel.setText(name);
            showAlert("Đổi nghĩa thành công");
            try {
                dic.dictionaryExportToFile();
            } catch(IOException ie) {
                ie.printStackTrace();
            }
        });
    }

    public void showAlert(String newAlert)  {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setContentText(newAlert);

        alert.showAndWait();
    }

    public void deleteWord(ActionEvent e) throws IOException  {
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
        showAlert("xoá thành công");
    }

    public void playSound(String target) {
        Media sound = new Media("http://api.voicerss.org/?key=458d0ac0b00d4d5bb52175e4c1e7159c&hl=en-us&src=" + target);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}