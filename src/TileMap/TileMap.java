package TileMap;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TileMap {

    // position
    private double x;
    private double y;

    // bounds
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    private double tween;

    // map
    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    // tileset
    private BufferedImage tileset;
    private int numTilesAcross;
    private Tile[][] tiles;

    // drawing
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
        numColsToDraw = GamePanel.WIDTH / tileSize + 2;
        tween = 0.07;
    }

    public void loadTiles(String s) {

        try {

            tileset = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
            numTilesAcross = tileset.getWidth() / tileSize;
            tiles = new Tile[2][numTilesAcross];

            BufferedImage subimage;
            for (int col = 0; col < numTilesAcross; col++) {
                subimage = tileset.getSubimage(
                        col * tileSize,
                        0,
                        tileSize,
                        tileSize
                );
                tiles[0][col] = new Tile(subimage, Tile.NORMAL);
                subimage = tileset.getSubimage(
                        col * tileSize,
                        tileSize,
                        tileSize,
                        tileSize
                );
                tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String s) {

        try {

            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            xmin = GamePanel.WIDTH - width;
            xmax = 0;
            ymin = GamePanel.HEIGHT - height;
            ymax = 0;

            String delims = "\\s+";
            for (int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getTileSize() {
        return tileSize;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getType(int row, int col) {
        int rc = map[row][col];
        int r = rc / numTilesAcross;
        int c = rc % numTilesAcross;
        return tiles[r][c].getType();
    }

    public void setTween(double d) {
        tween = d;
    }

    public void setPosition(double x, double y) {

        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;

        fixBounds();

        colOffset = (int) -this.x / tileSize;
        rowOffset = (int) -this.y / tileSize;

    }

    private void fixBounds() {
        if (x < xmin) x = xmin;
        if (y < ymin) y = ymin;
        if (x > xmax) x = xmax;
        if (y > ymax) y = ymax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TileMap)) return false;

        TileMap tileMap = (TileMap) o;

        if (colOffset != tileMap.colOffset) return false;
        if (height != tileMap.height) return false;
        if (numCols != tileMap.numCols) return false;
        if (numColsToDraw != tileMap.numColsToDraw) return false;
        if (numRows != tileMap.numRows) return false;
        if (numRowsToDraw != tileMap.numRowsToDraw) return false;
        if (numTilesAcross != tileMap.numTilesAcross) return false;
        if (rowOffset != tileMap.rowOffset) return false;
        if (tileSize != tileMap.tileSize) return false;
        if (Double.compare(tileMap.tween, tween) != 0) return false;
        if (width != tileMap.width) return false;
        if (Double.compare(tileMap.x, x) != 0) return false;
        if (xmax != tileMap.xmax) return false;
        if (xmin != tileMap.xmin) return false;
        if (Double.compare(tileMap.y, y) != 0) return false;
        if (ymax != tileMap.ymax) return false;
        if (ymin != tileMap.ymin) return false;
        if (!tileset.equals(tileMap.tileset)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + xmin;
        result = 31 * result + ymin;
        result = 31 * result + xmax;
        result = 31 * result + ymax;
        temp = Double.doubleToLongBits(tween);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + tileSize;
        result = 31 * result + numRows;
        result = 31 * result + numCols;
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + tileset.hashCode();
        result = 31 * result + numTilesAcross;
        result = 31 * result + rowOffset;
        result = 31 * result + colOffset;
        result = 31 * result + numRowsToDraw;
        result = 31 * result + numColsToDraw;
        return result;
    }

    @Override
    public String toString() {
        return "TileMap{" +
                "x=" + x +
                ", y=" + y +
                ", xmin=" + xmin +
                ", ymin=" + ymin +
                ", xmax=" + xmax +
                ", ymax=" + ymax +
                ", tween=" + tween +
                ", map=" + Arrays.toString(map) +
                ", tileSize=" + tileSize +
                ", numRows=" + numRows +
                ", numCols=" + numCols +
                ", width=" + width +
                ", height=" + height +
                ", tileset=" + tileset +
                ", numTilesAcross=" + numTilesAcross +
                ", tiles=" + Arrays.toString(tiles) +
                ", rowOffset=" + rowOffset +
                ", colOffset=" + colOffset +
                ", numRowsToDraw=" + numRowsToDraw +
                ", numColsToDraw=" + numColsToDraw +
                '}';
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getXmin() {
        return xmin;
    }

    public void setXmin(int xmin) {
        this.xmin = xmin;
    }

    public int getYmin() {
        return ymin;
    }

    public void setYmin(int ymin) {
        this.ymin = ymin;
    }

    public int getXmax() {
        return xmax;
    }

    public void setXmax(int xmax) {
        this.xmax = xmax;
    }

    public int getYmax() {
        return ymax;
    }

    public void setYmax(int ymax) {
        this.ymax = ymax;
    }

    public double getTween() {
        return tween;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getTileset() {
        return tileset;
    }

    public void setTileset(BufferedImage tileset) {
        this.tileset = tileset;
    }

    public int getNumTilesAcross() {
        return numTilesAcross;
    }

    public void setNumTilesAcross(int numTilesAcross) {
        this.numTilesAcross = numTilesAcross;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public void setRowOffset(int rowOffset) {
        this.rowOffset = rowOffset;
    }

    public int getColOffset() {
        return colOffset;
    }

    public void setColOffset(int colOffset) {
        this.colOffset = colOffset;
    }

    public int getNumRowsToDraw() {
        return numRowsToDraw;
    }

    public void setNumRowsToDraw(int numRowsToDraw) {
        this.numRowsToDraw = numRowsToDraw;
    }

    public int getNumColsToDraw() {
        return numColsToDraw;
    }

    public void setNumColsToDraw(int numColsToDraw) {
        this.numColsToDraw = numColsToDraw;
    }

    public void setY(double y) {

        this.y = y;
    }

    public void draw(Graphics2D g) {

        for (

                int row = rowOffset;
                row < rowOffset + numRowsToDraw;
                row++) {

            if (row >= numRows) break;

            for (
                    int col = colOffset;
                    col < colOffset + numColsToDraw;
                    col++) {

                if (col >= numCols) break;

                if (map[row][col] == 0) continue;

                int rc = map[row][col];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;

                g.drawImage(
                        tiles[r][c].getImage(),
                        (int) x + col * tileSize,
                        (int) y + row * tileSize,
                        null
                );

            }

        }

    }

}



















