
class Tile {
    public int _row;     // row of final position
    public int _col;     // col of final position
    private String _face;  // string to display 
    
    public Tile(int row, int col, String face) {
        _row = row;
        _col = col;
        _face = face;
    }
 
    public void setFace(String newFace) {
        _face = newFace;
    }
 
    public String getFace() {
        return _face;
    }

    public boolean isInFinalPosition(int r, int c) {
        return r == _row && c == _col;
    }
}
