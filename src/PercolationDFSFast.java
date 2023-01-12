public class PercolationDFSFast extends PercolationDFS {
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationDFSFast(int n) {
        super(n);
    }

    @Override
    protected void updateOnOpen(int row, int col) {
        /**
         * five if statements are written that all change openUp to true if they pass
         * will call the recursive dfs(row, col) method if one of the if statements changed openUp to true
         * dfs will set the cell to full and recursively check the neighboring cells to see if they also
         * should be set to full
         */
        boolean openUp = false;
        if (row == 0) {
            openUp = true;
        }
        if(inBounds(row - 1, col) && isFull(row - 1, col)){
            openUp = true;
        }
        if (inBounds(row, col - 1) && isFull(row, col -1)){
            openUp = true;
        }
        if(inBounds(row, col + 1) && isFull(row, col + 1)) {
            openUp = true;
        }
        if(inBounds(row + 1, col) && isFull(row + 1, col)){
            openUp = true;
        }
        if(openUp){
            dfs(row, col);
        }
    }
}



