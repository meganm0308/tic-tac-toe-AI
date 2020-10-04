package tictactoe;

import static tictactoe.Main.fieldSize;
import static tictactoe.Main.scanner;

public class User implements Player{
    protected int[] move;
    protected char[][] state;

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
    static int[] checkEmpty (char[][] state) {
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


    public User(char[][] state) {
        this.state = state;
    }

    public void setMove() {
        move = checkEmpty(state);
    }

    public char[][] getState() {
        return state;
    }

    public void setState(char[][] state) {
        this.state = Player.addToField(state, move);
    }

    public char[][] plays () {
        setMove();
        setState(state);
        state = getState();
        return state;
    }
}

