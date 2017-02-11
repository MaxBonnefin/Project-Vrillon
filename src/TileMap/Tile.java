package TileMap;

import java.awt.image.BufferedImage;

public class Tile {

    // tile types
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;
    private BufferedImage image;
    private int type;

    public Tile(BufferedImage image, int type) {
        this.image = image;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;

        Tile tile = (Tile) o;

        if (type != tile.type) return false;
        if (!image.equals(tile.image)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = image.hashCode();
        result = 31 * result + type;
        return result;
    }

    public static int getNormal() {

        return NORMAL;
    }

    public static int getBlocked() {
        return BLOCKED;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "image=" + image +
                ", type=" + type +
                '}';
    }
}
