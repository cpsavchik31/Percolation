import java.util.Arrays;

public class PercolationUF implements IPercolate {
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    public PercolationUF(IUnionFind finder, int size) {

        //myFinder = new QuickUWPC();
        //sets values to instance variables
        finder.initialize(size * size + 2);
        myFinder = finder;
        myGrid = new boolean[size][size];
        VTOP = size * size;
        VBOTTOM = size * size + 1;
        myOpenCount = 0;
        for (boolean[] row: myGrid){
            Arrays.fill(row, false);
        }
    }
    @Override
    public void open(int row, int col) {
        /**
         *   opens a specific cell at row col, as long as the cell
         *  is in bounds and not already open; cell is changed to true.
         *  checks are made to see if the cell is in the top or bottom row; will be added to the top/bottom set accordingly
         *  checks adjacent cells to see if they are open and will group them
         */
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        if (isOpen(row, col)) {
            return;
        }
        if (!isOpen(row, col)) {
            myGrid[row][col] = true;
            myOpenCount++;
        }
        if (isOpen(row, col)) {
            if (row == 0) {
                myFinder.union(VTOP, row * myGrid.length + col);
            }
            if (row == myGrid.length - 1) {
                myFinder.union(VBOTTOM, row * myGrid.length + col);
            }
            int[] rowDelta = {-1, 1, 0, 0};
            int[] colDelta = {0, 0, -1, 1};
            for (int k = 0; k < rowDelta.length; k++) {
                int rower = row + rowDelta[k];
                int colu = col + colDelta[k];
                if (inBounds(rower, colu)) {
                    if (isOpen(rower, colu)) {
                        myFinder.union((row * myGrid.length + col), (rower * myGrid.length + colu));
                    }
                }
            }
        }
    }
    @Override
    public boolean isOpen(int row, int col) {
        /**
         * checks if a certain cell is open
         * returns the boolean value associated with it
         */

		if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        /**
         * checks if a certain cell is full by
         * calling .connected with the corresponding integer value and VTOP
         */

        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        return myFinder.connected((row * myGrid.length + col), VTOP);
    }

    @Override
    public boolean percolates() {
        //checks if VTOP and VBOTTOM are connected
        return myFinder.connected(VTOP, VBOTTOM);
    }
    @Override
    public int numberOfOpenSites() {
        //returns the number of open cells
        return myOpenCount;
    }

    protected boolean inBounds(int row, int col) {
        //checks if a certain pair of coordinates lies with in the bounds of the cell
            if (row < 0 || row >= myGrid.length) return false;
            if (col < 0 || col >= myGrid[0].length) return false;
            return true;
        }
}
