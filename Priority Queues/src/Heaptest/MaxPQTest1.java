package Heaptest;
import heap.*;

import org.junit.*;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class MaxPQTest1 {



    @Test
    public void check_1(){
        MaxPQ<Key> heap=new MaxPQ<>(100);

        heap.insert(new Key(1));
        heap.insert(new Key(8));
        heap.insert(new Key(4));
        heap.insert(new Key(9));

        List<Key> expected =new ArrayList<>();
        expected.add(new Key(9)); expected.add(new Key(8)); expected.add(new Key(4)); expected.add(new Key(1));

        assertEquals(expected.toString(), heap.ToList().toString());
        System.out.println(expected.toString());
    }

    @Test
    public void check_2(){
        MaxPQ<Key> heap=new MaxPQ<>(100);

        heap.insert(new Key(1));
        heap.insert(new Key(8));
        heap.insert(new Key(4));
        heap.insert(new Key(9));

        assertEquals(heap.delMax().num,9);

        assertEquals(heap.ToList().toString(),"[8, 1, 4]");

        System.out.println(heap.ToList().toString());
    }


    private static class Key implements Comparable<Key>
    {

        int num;
        public Key(){}
        public Key(int num){
            this.num =num;
        }


        @Override
        public int compareTo(Key o) {
            return this.num-o.num;
        }
        @Override
        public String toString(){
            return ""+this.num;
        }
    }
}
