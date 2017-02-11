package GameState;

public abstract class GameState {

    protected GameStateManager gsm;

    public abstract void init();

    public abstract void update();

    public abstract void draw(java.awt.Graphics2D g);

    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);

    @Override
    public String toString() {
        return "GameState{" +
                "gsm=" + gsm +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameState)) return false;

        GameState gameState = (GameState) o;

        if (!gsm.equals(gameState.gsm)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return gsm.hashCode();
    }

    public GameStateManager getGsm() {

        return gsm;
    }

    public void setGsm(GameStateManager gsm) {
        this.gsm = gsm;
    }
}
