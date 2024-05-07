package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.PongGame;

public class Ball extends GameItem {

    private static int VELOCITY = 4;

    private boolean forward;
    private boolean ascending;

    public Ball(final Texture texture, final int textureWidth, final int textureHeight) {
        super(texture, textureWidth, textureHeight, PongGame.WIDTH / 2, PongGame.HEIGHT / 2);

        this.forward = false;
        this.ascending = true;
    }

    public void update(final Paddle leftPaddle, final Paddle rightPaddle) {

        this.xLocation += (this.forward ? VELOCITY : -VELOCITY);
        this.yLocation += (this.ascending ? VELOCITY : -VELOCITY);

        if (this.widthCheck()) {
            this.forward = !this.forward;
        }

        if (this.heightCheck()) {
            this.ascending = !this.ascending;
        }

       if (this.isColliding(leftPaddle)) {
            this.forward = true;
        } else if (this.isColliding(rightPaddle)) {
           this.forward = false;
       }

       if (this.xLocation < leftPaddle.getX() + leftPaddle.getWidth() / 2) {
           rightPaddle.addPoint();
           this.resetLocation();
           this.resetVelocity();
       } else if (this.xLocation > rightPaddle.getX() + rightPaddle.getWidth() / 2) {
           leftPaddle.addPoint();
           this.resetLocation();
           this.resetVelocity();
       } else {
           VELOCITY += 0.5;
       }

    }

    public void resetLocation() {
        this.xLocation = PongGame.WIDTH / 2;
        this.yLocation = PongGame.HEIGHT / 2;
        this.forward = Math.random() < 0.5;
    }

    private void resetVelocity() {
        VELOCITY = 4;
    }
}
