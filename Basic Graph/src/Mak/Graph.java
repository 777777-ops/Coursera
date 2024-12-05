package Mak;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph<Key> {
    //不适合使用Map
    //private Map<Key,Integer> map=new HashMap<>();
    protected List<Key> keyList=new ArrayList<>();



    protected int V;  //总点数

    //带权实现

    abstract public int getV();

    abstract public int findIndex(Key key);

    abstract public void addEdge(Key k1,Key k2);

    abstract public Iterable<Key> neighborVertex(Key key);

}
