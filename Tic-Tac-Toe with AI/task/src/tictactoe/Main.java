package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public final static Scanner scanner = new Scanner(System.in);
    public final static Random random = new Random();
    public final static int fieldSize = 3;

    // given a field state print the field
    private static void printField (char[][] state) {
        for (int i = 0; i < 4 + 2 * fieldSize - 1; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
        for (char[] chars : state) {
            System.out.print("| ");
            for (int j = 0; j < state.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.print("|\n");
        }
        for (int i = 0; i < 4 + 2 * fieldSize - 1; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
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

    // check if user coordinates are within [1,fieldSize]
    private static int[] checkCoordinates () {
        int []xy = ifNumbers();
        while (true) {
            if (xy[0] <= fieldSize && xy[0] >= 1 && xy[1] <= fieldSize && xy[1] >= 1) {
                break;
            } else {
                System.out.printf("Coordinates should be from 1 to %d!", fieldSize);
                xy = ifNumbers();
            }
        }
        return xy;
    }

    // check if the field is empty at the user coordinates, call this method if want an user input
    public static int[] checkEmpty (char[][] state) {
        int[]xy = checkCoordinates();
        int temp = xy[0];
        xy[0] = fieldSize - xy[1];
        xy[1] = temp - 1;

        while (state[xy[0]][xy[1]] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            xy = checkCoordinates();
            temp = xy[0];
            xy[0] = fieldSize - xy[1];
            xy[1] = temp -1;
        }
        return xy;
    }

    public static char xOrO (char[][]state) {
        char move;
        int numOfX = 0;
        int numOfO = 0;

        for (char[] chars : state) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'X') {
                    numOfX += 1;
                } else if (chars[i] == 'O') {
                    numOfO += 1;
                }
            }
        }

        if (numOfX > numOfO) {
            move = 'O';
        } else {
            move = 'X';
        }
        return move;
    }

    // if everything is good, add this move to the field state char[][] and print it
    public static char[][] addToField (char[][] state, int[] xy) {
        char move = xOrO(state);
        state[xy[0]][xy[1]] = move;
        printField(state);
        return state;
    }

    // all rows and columns and diagonals
    protected static String[] fieldPossibilities (char[][] state) {
        String[] fieldResults = new String[fieldSize * 2 + 2];
        Arrays.fill(fieldResults, "");

        int counter = 0;
        for (int i = 0; i < state.length; i++) { // each row
            for (int j = 0; j < state.length; j++) { // each column
                fieldResults[counter] += Character.toString(state[i][j]);
            }
            counter += 1;
        }

        for (int i = 0; i < state.length; i++) { // each row
            for (int j = 0; j < state.length; j++) { // each column
                fieldResults[counter] += Character.toString(state[j][i]);
            }
            counter += 1;
        }

        for (int i = 0; i < state.length; i++) {
            fieldResults[counter] += Character.toString(state[i][i]);
        }

        counter += 1;

        for (int i = 0; i < state.length; i++) {
            fieldResults[counter] += Character.toString(state[i][state.length - 1 - i]);

        }
        return fieldResults;
    }

    // who wins?
    private static String gameResults (char[][] state) {
        String[] fieldResults = fieldPossibilities(state);
        int emptyCells = 0;
        String result = null;
        int xInRow = 0;
        int yInRow = 0;

        for (String fieldResult : fieldResults) {
            for (int i = 0; i < fieldResult.length(); i++) {
                if (fieldResult.charAt(i) == 'X') {
                    xInRow += 1;
                }
                if (fieldResult.charAt(i) == 'O') {
                    yInRow += 1;
                }
            }
            if (xInRow == fieldSize) {
                result = "X wins";
                break;
            } else if (yInRow == fieldSize) {
                result = "O wins";
                break;
            } else {
                xInRow = 0;
                yInRow = 0;
            }
        }

        for (char[] chars : state) {
            for (int i = 0; i < chars.length; i++) {
                emptyCells = chars[i] == '_' ? emptyCells + 1 : emptyCells;
            }
        }

        if (xInRow != fieldSize && yInRow != fieldSize && emptyCells == 0) {
            result = "Draw";
        } else if (xInRow != fieldSize && yInRow != fieldSize && emptyCells != 0){
            result = "Game not finished";
        }
        return result;
    }

    private static int menu () {
        String[] menuOptions = {"start easy easy",
                "start user easy",
                "start easy user",
                "start user user",
                "start user medium",
                "start medium user",
                "start medium medium",
                "start easy medium",
                "start medium easy",
                "exit"};
        int optionIndex = menuOptions.length;

        // get command
        while (true) {
            System.out.print("Input command: ");
            String userInput = scanner.nextLine();
            for (int i = 0; i < menuOptions.length; i++) {
                if (userInput.equals(menuOptions[i])) {
                    optionIndex = i;
                }
            }
            if (optionIndex == menuOptions.length) {
                System.out.println("Bad parameters!");
            } else break;
        }
        return optionIndex;
    }

    public static void main(String[] args) {
        char[][] state = new char[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                state[i][j] = '_';
            }
        }

        switch (menu()) {
            case 0:
                printField(state);
                while (true) {
                    EasyAI ai1 = new EasyAI(state);
                    System.out.println("Making move level \"easy\"");
                    state = ai1.plays();
                    if (!gameResults(state).equals("Game not finished")){
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }

                    EasyAI ai2 = new EasyAI(state);
                    System.out.println("Making move level \"easy\"");
                    state = ai2.plays();
                    if (!gameResults(state).equals("Game not finished")){
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 1:
                printField(state);
                while (true) {
                    User user1 = new User(state);
                    state = user1.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }

                    EasyAI ai1 = new EasyAI(state);
                    System.out.println("Making move level \"easy\"");
                    state = ai1.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 2:
                printField(state);
                while (true) {
                    EasyAI ai1 = new EasyAI(state);
                    System.out.println("Making move level \"easy\"");
                    state = ai1.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                    User user1 = new User(state);
                    state = user1.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 3:
                printField(state);
                while (true) {
                    User user1 = new User(state);
                    state = user1.plays();
                    if (!gameResults(state).equals("Game not finished")){
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }

                    User user2 = new User(state);
                    state = user2.plays();
                    if (!gameResults(state).equals("Game not finished")){
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 4:
                printField(state);
                while (true) {
                    User user1 = new User(state);
                    state = user1.plays();
                    if (!gameResults(state).equals("Game not finished")){
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }

                    System.out.println("Making move level \"medium\"");
                    MediumAI mediumAI1 = new MediumAI(state);
                    state = mediumAI1.plays();
                    if (!gameResults(state).equals("Game not finished")){
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 5:
                printField(state);
                while (true) {
                    System.out.println("Making move level \"medium\"");
                    MediumAI mediumAI1 = new MediumAI(state);
                    state = mediumAI1.plays();
                    if (!gameResults(state).equals("Game not finished")){
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }

                    User user1 = new User(state);
                    state = user1.plays();
                    if (!gameResults(state).equals("Game not finished")){
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 6:
                printField(state);
                while (true) {
                    System.out.println("Making move level \"medium\"");
                    MediumAI mediumAI1 = new MediumAI(state);
                    state = mediumAI1.plays();
                    if (!gameResults(state).equals("Game not finished")){
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }

                    System.out.println("Making move level \"medium\"");
                    MediumAI mediumAI2 = new MediumAI(state);
                    state = mediumAI2.plays();
                    if (!gameResults(state).equals("Game not finished")){
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 7:
                printField(state);
                while (true) {
                    EasyAI ai1 = new EasyAI(state);
                    System.out.println("Making move level \"easy\"");
                    state = ai1.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }

                    System.out.println("Making move level \"medium\"");
                    MediumAI mediumAI1 = new MediumAI(state);
                    state = mediumAI1.plays();
                    if (!gameResults(state).equals("Game not finished")){
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 8:
                printField(state);
                while (true) {
                    System.out.println("Making move level \"medium\"");
                    MediumAI mediumAI1 = new MediumAI(state);
                    state = mediumAI1.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }

                    EasyAI ai1 = new EasyAI(state);
                    System.out.println("Making move level \"easy\"");
                    state = ai1.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 9:
                System.exit(1);
        }
    }
}