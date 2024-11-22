package heap;


import java.util.ArrayList;
import java.util.List;
public class MaxPQ<T extends Comparable<T>> {

    private T[] pq;   //
    private int N;    //size

    public MaxPQ(int capacity)
    {
        pq=(T[]) new Comparable[capacity+1];   //why? 类型形参 'Key' 不能直接实例化
    }

    public boolean isEmpty(){
        return N==0;
    }

    public List<T> ToList()
    {
        List<T> arr=new ArrayList<>();
        for(int i=1;i<=N;i++)
        {
            arr.add(pq[i]);
        }
        return arr;
    }
    public void insert(T key)
    {
        if(N>=pq.length)    //the heap is full
            return;
        N++;
        pq[N]=key;   //the N is the new insert index now;
        swim(N);
    }

    private void swim(int k)   //1.k is index  2.swim up 3.helper function of insert
    {
        while(k>1&&less(k/2,k))
        {
            exch(k,k/2);
            k=k/2;
        }
    }
    
    public T delMax()
    {
        T retKey =pq[1];
        exch(1,N);
        pq[N]=null;
        N--;
        sink(1);
        return retKey;
    }

    private void sink(int k)
    {
        while(2*k<N)    //equal or small equal?
        {
            int j=2*k;
            if(j<N&&less(j,j+1)) j++;  //the j is the bigger index;
            if(!less(k,j)) break;     // if k bigger than j   breaK:
            exch(k,j);
            k=j;
        }
    }

    private boolean less(int i,int j)  //if i smaller than j,return ture;
    {
        return pq[i].compareTo(pq[j])<0;
    }

    private void exch(int i,int j)
    {
        T t=pq[i];
        pq[i]=pq[j];
        pq[j]=t;
    }


}
