import java.util.Scanner;


public class TT {
    private static final int BOARD_SIZE = 3;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final char EMPTY_CELL = ' ';


    private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];


    public static void main(String[] args) {
        System.out.println("Welcome to our Tic-Tac-Toe Game");


        System.out.println(" Symbol - X would represent Human player i.e. you");
        System.out.println(" Symbol - O would represent Ai Player");
        System.out.println("Enter a number from 1 to 9 to make a move.");
        initializeBoard();
        playGame();
    }


    private static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }


    private static void playGame() {
        char currentPlayer = PLAYER_X;
        boolean isGameOver = false;


        while (!isGameOver) {
            displayBoard();
            if (currentPlayer == PLAYER_X) {
                playerMove(currentPlayer);
            } else {
                computerMove(currentPlayer);
            }


            if (checkWin(currentPlayer)) {
                displayBoard();
                if(currentPlayer == PLAYER_X){
                    System.out.println("Good job human, you won!!! \uD83E\uDD73 \uD83E\uDD73");
                }
                if(currentPlayer == PLAYER_O){
                    System.out.println("Opps! Looks like the AI won :) Better luck next time");
                }


                isGameOver = true;
            } else if (isBoardFull()) {
                displayBoard();
                System.out.println("It's a draw!");
                isGameOver = true;
            }


            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
        }
    }


    private static void playerMove(char player) {
        Scanner scanner = new Scanner(System.in);
        int position;


        do {
            System.out.print("Player " + player + ", enter a position (1-9): ");
            position = scanner.nextInt();
        } while (!isValidMove(position));


        int row = (position - 1) / BOARD_SIZE;
        int col = (position - 1) % BOARD_SIZE;
        board[row][col] = player;
    }


    private static void computerMove(char player) {
        int[] bestMove = getBestMove();
        board[bestMove[0]][bestMove[1]] = player;
    }


    private static int[] getBestMove() {
        int[] bestMove = new int[]{-1, -1};
        int bestScore = Integer.MIN_VALUE;


        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    board[i][j] = PLAYER_O;
                    int score = minimax(board, 0, false);
                    board[i][j] = EMPTY_CELL;


                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }


        return bestMove;
    }


    private static int minimax(char[][] board, int depth, boolean isMax) {
        if (checkWin(PLAYER_X)) {
            return -10 + depth;
        } else if (checkWin(PLAYER_O)) {
            return 10 - depth;
        } else if (isBoardFull()) {
            return 0;
        }

        int val = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    if (isMax) {
                        board[i][j] = PLAYER_O;
                        val = Math.max(val, minimax(board, depth + 1, !isMax));
                        board[i][j] = EMPTY_CELL;
                    } else {
                        board[i][j] = PLAYER_X;
                        val = Math.min(val, minimax(board, depth + 1, !isMax));
                        board[i][j] = EMPTY_CELL;
                    }
                }
            }
        }
        return val;


        // if (isMaximizing) {
        //     int bestScore = Integer.MIN_VALUE;
        //     for (int i = 0; i < BOARD_SIZE; i++) {
        //         for (int j = 0; j < BOARD_SIZE; j++) {
        //             if (board[i][j] == EMPTY_CELL) {
        //                 board[i][j] = PLAYER_O;
        //                 int score = minimax(board, depth + 1, false);
        //                 board[i][j] = EMPTY_CELL;
        //                 bestScore = Math.max(bestScore, score);
        //             }
        //         }
        //     }
        //     return bestScore;
        // } else {
        //     int bestScore = Integer.MAX_VALUE;
        //     for (int i = 0; i < BOARD_SIZE; i++) {
        //         for (int j = 0; j < BOARD_SIZE; j++) {
        //             if (board[i][j] == EMPTY_CELL) {
        //                 board[i][j] = PLAYER_X;
        //                 int score = minimax(board, depth + 1, true);
        //                 board[i][j] = EMPTY_CELL;
        //                 bestScore = Math.min(bestScore, score);
        //             }
        //         }
        //     }
        //     return bestScore;
        // }

    }


    private static boolean isValidMove(int position) {
        int row = (position - 1) / BOARD_SIZE;
        int col = (position - 1) % BOARD_SIZE;
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }
        return board[row][col] == EMPTY_CELL;
    }


    private static boolean checkWin(char player) {
        // Check rows and columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player)
                    || (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player)
                || (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }


    private static boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }


    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
            System.out.println("-------------");
        }
        System.out.println();
    }


}
