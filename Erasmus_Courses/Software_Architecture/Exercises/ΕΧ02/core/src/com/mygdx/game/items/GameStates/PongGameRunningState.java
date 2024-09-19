package com.mygdx.game.items.GameStates;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.PongGame;
import com.mygdx.game.items.Ball;
import com.mygdx.game.items.Paddle;

public class PongGameRunningState extends PongGameState{

        public PongGameRunningState(PongGame game) {
            super(game);
        }

        @Override
        public void render(Ball ball, Paddle leftPaddle, Paddle rightPaddle) {
            this.game.batch.draw(ball.getTexture(), ball.getX(), ball.getY());
            this.game.batch.draw(leftPaddle.getTexture(), leftPaddle.getX(), leftPaddle.getY());
            this.game.batch.draw(rightPaddle.getTexture(), rightPaddle.getX(), rightPaddle.getY());

            ball.update(leftPaddle, rightPaddle);
            rightPaddle.update(ball.getY());

            if(Gdx.input.isTouched()) {
                this.game.touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                this.game.camera.unproject(this.game.touchPos);

                leftPaddle.update(this.game.touchPos.y);
            }

            if (leftPaddle.getPoints() == 21 || rightPaddle.getPoints() == 21) {
                this.game.gameState = new PongGameFinishedState(this.game);
            }
        }

}
