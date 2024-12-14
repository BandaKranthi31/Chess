package com.clichess.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main{
    static Cell[][] board;
    
    public static void main(String[] args) {
        
        board = new Cell[8][8];

        Pawn pawn = new Pawn(0, 0, 'W');
    
     
        for (int col = 0; col < 8; col++){
            // if (col == 0) {
            //     continue;
            // }

            Cell bCell = new Cell(new Pawn(1, col, 'B'));
            board[1][col] = bCell;

            Cell wCell = new Cell(new Pawn(6, col, 'W'));
            board[6][col] = wCell;
        }
        
        board[0][0] = new Cell(new Rook(0, 0, 'B'));
        board[0][1] = new Cell(new Knight(0, 1, 'B'));
        board[0][2] = new Cell(new Bishop(0, 2, 'B'));
        board[0][3] = new Cell(new Queen(0, 3, 'B'));
        board[0][4] = new Cell(new King(0, 4, 'B'));
        board[0][5] = new Cell(new Bishop(0, 5, 'B'));
        board[0][6] = new Cell(new Knight(0, 6, 'B'));
        board[0][7] = new Cell(new Rook(0, 7, 'B'));


        board[7][0] = new Cell(new Rook(7, 0, 'W'));
        board[7][1] = new Cell(new Knight(7, 1, 'W'));
        board[7][2] = new Cell(new Bishop(7, 2, 'W'));
        board[7][3] = new Cell(new Queen(7, 3, 'W'));
        board[7][4] = new Cell(new King(7, 4, 'W'));
        board[7][5] = new Cell(new Bishop(7, 5, 'W'));
        board[7][6] = new Cell(new Knight(7, 6, 'W'));
        board[7][7] = new Cell(new Rook(7, 7, 'W'));

        // board[4][2] = new Cell(new Pawn(4, 2, 'B'));
        // board[4][1] = new Cell(new Pawn(4, 3, 'W'));

        // board[3][0] = new Cell(new Rook(3, 0, 'B'));
        // 
        board[3][4] = new Cell(new Knight(3, 4, 'B'));
    

        printBoard();

        ArrayList<List> pMoves = generateMoves(3, 4);

        System.out.println(pMoves);
        
        // boolean mac = isValid(3, 0);
        // System.out.println(mac);
    }

    public static ArrayList<List> generateMoves(int row, int col){
        if (board[row][col] == null){
            return null;
        }

        Cell cell = board[row][col];

        if (cell.p1 instanceof Pawn){
            return generateMovesForPawn( row, col);
        }        

        if (cell.p1 instanceof Rook){
            return generateMovesForRook( row,col);
        }

        if (cell.p1 instanceof Bishop){
            return generateMovesForBishop(row, col);
        }

        if(cell.p1 instanceof King){
            return generateMovesForKing(row, col);
        }

        if(cell.p1 instanceof Queen){
            return  generateMovesForQueen(row, col);
        }
        if(cell.p1 instanceof Knight){
            return generateMovesForKnight(row, col);
        }
        
        return null;
    }


    public static ArrayList<List> generateMovesForPawn( int row , int col){
        Pawn pawn = new Pawn(row, col, board[row][col].p1.player);
        ArrayList<List> pawnMoves = new ArrayList<>();

        if (pawn.player == 'B'){
            int nextRow = pawn.row + 1, nextCol = pawn.col;

            if (isInBounds(nextRow, nextCol) && isValid(nextRow, nextCol)) {
                pawnMoves.add(Arrays.asList(nextRow, nextCol));
            }

            if (!pawn.hasMadeFirstMove){
                int nextRow1 = pawn.row + 2, nextCol1 = pawn.col;

                if (isInBounds(nextRow1, nextCol1) && isValid(nextRow1, nextCol1)) {
                    pawnMoves.add(Arrays.asList(nextRow1, nextCol1));
                }
            }  
            int drow = pawn.row+1;
            int drcol = pawn.col+1;
            int dlcol = pawn.col-1;

            if(toCapture(drow, dlcol, pawn.player)){
              pawnMoves.add(Arrays.asList(drow,dlcol));
            }

            if(toCapture(drow, drcol, pawn.player)){
             pawnMoves.add(Arrays.asList(drow,drcol));
            }


        }
        else{
            int nextRow = pawn.row - 1, nextCol = pawn.col;

            if (isInBounds(nextRow, nextCol) && isValid(nextRow, nextCol)) {
                pawnMoves.add(Arrays.asList(nextRow, nextCol));
            }

            if (!pawn.hasMadeFirstMove){
                int nextRow1 = pawn.row - 2, nextCol1 = pawn.col;

                if (isInBounds(nextRow1, nextCol1) && isValid(nextRow1, nextCol1)) {
                    pawnMoves.add(Arrays.asList(nextRow1, nextCol1));
                }
            }

            int drow = pawn.row-1;
            int drcol = pawn.col-1;
            int dlcol = pawn.col+1;
    
            if(toCapture(drow, dlcol, pawn.player)){
              pawnMoves.add(Arrays.asList(drow,dlcol));
            }
    
            if(toCapture(drow, drcol, pawn.player)){
             pawnMoves.add(Arrays.asList(drow,drcol));
            }
        }

        return pawnMoves;
    }
    
    public static ArrayList<List> generateMovesForRook(int row , int col){
        Rook rook = new Rook(row, col,board[row][col].p1.player);
        int[][] directions = {{1,0}, {-1,0}, {0,-1}, {0,1}};
        
        ArrayList<List> validMoves = new ArrayList<>();

        for (int i = 0; i < directions.length; i++){
            int r = directions[i][0], c = directions[i][1];

            int curRow = rook.row + r, curCol = rook.col + c;

            while (isInBounds(curRow, curCol)) {
                if (!isValid(curRow, curCol)) {
                    if (board[curRow][curCol].p1.player != rook.player){
                        validMoves.add(Arrays.asList(curRow, curCol));
                    }
                    break;
                }
                validMoves.add(Arrays.asList(curRow, curCol));
                curRow += r;
                curCol += c;
            }

        }
        // for (int row = rook.row + 1; row < 8; row++){
        //     if (!isValid(row, rook.col)) {
        //         if (board[row][rook.col].p1.player != rook.player){
        //             validMoves.add(Arrays.asList(row, rook.col));
        //         }

        //         break;
        //     }
        //     validMoves.add(Arrays.asList(row, rook.col));
        // }

        // for (int row = rook.row + 1; row < 8; row++){
        //     if (!isValid(row, rook.col)) {
        //         if (board[row][rook.col].p1.player != rook.player){
        //             validMoves.add(Arrays.asList(row, rook.col));
        //         }

        //         break;
        //     }
        //     validMoves.add(Arrays.asList(row, rook.col));
        // }

        // for (int row = rook.row + 1; row < 8; row++){
        //     if (!isValid(row, rook.col)) {
        //         if (board[row][rook.col].p1.player != rook.player){
        //             validMoves.add(Arrays.asList(row, rook.col));
        //         }

        //         break;
        //     }
        //     validMoves.add(Arrays.asList(row, rook.col));
        // }

        // for (int row = rook.row + 1; row < 8; row++){
        //     if (!isValid(row, rook.col)) {
        //         if (board[row][rook.col].p1.player != rook.player){
        //             validMoves.add(Arrays.asList(row, rook.col));
        //         }

        //         break;
        //     }
        //     validMoves.add(Arrays.asList(row, rook.col));
        // }
       
        return validMoves;
    }

    public static ArrayList<List> generateMovesForBishop(int row, int col){
        Bishop bishop = new Bishop(row, col, board[row][col].p1.player);
        int[][] directions = {{1,1}, {1,-1}, {-1,-1}, {-1,1}};
        
        ArrayList<List> validMoves = new ArrayList<>();

        for (int i = 0; i < directions.length; i++){
            int r = directions[i][0], c = directions[i][1];

            int curRow = bishop.row + r, curCol = bishop.col + c;

            while (isInBounds(curRow, curCol)) {
                if (!isValid(curRow, curCol)) {
                    if (board[curRow][curCol].p1.player != bishop.player){
                        validMoves.add(Arrays.asList(curRow, curCol));
                    }
                    break;
                }
                validMoves.add(Arrays.asList(curRow, curCol));
                curRow += r;
                curCol += c;
            }

        }
        return validMoves;
    }

    public static ArrayList<List> generateMovesForKing(int row, int col){
        King king = new King(row, col, board[row][col].p1.player);
        ArrayList<List> validMoves = new ArrayList<>();
        int[][] directions = {{1,1},{1,-1},{-1,-1},{-1,1},{0,1},{1,0},{-1,0},{0,-1}};

        for(int i =0;i<directions.length;i++){
            int r = directions[i][0], c = directions[i][1];
            int curRow = king.row + r, curCol = king.col+c;
            if(isInBounds(curRow, curCol)){
                if( null == board[curRow][curCol]  || board[curRow][curCol].p1.player != king.player ){
                   validMoves.add(Arrays.asList(curRow,curCol));
                }
            
            }
            
        }

        return validMoves;
    }

    private static  ArrayList<List> generateMovesForQueen(int row, int col){
    // HashSet<List> validMoves = new HashSet<>();
    ArrayList<List> validMoves = new ArrayList<>();

    
        ArrayList<List> bishopMoves = generateMovesForBishop(row,col);
        ArrayList<List> rookMoves = generateMovesForRook(row, col);


        int k = bishopMoves.size();
        int s =rookMoves.size();
        System.out.println(k+s);

        validMoves.add(rookMoves);
        validMoves.add(bishopMoves);
        System.out.println(validMoves.size());

        return validMoves;
    }

    private  static ArrayList<List> generateMovesForKnight(int row, int col){
        Knight knight = new Knight(row, col, board[row][col].p1.player);
       ArrayList<List> validMoves = new ArrayList<>();
       //TOP
       if(isInBounds(row-2, col+1) &&( null == board[row-2][col+1]  || board[row-2][col+1].p1.player != knight.player)  ){
        validMoves.add(Arrays.asList(row-2,col+1));
       }
       if(isInBounds(row-2, col-1) &&(  null == board[row-2][col-1] ||board[row-2][col-1].p1.player != knight.player ) ){
        validMoves.add(Arrays.asList(row-2,col-1));
       }

       //BOTTOM
       if(isInBounds(row+2, col-1)  && ( null ==  board[row+2][col-1]  ||board[row+2][col-1].p1.player != knight.player  )  ){
        validMoves.add(Arrays.asList(row+2,col-1));
       }
       if(isInBounds(row+2, col+1)  && (  null == board[row+2][col+1] || board[row+2][col+1].p1.player != knight.player) ){
        validMoves.add(Arrays.asList(row+2,col+1));
       }

       //RIGHT
       if(isInBounds(row+1, col-2) &&(  null == board[row+1][col-2] ||  board[row+1][col-2].p1.player != knight.player ) ){
        validMoves.add(Arrays.asList(row+1,col-2));
       }
       if(isInBounds(row-1, col+2) &&(  null == board[row-1][col+2] ||  board[row-1][col+2].p1.player != knight.player  ) ){
        validMoves.add(Arrays.asList(row-1,col+2));
       }

       //LEFT
       if(isInBounds(row+1, col-2)  &&(  null == board[row+1][col-2] || board[row+1][col-2].p1.player != knight.player  ) ){
        validMoves.add(Arrays.asList(row-1,col-2));
       }
       if(isInBounds(row-1, col-2)  && (   null == board[row-1][col-2] || board[row-1][col-2].p1.player != knight.player )){
        validMoves.add(Arrays.asList(row-1,col+2));
       }


       return validMoves;
    }

    private static boolean isInBounds(int row, int col){
        return row >= 0 && row < 8 && col >=0 && col < 8;
    }

    private static boolean toCapture(int row, int col, char player){
        if(isInBounds(row, col) && (board[row][col]!= null) && board[row][col].p1.player != player){
             return  true;
        }
        return  false;
    }

    private  static boolean isValid(int row,int col){
        if(board[row][col]== null){
            return true;
        }
        return false;
    }

    private static void printBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                Cell cell = board[i][j];

                if (cell == null){
                    System.out.print("- ");
                }else{
                    System.out.print(cell.p1.player + " ");
                }
                
            }
            System.out.println();
        }
    }
}