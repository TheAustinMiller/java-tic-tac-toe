import java.util.*;
import java.io.*;

public class Main{
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        boolean player = true;
        String playernum = "ONE";
        boolean gameOver = false;
        int cnt = 0;
        // FILL BLANK BOARD
        String[] board = new String[9];
        for (int lcv = 0; lcv < board.length; lcv++){
            board[lcv] = " ";
        }

        System.out.println("WELCOME TO THE TIC TAC TOE EPIC GAME");
        System.out.println("____________________________________");
        System.out.println("type 'begin' to start");


        //BEGIN GAME
        String aaa = input.nextLine();
        while (!(aaa.equals("begin"))){
            aaa = input.nextLine();
        }
        printBoard(board);
        String winner = "";
        //GAME
        while ((cnt < 9) && (gameOver == false)){
            System.out.println();
            System.out.println();
            System.out.println("PLAYER " + playernum + " - What move would you like to make? (Enter a number 1-9)");
            System.out.println("___________________________________________________________________");
            int a = input.nextInt(); //player enters pos
            placePiece(a, player, board); // it is placed in spot based on player number
            System.out.println();
            printBoard(board); // update the board
            if (checkWin(board) == true)
            {
                gameOver = true;
                if (player == true){
                    winner = "PLAYER ONE";
                }
                if (player == false){
                    winner = "PLAYER TWO";
                }
            }
            player = !player;
            if (player == true)
            {
                playernum = "ONE";
            }
            if (player == false){
                playernum = "TWO";
            }
            cnt++;
        }
        if (cnt == 9){
            winner = "THE CAT";
        }
        System.out.println();

        //GAME OVER
        System.out.println("GAME OVER");
        System.out.println("_________");
        System.out.println(winner + " WINS!");
    }

    public static boolean checkWin(String[] b){
        if ((b[0] == "X") && (b[1] == "X") && (b[2] == "X")){
            return true;
        }
        if ((b[3] == "X") && (b[4] == "X") && (b[5] == "X")){
            return true;
        }
        if ((b[6] == "X") && (b[7] == "X") && (b[8] == "X")){
            return true;
        }
        if ((b[0] == "X") && (b[3] == "X") && (b[6] == "X")){
            return true;
        }
        if ((b[1] == "X") && (b[4] == "X") && (b[7] == "X")){
            return true;
        }
        if ((b[2] == "X") && (b[5] == "X") && (b[8] == "X")){
            return true;
        }
        if ((b[0] == "X") && (b[4] == "X") && (b[8] == "X")){
            return true;
        }
        if ((b[2] == "X") && (b[4] == "X") && (b[6] == "X")){
            return true;
        }

        if ((b[0] == "O") && (b[1] == "O") && (b[2] == "O")){
            return true;
        }
        if ((b[3] == "O") && (b[4] == "O") && (b[5] == "O")){
            return true;
        }
        if ((b[6] == "O") && (b[7] == "O") && (b[8] == "O")){
            return true;
        }
        if ((b[0] == "O") && (b[3] == "O") && (b[6] == "O")){
            return true;
        }
        if ((b[1] == "O") && (b[4] == "O") && (b[7] == "O")){
            return true;
        }
        if ((b[2] == "O") && (b[5] == "O") && (b[8] == "O")){
            return true;
        }
        if ((b[0] == "O") && (b[4] == "O") && (b[8] == "O")){
            return true;
        }
        if ((b[2] == "O") && (b[4] == "O") && (b[6] == "O")){
            return true;
        }

        return false;
    }

    public static void printBoard(String[] b){
        System.out.println("          " +b[0] + " | " + b[1] + " | " + b[2] + "          " + "1" + " | " + "2" + " | " + "3");
        System.out.println("          —" + " +" + " —" + " +" + " —" + "          —" + " +" + " —" + " +" + " —");
        System.out.println("          " +b[3] + " | " + b[4] + " | " + b[5] + "          " +"4" + " | " + "5" + " | " + "6");
        System.out.println("          —" + " +" + " —" + " +" + " —" + "          —" + " +" + " —" + " +" + " —");
        System.out.println("          " +b[6] + " | " + b[7] + " | " + b[8] + "          " +"7" + " | " + "8" + " | " + "9");
    }

    public static void placePiece(int A, boolean p, String[] b){
        if(A == 1){
            if (p == true){
                b[0] = "X";
            }
            if (p == false){
                b[0] = "O";
            }
        }
        if(A == 2){
            if (p == true){
                b[1] = "X";
            }
            if (p == false){
                b[1] = "O";
            }
        }
        if(A == 3){
            if (p == true){
                b[2] = "X";
            }
            if (p == false){
                b[2] = "O";
            }
        }
        if(A == 4){
            if (p == true){
                b[3] = "X";
            }
            if (p == false){
                b[3] = "O";
            }
        }
        if(A == 5){
            if (p == true){
                b[4] = "X";
            }
            if (p == false){
                b[4] = "O";
            }
        }
        if(A == 6){
            if (p == true){
                b[5] = "X";
            }
            if (p == false){
                b[5] = "O";
            }
        }
        if(A == 7){
            if (p == true){
                b[6] = "X";
            }
            if (p == false){
                b[6] = "O";
            }
        }
        if(A == 8){
            if (p == true){
                b[7] = "X";
            }
            if (p == false){
                b[7] = "O";
            }
        }
        if(A == 9){
            if (p == true){
                b[8] = "X";
            }
            if (p == false){
                b[8] = "O";
            }
        }
    }

    public static void changePlayer(boolean p){
        p = !p;
    }
}
