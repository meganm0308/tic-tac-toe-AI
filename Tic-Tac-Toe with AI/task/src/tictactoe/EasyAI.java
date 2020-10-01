package tictactoe;

public class EasyAI extends User{

    public EasyAI(char[][] state) {
        super(state);
    }

    @Override
    public void setMove() {
        while (true) {
            move = new int[]{Main.random.nextInt(Main.fieldSize), Main.random.nextInt(Main.fieldSize)};
            if (state[move[0]][move[1]] == '_') {
                break;
            }
        }
    }
}