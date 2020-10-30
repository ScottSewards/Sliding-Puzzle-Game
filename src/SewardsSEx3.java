
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
FEATURES
MOVE COUNT IS TRACKED AND DISPLAYED
COL AND ROW VARIABLES ARE REMOVED FROM SLIDEPUZZLEGUI
TILES CAN MOVE DIAGONALLY
BUG IN ISGAMEOVER FIXED
COMPLETING THE GAME WILL PRINT A MESSAGE RATHER THAN PLAY A SOUND
MOVE SEVERAL CELLS IN A ROW OR COLUMN INTO EMPTY SPACE
TIMER
*/

class SewardsSEx3 { 
    final JFrame frame = new JFrame("SewardsSEx3"); //CREATE INSTANCE OF FRMAE
    
    //BufferedImage image; //STORES AN IMAGE TO BE CLIPPED AND OVERLAYED ONTO TILES
    SlidePuzzleGUI gui; //CREATES AN INSTANCE OF PUZZLE GUI
    
    int minutes = 0; //STORES MINUTES PLAYED
    int seconds = 0; //STORES SECONDS PLAYED
    int numberOfMoves = 0; //STORES NUMBER OF MOVES MADE
    
    public SewardsSEx3() {
        new Timer(1000, (ActionEvent e) -> { //THIS TIMER IS CALLED EVERY SECOND TO UPDATE TIMER DISPLAY
            seconds += 1; //INCREMENT SECOND PER SECOND
            updateTimer(minutes, seconds); //UPDATE TIMER LABEL EVERY 1 SECOND
        }).start();
    }
    
    public void initiateWindow(SewardsSEx3 program) {
        gui = new SlidePuzzleGUI(frame);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setContentPane(gui);
        frame.pack();  
        frame.show();  
        frame.setVisible(true);
        //frame.getContentPane().add(program); //ADD THIS PROGRAM (SEWARDSEX2) TO BE SHOW IN THE FRAME PANEL
    }
    
    public static void main(String[] args) {
        SewardsSEx3 program = new SewardsSEx3(); //CREATE AN INSTANCE OF THIS PROGRAM
        program.initiateWindow(program); //INITIALISE FRAME AND GUI
    } 	
    
    private void updateTimer(int minutes, int seconds) {
        if (seconds > 59) { //IF MORE THAN 59 SECONDS THEN ADD TO A MINUTE AND RESET SECONDS
            minutes += 1;
            seconds = 0;
        }
        
        if (minutes < 10 && seconds < 10) { //ADD DIGITS TO TIMER LABEL BASED ON IF LESS THAN 10
            gui.timer.setText("0" + minutes + ":0" + seconds); //SET LABEL VALUE IN GUI CLASS
        }
        else if (minutes < 10) {
            gui.timer.setText("0" + minutes + ":" + seconds);
        }
        else if (seconds < 10) {
            gui.timer.setText("" + minutes + ":0" + seconds);
        }
        else {
            gui.timer.setText(minutes + ":" + seconds);            
        }
    }
} 