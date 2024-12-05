package Mak;


import java.util.ArrayList;
import java.util.List;


//后续如果要实现带权的功能要在Key上做文章，如果要直接在MGraph内实现权，就不能<Key>
public class LGraph<Key> extends Graph<Key>{

    //邻接表  //也可以是邻接矩阵
    protected List<Edge>[] adj;

    public class Edge{
        Key key;  //邻接点
        int weight; //权值
        public Edge(Key initkey){this.weight=1; key=initkey;}
        public Edge(Key initkey,int initWeight){this.weight=initWeight;this.key=initkey;}
    }



    public LGraph(Key... keys){
        this.V=keys.length;
        adj= (List<Edge>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
            keyList.add(keys[v]);
        }



    }

    //在k1和k2之间创建edge
    public void addEdge(Key k1,Key k2)
    {
        Edge E1=new Edge(k1);
        Edge E2=new Edge(k2);

        int p=findIndex(k1);
        int q=findIndex(k2);

        adj[p].add(E2);  adj[q].add(E1);
    }

    //k1和k2之间创建有权值的Edge
    public void addEdge(Key k1,Key k2,int weight)
    {
        Edge E1=new Edge(k1,weight);
        Edge E2=new Edge(k2,weight);

        int p=findIndex(k1);
        int q=findIndex(k2);

        adj[p].add(E2);  adj[q].add(E1);
    }

    //找到key对应的下标
    public int findIndex(Key key)
    {
        for (int i = 0; i < keyList.size(); i++) {
            if(key==keyList.get(i))
                return i;
        }
        return -1;
    }

    public Iterable<Key> neighborVertex(Key key){
        List<Key> keyIterable=new ArrayList<>();
        int index=findIndex(key);
        for (int size = adj[index].size()-1; size >= 0; size--) {
            keyIterable.add(adj[index].get(size).key);
        }
        return keyIterable;
    }

    public Iterable<Edge> neighborEdge(Key key){
        List<Edge> keyIterable=new ArrayList<>();
        int index=findIndex(key);
        for (int size = adj[index].size()-1; size >= 0; size--) {
            keyIterable.add(adj[index].get(size));
        }
        return keyIterable;
    }

    public static void main(String[] args) {
        String str1="a";
        String str2="B";
        String str3="c";
        String str4="d";

        LGraph<String> graph=new LGraph<>(str1,str2,str3,str4);

        graph.addEdge(str1,str2);


    }

    @Override
    public int getV(){return this.V;}
}
