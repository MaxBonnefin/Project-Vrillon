package GameState;

import Main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayState extends GameState {

    public PlayState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }


    public void init() {

    }

    public void update() {

    }

    public void draw(Graphics2D g) {
        //clear screen
        g.setColor(Color.black);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }

    public void keyReleased(int k) {

    }
}
