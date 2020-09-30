package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public final static Scanner scanner = new Scanner(System.in);
    public final static Random random = new Random();

    // given a field state print the field
    private static void printField (char[][] state) {
        System.out.println("---------");
        for (char[] chars : state) {
            System.out.print("| ");
            for (int j = 0; j < state.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    // check if user coordinates are numbers
    private static int[] ifNumbers () {
        int[] xy = new int[2];
        while (true) {
            System.out.println("Enter the coordinates:");
            try {
                String xyString = scanner.nextLine();
                xy[0] = Integer.parseInt(xyString.split(" ")[0]);
                xy[1] = Integer.parseInt(xyString.split(" ")[1]);
                break;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
        return xy;
    }

    // check if user coordinates are within [1,3]
    private static int[] checkCoordinates () {
        int []xy = ifNumbers();
        while (true) {
            if (xy[0] <= 3 && xy[0] >= 1 && xy[1] <= 3 && xy[1] >= 1) {
                break;
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                xy = ifNumbers();
            }
        }
        return xy;
    }

    // check if the field is empty at the user coordinates, call this method if want an user input
    private static int[] checkEmpty (char[][] state) {
        int[]xy = checkCoordinates();
        int temp = xy[0];
        xy[0] = 3- xy[1];
        xy[1] = temp - 1;

        while (state[xy[0]][xy[1]] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            xy = checkCoordinates();
            temp = xy[0];
            xy[0] = 3 - xy[1];
            xy[1] = temp -1;
        }
        return xy;
    }

    // if everything is good, add this move to the field state char[][] and print it
    private static char[][] addToField (char[][] state, int[] xy) {
        char move = ' ';
        int numOfX = 0;
        int numOfY = 0;

        for (char[] chars : state) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'X') {
                    numOfX += 1;
                } else if (chars[i] == 'O') {
                    numOfY += 1;
                }
            }
        }

        if (numOfX > numOfY) {
            move = 'O';
        } else if (numOfX == numOfY) {
            move = 'X';
        } else {
            System.out.println("Wrong state");
            System.exit(1);
        }
        state[xy[0]][xy[1]] = move;
        printField(state);
        return state;
    }

    private static String gameResults (char[][] state) {
        String[] fieldResults = new String[8];
        fieldResults[0] = Character.toString(state[0][0]) + state[0][1] + state[0][2];
        fieldResults[1] = Character.toString(state[1][0]) + state[1][1] + state[1][2];
        fieldResults[2] = Character.toString(state[2][0]) + state[2][1] + state[2][2];
        fieldResults[3] = Character.toString(state[0][0]) + state[1][0] + state[2][0];
        fieldResults[4] = Character.toString(state[0][1]) + state[1][1] + state[2][1];
        fieldResults[5] = Character.toString(state[0][2]) + state[1][2] + state[2][2];
        fieldResults[6] = Character.toString(state[0][0]) + state[1][1] + state[2][2];
        fieldResults[7] = Character.toString(state[0][2]) + state[1][1] + state[2][0];
        int numOf3X = 0;
        int numOf3Y = 0;
        int emptyCells = 0;
        String result = null;

        for (String fieldResult : fieldResults) {
            if (fieldResult.equals("XXX")) {
                numOf3X += 1;
            } else if (fieldResult.equals("OOO")) {
                numOf3Y += 1;
            }
        }

        for (char[] chars : state) {
            for (int i = 0; i < chars.length; i++) {
                emptyCells = chars[i] == '_' ? emptyCells + 1 : emptyCells;
            }
        }

        if (numOf3X == 1) {
            result = "X wins";
        } else if (numOf3Y == 1) {
            result = "O wins";
        }

        if (numOf3X == 0 && numOf3Y == 0 && emptyCells == 0) {
            result = "Draw";
        } else if (numOf3X == 0 && numOf3Y == 0 && emptyCells != 0) {
            result = "Game not finished";
        }
        return result;
    }

    private static void easyAI () {
        char[][] state = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = '_';
            }
        }
        printField(state);
        while (true) {
            state = addToField(state, checkEmpty(state));
            if (!gameResults(state).equals("Game not finished")) {
                System.out.println(gameResults(state));
                System.exit(1);
            }
            System.out.println("Making move level \"easy\"");
            int[] easyMove;
            while (true) {
                easyMove = new int[]{random.nextInt(3), random.nextInt(3)};
                if (state[easyMove[0]][easyMove[1]] == '_') {
                    break;
                }
            }
            state = addToField(state,easyMove);
            if (!gameResults(state).equals("Game not finished")) {
                System.out.println(gameResults(state));
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        easyAI();
    }
}
