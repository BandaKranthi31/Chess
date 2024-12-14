package com.clichess.chess.pieces;

public class Piece {
   public int row, col;
    
   public char player;

    public Piece(int row, int col, char player){
        this.row = row;
        this.col = col;
        this.player = player;
    }
}
