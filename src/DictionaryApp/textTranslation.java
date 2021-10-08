package DictionaryApp;


import com.darkprograms.speech.translator.GoogleTranslate;
import com.google.api.translate.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;


public class textTranslation {

    @FXML
    private TextArea TextT1;
    @FXML
    private TextArea TextT2;

    public void goHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home1.fxml"));
        Parent homeParent = loader.load();
        Scene homeScene = new Scene(homeParent);
        stage.setScene(homeScene);

    }

    public static String text_Translate(String languagetarget,String target)
    {
        String target_translated="";
        try {
            target_translated= GoogleTranslate.translate(languagetarget,target);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return target_translated;
    }

    public void TTranslate() {
        String toVie = Language.VIETNAMESE.toString();
        String trans = text_Translate(toVie, TextT1.getText().replaceAll("\n",""));
        TextT2.setText(trans);
    }

    public void T2Translate() {
        String toEng = Language.ENGLISH.toString();
        String trans = text_Translate(toEng, TextT2.getText().replaceAll("\n",""));
        TextT1.setText(trans);
    }

}
