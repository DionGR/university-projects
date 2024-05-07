package com.mygdx.game.items.GameStates;

import static com.mygdx.game.PongGame.HEIGHT;
import static com.mygdx.game.PongGame.WIDTH;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.PongGame;
import com.mygdx.game.items.Ball;
import com.mygdx.game.items.Paddle;

public class PongGameFinishedState extends PongGameState{

        public PongGameFinishedState(PongGame game) {
            super(game);
        }

        @Override
        public void render(Ball ball, Paddle leftPaddle, Paddle rightPaddle) {
            this.game.font.draw(this.game.batch, "Game Over", (float) WIDTH / 2 - 32, (float) HEIGHT / 2);
            this.game.font.draw(this.game.batch, "Tap to restart", (float) WIDTH / 2 - 32, (float) HEIGHT / 2 - 32);
            this.game.font.draw(this.game.batch, "Winner: " + (leftPaddle.getPoints() > rightPaddle.getPoints() ? "Left" : "Right"), (float) WIDTH / 2 - 32, (float) HEIGHT / 2 - 64);

            if(Gdx.input.isTouched()) {
                ball.resetLocation();
                leftPaddle.resetPoints();
                rightPaddle.resetPoints();
                this.game.gameState = new PongGameRunningState(this.game);
            }
        }

}
