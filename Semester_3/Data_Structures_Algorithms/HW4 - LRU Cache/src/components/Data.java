package components;

/**
 * Data Class
 *
 * A class that holds a Key and a Value pair.
 *
 * @author P3200262
 * @info Made for the course of Data Structures @ AUEB 2021-2022 as a BONUS assignment
 **/

public class Data <K, V>{
    private final K key;
    private final V value;
    
    public Data(K key, V value){
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return key;
    }
    
    public V getValue(){
        return value;
    }
}
