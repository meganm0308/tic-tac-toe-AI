package tictactoe;

public interface Player {

     static char xOrO (char[][]state) {
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
     static char[][] addToField (char[][] state, int[] xy) {
        char move = xOrO(state);
        state[xy[0]][xy[1]] = move;
        Main.printField(state);
        return state;
    }
}
