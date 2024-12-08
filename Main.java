import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{
    static Cell[][] board;
    
    public static void main(String[] args) {
        
        board = new Cell[8][8];
     
        for (int col = 0; col < 8; col++){
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

        printBoard();

        ArrayList<List> pMoves = generateMoves(6, 0);

        System.out.println(pMoves);
    }

    public static ArrayList<List> generateMoves(int row, int col){
        if (board[row][col] == null){
            return null;
        }

        Cell cell = board[row][col];

        if (cell.p1 instanceof Pawn){
            return generateMovesForPawn((Pawn) cell.p1);
        }        
        
        return null;
    }

    public static ArrayList<List> generateMovesForPawn(Pawn pawn){
        ArrayList<List> pawnMoves = new ArrayList<>();

        if (pawn.player == 'B'){
            int nextRow = pawn.row + 1, nextCol = pawn.col;

            if (isInBounds(nextRow, nextCol)) {
                pawnMoves.add(Arrays.asList(nextRow, nextCol));
            }

            if (!pawn.hasMadeFirstMove){
                int nextRow1 = pawn.row + 2, nextCol1 = pawn.col;

                if (isInBounds(nextRow1, nextCol1)) {
                    pawnMoves.add(Arrays.asList(nextRow1, nextCol1));
                }
            }
        }else{
            int nextRow = pawn.row - 1, nextCol = pawn.col;

            if (isInBounds(nextRow, nextCol)) {
                pawnMoves.add(Arrays.asList(nextRow, nextCol));
            }

            if (!pawn.hasMadeFirstMove){
                int nextRow1 = pawn.row - 2, nextCol1 = pawn.col;

                if (isInBounds(nextRow1, nextCol1)) {
                    pawnMoves.add(Arrays.asList(nextRow1, nextCol1));
                }
            }
        }


        return pawnMoves;
    }

    private static boolean isInBounds(int row, int col){
        return row < 8 && col < 8;
    }

    private static void printBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                Cell cell = board[i][j];

                if (cell == null){
                    System.out.print("-1");
                }else{
                    System.out.print(cell.p1.player);
                }
                
            }
            System.out.println();
        }
    }
}