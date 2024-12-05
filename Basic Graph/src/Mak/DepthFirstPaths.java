package Mak;

import java.util.*;

public class DepthFirstPaths<Key> {

    private boolean[] marked;
    private Stack<Key> stack;
    private Key[] edgeTo;  //上一个所连接的节点
    private Graph<Key> graph;
    private Key head;

    public DepthFirstPaths(Graph<Key> initgraph,Key inithead){
        this.graph=initgraph;
        this.head=inithead;
        this.marked=new boolean[graph.getV()];
        this.stack=new Stack<>();
        this.edgeTo=(Key[])new Object[graph.getV()];
        Arrays.fill(this.edgeTo, null);


        dfp();
    }

    private void dfp(){

        stack.add(head);
        marked[graph.findIndex(head)]=true;

        Key key=head;
        //System.out.print(key+" ");
        //int index=graph.findIndex(key);
        while(true){
            //index=graph.findIndex(key);

            Iterable<Key> iterable=graph.neighborVertex(key);
            boolean isAllMarked=true;
            for (Key neighborkey : iterable) {
                int neighborkeyindex=graph.findIndex(neighborkey);
                if(marked[neighborkeyindex])
                    continue;

                isAllMarked=false;
                stack.add(neighborkey);
                marked[neighborkeyindex]=true;
                edgeTo[neighborkeyindex]=key;

                key=neighborkey;
                //System.out.print(key+" ");

                break;
            }
            if(isAllMarked){
                stack.pop();  //pop出来的这个元素是没有用的 就是出度都已经marked过了
                if(stack.isEmpty()) break;
                key=stack.peek();

            }

        }
    }

    public Iterable<Key> isPath(Key key)
    {
        List<Key> path=new ArrayList<>();
        path.add(key);
        Key travel=key;
        int index= graph.findIndex(travel);
        while(edgeTo[index]!=null)
        {
            travel=edgeTo[index];
            index= graph.findIndex(travel);
            path.add(travel);
        }


        Collections.reverse(path);

        return path;
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
        graph.addEdge(str1,str2);
        graph.addEdge(str1,str3);
        graph.addEdge(str4,str2);
        graph.addEdge(str3,str5);
        graph.addEdge(str4,str5);
        graph.addEdge(str6,str5);
        graph.addEdge(str7,str6);
        graph.addEdge(str8,str7);
        graph.addEdge(str8,str5);

        DepthFirstPaths<String> dfp=new DepthFirstPaths<>(graph,str1);

        System.out.println(graph.findIndex(str8));
        System.out.println(dfp.isPath(str8));
    }



}
