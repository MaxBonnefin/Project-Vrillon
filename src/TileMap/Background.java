package TileMap;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {

    private BufferedImage image;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;

    public Background(String s, double ms) {

        try {
            image = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
            moveScale = ms;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % GamePanel.WIDTH;
        this.y = (y * moveScale) % GamePanel.HEIGHT;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g) {

        g.drawImage(image, (int) x, (int) y, null);

        if (x < 0) {
            g.drawImage(
                    image,
                    (int) x + GamePanel.WIDTH,
                    (int) y,
                    null
            );
        }
        if (x > 0) {
            g.drawImage(
                    image,
                    (int) x - GamePanel.WIDTH,
                    (int) y,
                    null
            );
        }
    }

    @Override
    public String toString() {
        return "Background{" +
                "image=" + image +
                ", x=" + x +
                ", y=" + y +
                ", dx=" + dx +
                ", dy=" + dy +
                ", moveScale=" + moveScale +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Background)) return false;

        Background that = (Background) o;

        if (Double.compare(that.dx, dx) != 0) return false;
        if (Double.compare(that.dy, dy) != 0) return false;
        if (Double.compare(that.moveScale, moveScale) != 0) return false;
        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        if (!image.equals(that.image)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = image.hashCode();
        temp = Double.doubleToLongBits(x);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dx);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dy);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(moveScale);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public BufferedImage getImage() {

        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getMoveScale() {
        return moveScale;
    }

    public void setMoveScale(double moveScale) {
        this.moveScale = moveScale;
    }
}







