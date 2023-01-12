

import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast {
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationBFS(int n) {
        super(n);
    }

    @Override
    protected void dfs(int row, int col) {
        /**
         * creates a queue and adds
         * a full cell to the queue, which is then removed
         * the neighboring cells are checked for bounds and if open,
         * and will be set to full and added to the queue as well
         */
        Queue<Integer> qp = new LinkedList<>();
        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};
        if (!inBounds(row,col)) return;
        if (isFull(row, col) || !isOpen(row, col)){
            return;
        }
        myGrid[row][col] = FULL;
        qp.add(row * myGrid.length + col);
        while (qp.size() != 0){
            int withmyPair = qp.remove();
            int wmpRow = withmyPair/myGrid.length;
            int wmpCol = withmyPair % myGrid.length;
            for(int k = 0; k < rowDelta.length; k++){
                int rower = wmpRow + rowDelta[k];
                int colu = wmpCol + colDelta[k];
                if(inBounds(rower,colu) && isOpen(rower,colu) && !isFull(rower,colu)){
                    myGrid[rower][colu] = FULL;
                    qp.add(rower * myGrid.length + colu);
                }
                //If the neighboring cell is open and not full, it should be marked as full and enqueued
            }
        }
    }
}
