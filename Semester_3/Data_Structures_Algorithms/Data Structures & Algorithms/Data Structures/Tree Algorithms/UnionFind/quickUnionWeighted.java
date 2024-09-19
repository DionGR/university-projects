/*
author: @Dion
content: A smart approach to the quick-find algorithm with weight and path compression.

Pros: Keeps trees flat and has little cost and overhead.

Space Complexity: O(n)
Initialize: O(n)
Union: O(lgN)
Find: O(lgN)
Array Accesses: n
 */

public class quickUnionWeighted {
    private int[] id;
    private int[] size;
    
    public quickUnionWeighted(int n){
        this.id = new int[n];
        for(int i = 0; i < n; i++)
            this.id[i] = i;
    }// Constructor. Initializes all the nodes with their respective IDs. No unions.
    
    private int root(int i){
        while(i != id[i]){
            id[i] = id[id[i]];
            i = id[i];
        }// Goes up the tree, finding the root of all roots of that path. Also flattens the tree with path compression.
        return i;
    }
    
    public boolean connected(int p, int q){
        return root(p) == root(q);
    }
    
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if(i == j) return;
        if(size[i] < size[j]){
            id[i] = j;
            size[j] += size[i];
        }else {
            id[j] = i;
            size[i] += size[j];
        }
    }
}
