package PracticeChess;

public class main{
    public static void main(String[] args) {
        int[][] board = new int[8][8];


int k =1;
        for (int i = 0; i < board.length; i++) {
            for(int j = 0;j<board[0].length;j++){
                if(k < 10){
                    System.out.print("0"+ k+" ");
                    k++;
                }  else{
                System.out.print(k + " ");
                k++;
                }
            }
            System.out.println();
        }
    }
}


