

public class Main{

    public static void main(String[] args) {


        Cell[][] board = new Cell[8][8];
     
        for (int col = 0; col < 8; col++){
            Cell bCell = new Cell(new Pawn(0, col, 'B'));
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


        board[7][0] = new Cell(new Rook(0, 0, 'W'));
        board[7][1] = new Cell(new Knight(0, 1, 'W'));
        board[7][2] = new Cell(new Bishop(0, 2, 'W'));
        board[7][3] = new Cell(new Queen(0, 3, 'W'));
        board[7][4] = new Cell(new King(0, 4, 'W'));
        board[7][5] = new Cell(new Bishop(0, 5, 'W'));
        board[7][6] = new Cell(new Knight(0, 6, 'W'));
        board[7][7] = new Cell(new Rook(0, 7, 'W'));




        

           


        for (Cell[] board1 : board) {
                    for (int j = 0; j < board.length; j++) {
                        System.out.print(board1[j]);
                    }
                    System.out.println();
                }
            
        // printBoard(board[][]);
    }

    // private  void printBoard(new board[]){
        
        
    //     for (Cell[] board1 : board) {
    //         for (int j = 0; j < board.length; j++) {
    //             System.out.print(board1[j]);
    //         }
    //         System.out.println();
    //     }
    // }
}