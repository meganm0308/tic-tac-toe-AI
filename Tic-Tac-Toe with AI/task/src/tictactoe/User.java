package tictactoe;

public class User {
    protected int[] move;
    protected char[][] state;

    public User(char[][] state) {
        this.state = state;
    }

    public void setMove() {
        move = Main.checkEmpty(state);
    }

    public char[][] getState() {
        return state;
    }

    public void setState(char[][] state) {
        this.state = Main.addToField(state, move);
    }
}
