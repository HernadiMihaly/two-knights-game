package twoknightsgame.gui.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import twoknightsgame.state.piece.Color;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TwoKnightsStartController implements Initializable {

    @FXML
    private TextField input1;
    @FXML
    private TextField input2;
    @FXML
    private ChoiceBox<Color> choiceBoxP1;
    @FXML
    private ChoiceBox<Color> choiceBoxP2;
    @FXML
    private Pane alertPane;
    @FXML
    private Text alertText;
    @FXML
    private Pane alertPane2;
    @FXML
    private Text alertText2;

    private static String player1Name;
    private static String player2Name;
    private static Color player1Color;
    private static Color player2Color;

    public static Pair<String, Color> getPlayer1Properties() {
        return new Pair<>(player1Name, player1Color);
    }

    public static Pair<String, Color> getPlayer2Properties() {
        return new Pair<>(player2Name, player2Color);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addColorChoices(choiceBoxP1);
        addColorChoices(choiceBoxP2);
    }

    private void addColorChoices(ChoiceBox<Color> cb) {
        Color[] colors = Color.values();

        for (Color color : colors) {
            if (!color.equals(Color.EMPTY)) {
                cb.getItems().add(color);
            }
        }
    }

    @FXML
    void btnCancelClicked(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnOKClicked(ActionEvent event) throws IOException {
        setUserInput();
        setUserChoice();

        if (!(Objects.equals(player1Name, "")) && !(player1Color == Color.EMPTY)) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXMLs/gameui.fxml")));
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    private void setUserInput() {
        if (getUserInput(input1).equals("") || getUserInput(input2).equals("")) {
            if (!alertPane2.isVisible()) {
                alertPane2.setVisible(true);
            }
            alertText2.setText("HIBA! A nevek nem lehetnek üresek!");
            player1Name = "";
            player2Name = "";
        } else if (!getUserInput(input1).equals(getUserInput(input2))) {
            player1Name = getUserInput(input1);
            player2Name = getUserInput(input2);
            if (alertPane2.isVisible())
                alertPane2.setVisible(false);
        } else {
            if (!alertPane2.isVisible()) {
                alertPane2.setVisible(true);
            }
            alertText2.setText("HIBA! A két név nem egyezhet egymással!");
            player1Name = "";
            player2Name = "";
        }
    }

    private String getUserInput(TextField input) {
        return input.getText();
    }

    private void setUserChoice() {
        if (getUserColorChoice(choiceBoxP1) == null ||
                getUserColorChoice(choiceBoxP2) == null) {
            if (!alertPane.isVisible())
                alertPane.setVisible(true);
            alertText.setText("HIBA! A színek nem lehetnek üresek!");
            player1Color = Color.EMPTY;
            player2Color = Color.EMPTY;
        } else if (!getUserColorChoice(choiceBoxP1).equals(getUserColorChoice(choiceBoxP2))) {
            player1Color = getUserColorChoice(choiceBoxP1);
            player2Color = getUserColorChoice(choiceBoxP2);
            if (alertPane.isVisible())
                alertPane.setVisible(false);
        } else {
            if (!alertPane.isVisible())
                alertPane.setVisible(true);
            alertText.setText("HIBA! A két megadott színnek különböznie kell!");
            player1Color = Color.EMPTY;
            player2Color = Color.EMPTY;
        }
    }

    private Color getUserColorChoice(ChoiceBox<Color> cb) {
        return cb.getValue();
    }
}