package tictactoe;

import java.util.Random;

public class EasyAI extends User {
    final static Random random = new Random();

    public EasyAI(char[][] state) {
        super(state);
    }

    @Override
    public void setMove() {
        while (true) {
            move = new int[]{random.nextInt(Main.fieldSize), random.nextInt(Main.fieldSize)};
            if (state[move[0]][move[1]] == '_') {
                break;
            }
        }
    }
}
