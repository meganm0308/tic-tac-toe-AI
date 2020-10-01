package tictactoe;

public class MediumAI extends EasyAI {
    public MediumAI(char[][] state) {
        super(state);
    }

    @Override
    public void setMove() {
        move = new int[2];
        char makeMove = Main.xOrO(state);
        String[] fieldResults = Main.fieldPossibilities(state);
        int winInOneMove = -1;
        int blockOpponent = -1;
        for (int i = 0; i < fieldResults.length; i++) {
            if (makeMove == 'X' && fieldResults[i].matches("X*_X*")) {
                winInOneMove = i;
            } else if (makeMove == 'O' && fieldResults[i].matches("O*_O*")) {
                winInOneMove = i;
            } else if (makeMove == 'X' && fieldResults[i].matches("O*_O*")) {
                blockOpponent = i;
            } else if (makeMove == 'O' && fieldResults[i].matches("X*_X*")) {
                blockOpponent = i;
            }
        }

        if (winInOneMove == -1 && blockOpponent == -1) {
            while (true) {
                move = new int[]{Main.random.nextInt(Main.fieldSize), Main.random.nextInt(Main.fieldSize)};
                if (state[move[0]][move[1]] == '_') {
                    break;
                }
            }
        } else if (winInOneMove != -1) {
            if (winInOneMove < state.length) {
                move[0] = winInOneMove;
                move[1] = fieldResults[winInOneMove].indexOf("_");
            } else if (winInOneMove < 2 * state.length) {
                move[1] = winInOneMove - state.length;
                move[0] = fieldResults[winInOneMove].indexOf("_");
            } else if (winInOneMove == 2 * state.length){
                move[0] = fieldResults[winInOneMove].indexOf("_");
                move[1] = fieldResults[winInOneMove].indexOf("_");
            } else {
                move[0] = fieldResults[winInOneMove].indexOf("_");
                move[1] = state.length - 1 - fieldResults[winInOneMove].indexOf("_");
            }
        } else if (blockOpponent != -1) {
            if (blockOpponent < state.length) {
                move[0] = blockOpponent;
                move[1] = fieldResults[blockOpponent].indexOf("_");
            } else if (blockOpponent < 2 * state.length) {
                move[1] = blockOpponent - state.length;
                move[0] = fieldResults[blockOpponent].indexOf("_");
            } else if (blockOpponent == 2 * state.length){
                move[0] = fieldResults[blockOpponent].indexOf("_");
                move[1] = fieldResults[blockOpponent].indexOf("_");
            } else {
                move[0] = fieldResults[blockOpponent].indexOf("_");
                move[1] = state.length - 1 - fieldResults[blockOpponent].indexOf("_");
            }
        }
    }

}

