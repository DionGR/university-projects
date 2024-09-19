package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.PongGame;

public class GameItem {

    protected final Texture texture;

    protected final int textureWidth;
    protected final int textureHeight;

    protected int xLocation;
    protected int yLocation;

    public GameItem(final Texture texture, final int textureWidth, final int textureHeight, final int xLocation, final int yLocation) {
        this.texture = texture;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;


        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public int getX() {
        return this.xLocation;
    }

    public int getY() {
        return this.yLocation;
    }

    public int getWidth() {
        return this.textureWidth;
    }

    public int getHeight() {
        return this.textureHeight;
    }

    public boolean widthCheck() {
        return this.getX() + this.getWidth() > PongGame.WIDTH || this.getX() < 0;
    }

    public boolean heightCheck() {
        return this.getY() + this.getHeight() > PongGame.HEIGHT  || this.getY() < 0;
    }

    public boolean isColliding(GameItem item) {
        return (this.xLocation <= item.getX() + item.getWidth() && this.xLocation >= item.getX()
                && ((this.yLocation <= item.getY() + item.getHeight() && this.yLocation >= item.getY())
                || (this.yLocation + this.textureHeight <= item.getY() + item.getHeight()
                && this.yLocation + this.textureHeight >= item.getY()))
        );
    }
}
