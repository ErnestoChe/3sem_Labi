package com.company.CellPackage_2b;

import java.util.ArrayList;

public class Board{
    private final static int LENGTH_CONST = 20;
    public boolean[][] cells;

    //random init
    Board() {
        cells = new boolean[LENGTH_CONST][LENGTH_CONST];
        for (int i = 0; i < LENGTH_CONST; i++) {
            for (int j = 0; j < LENGTH_CONST; j++) {
                if (Math.floor(Math.random() * 100) % 2 == 0) {
                    cells[i][j] = true;
                } else
                    cells[i][j] = false;
            }
        }
    }

    //not random init(for debug mostly)
    Board(int[][] arr) {
        if (arr.length != LENGTH_CONST) {
            System.out.println("enter correct array, wrong length," + "\n" +
                    "all cells on this board will be dead, is this what you wanted???");
        } else {
            cells = new boolean[LENGTH_CONST][LENGTH_CONST];
            for (int i = 0; i < LENGTH_CONST; i++) {
                for (int j = 0; j < LENGTH_CONST; j++) {
                    cells[i][j] = arr[i][j] == 1;
                }
            }
        }
    }

    //prints board
    public void printBoard() {
        for (int i = 0; i < LENGTH_CONST; i++) {
            for (int j = 0; j < LENGTH_CONST; j++) {
                if (cells[i][j]) {
                    System.out.print("O ");
                } else System.out.print("- ");
            }
            System.out.println();
        }
    }

    //count neighbours of cell on (i, j)
    public int countNeighbours(int i, int j) {
        int k = 0;
        int x = i + LENGTH_CONST, y = j + LENGTH_CONST;
        if (cells[(x - 1) % LENGTH_CONST][(y - 1) % LENGTH_CONST]) k++;
        if (cells[(x - 1) % LENGTH_CONST][(y) % LENGTH_CONST]) k++;
        if (cells[(x - 1) % LENGTH_CONST][(y + 1) % LENGTH_CONST]) k++;

        if (cells[(x) % LENGTH_CONST][(y - 1) % LENGTH_CONST]) k++;
        if (cells[(x) % LENGTH_CONST][(y + 1) % LENGTH_CONST]) k++;

        if (cells[(x + 1) % LENGTH_CONST][(y - 1) % LENGTH_CONST]) k++;
        if (cells[(x + 1) % LENGTH_CONST][(y) % LENGTH_CONST]) k++;
        if (cells[(x + 1) % LENGTH_CONST][(y + 1) % LENGTH_CONST]) k++;

        return k;
    }

    //one "age"
    public Board stepBoard() {
        boolean[][] tmp = new boolean[LENGTH_CONST][LENGTH_CONST];
        for (int i = 0; i < LENGTH_CONST; i++) {
            for (int j = 0; j < LENGTH_CONST; j++) {
                if (!cells[i][j]) {
                    if (countNeighbours(i, j) == 3) tmp[i][j] = true;
                } else {
                    if (countNeighbours(i, j) == 3 || countNeighbours(i, j) == 2) {
                        tmp[i][j] = true;
                    } else tmp[i][j] = false;
                }
            }
        }
        Board new_b = new Board();
        new_b.cells = tmp;
        return new_b;
        //cells = tmp;
    }

    //check if board has live cells
    public boolean isBoardAlive() {
        for (int i = 0; i < LENGTH_CONST; i++) {
            for (int j = 0; j < LENGTH_CONST; j++) {
                if (cells[i][j]) return true;
            }
        }
        return false;
    }

    //show on
    public void startGame() {
        ArrayList<Board> list = new ArrayList<>();
        int i = 0;
        Board b = new Board();
        while (b.isBoardAlive()) {
            System.out.println("----------" + i + "---------"); i++;
            b = b.stepBoard();
            b.printBoard();
            for (Board asd: list) {
                if(asd.equals(b)){
                    System.out.println("povtor");
                    return;
                }
            }
            list.add(b);
        }
        System.out.println("dead");
    }

    //equals method
    public boolean equals(Board b) {
        for (int i = 0; i < LENGTH_CONST; i++) {
            for (int j = 0; j < LENGTH_CONST; j++) {
                if (this.cells[i][j] != b.cells[i][j]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Board b = new Board();
        b.startGame();
    }
}
