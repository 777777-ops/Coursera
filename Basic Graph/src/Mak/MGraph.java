package Mak;

import java.util.ArrayList;
import java.util.List;

public class MGraph<Key> extends Graph<Key> {

    //邻接表  //也可以是邻接矩阵
    protected int[][] adj;

    //通过比顶点数Keys初始化
    public MGraph(Key... keys){
        this.V=keys.length;
        adj= new int[V][V];
        for (int v = 0; v < V; v++) {
            for(int e=0 ; e<V ; e++) {adj[v][e]=0;}
            keyList.add(keys[v]);
        }
    }



    @Override
    public int getV() {
        return this.V;
    }

    @Override
    public int findIndex(Key key)
    {
        for (int i = 0; i < keyList.size(); i++) {
            if(key==keyList.get(i))
                return i;
        }
        return -1;
    }

    @Override
    public void addEdge(Key k1, Key k2) {
        int index1=findIndex(k1);
        int index2=findIndex(k2);
        adj[index1][index2]=1;
        adj[index2][index1]=1;
    }

    @Override
    public Iterable<Key> neighborVertex(Key key) {
        List<Key> keyIterable=new ArrayList<>();
        int index=findIndex(key);
        for (int i = 0; i < V; i++) {
            int f=adj[index][i];
            if(f==1)
                keyIterable.add(keyList.get(i));
        }
        return keyIterable;
    }
}
