package com.mygdx.game.items.GameStates;

import com.mygdx.game.PongGame;
import com.mygdx.game.items.Ball;
import com.mygdx.game.items.Paddle;

public abstract class PongGameState {

    protected PongGame game;

    public PongGameState(PongGame game) {
        this.game = game;
    }

    public abstract void render(Ball ball, Paddle leftPaddle, Paddle rightPaddle);
}
