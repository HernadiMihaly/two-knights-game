package twoknightsgame.gui.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.tinylog.Logger;
import twoknightsgame.model.Database;
import twoknightsgame.model.Game;
import twoknightsgame.state.board.Board;
import twoknightsgame.state.board.Point;
import twoknightsgame.state.board.SimpleBoard;
import twoknightsgame.state.board.Tile;
import twoknightsgame.state.piece.Color;
import twoknightsgame.state.piece.Knight;

import java.net.URL;
import java.util.*;

public class TwoKnightsGameController implements Initializable {

    @FXML
    private Text whoseTurn;
    @FXML
    private Text topTen;
    @FXML
    private GridPane gridPane;
    @FXML
    private Pane whiteKnight;
    @FXML
    private Pane blackKnight;

    private final Board chessBoard = new SimpleBoard();
    private final Knight knight = new Knight(chessBoard);
    private Tile otherKnightBeforeStep;
    private Tile newKnightPosition;
    private boolean firstWhiteStep = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                createGUIBoard(i, j);
            }
        }
        Logger.debug("The Board is displayed on the GUI");
        Logger.info("The GUI's board contains of a Grid-pane which is filled with panes!");
    }

    private void createGUIBoard(int i, int j) {
        Pane pane = new Pane();
        pane.setId(i + "" + j);
        switch (chessBoard.getCurrentBoard()[j][i].getColor()) {
            case BLACK -> gridPane.add(blackKnight, i, j);
            case WHITE -> gridPane.add(whiteKnight, i, j);
            case EMPTY -> {
                gridPane.add(pane, i, j);
                pane.setStyle("-fx-background-color: grey; -fx-border-color: rgba(30,30,30,0.95)");
            }
            default -> throw new IllegalStateException("Unexpected value: " + chessBoard.getCurrentBoard()[j][i].getColor());
        }
    }

    public void btnExitClicked(ActionEvent actionEvent) {
        Logger.debug("Button exit clicked!");
        Platform.exit();
    }

    public void btnTop10Clicked(ActionEvent actionEvent) {
        topTen.setText(Database.getTopTen());
        whoseTurn.setVisible(false);
        topTen.setVisible(true);
    }

    private void setWhoseTurn() {
        if (!knight.isGameOver()) {
            whoseTurn.setVisible(true);
            topTen.setVisible(false);
            if (knight.isWhiteTurn()) {
                whoseTurn.setText("It's White's Turn");
            } else {
                whoseTurn.setText("It's Black's Turn");
            }
        } else if (knight.isWhiteWon() || knight.isBlackWon()) {
            whoseTurn.setText(getWinner() + " WON!");
            prepareGameForDB(getWinner(), getLoser());
            Logger.debug("It's a WIN!");
        }
    }

    private void prepareGameForDB(String winner, String loser) {
        Game game = new Game();
        game.setWinner(winner);
        game.setLoser(loser);
        Database.saveGameResultToDB(game);
    }

    private String getLoser() {
        if (TwoKnightsStartController.getPlayer1Properties().getKey().equals(getWinner())) {
            return TwoKnightsStartController.getPlayer2Properties().getKey();
        }
        return TwoKnightsStartController.getPlayer1Properties().getKey();
    }

    private String getWinner() {
        String winner = "";
        if (knight.isWhiteWon()) {
            winner = whoIsWhite();
        } else if (knight.isBlackWon()) {
            winner = whoIsBlack();
        }
        return winner;
    }

    private String whoIsWhite() {
        if (TwoKnightsStartController.getPlayer1Properties().getValue().equals(Color.WHITE)) {
            return TwoKnightsStartController.getPlayer1Properties().getKey();
        } else {
            return TwoKnightsStartController.getPlayer2Properties().getKey();
        }
    }

    private String whoIsBlack() {
        if (TwoKnightsStartController.getPlayer1Properties().getValue().equals(Color.BLACK)) {
            return TwoKnightsStartController.getPlayer1Properties().getKey();
        } else {
            return TwoKnightsStartController.getPlayer2Properties().getKey();
        }
    }

    @FXML
    private void whiteWantsToStep(Event event) {
        if (knight.isWhiteTurn() && !knight.isGameOver()) {
            Logger.debug("White wants to step!");
            List<Point> list = chessBoard.possibleSteps(knight.stepFrom());

            otherKnightBeforeStep = new Tile(new Point(Objects.requireNonNull(getPointFromNodeId(gridPane, "blackKnight")).getY(), Objects.requireNonNull(getPointFromNodeId(gridPane, "blackKnight")).getX()), Color.BLACK);

            for (Point to : list) {
                getNodeFromPoint(to, gridPane).setStyle("-fx-background-color: lightblue;");
                getNodeFromPoint(to, gridPane).setOnMouseClicked(event1 -> oneMove(event, new Tile(to, Color.WHITE)));
            }
            firstWhiteStep = false;
        }
    }

    @FXML
    private void blackWantsToStep(MouseEvent mouseEvent) {
        if (!knight.isWhiteTurn() && !knight.isGameOver()) {
            Logger.debug("Black wants to step!");
            List<Point> list = chessBoard.possibleSteps(knight.stepFrom());

            otherKnightBeforeStep = new Tile(new Point(Objects.requireNonNull(getPointFromNodeId(gridPane, "whiteKnight")).getY(), Objects.requireNonNull(getPointFromNodeId(gridPane, "whiteKnight")).getX()), Color.WHITE);

            for (Point to : list) {
                getNodeFromPoint(to, gridPane).setStyle("-fx-background-color: lightblue;");
                getNodeFromPoint(to, gridPane).setOnMouseClicked(event1 -> oneMove(mouseEvent, new Tile(to, Color.BLACK)));
            }
        }
    }

    private void oneMove(Event event, Tile movingKnight) {
        newKnightPosition = movingKnight;
        Point to = newKnightPosition.getPoint();
        knight.move(to);
        rePrintBoard();
        Logger.debug("The step is displayed!");
    }

    private Point getPointFromNodeId(GridPane gridPane, String s) {
        long gridPaneSize = gridPane.getChildren().size();
        for (int i = 1; i < gridPaneSize; i++) {
            if (firstWhiteStep) {
                if (gridPane.getChildren().get(i + 1).getId().equals(s)) {
                    return getPointFromIndex(i);
                }
            } else {
                if (gridPane.getChildren().get(i).getId().equals(s)) {
                    return getPointFromIndex(i);
                }
            }
        }
        return null;
    }

    private Point getPointFromIndex(int i) {
        String coordinate;
        if (i < 10) {
            coordinate = "0" + "" + i;
        } else {
            coordinate = Integer.toString(i);
        }
        return new Point(Integer.parseInt(coordinate.split("")[0]), Integer.parseInt(coordinate.split("")[1]));
    }

    private Node getNodeFromPoint(Point p, GridPane gridPane) {
        int real_coordinate;
        String coordinate = p.getY() + Integer.toString(p.getX());
        if (firstWhiteStep) {
            real_coordinate = Integer.parseInt(coordinate) + 1;
        } else {
            real_coordinate = Integer.parseInt(coordinate);
        }
        return gridPane.getChildren().get(real_coordinate);
    }

    private void rePrintBoard() {
        Logger.debug("Reprinting board.");
        setWhoseTurn();
        gridPane.getChildren().clear();
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                Pane pane = new Pane();
                pane.setId(i + "" + j);
                refreshBoard(i, j, pane);
            }
        }
    }

    private void refreshBoard(int i, int j, Pane pane) {
        refreshOtherKnight(i, j);
        refreshEmptyPanes(i, j, pane);
        refreshWhiteElements(i, j, pane);
        refreshBlackElements(i, j, pane);
    }

    private void refreshWhiteElements(int i, int j, Pane pane) {
        if (chessBoard.getCurrentBoard()[j][i].getColor().equals(Color.WHITE) && chessBoard.getCurrentBoard()[j][i].getPoint().equals(newKnightPosition.getPoint())) {
            gridPane.add(whiteKnight, i, j);
        } else if (!chessBoard.getCurrentBoard()[j][i].getPoint().equals(otherKnightBeforeStep.getPoint()) && chessBoard.getCurrentBoard()[j][i].getColor().equals(Color.WHITE)) {
            pane.setStyle("-fx-background-color: white; -fx-border-color: black");
            gridPane.add(pane, i, j);
        }
    }

    private void refreshBlackElements(int i, int j, Pane pane) {
        if (chessBoard.getCurrentBoard()[j][i].getColor().equals(Color.BLACK) && chessBoard.getCurrentBoard()[j][i].getPoint().equals(newKnightPosition.getPoint())) {
            gridPane.add(blackKnight, i, j);
        } else if (!chessBoard.getCurrentBoard()[j][i].getPoint().equals(otherKnightBeforeStep.getPoint()) && chessBoard.getCurrentBoard()[j][i].getColor().equals(Color.BLACK)) {
            pane.setStyle("-fx-background-color: black; -fx-border-color: white");
            gridPane.add(pane, i, j);
        }
    }

    private void refreshOtherKnight(int i, int j) {
        if (chessBoard.getCurrentBoard()[j][i].getPoint().equals(otherKnightBeforeStep.getPoint())) {
            if (otherKnightBeforeStep.getColor().equals(Color.WHITE)) {
                gridPane.add(whiteKnight, i, j);
            } else {
                gridPane.add(blackKnight, i, j);
            }
        }
    }

    private void refreshEmptyPanes(int i, int j, Pane pane) {
        if (chessBoard.getCurrentBoard()[j][i].getColor().equals(Color.EMPTY)) {
            pane.setStyle("-fx-background-color: grey; -fx-border-color: rgba(30,30,30,0.95)");
            gridPane.add(pane, i, j);
        }
    }

}