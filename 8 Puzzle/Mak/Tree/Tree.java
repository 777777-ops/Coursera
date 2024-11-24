package Mak.Tree;


import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
    public T element;
    List<Tree<T>> nodelist = new ArrayList<>();

    public Tree(T data) {
        this.element = data;
    }

    public Tree() {

    }

    public void insert(T data) {
        this.nodelist.add(new Tree<T>(data));
    }

    public static <T> boolean hasEqual(T staticdata, Tree<T> node) {
        if (!node.element.equals(staticdata)) {
            for (int i = 0; i < node.nodelist.size(); i++) {
                if (hasEqual(staticdata, node.nodelist.get(i)))
                    return true;
            }
            return false;
        }
        return true;

    }

    public static <T> Tree<T> reTree(T staticdata, Tree<T> node) {
        if (node.element.equals(staticdata))
            return node;
        else {
            for (int i = 0; i < node.nodelist.size(); i++) {
                Tree<T> t1 = reTree(staticdata, node.nodelist.get(i));
                if (t1 != null)
                    return t1;
            }
            return null;
        }

    }
}