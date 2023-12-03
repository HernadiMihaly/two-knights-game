package twoknightsgame.gui.main;

import javafx.application.Application;
import org.tinylog.Logger;

/**
 * A program indításáért felelős fő/main osztály.
 */
public class Main {
    public static void main(String[] args){
        Logger.debug("Application starts..");
        Application.launch(TwoKnightsGameApplication.class, args);
        Logger.debug("Application closed!");
    }
}
