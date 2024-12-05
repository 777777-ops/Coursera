package Mak;


import Funtion.MinPQ;

import java.util.Comparator;


/**
    修改堆中的数据并重新排序的时间复杂度log(n)，但是本码引用Coursera中的MinPQ未实现该功能，所以偷懒采用时间复杂度为n的堆重构
*/
public class Dijkstra<Key> {

    final int INFINITE=65565; //无穷
    int[] distTo;
    Key[] edgeTo;
    Key head;  //源点
    LGraph<Key> graph;  //图
    private class Fringe{   //起点到点的距离
        Key targetKey;
        int dist;

        public Fringe(Key targetKey,int initdist)
        {
            this.targetKey=targetKey;
            dist=initdist;
        }
    }

    MinPQ<Fringe> pq= new MinPQ<>(new Comparator<>() {
        @Override
        public int compare(Fringe o1, Fringe o2) {
            return o1.dist - o2.dist;
        }
    });

    public Dijkstra(LGraph<Key> initgraph,Key inithead){

        //DIjkstra<Key>中的key会因为Graph<Key>而确定

        this.head=inithead;
        this.graph=initgraph;
        this.distTo=new int[graph.V];
        this.edgeTo=(Key[]) new Object[graph.V];
        for (int i = 0; i < graph.V; i++) {edgeTo[i]=null; distTo[i]=INFINITE; pq.insert(new Fringe(graph.keyList.get(i),INFINITE));}
        int headindex=graph.findIndex(head);   distTo[headindex]=0;  //初始化源点数据
        pq.resort(SetKeyInPQ(head,0));

        Dijk();
    }

    /////////////初始化完成

    private void Dijk(){

        Fringe fringe;  //被取出的最短路径
        Key origKey;
        while(!pq.isEmpty()) {
            fringe=pq.delMin();
            origKey=fringe.targetKey;
            int originW=fringe.dist;   //原来到达traget点的最短路径

            for (LGraph<Key>.Edge neigEdge : graph.neighborEdge(origKey)) {
                Key key=neigEdge.key;
                int weight=neigEdge.weight;
                if(distTo[graph.findIndex(key)]!=INFINITE)
                    continue;
                SetKeyInPQ(key,weight+originW);   //对堆中的元素重新赋值 并排序
                edgeTo[graph.findIndex(neigEdge.key)]=origKey;
            }
            distTo[graph.findIndex(origKey)]=originW;  //这边的最短距离数组不是实时变化的！！  所以可以被作为确定是否已确定最短路径的标志
        }
    }

    private  Fringe FindKeyInPQ(Key found)
    {
        for (Fringe fringe : pq) {
            if(fringe.targetKey.equals(found))
            {
                return fringe;
            }
        }
        System.out.println("Couldn't found the key");
        throw new NullPointerException();
    }

    private Fringe SetKeyInPQ(Key found,int n)
    {
        for (Fringe fringe : pq) {
            if(fringe.targetKey.equals(found))
            {
                fringe.dist=n;
                pq.resort(fringe);
                return fringe;
            }
        }
        System.out.println("Couldn't found the key");
        return null;
    }


    public static void main(String[] args) {
        String str1 = "a";
        String str2 = "B";
        String str3 = "c";
        String str4 = "d";
        String str5 = "e";
        String str6 = "f";
        String str7 = "g";
        String str8 = "h";

        LGraph<String> graph=new LGraph<>(str1,str2,str3,str4,str5,str6,str7,str8);
        graph.addEdge(str1,str2,8);
        graph.addEdge(str1,str3,7);
        graph.addEdge(str4,str2,6);
        graph.addEdge(str3,str5,12);
        graph.addEdge(str4,str5,4);
        graph.addEdge(str6,str5,1);
        graph.addEdge(str7,str6,7);
        graph.addEdge(str8,str7,3);
        graph.addEdge(str8,str5,15);

        Dijkstra<String> dfp=new Dijkstra<>(graph,str1);

        ///////////~!!
    }

}
