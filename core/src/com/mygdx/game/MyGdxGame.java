package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame  implements Screen {
	//private Table table;
	//private TextButton buttonPlay, buttonExit;
	//private Label label;
	//private BitmapFont font;
	private SpriteBatch batch;
	protected Stage stage;
	private TextureAtlas atlas;
	protected Skin skin;
	private Viewport viewport;
	private OrthographicCamera camera;


	public MyGdxGame(){
		atlas = new TextureAtlas("skin.atlas");
		skin = new Skin(Gdx.files.internal("skin.json"), atlas);
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new FillViewport(100, 100, camera);
		viewport.apply();

		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		camera.update();

		stage = new Stage(viewport, batch);
	}


	@Override
	public void show() {
		//Stage should controll input:
		Gdx.input.setInputProcessor(stage);

		//create Table
		final Table mainTable = new Table();

		//Set table to fill stage
		mainTable.setFillParent(true);

		//set alignment of contents in the table
		mainTable.top();

		//Create buttons
		final TextButton playButton = new TextButton("Play", skin);
		final TextButton optionsButton = new TextButton("options", skin);
		final TextButton exitButton = new TextButton("Exit", skin);

		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MyGdxGame());
			}
		});


		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}

		});
		//Add buttons to table
		mainTable.add(playButton);
		mainTable.row();
		mainTable.add(optionsButton);
		mainTable.row();
		mainTable.add(exitButton);

		//Add table to stage
		stage.addActor(mainTable);
	}
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		camera.update();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}


	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		skin.dispose();
		atlas.dispose();
	}
}
