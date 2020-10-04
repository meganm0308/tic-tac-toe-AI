package tictactoe;

public class HardAI extends MediumAI{

    public HardAI(char[][] state) {
        super(state);
    }

    protected int minimax (char[][] state, char player) {
        if (!Main.gameResults(state).equals("Game not finished")) {
            if (Main.gameResults(state).equals("X wins"))
                return 1;
            else if (Main.gameResults(state).equals("O wins"))
                return -1;
            else return 0;
        }

        int bestScore;
        if (player == 'X') {
            bestScore = -10;
            for (int i = 0; i < state.length; i++) {
                for (int j = 0; j < state.length; j++) {
                    if (state[i][j] == '_') {
                        state[i][j] = 'X';
                        bestScore = Math.max(bestScore, minimax(state, 'O'));
                        state[i][j] = '_';
                     }
                }
            }
        } else {
            bestScore = 10;
            for (int i = 0; i < state.length; i++) {
                for (int j = 0; j < state.length; j++) {
                    if (state[i][j] == '_') {
                        state[i][j] = 'O';
                        bestScore = Math.min(bestScore, minimax(state, 'X'));
                        state[i][j] = '_';
                    }
                }
            }
        }
        return bestScore;
    }

    @Override
    public void setMove() {
        move = new int[2];
        char player = Player.xOrO(state);
        if (player == 'X') {
            int bestScore = -10;
            for (int i = 0; i < state.length; i++) {
                for (int j = 0; j < state.length; j++) {
                    if (state[i][j] == '_') {
                        state[i][j] = player;
                        int score = minimax(state, 'O');
                        state [i][j] = '_';
                        if (score > bestScore) {
                            bestScore = score;
                            move[0] = i;
                            move[1] = j;
                        }
                    }
                }
            }
        } else {
            int bestScore = 10;
            for (int i = 0; i < state.length; i++) {
                for (int j = 0; j < state.length; j++) {
                    if (state[i][j] == '_') {
                        state[i][j] = player;
                        int score = minimax(state, 'X');
                        state [i][j] = '_';
                        if (score < bestScore) {
                            bestScore = score;
                            move[0] = i;
                            move[1] = j;
                        }
                    }
                }
            }
        }
    }
}
