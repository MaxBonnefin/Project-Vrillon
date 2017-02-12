package GameState;

import Main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class MenuState extends GameState {

    private int currentChoice = 0;
    private String[] options = {
            "Start",
            "Help",
            "Quit"
    };

    private Font titleFont;

    private Font font;

    public MenuState(GameStateManager gsm) {

        this.gsm = gsm;

        titleFont = new Font(
                "Century Gothic",
                Font.PLAIN,
                28);

        font = new Font("Arial", Font.PLAIN, 12);
    }

    public void init() {
    }

    public void update() {
    }

    public void draw(Graphics2D g) {
        //clear screen
        g.setColor(Color.black);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        // draw title
        g.setColor(Color.WHITE);
        g.setFont(titleFont);
        g.drawString("indev", 120, 70);

        // draw menu options
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {

            g.setColor(Color.black);
            g.drawString(options[i], 145, 140 + i * 15);

            if (i == currentChoice) {
                g.setColor(Color.WHITE);
                if (!options[i].contains(">")) {
                    options[i] = ">" + options[i];
                }
            } else {
                g.setColor(Color.GRAY);
                if (options[i].contains(">")) {
                    options[i] = options[i].replace(">", "");
                }
            }
            g.drawString(options[i], 145, 140 + i * 15);
        }

    }

    private void select() {
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.PLAYSTATE);
        }
        if (currentChoice == 1) {
            gsm.setState(GameStateManager.HELPSTATE);
        }
        if (currentChoice == 2) {
            System.exit(0);
        }
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
        if (k == KeyEvent.VK_W) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_S) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    public void keyReleased(int k) {
    }

    @Override
    public String toString() {
        return "MenuState{" +
                "currentChoice=" + currentChoice +
                ", options=" + Arrays.toString(options) +
                ", titleFont=" + titleFont +
                ", font=" + font +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuState)) return false;

        MenuState menuState = (MenuState) o;

        if (currentChoice != menuState.currentChoice) return false;
        if (!font.equals(menuState.font)) return false;
        if (!Arrays.equals(options, menuState.options)) return false;
        if (!titleFont.equals(menuState.titleFont)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = currentChoice;
        result = 31 * result + Arrays.hashCode(options);
        result = 31 * result + titleFont.hashCode();
        result = 31 * result + font.hashCode();
        return result;
    }

    public int getCurrentChoice() {

        return currentChoice;
    }

    public void setCurrentChoice(int currentChoice) {
        this.currentChoice = currentChoice;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}










