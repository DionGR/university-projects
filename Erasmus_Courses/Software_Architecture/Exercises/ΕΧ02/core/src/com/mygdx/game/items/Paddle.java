package com.mygdx.game.items;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.PongGame;

public class Paddle extends GameItem{

    private final boolean isAI;

    private int points;

    public Paddle(Texture texture, int textureWidth, int textureHeight, int xLocation, int yLocation) {
        this(texture, textureWidth, textureHeight, xLocation, yLocation, false);
    }

    public Paddle(Texture texture, int textureWidth, int textureHeight, int xLocation, int yLocation, boolean isAI) {
        super(texture, textureWidth, textureHeight, xLocation, yLocation);
        this.isAI = isAI;
        this.points = 0;
    }

    public void update(float targetY) {
        if (this.isAI) {
            if (random(0, 100) > 80) {
                this.yLocation = (int) targetY;
            }
        } else {
            this.yLocation = this.borderCheck(targetY);
        }
    }

    public void addPoint() {
        this.points++;
    }

    public int borderCheck(final float targetY) {
        if (targetY + this.textureHeight > PongGame.HEIGHT) {
            return PongGame.HEIGHT - this.textureHeight;
        } else return (int) Math.max(targetY, 0);
    }

    public int getPoints() {
        return this.points;
    }

    public void resetPoints() {
        this.points = 0;
    }

}
