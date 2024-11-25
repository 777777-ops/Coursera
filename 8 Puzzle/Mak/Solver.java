package Mak;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {

    private int minmove;
    private boolean isSolvable;
    private Board initboard;
    private Node headNode;

    private class Node {
        Board board;
        List<Node> nodeList = new ArrayList<>();
        Node preNode;

        public Node(Board initboard) {
            this.board = initboard;
            preNode = null;
        }

        public void insert(Board insboard) {
            Node insNode = new Node(insboard);
            insNode.preNode = this;
            this.nodeList.add(insNode);
        }

        public static Node FindNode(Node headNode, Board tarBoard) {
            if (headNode.board.equals(tarBoard))
                return headNode;
            else {
                for (Node node : headNode.nodeList) {
                    Node findNode = FindNode(node, tarBoard);
                    if (findNode != null)
                        return findNode;
                }
            }
            return null;

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
        MinPQ<Board> pq = new MinPQ<>(new Comparator<Board>() {
            public int compare(Board o1, Board o2) {
                return -(o2.hasMove + o2.manhattan()) + (o1.hasMove + o1.manhattan());
            }
        });
        pq.insert(initboard);


        Board board = initboard;
        Node node = headNode;

        List<Board> hasBoardList = new ArrayList<>();
        hasBoardList.add(board);

        while (!board.isGoal()) {

            for (Board neighbor : board.neighbors()) {
                if (Node.FindNode(headNode, neighbor) != null)
                    continue;

                node.insert(neighbor);
                pq.insert(neighbor);
            }

            board = pq.delMin();
            node = Node.FindNode(headNode, board);

        }

        minmove = board.hasMove;
        this.isSolvable = true;


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
    // public Iterable<Board> solution() {}

    // test client (see below)
    public static void main(String[] args) {
        String filename = "puzzle4x4-30.txt";

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

        System.out.println(solver.isSolvable());
        if (solver.isSolvable()) {
            System.out.println(solver.moves());

            /*
            Iterable<Board> iterable = solver.solution();

            for (Board board : iterable) {
                System.out.println(board);
            }

             */

        }
    }

}