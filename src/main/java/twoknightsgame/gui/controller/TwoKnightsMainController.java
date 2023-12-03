package twoknightsgame.gui.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TwoKnightsMainController {

    @FXML
    void btnStartClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXMLs/ui.fxml")));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void btnCancelClicked(ActionEvent event) {
        Platform.exit();
    }

}
