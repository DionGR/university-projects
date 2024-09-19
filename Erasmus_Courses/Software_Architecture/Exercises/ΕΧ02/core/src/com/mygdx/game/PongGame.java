package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.items.Ball;
import com.mygdx.game.items.GameStates.PongGameRunningState;
import com.mygdx.game.items.GameStates.PongGameState;
import com.mygdx.game.items.Paddle;

public class PongGame extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;

	public OrthographicCamera camera;
	public SpriteBatch batch;
	public BitmapFont font;

	public Vector3 touchPos;

	public Texture background;
	public Texture ballTexture;
	public Texture paddleTexture;

	public Paddle leftPaddle;
	public Paddle rightPaddle;
	public Ball ball;

	private static PongGame pongGameInstance;
	public PongGameState gameState;

	private PongGame() {}

	public static PongGame getInstance() {
		if (pongGameInstance == null) {
			pongGameInstance = new PongGame();
		}

		return pongGameInstance;
	}

	@Override
	public void create () {
		gameState = new PongGameRunningState(this);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);

		font = new BitmapFont();
		font.setColor(Color.WHITE);

		batch = new SpriteBatch();
		background = new Texture("background.png");
		ballTexture = new Texture("ball.png");
		paddleTexture = new Texture("paddle.png");

		touchPos = new Vector3();

		leftPaddle = new Paddle(paddleTexture, 16, 64, 16, HEIGHT / 2 - 32);
		rightPaddle = new Paddle(paddleTexture, 16, 64, WIDTH - 16 - 16, HEIGHT / 2 - 32, true);
		ball = new Ball(ballTexture, 16, 16);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		batch.draw(background, 0, 0);
		font.draw(batch, String.valueOf(leftPaddle.getPoints()), WIDTH / 2 - 16, HEIGHT - 16);
		font.draw(batch, String.valueOf(rightPaddle.getPoints()), WIDTH / 2 + 16, HEIGHT - 16);

		gameState.render(ball, leftPaddle, rightPaddle);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		ballTexture.dispose();
		paddleTexture.dispose();
		font.dispose();
	}

}
