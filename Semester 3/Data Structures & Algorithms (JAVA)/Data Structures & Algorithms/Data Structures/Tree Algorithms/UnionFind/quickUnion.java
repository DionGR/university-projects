/*
author: @Dion
content: Content: A lazy approach to the quick-find algorithm.

Issue: Trees can get tall and find is too expensive.

Space Complexity: O(n)
Initialize: O(n)
Union: O(n)
Find: O(n)
Array Accesses: n
 */

public class quickUnion {
    private int[] id;
    
    public quickUnion(int n){
        this.id = new int[n];
        for(int i = 0; i < n; i++) id[i] = i;
    }
    
    private int root(int i){
        while(i != id[i]) i = id[i];
        return i;
    }
    
    public boolean connected(int p, int q){
        return root(p) == root(q);
    }
    
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}
