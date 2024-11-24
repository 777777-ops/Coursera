package Mak;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {

    private int minmove = 999990;
    private boolean isSolvable = false;
    public Board board = null;
    private MinPQ<Board> pq = new MinPQ<>(new Comparator<Board>() {
        public int compare(Board o1, Board o2) {
            return o2.hasMove + o2.manhattan() - (o1.hasMove + o1.manhattan());
        }
    });

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.board = initial;
        goAlgorithm(this.board);
    }

    private void goAlgorithm(Board goboard) {
        pq.insert(goboard);

        List<Board> hasBoard = new ArrayList<>();

        Board headboard = goboard;

        while (!headboard.isGoal()) {
            hasBoard.add(pq.delMin());
            for (Board b : headboard.neighbors()) {
                boolean flag = true;
                for (int i = 0; i < hasBoard.size(); i++) {
                    if (b.equals(hasBoard.get(i)))
                        flag = false;
                }
                if (!flag)
                    continue;

                b.hasMove++;
                pq.insert(b);
            }
            if (pq.isEmpty()) {
                this.isSolvable = false;
                return;
            }
            headboard = pq.min();
        }
        minmove = headboard.hasMove;
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
        String filename = "puzzle2x2-unsolvable1.txt";

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
    }

}