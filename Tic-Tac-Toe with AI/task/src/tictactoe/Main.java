package tictactoe;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

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

    // check if the field is empty at the user coordinates
    private static int[] checkEmpty (char[][] state) {
        int[]xy = checkCoordinates();
        int temp = xy[0];
        int x = 3- xy[1];
        int y = temp - 1;

        while (true) {
            if (state[x][y] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                xy = checkCoordinates();
            } else break;
        }
        xy = new int[]{x, y};
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

    private static void gameResults (char[][] state) {
        for (char[] chars : state) {
            for (int i = 0; i < chars.length; i++) {

            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Enter cells: ");
        String initialState = scanner.nextLine();

        int count = 0;
        char[][] state = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = initialState.charAt(count);
                count++;
            }
        }

        printField(state);
        gameResults(addToField(state, checkEmpty(state)));
    }
}
