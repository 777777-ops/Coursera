package Mak.Tree;

import java.util.ArrayList;
import java.util.List;

public class tree<T> {
    public T data;
    List<tree<T>> nodelist = new ArrayList<>();

    public tree(T data) {
        this.data = data;
    }

    public tree() {

    }

    public void insert(T data) {
        this.nodelist.add(new tree<T>(data));
    }

    public static <Key, T> boolean hasEqual(Key staticdata, tree<T> node) {
        if (!node.data.equals(staticdata)) {
            for (int i = 0; i < node.nodelist.size(); i++) {
                if (hasEqual(staticdata, node.nodelist.get(i)))
                    return true;
            }
            return false;
        }
        return true;

    }
}
