import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Game { 
    final JFrame frame = new JFrame("Sliding Puzzle Game"); //CREATE INSTANCE OF FRMAE
   
    GUI gui; //CREATES AN INSTANCE OF PUZZLE GUI
    
    int minutes = 0; //STORES MINUTES PLAYED
    int seconds = 0; //STORES SECONDS PLAYED
    
    public Game() {
        new Timer(1000, (ActionEvent e) -> { //THIS TIMER IS CALLED EVERY SECOND TO UPDATE TIMER DISPLAY
            seconds += 1; //INCREMENT SECOND PER SECOND
            updateTimer(minutes, seconds); //UPDATE TIMER LABEL EVERY 1 SECOND
        }).start();
    }
    
    public void initiateWindow(Game program) {
        GUI gui = new GUI(frame);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setContentPane(gui);
        frame.pack();  
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        Game game = new Game(); //CREATE AN INSTANCE OF THIS PROGRAM
        game.initiateWindow(game); //INITIALISE FRAME AND GUI
    } 	
    
    private void updateTimer(int minutes, int seconds) {
        if (seconds > 59) { //IF MORE THAN 59 SECONDS THEN ADD TO A MINUTE AND RESET SECONDS
            minutes += 1;
            seconds = 0;
        }
        
        if (minutes < 10 && seconds < 10) gui.timer.setText("0" + minutes + ":0" + seconds); 
        else if (minutes < 10) gui.timer.setText("0" + minutes + ":" + seconds);
        else if (seconds < 10) gui.timer.setText("" + minutes + ":0" + seconds);
        else gui.timer.setText(minutes + ":" + seconds);            
    }
} 