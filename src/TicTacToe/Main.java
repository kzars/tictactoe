package TicTacToe;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        DB db = new DB();
        Scanner scanner = new Scanner(System.in);
        char again = 'n';

        do {
            System.out.println("Do you want to play or see results? p/r");
            char option = scanner.next().charAt(0);
            scanner.nextLine();
            if(option == 'p') {
                System.out.println("Please eneter player 1 name");
                String firstPlayer = scanner.nextLine();
                System.out.println("Please enter player 2 name ");
                String secondPlayer = scanner.nextLine();

                Scanner scan = new Scanner(System.in);
                //TTT game = new TTT();
                TICTACTOE game = new TICTACTOE();
                game.initializeBoard();
                System.out.println("Tic-Tac-Toe!");
                do
                {
                    System.out.println("Current board layout:");
                    game.printBoard();
                    int row;
                    int col;
                    do
                    {
                        System.out.println("Player " + game.getCurrentPlayerMark() + ", enter an empty row and column to place your mark!");
                        row = scan.nextInt()-1;
                        col = scan.nextInt()-1;
                    }
                    while (!game.placeMark(row, col));
                    game.changePlayer();
                }
                while(!game.checkForWin() && !game.isBoardFull());
                if (game.isBoardFull() && !game.checkForWin())
                {
                    try {
                        db.insertResult(firstPlayer, secondPlayer, "TIE");
                    } catch (SQLException e){

                    }

                    System.out.println("The game was a tie!");
                }
                else
                {
                    System.out.println("Current board layout:");
                    game.printBoard();
                    game.changePlayer();
                    System.out.println(Character.toUpperCase(game.getCurrentPlayerMark()) + " Wins!");
                    if (game.getCurrentPlayerMark() == 'x'){
                        try{
                            db.insertResult(firstPlayer,secondPlayer,firstPlayer);
                        } catch (SQLException e){

                        }

                    } else {
                        try{
                            db.insertResult(firstPlayer,secondPlayer,secondPlayer);
                        } catch (SQLException e) {
                        }

                    }
                }



            }  else if (option == 'r') {

                db.readResults();
            } else {
                System.out.println("Not valid input");
            }

            System.out.println("Do you want to do something more? y/n");
            again = scanner.next().charAt(0);

        } while (again == 'y');

    }


    //validate child or parent
/*    public static boolean isChild (String input){
        boolean true;
    }*/

    //validate date

    //
}
