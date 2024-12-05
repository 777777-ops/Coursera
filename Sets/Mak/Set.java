package Mak;

import java.util.ArrayList;
import java.util.List;

public class Set<Key> {


    int[] parents=new int[100];
    Key[] keyList=(Key[])new Object[100];  //key值和parents的映射表
    public int size;

    public Set(){
        this.size=0;
    }


    //新建一个节点
    public void insertKey(Key key)
    {
        keyList[size]=key;
        parents[size]=-1;   //-1代表该集合的大小为1
        size++;
    }

    private int findRoot(int index)
    {
        if(parents[index]<0)
            return index;
        else {
            parents[index] = findRoot(parents[index]);  //路径压缩 ，尽量使每个节点都直接指向根据点
            return parents[index] ;
        }
    }

    public boolean isConnected(Key p,Key q)
    {
        return findRoot(findKeyIndex(p))==findRoot(findKeyIndex(q));
    }

    public void Connected(Key p,Key q)
    {
        if(isConnected(p,q))
            return;
        else{

            int pRoot=findRoot(findKeyIndex(p));
            int qRoot=findRoot(findKeyIndex(q));
            //q比较大
            if(parents[pRoot]>parents[qRoot]){
                parents[qRoot]+=parents[pRoot];     //对集合大小进行改变
                parents[pRoot]=qRoot;
            }else{
                parents[pRoot]+=parents[qRoot];     //对集合大小进行改变
                parents[qRoot]=pRoot;
            }

        }

    }

    private int findKeyIndex(Key key)
    {
        for (int i = 0; i < size; i++) {
            if(keyList[i].equals(key))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String str1="A";
        String str2="B";
        String str3="C";
        String str4="D";

        Set<String> set=new Set<>();

        set.insertKey(str1);
        set.insertKey(str2);
        set.insertKey(str3);
        set.insertKey(str4);


       set.Connected(str1,str3);
        set.Connected(str3,str4);
        System.out.println(set.isConnected(str1,str4));

    }
}