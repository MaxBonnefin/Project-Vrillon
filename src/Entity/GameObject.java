package Entity;
import Main.GamePanel;
import TileMap.Tile;
import TileMap.TileMap;

public abstract class GameObject {

    //reusing tilemap code for all gameobject sprites
    protected TileMap tileMap;
    protected int tileSize;
    protected double xMap, yMap;

    public double x, y; // for positions
    protected double dx, dy; //vector

    protected int width, height;

    //collision boundaries
    protected int collisionWidth, collisionHeight;

    //collisions
    protected int currentRow, currentCol;
    protected double xDest, yDest, xTemp, yTemp;
    protected boolean topLeft, topRight, bottomLeft, bottomRight; //for checking the collisions on each corner of the object

    //animation
    //protected Animation animation;//TODO
    protected int currentAction;
    protected int previousAction;

    //movement
    protected boolean left;
    protected boolean right;

    @Override
    public String toString() {
        return "GameObject{" +
                "tileMap=" + tileMap +
                ", tileSize=" + tileSize +
                ", xMap=" + xMap +
                ", yMap=" + yMap +
                ", x=" + x +
                ", y=" + y +
                ", dx=" + dx +
                ", dy=" + dy +
                ", width=" + width +
                ", height=" + height +
                ", collisionWidth=" + collisionWidth +
                ", collisionHeight=" + collisionHeight +
                ", currentRow=" + currentRow +
                ", currentCol=" + currentCol +
                ", xDest=" + xDest +
                ", yDest=" + yDest +
                ", xTemp=" + xTemp +
                ", yTemp=" + yTemp +
                ", topLeft=" + topLeft +
                ", topRight=" + topRight +
                ", bottomLeft=" + bottomLeft +
                ", bottomRight=" + bottomRight +
                ", currentAction=" + currentAction +
                ", previousAction=" + previousAction +
                ", left=" + left +
                ", right=" + right +
                ", up=" + up +
                ", down=" + down +
                ", moveSpeed=" + moveSpeed +
                ", maxSpeed=" + maxSpeed +
                ", stopSpeed=" + stopSpeed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameObject)) return false;

        GameObject that = (GameObject) o;

        if (bottomLeft != that.bottomLeft) return false;
        if (bottomRight != that.bottomRight) return false;
        if (collisionHeight != that.collisionHeight) return false;
        if (collisionWidth != that.collisionWidth) return false;
        if (currentAction != that.currentAction) return false;
        if (currentCol != that.currentCol) return false;
        if (currentRow != that.currentRow) return false;
        if (down != that.down) return false;
        if (Double.compare(that.dx, dx) != 0) return false;
        if (Double.compare(that.dy, dy) != 0) return false;
        if (height != that.height) return false;
        if (left != that.left) return false;
        if (Double.compare(that.maxSpeed, maxSpeed) != 0) return false;
        if (Double.compare(that.moveSpeed, moveSpeed) != 0) return false;
        if (previousAction != that.previousAction) return false;
        if (right != that.right) return false;
        if (Double.compare(that.stopSpeed, stopSpeed) != 0) return false;
        if (tileSize != that.tileSize) return false;
        if (topLeft != that.topLeft) return false;
        if (topRight != that.topRight) return false;
        if (up != that.up) return false;
        if (width != that.width) return false;
        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.xDest, xDest) != 0) return false;
        if (Double.compare(that.xMap, xMap) != 0) return false;
        if (Double.compare(that.xTemp, xTemp) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        if (Double.compare(that.yDest, yDest) != 0) return false;
        if (Double.compare(that.yMap, yMap) != 0) return false;
        if (Double.compare(that.yTemp, yTemp) != 0) return false;
        if (!tileMap.equals(that.tileMap)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = tileMap.hashCode();
        result = 31 * result + tileSize;
        temp = Double.doubleToLongBits(xMap);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yMap);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(x);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dx);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dy);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + collisionWidth;
        result = 31 * result + collisionHeight;
        result = 31 * result + currentRow;
        result = 31 * result + currentCol;
        temp = Double.doubleToLongBits(xDest);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yDest);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(xTemp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yTemp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (topLeft ? 1 : 0);
        result = 31 * result + (topRight ? 1 : 0);
        result = 31 * result + (bottomLeft ? 1 : 0);
        result = 31 * result + (bottomRight ? 1 : 0);
        result = 31 * result + currentAction;
        result = 31 * result + previousAction;
        result = 31 * result + (left ? 1 : 0);
        result = 31 * result + (right ? 1 : 0);
        result = 31 * result + (up ? 1 : 0);
        result = 31 * result + (down ? 1 : 0);
        temp = Double.doubleToLongBits(moveSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(stopSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    protected boolean up;
    protected boolean down;

    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;

    public GameObject(TileMap tm){
        tileMap = tm;
        tileSize = tm.getTileSize();
    }

    public boolean intersects(GameObject other){

        return x < other.x + other.width && x + width > other.x && y < other.y + other.height && y + height > other.y;

    }

    public void checkObstacleCollision(){
        currentCol = (int)x / tileSize;
        currentRow = (int)y / tileSize;

        xDest = x + dx;
        yDest = y + dy;

        xTemp = x;
        yTemp = y;

        //checks for collision with obstacles in the x direction
        calculateCorners(xDest, y);
        if(dx < 0){
            if(topLeft||bottomLeft){
                dx = 0;
                xTemp = currentCol * tileSize + collisionWidth / 2;
            }else{
                xTemp += dx;
            }
        }
        if(dx > 0){
            if(topRight||bottomRight){
                dx = 0;
                xTemp = (currentCol + 1)* tileSize - collisionHeight / 2;
            }else{
                xTemp += dx;
            }
        }

        //checks for collision with obstacles in the y direction
        calculateCorners(x, yDest);
        if(dy < 0){
            if(topLeft||topRight){
                dy = 0;
                yTemp = currentRow * tileSize + collisionHeight / 2;
            }else{
                yTemp += dy;
            }
        }
        if(dy > 0){
            if(bottomLeft||bottomRight){
                dy = 0;
                yTemp = (currentRow + 1)* tileSize - collisionHeight / 2;
            }else{
                yTemp += dy;
            }
        }

    }

    public TileMap getTileMap() {
        return tileMap;
    }

    public void setTileMap(TileMap tileMap) {
        this.tileMap = tileMap;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public double getxMap() {
        return xMap;
    }

    public void setxMap(double xMap) {
        this.xMap = xMap;
    }

    public double getyMap() {
        return yMap;
    }

    public void setyMap(double yMap) {
        this.yMap = yMap;
    }

    public void setX(double x) {
        this.x = x;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setCollisionWidth(int collisionWidth) {
        this.collisionWidth = collisionWidth;
    }

    public void setCollisionHeight(int collisionHeight) {
        this.collisionHeight = collisionHeight;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }

    public double getxDest() {
        return xDest;
    }

    public void setxDest(double xDest) {
        this.xDest = xDest;
    }

    public double getyDest() {
        return yDest;
    }

    public void setyDest(double yDest) {
        this.yDest = yDest;
    }

    public double getxTemp() {
        return xTemp;
    }

    public void setxTemp(double xTemp) {
        this.xTemp = xTemp;
    }

    public double getyTemp() {
        return yTemp;
    }

    public void setyTemp(double yTemp) {
        this.yTemp = yTemp;
    }

    public boolean isTopLeft() {
        return topLeft;
    }

    public void setTopLeft(boolean topLeft) {
        this.topLeft = topLeft;
    }

    public boolean isTopRight() {
        return topRight;
    }

    public void setTopRight(boolean topRight) {
        this.topRight = topRight;
    }

    public boolean isBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(boolean bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public boolean isBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(boolean bottomRight) {
        this.bottomRight = bottomRight;
    }

    public int getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }

    public int getPreviousAction() {
        return previousAction;
    }

    public void setPreviousAction(int previousAction) {
        this.previousAction = previousAction;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getStopSpeed() {
        return stopSpeed;
    }

    public void setStopSpeed(double stopSpeed) {
        this.stopSpeed = stopSpeed;
    }

    protected void calculateCorners(double x, double y){
        int leftTile = ((int)x - collisionWidth / 2) / tileSize;
        int rightTile = ((int)x + collisionWidth / 2 - 1) / tileSize;
        int topTile = ((int)y - collisionHeight / 2) / tileSize;
        int bottomTile = ((int)y + collisionHeight / 2 - 1) / tileSize;

        if(topTile < 0 || bottomTile >= tileMap.getNumRows() || leftTile < 0 || rightTile >= tileMap.getNumCols()) {
            topLeft = topRight = bottomLeft = bottomRight = false;
            return;
        }
        int tLeft = tileMap.getType(topTile, leftTile);
        int tRight = tileMap.getType(topTile, rightTile);
        int bLeft = tileMap.getType(bottomTile, leftTile);
        int bRight = tileMap.getType(bottomTile, rightTile);

        topLeft = tLeft == Tile.BLOCKED;
        topRight = tRight == Tile.BLOCKED;
        bottomLeft = bLeft == Tile.BLOCKED;
        bottomRight = bRight == Tile.BLOCKED;

    }

    public int getX(){
        return (int)x;
    }
    public int getY(){
        return (int)y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getCollisionWidth() {
        return collisionWidth;
    }
    public int getCollisionHeight() {
        return collisionWidth;
    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setMapPosition(){
        xMap = tileMap.getX();
        yMap = tileMap.getY();
    }

    //returns true if the game object is on the screen because if not then it will not need to be rendered.
    public boolean notOnScreen(){
        return (x + xMap + width < 0) || (x + xMap - width > GamePanel.WIDTH) || (y + yMap + height < 0) || (y + yMap - height > GamePanel.HEIGHT);
    }

    public void setLeft(boolean b){
        left = b;
    }
    public void setRight(boolean b){
        right = b;
    }
    public void setUp(boolean b){
        up = b;
    }
    public void setDown(boolean b){
        down = b;
    }

    public abstract void calculateKnockback(double newAngle, int i);

    public abstract void hit(int slashDamage);
}
