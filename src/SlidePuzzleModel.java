
import java.util.*;

class SlidePuzzleModel {
    public int cols = 6, rows = 3; //SLIDEPUZZLEGUI GETS COL AND ROW FROM THIS CLASS BECAUSE
    public Tile[][] _contents;  //STORES ALL TILES
    private Tile _emptyTile; //STORES EMPTY TILE 

    public SlidePuzzleModel(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        _contents = new Tile[rows][cols];
        reset();
    }
 
    String getFace(int row, int col) {
        return _contents[row][col].getFace();
    }
 
    public void reset() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                _contents[r][c] = new Tile(r, c, "" + (r * cols + c));
            }
        }

        _emptyTile = _contents[rows-1][cols-1];
        _emptyTile.setFace(null);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                exchangeTiles(r, c, (int)(Math.random() * rows), (int)(Math.random() * cols));
            }
        }
    }
 
    public boolean moveTile(int r, int c) {      
        return checkEmpty(r, c, -1, 0) || checkEmpty(r, c, 1, 0) || checkEmpty(r, c, 0, -1) || 
                checkEmpty(r, c, 0, 1) || checkEmpty(r, c, -1, 1) || checkEmpty(r, c, -1, -1) || 
                checkEmpty(r, c, 1, -1) || checkEmpty(r, c, 1, 1); //CHECK TILES IN ALL 8 DIRECTIONS ALLOWING FOR DIAGONAL MOVEMENT   
    }

    ArrayList<Tile> tileList = new ArrayList<Tile>(); //STORES TILES
    private boolean checkEmpty(int r, int c, int rdelta, int cdelta) {
        int rNeighbor = r + rdelta, cNeighbor = c + cdelta;

        if (isLegalRowCol(rNeighbor, cNeighbor) && _contents[rNeighbor][cNeighbor] == _emptyTile) { //CHECK FOR EMPTY TILES
            tileList.add(new Tile(r, c, getFace(r, c))); //ADD EMPTY TILE NEIGHBOR
            Collections.reverse(tileList); //REVERSE LIST
            for (int i = 0; i < tileList.size(); i++) exchangeTiles(tileList.get(i)._row, tileList.get(i)._col, tileList.get(i)._row + rdelta, tileList.get(i)._col + cdelta); //MOVES TILES ONE AFTER ANOTHER IN TILELIST
            tileList.clear(); //REMOVES ALL TILES FROM LIST
            return true; //EMPTY TILE HAS BEEN FOUND
        } 
        else if (isLegalRowCol(rNeighbor, cNeighbor)) { //CHECK CELL IF EMPTY ISNT FOUND
            tileList.add(new Tile(r, c, getFace(r, c))); //ADDS A TILE TO LIST
            checkEmpty(rNeighbor, cNeighbor, rdelta, cdelta); //CHECKS NEXT NEIGHTBOR BEFORE MOVING TILES
        }
        else {
            tileList.clear(); //REMOVES ALL TILES FROM LIST
        }           
        
        return false;
    } 
 
    public boolean isLegalRowCol(int r, int c) { //CHECK IF INDEX IS OUT OR RANGE
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    private void exchangeTiles(int r1, int c1, int r2, int c2) { //THIS METHOD SWITCHES TILES
        try { //PREVENTS AN ERROR
            Tile temp = _contents[r1][c1];
            _contents[r1][c1] = _contents[r2][c2];
            _contents[r2][c2] = temp;
        }
        catch (Exception e) {}
    }
     
    public boolean isSolvable() { //THIS METHOD WAS NOT COMPLETED
        return true;
    }
    
    public boolean isGameOver() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) { //CHANGES ROWS TO COLS
                Tile trc = _contents[r][c];
                if (!trc.isInFinalPosition(r, c)) return false; //GAME IS NOT OVER IF ANY GIVEN TILE IS NOT IN FINAL POSITION
            }
        }
     
        return true; //IF NONE OF THE TILES ARE IN THE INCORRECT PLACE THEN THE GAME IS OVER
    }
}        
