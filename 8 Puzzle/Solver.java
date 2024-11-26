import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solver {

    private int minmove;
    private boolean isSolvable;
    private Board initboard;
    private Node headNode;
    private Node finalNode;

    private class Node {
        int move;
        int manhattan;

        Board board;
        List<Node> nodeList = new ArrayList<>();
        Node preNode;

        public Node(Board initboard) {
            this.manhattan = initboard.manhattan();
            this.board = initboard;
            this.move = 0;
            preNode = null;
        }

        public void insert(Node insNode) {
            this.nodeList.add(insNode);
        }


    }


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.initboard = initial;
        this.isSolvable = false;
        this.headNode = new Node(initboard);

        goAlgorithm();
    }

    private void goAlgorithm() {
        MinPQ<Node> pq = new MinPQ<>(new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                return -(o2.move + o2.manhattan) + (o1.move + o1.manhattan);
            }
        });
        pq.insert(headNode);


        // Board board = initboard;
        Node node = headNode;

        while (!node.board.isGoal()) {
            for (Board neighbor : node.board.neighbors()) {
                if (node.preNode == null || !node.preNode.board.equals(neighbor)) {
                    Node newNode = new Node(neighbor);
                    newNode.move = node.move + 1;
                    newNode.preNode = node;

                    node.insert(newNode);
                    pq.insert(newNode);
                }
            }
            node = pq.delMin();
        }

        minmove = node.move;
        this.isSolvable = true;
        this.finalNode = node;


    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return minmove;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        List<Board> boardList = new ArrayList<>();
        Node node = finalNode;
        while (node != null) {
            boardList.add(node.board);
            node = node.preNode;
        }
        Collections.reverse(boardList);
        return boardList;
    }

    // test client (see below)
    public static void main(String[] args) {
        String filename = "puzzle50.txt";

        In in = new In(filename);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }


        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);


        if (solver.isSolvable()) {
            System.out.println("Minimum number of moves = " + solver.moves());
            System.out.println();


            Iterable<Board> iterable = solver.solution();

            for (Board board : iterable) {
                System.out.println(board);
            }


        }
    }

}