package Mak;

// 学会throw错误

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int[][] tiles;
    public int hasMove = 0;


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
    }


    // string representation of this board
    public String toString() {
        StringBuilder represtr = new StringBuilder(this.dimension() + "\n");
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++)
                represtr.append(" " + this.tiles[i][j]);
            represtr.append("\n");
        }
        return represtr.toString();
    }

    // board dimension n
    public int dimension() {
        return this.tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        int sum = 0;
        for (int i = 0; i < this.tiles.length; i++)
            for (int j = 0; j < this.tiles[i].length; j++)
                if (this.tiles[i][j] != i * this.dimension() + j + 1)
                    sum++;
        if (this.tiles[this.dimension() - 1][this.dimension() - 1] == 0)
            sum--;
        return sum;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < this.tiles.length; i++)
            for (int j = 0; j < this.tiles[i].length; j++) {
                if (this.tiles[i][j] == 0)
                    continue;
                sum += Math.abs((this.tiles[i][j] - 1) / this.dimension() - i)
                        + Math.abs((this.tiles[i][j] - 1) % this.dimension() - j);
            }
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        Board yy;
        yy = (Board) y;


        for (int i = 0; i < this.tiles.length; i++)
            for (int j = 0; j < this.tiles[i].length; j++)
                if (yy.tiles[i][j] != this.tiles[i][j])
                    return false;
        return true;

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> boardList = new ArrayList<>();

        int row = 0;
        int column = 0;
        for (int i = 0; i < this.tiles.length; i++)
            for (int j = 0; j < this.tiles[i].length; j++)
                if (this.tiles[i][j] == 0) {
                    row = i;
                    column = j;
                }

        if (row > 0) {
            boardList.add(twin(row, column, row - 1, column));
        }
        if (row < this.dimension() - 1) {
            boardList.add(twin(row, column, row + 1, column));
        }
        if (column > 0) {
            boardList.add(twin(row, column, row, column - 1));
        }
        if (column < this.dimension() - 1) {
            boardList.add(twin(row, column, row, column + 1));
        }

        boardList.iterator();  // 实现接口

        return boardList;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(int row, int column, int exrow, int excolumn) {


        int[][] newtiles = new int[this.tiles.length][this.tiles.length];
        for (int i = 0; i < this.tiles.length; i++)
            for (int j = 0; j < this.tiles[i].length; j++)
                newtiles[i][j] = this.tiles[i][j];


        /*   虽然newtiles 和 this.tiles的地址值不一样但是  newtile[1] newtiles[2]  newtiles[3] 分别和this.tiles[1] .[2]  .[3]相同
        int[][] newtiles = this.tiles.clone();

        System.out.println(newtiles);
        System.out.println(this.tiles);
        */


        newtiles[row][column] = newtiles[exrow][excolumn];
        newtiles[exrow][excolumn] = 0;

        return new Board(newtiles);
    }
    /*   因为this.tiles和board.tiles中的tiles是同一个地址值  所以不行
    public Board twin(int row, int column, int exrow, int excolumn) {
        Board board=new Board(this.tiles);
        board.tiles[row][column] = board.tiles[exrow][excolumn];
        board.tiles[exrow][excolumn] = 0;
        return board;
    }
     */

    public int[][] getTiles() {
        return this.tiles;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

        String filename = "puzzle01.txt";

        // read in the board specified in the filename
        In in = new In(filename);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        Board board = new Board(tiles);
        // board.neighbors();
        for (Board b : board.neighbors())
            System.out.println(b);


    }
}
