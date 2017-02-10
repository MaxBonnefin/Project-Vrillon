package GameState;


import Main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class HelpState extends GameState {

    private Font font;

    public HelpState(GameStateManager gsm) {

        this.gsm = gsm;
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
        g.setColor(Color.gray);

        //help text content
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras neque orci, fermentum in sagittis a, maximus sit amet urna. Aliquam tellus lectus, volutpat eu libero a, suscipit cursus est. Ut ac luctus neque, eu tempor mauris. Etiam eu pellentesque metus. Praesent efficitur aliquam est sit amet ornare. Suspendisse in est nec diam auctor tincidunt vel quis felis. Phasellus aliquam efficitur sapien, vel viverra libero vestibulum at. Proin ultricies nisl ac purus gravida tempor. Curabitur ac nunc libero. Praesent sit amet purus id turpis ultrices ultricies quis nec augue. Etiam et pretium nisl. Etiam egestas finibus viverra. Nulla hendrerit nibh tortor, id bibendum diam mattis ut. Phasellus et arcu ligula. Sed malesuada risus ac suscipit lobortis.";

        //help text formatting and drawing
        StringBuilder sb = new StringBuilder(text);
        int i = 0;
        while (i + 45 < sb.length() && (i = sb.lastIndexOf(" ", i + 45)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        String s = sb.toString();

        FontRenderContext frc = g.getFontRenderContext();
        TextLayout layout = new TextLayout(s, font, frc);
        String[] outputs = s.split("\n");
        for (int n = 0; n < outputs.length; n++) {
            g.drawString(outputs[n], 15, (int) (15 + n * layout.getBounds().getHeight() + 0.5));
        }
        //draw back text
        g.setColor(Color.white);
        g.drawString(">Back", 15, GamePanel.HEIGHT - 15);
    }


    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }

    public void keyReleased(int k) {
    }

}