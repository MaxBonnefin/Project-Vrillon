package GameState;

import Entity.Player;
import Main.GamePanel;
import TileMap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayState extends GameState {

    private TileMap tileMap;
    private Player player;

    public PlayState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }


    public void init() {
        tileMap = new TileMap(50);
        tileMap.setPosition(0,0);

        player = new Player(tileMap);
        player.setPosition(100,100);
    }

    public void update() {
        player.update();
    }

    public void draw(Graphics2D g) {
        //clear screen
        g.setColor(Color.black);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        player.draw(g);
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
        //player controls
        if (k == KeyEvent.VK_W) {
            player.setUp(true);
        }
        if (k == KeyEvent.VK_A) {
            player.setLeft(true);
        }
        if (k == KeyEvent.VK_S) {
            player.setDown(true);
        }
        if (k == KeyEvent.VK_D) {
            player.setRight(true);
        }
        if (k == KeyEvent.VK_UP) {
            player.setUp(true);
        }
        if (k == KeyEvent.VK_LEFT) {
            player.setLeft(true);
        }
        if (k == KeyEvent.VK_DOWN) {
            player.setDown(true);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setRight(true);
        }
    }

    @Override
    public String toString() {
        return "PlayState{" +
                "tileMap=" + tileMap +
                ", player=" + player +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayState)) return false;
        if (!super.equals(o)) return false;

        PlayState playState = (PlayState) o;

        if (!player.equals(playState.player)) return false;
        if (!tileMap.equals(playState.tileMap)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + tileMap.hashCode();
        result = 31 * result + player.hashCode();
        return result;
    }

    public TileMap getTileMap() {

        return tileMap;
    }

    public void setTileMap(TileMap tileMap) {
        this.tileMap = tileMap;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void keyReleased(int k) {
        //player controls
        if (k == KeyEvent.VK_W) {
            player.setUp(false);
        }
        if (k == KeyEvent.VK_A) {
            player.setLeft(false);
        }
        if (k == KeyEvent.VK_S) {
            player.setDown(false);
        }
        if (k == KeyEvent.VK_D) {
            player.setRight(false);
        }
        if (k == KeyEvent.VK_UP) {
            player.setUp(false);
        }
        if (k == KeyEvent.VK_LEFT) {
            player.setLeft(false);
        }
        if (k == KeyEvent.VK_DOWN) {
            player.setDown(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setRight(false);
        }
    }
}
