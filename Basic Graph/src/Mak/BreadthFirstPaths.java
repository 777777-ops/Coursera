package Mak;


import java.util.*;

//广度优先搜索实现 BFP
public class BreadthFirstPaths<Key> {

    private boolean[] marked;
    private Queue<Key> queue;
    private Key[] edgeTo;  //上一个所连接的节点
    private Graph<Key> Graph;
    private Key head;

    //
    public BreadthFirstPaths(Graph<Key> graph,Key head){
        this.queue=new AbstractQueue<Key>() {

            List<Key> keyqueue=new ArrayList<>();
            @Override
            public boolean offer(Key key) {
                keyqueue.add(key);
                return true;
            }

            @Override
            public Key poll() {

                if(keyqueue.isEmpty()){return null;}
                else{
                    Key k=keyqueue.get(0);

                    keyqueue.remove(0);
                    return k;
                }

            }

            @Override
            public Key peek() {
                return keyqueue.get(0);
            }

            @Override
            public Iterator<Key> iterator() {
                return null;
            }

            @Override
            public int size() {
                return  keyqueue.size();
            }
        };

        this.Graph =graph;
        this.head=head;
        marked=new boolean[graph.getV()];
        edgeTo=(Key[])new Object[graph.getV()];

        for(int i=0;i<graph.getV();i++){marked[i]=false; edgeTo[i]=null;}


      dfs();


    }

    private void dfs(){
        queue.add(head);
        marked[Graph.findIndex(head)]=true;
        System.out.print(head+" ");

        Key key;
        while(!queue.isEmpty())
        {
            key=queue.remove();
            //
            Iterable<Key> keyNeighborList= Graph.neighborVertex(key);
            for(Key keyNeighbor:keyNeighborList)
            {
                int index= Graph.findIndex(keyNeighbor);
                if(marked[index])
                    continue;

                edgeTo[index]=key;
                marked[index]=true;
                queue.add(keyNeighbor);
                System.out.print(keyNeighbor+" ");
            }
        }
    }

    public Iterable<Key> shortestPath(Key key)
    {
        List<Key> path=new ArrayList<>();
        path.add(key);
        Key travel=key;
        int index= Graph.findIndex(travel);
        while(edgeTo[index]!=null)
        {
            travel=edgeTo[index];
            index= Graph.findIndex(travel);
            path.add(travel);
        }


        Collections.reverse(path);

        return path;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int V=sc.nextInt();
        int E=sc.nextInt();




        Integer[] keys=new Integer[V];
        for (int i = 0; i < V; i++) {
            keys[i]=i;
        }
        LGraph<Integer> L=new LGraph<>(keys);

        for (int i = 0; i < E; i++) {
            L.addEdge(keys[sc.nextInt()],keys[sc.nextInt()]);
        }
        for (int i = 0; i < L.getV(); i++) {
            System.out.print(i);
            for (int size = L.adj[i].size()-1; size >= 0; size--) {
                System.out.print(" "+L.adj[i].get(size));
            }
            System.out.println();
        }

        System.out.println();
        BreadthFirstPaths<Integer> BFP=new BreadthFirstPaths<>(L,keys[0]);
    }
}
