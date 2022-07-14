package UnionFind.HW;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF WQUF;
    private final boolean[] sites;
    private final int n;
    private int open_sites;

    
    public Percolation(int n){
        if (n <= 0) throw new IllegalArgumentException();
    
        this.WQUF = new WeightedQuickUnionUF(n*n + 2);
        this.sites = new boolean[n*n + 2];
        
        this.n = n;
        this.open_sites = 0;
    }// Creates n-by-n grid, with all sites initially set to false AKA blocked.
    
    public void open(int row, int col){
        checkSpot(row, col);
        
        int index = idxOf(row, col);
        
        if(!sites[index]){
            sites[index] = true;
            open_sites++;
            
            if(row == 1) WQUF.union(index, 0);
            if(row == n - 1) WQUF.union(index, n*n + 1);
            
            if(row != 1 && isOpen(row - 1, col)){
                assert(index > n);
                WQUF.union(index, index - n);
            }
            if (row != n - 1 && isOpen(row+1, col)) {
                assert(index+n < n*n);
                WQUF.union(index, index+n);
            }
            if (col != 1 && isOpen(row, col-1)) {
                WQUF.union(index, index-1);
            }
            if (col != n - 1 && isOpen(row, col+1)) {
                WQUF.union(index, index+1);
            }
        }
    }// Opens the cell at the coordinates and connects it with open neighbours.
    
    public boolean percolates(){
        return WQUF.connected(0, n*n + 1);
    }// Determines whether the system percolates AKA if the imaginary bottom is connected to the top.
    
    public boolean isOpen(int row, int col){
        checkSpot(row, col);
        return sites[idxOf(row, col)];
    }// Determines whether a cell is open.
    
    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        checkSpot(row,col);
        if (!isOpen(row, col)) {
            return false;
        }
        return WQUF.connected(idxOf(row, col), 0);
    }// Determines whether a cell is connected to the imaginary top.
    
    public int numberOfOpenSites(){
        return this.open_sites;
    }// Returns the number of open sites.
    
    
    private void checkSpot (int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new java.lang.IllegalArgumentException();
        }
    }// Checks if the cords given are correct.
    
    private int idxOf(int row, int col) {
        return (row - 1)*n + (col - 1);
    }// Converts 2D cords to 1D cords.
    
    
    public static void main(String[] args){
        Percolation grid = new Percolation(20);

        while(!grid.percolates()){
            grid.open(StdRandom.uniform(1, 20), StdRandom.uniform(1, 20));
        }
    }
}