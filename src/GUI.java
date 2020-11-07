
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GUI extends JPanel {
    JFrame frame;
    JMenuBar menu = new JMenuBar();
    JMenu game = new JMenu("Game"), options = new JMenu("Options");
    JMenuItem newGame = new JMenuItem("New Game"), restartGame = new JMenuItem("Restart Game"), cellColour = new JMenuItem("Change Tile Colour");
    JButton newGameButton = new JButton("New Game"), restartGameButton = new JButton("Restart Game");
    JLabel moves = new JLabel("0 Moves"), timer = new JLabel("00:00"), message = new JLabel("Welcome."); //LABELS TO DISPLAY TIMER AND MOVES
        
    private GraphicsPanel graphicsPanel;
    private Model puzzleModel = new Model(5, 5);
    //private Tile[][] contents;  //STORES NEW GENERATION OF TILES SO IT CAN BE RESTARTED
    
    int numberOfMoves = 0; //STORES NUMBER OF MOVES
    Color colour = Color.WHITE; //CUSTOM COLOUR FOR CELLS
    
    public GUI(JFrame frame) {
        this.frame = frame;

        JPanel messaggePanel = new JPanel(); //CREATE PANEL FOR TOP OF FRAME
        messaggePanel.setLayout(new FlowLayout());
        messaggePanel.add(message);

        JPanel infoPanel = new JPanel(); //CREATE PANEL FOR BOTTOM OF FRAME
        infoPanel.setLayout(new FlowLayout());
        infoPanel.add(this.timer);
        infoPanel.add(this.moves);
     
        graphicsPanel = new GraphicsPanel(); //CREATE A NEW GRAPHICS PANEL FOR DRAWING THE PUZZLE
             
        game.add(newGame); //ADD BUTTON TO MENUBAR
        newGame.addActionListener((ActionEvent e) -> { //REMOVE ACTIONLISTENER CLASSES AND USE LAMBDA EXPRESSIONS TO DECREASE NUMBER OF LINES
            int cols = Integer.parseInt(JOptionPane.showInputDialog("Enter number of columns: ")); //INPUT A NEW NNUMBER OF COLUMNS AND ROWS
            int rows = Integer.parseInt(JOptionPane.showInputDialog("Enter number of rows: "));
            puzzleModel = new Model(cols, rows);
            puzzleModel.reset(); //GENERATE A NEW PUZZLE
            //contents = puzzleModel._contents; //
            graphicsPanel.repaint();
        });
        //game.add(restartGame);
        restartGame.addActionListener((ActionEvent e) -> {
            //puzzleModel._contents = puzzleModel._restart;
            //puzzleModel._emptyTile = puzzleModel._emptyRestart;
            graphicsPanel.repaint();
        });
        menu.add(game);
        
        options.add(cellColour);
        cellColour.addActionListener((ActionEvent e) -> { 
            Color temp = colour; //CREATE TEMP COLOUR
            colour = JColorChooser.showDialog(null, "Change Colour", colour); //PROMPT COLOUR CHANGE
            if (colour == null) colour = temp; //RESTORE ORIGINAL COLOUR CANCELLED CLICKED OR WINDOW CLOSED
            graphicsPanel.repaint();
        });        
        menu.add(options);
        
        this.setLayout(new BorderLayout()); //CREATE CONTAINER LAYOUT
        this.add(messaggePanel, BorderLayout.NORTH); //ADD PANELS TO CONTAINER
        this.add(graphicsPanel, BorderLayout.CENTER);
        this.add(infoPanel, BorderLayout.SOUTH);
        frame.setJMenuBar(menu); //ADD JMENU AS FRAME MENU BAR
    }

    class GraphicsPanel extends JPanel implements MouseListener {
        int cellSize = 60; // Pixels
        Font font;

        public GraphicsPanel() {
            font = new Font("SansSerif", Font.BOLD, cellSize / 2);
            this.setPreferredSize(new Dimension(cellSize * puzzleModel.cols, cellSize * puzzleModel.rows));
            this.setBackground(Color.black);
            this.addMouseListener(this);  // Listen own mouse events.
        }     
 
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gtd = (Graphics2D)g;
            
            for (int r = 0; r < puzzleModel.rows; r++) { //DRAW ROWS
                for (int c = 0; c < puzzleModel.cols; c++) { //DRAW COLUMNS
                    int x = c * cellSize;
                    int y = r * cellSize;
                    
                    String text = puzzleModel.getFace(r, c);
                    
                    if (text != null) {
                        gtd.setColor(colour);
                        gtd.fillRect(x + 2, y + 2, cellSize - 4, cellSize - 4);
                        gtd.setColor(Color.BLACK);
                        gtd.setFont(font);
                        gtd.drawString(text, x + 20, y + (3 * cellSize) / 4);
                    }
                }
            }
        }
     
        public void mousePressed(MouseEvent e) {
            int col = e.getX() / cellSize;
            int row = e.getY() / cellSize;

            if (puzzleModel.moveTile(row, col)) { //RETURNS FALSE IS AN ILLEGAL MOVE
                numberOfMoves += 1;
                moves.setText(Integer.toString(numberOfMoves) + " Moves");
                if(puzzleModel.isGameOver()) message.setText("Congratulations, you completed a game!"); 
            }

            this.repaint();  // Show any updates to model.
        }

        public void mouseClicked (MouseEvent e) {

        }
        
        public void mouseReleased(MouseEvent e) {
        
        }
        
        public void mouseEntered (MouseEvent e) {
        
        }
        
        public void mouseExited  (MouseEvent e) {
        
        }
    }
}