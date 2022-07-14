/*
author: @Dion
content: Content: An eager approach to the quick-find algorithm.

Issue: Union is too expensive.

Space Complexity: O(n)
Initialize: O(n)
Union: O(n)
Find: O(1)
Array Accesses: n^2
 */

public class quickFind {
    private int[] id;
    
    public quickFind(int n){
        this.id = new int[n];
        for(int i = 0; i < n; i++) id[i] = i;
    }
    
    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    public void union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        
        for(int i = 0; i < id.length; i++)
            if (id[i] == pid) id[i] = qid;
    }
    
}
