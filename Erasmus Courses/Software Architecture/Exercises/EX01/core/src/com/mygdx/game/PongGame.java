package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.items.Ball;
import com.mygdx.game.items.Paddle;

public class PongGame extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;

	private Vector3 touchPos;

	private Texture background;
	private Texture ballTexture;
	private Texture paddleTexture;

	private Paddle leftPaddle;
	private Paddle rightPaddle;
	private Ball ball;

	private static PongGame pongGameInstance;

	private PongGame() {}

	public static PongGame getInstance() {
		if (pongGameInstance == null) {
			pongGameInstance = new PongGame();
		}

		return pongGameInstance;
	}

	@Override
	public void create () {

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


		if(rightPaddle.getPoints() < 21 && leftPaddle.getPoints() < 21) {

			batch.draw(ball.getTexture(), ball.getX(), ball.getY());
			batch.draw(leftPaddle.getTexture(), leftPaddle.getX(), leftPaddle.getY());
			batch.draw(rightPaddle.getTexture(), rightPaddle.getX(), rightPaddle.getY());

			ball.update(leftPaddle, rightPaddle);
			rightPaddle.update(ball.getY());

			if(Gdx.input.isTouched()) {
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPos);

				leftPaddle.update(touchPos.y);
			}
		} else {
			font.draw(batch, "Game Over", (float) WIDTH / 2 - 32, (float) HEIGHT / 2);
			font.draw(batch, "Tap to restart", (float) WIDTH / 2 - 32, (float) HEIGHT / 2 - 32);
			font.draw(batch, "Winner: " + (leftPaddle.getPoints() > rightPaddle.getPoints() ? "Left" : "Right"), (float) WIDTH / 2 - 32, (float) HEIGHT / 2 - 64);

			if(Gdx.input.isTouched()) {
				ball.resetLocation();
				leftPaddle.resetPoints();
				rightPaddle.resetPoints();
			}
		}

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
