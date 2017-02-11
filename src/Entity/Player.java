package Entity;

import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    private int health;
    private int maxHealth;
    private boolean hit;
    private BufferedImage sprite;


    private double x1, y1;

    public Player(TileMap tm) {
        super(tm);
        width = 50;
        height = 50;
        collisionWidth = 50;
        collisionHeight = 50;

        moveSpeed = 0.4;
        maxSpeed = 1.8;
        stopSpeed = 0.5;

        health = maxHealth = 50;


        //load sprites
        try{
            sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/player.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void calculateKnockback(double newAngle, int i) {

    }

    @Override
    public void hit(int slashDamage) {

    }

    private void getNextPosition(){
        //movement
        if(left){
            dx -= moveSpeed;
            if(dx < - maxSpeed){
                dx = - maxSpeed;
            }
        }else if(right){
            dx += moveSpeed;
            if(dx > maxSpeed){
                dx = maxSpeed;
            }
        } else{
            if(dx > 0){
                dx -= stopSpeed;
                if(dx < 0){
                    dx = 0;
                }
            }else if(dx < 0){
                dx += stopSpeed;
                if(dx > 0){
                    dx = 0;
                }
            }
        }
        if(up){
            dy -= moveSpeed;
            if(dy < - maxSpeed){
                dy = - maxSpeed;
            }
        }else if(down){
            dy += moveSpeed;
            if(dy > maxSpeed){
                dy = maxSpeed;
            }
        }else{
            if(dy > 0){
                dy -= stopSpeed;
                if(dy < 0){
                    dy = 0;
                }
            }else if(dy < 0){
                dy += stopSpeed;
                if(dy > 0){
                    dy = 0;
                }
            }
        }
    }

    public void update(){
        //update position
        getNextPosition();
        checkObstacleCollision();
        setPosition(xTemp, yTemp);

        //update health
        if(health > maxHealth){
            health = maxHealth;
        }
    }

    public void draw(Graphics2D g){
        setMapPosition();
        g.drawImage(sprite, (int) (x + xMap - width / 2), (int) (y + yMap - height / 2), null);
    }

    @Override
    public String toString() {
        return "Player{" +
                "health=" + health +
                ", maxHealth=" + maxHealth +
                ", hit=" + hit +
                ", sprite=" + sprite +
                ", x1=" + x1 +
                ", y1=" + y1 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        if (!super.equals(o)) return false;

        Player player = (Player) o;

        if (health != player.health) return false;
        if (hit != player.hit) return false;
        if (maxHealth != player.maxHealth) return false;
        if (Double.compare(player.x1, x1) != 0) return false;
        if (Double.compare(player.y1, y1) != 0) return false;
        if (!sprite.equals(player.sprite)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + health;
        result = 31 * result + maxHealth;
        result = 31 * result + (hit ? 1 : 0);
        result = 31 * result + sprite.hashCode();
        temp = Double.doubleToLongBits(x1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public void setMaxHealth(int maxHealth) {

        this.maxHealth = maxHealth;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public int getHealth(){
        return health;
    }
    public void setHealth(int h){
        health = h;
    }
    public int getMaxHealth(){
        return maxHealth;
    }

}
