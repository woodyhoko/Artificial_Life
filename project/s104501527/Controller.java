package project.s104501527;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


public class Controller {
	private int quan = 100;
    @FXML
    private Canvas canvas;
    @FXML
    private Canvas canvas2;
    @FXML
    private Canvas canvas3;
    private GameManager gameManager;
    private AnimationTimer animationTimer;

    @FXML
    private void initialize(){
        gameManager = new GameManager(canvas.getGraphicsContext2D (),canvas2.getGraphicsContext2D (),canvas3.getGraphicsContext2D (),quan);// initialize the GameManager
        gameManager.create();
        gameManager.initial();
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                // clear the canvas to repaint on the canvas.
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gameManager.thinking();
                gameManager.scoreboard();
                gameManager.nextpos(timestamp);
                
                gameManager.update();
                
            }
        };
        animationTimer.start();
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameManager.OnClick(event);
            }
        });
    }

    public void setScene(Scene scene){
        scene.setOnKeyPressed ( new EventHandler<KeyEvent>() {
            @Override
            public void handle ( KeyEvent event ) {
            	if(event.getCode() == KeyCode.UP){
                    gameManager.up();
            	}
            	if(event.getCode() == KeyCode.DOWN){
                    gameManager.down();
            	}
            }
        } );
        scene.setOnKeyReleased ( new EventHandler<KeyEvent>() {
            @Override
            public void handle ( KeyEvent event ) {
                //
            }
        } );
    }
    
}
