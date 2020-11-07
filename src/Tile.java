
class Tile {
    public int row;     // row of final position
    public int col;     // col of final position
    private String face;  // string to display 
    
    public Tile(int row, int col, String face) {
        this.row = row;
        this.col = col;
        this.face = face;
    }
 
    public void setFace(String face) {
        this.face = face;
    }
 
    public String getFace() {
        return this.face;
    }

    public boolean isInFinalPosition(int row, int col) {
        return row == this.row && col == this.col;
    }
}
