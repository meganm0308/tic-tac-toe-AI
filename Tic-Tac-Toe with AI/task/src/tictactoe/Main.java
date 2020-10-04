package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    final static int fieldSize = 3;

    // given a field state print the field
    static void printField(char[][] state) {
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

    // all rows and columns and diagonals
     static String[] fieldPossibilities (char[][] state) {
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
     static String gameResults (char[][] state) {
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
                "start user hard",
                "start hard user",
                "start hard hard",
                "start medium hard",
                "start hard medium",
                "start easy hard",
                "start hard easy",
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
                printField(state);

                while (true) {
                    User user1 = new User(state);
                    state = user1.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }

                    HardAI ai1 = new HardAI(state);
                    System.out.println("Making move level \"hard\"");
                    state = ai1.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 10:
                printField(state);

                while (true) {
                    HardAI ai1 = new HardAI(state);
                    System.out.println("Making move level \"hard\"");
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
            case 11:
                printField(state);

                while (true) {
                    HardAI ai1 = new HardAI(state);
                    System.out.println("Making move level \"hard\"");
                    state = ai1.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }

                    HardAI ai2 = new HardAI(state);
                    System.out.println("Making move level \"hard\"");
                    state = ai2.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 12:
                printField(state);

                while (true) {
                    System.out.println("Making move level \"medium\"");
                    MediumAI mediumAI1 = new MediumAI(state);
                    state = mediumAI1.plays();
                    if (!gameResults(state).equals("Game not finished")){
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                    HardAI ai1 = new HardAI(state);
                    System.out.println("Making move level \"hard\"");
                    state = ai1.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 13:
                printField(state);

                while (true) {
                    HardAI ai1 = new HardAI(state);
                    System.out.println("Making move level \"hard\"");
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
            case 14:
                printField(state);
                while (true) {
                    EasyAI ai1 = new EasyAI(state);
                    System.out.println("Making move level \"easy\"");
                    state = ai1.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }

                    HardAI ai2 = new HardAI(state);
                    System.out.println("Making move level \"hard\"");
                    state = ai2.plays();
                    if (!gameResults(state).equals("Game not finished")) {
                        System.out.println(gameResults(state));
                        System.exit(1);
                    }
                }
            case 15:
                printField(state);
                while (true) {
                    HardAI ai2 = new HardAI(state);
                    System.out.println("Making move level \"hard\"");
                    state = ai2.plays();
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
                case 16:
                System.exit(1);
        }
    }
}
