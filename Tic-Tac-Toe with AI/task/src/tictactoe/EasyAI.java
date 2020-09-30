package tictactoe;

public class EasyAI extends User{

    @Override
    public void setMove() {
        while (true) {
            move = new int[]{Main.random.nextInt(3), Main.random.nextInt(3)};
            if (state[move[0]][move[1]] == '_') {
                break;
            }
        }
    }

    public EasyAI(char[][] state) {
        super(state);
    }
}