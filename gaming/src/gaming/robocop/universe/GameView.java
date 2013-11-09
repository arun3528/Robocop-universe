package gaming.robocop.universe;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;

import android.graphics.Bitmap;
import android.graphics.Rect;

import android.graphics.BitmapFactory;

import android.graphics.Canvas;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.AudioManager;
import android.media.SoundPool;

import android.view.MotionEvent;
import android.view.SurfaceHolder;

import android.view.SurfaceView;
import android.widget.Toast;

public class GameView extends SurfaceView {

	private Bitmap bmp;

	private SurfaceHolder holder;

	private GameLoopThread gameLoopThread;

	private int x = 0;

	private int xSpeed = 1;
	private Exo exo;

	private List<Sprite> sprites = new ArrayList<Sprite>();

	private List<Firing> firing = new ArrayList<Firing>();

	private List<TempSprite> temps = new ArrayList<TempSprite>();

	private long lastClick;

	private Bitmap bmpBlood;

	private Bitmap robot;

	private Bitmap fire;

	private Bitmap background;

	private Bitmap robot1;

	private SoundPool sp;

	public  Context context;

	private int explosion;

	public static  int scorecount=0;

	public GameView(Context context) {

		super(context);

		// thread which keeps the game running as a thread
		gameLoopThread = new GameLoopThread(this);

		// gets hold of the surface view to do activity
		holder = getHolder();

		holder.addCallback(new SurfaceHolder.Callback() {

			// when surface view Destroys this method gets called
			public void surfaceDestroyed(SurfaceHolder holder) {

				boolean retry = true;

				gameLoopThread.setRunning(false);

				while (retry) {

					try {

						gameLoopThread.join();

						retry = false;

					} catch (InterruptedException e) {

					}

				}

			}

			// first time when surface view is created
			public void surfaceCreated(SurfaceHolder holder) {
				createSprites();

				// Start a thread which handles all the activity
				gameLoopThread.setRunning(true);
				gameLoopThread.start();

			}

			public void surfaceChanged(SurfaceHolder holder, int format,

					int width, int height) {

			}

		});

	}
	public int stopgame() {
		try {
			gameLoopThread.setRunning(false);
			gameLoopThread.stop();

		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return scorecount;
	}
	private void createSprites() {
		// create the sprints into a list which will be used later
		// these sprints will have there own property


		sprites.add(createSprite(R.drawable.girl));
		sprites.add(createSprite(R.drawable.girl));
		sprites.add(createSprite(R.drawable.girl));		
		sprites.add(createSprite(R.drawable.girl));
		sprites.add(createSprite(R.drawable.girl));
		sprites.add(createSprite(R.drawable.girl));
		sprites.add(createSprite(R.drawable.girl));
		sprites.add(createSprite(R.drawable.girl));
		sprites.add(createSprite(R.drawable.girl));
		sprites.add(createSprite(R.drawable.sprint));
		sprites.add(createSprite(R.drawable.sprint));
		sprites.add(createSprite(R.drawable.sprint));
		sprites.add(createSprite(R.drawable.sprint));
		sprites.add(createSprite(R.drawable.sprint));
		sprites.add(createSprite(R.drawable.sprint));
		sprites.add(createSprite(R.drawable.sprint));
		sprites.add(createSprite(R.drawable.sprint));
		background = BitmapFactory.decodeResource(getResources(),R.drawable.galaxy);
		fire = BitmapFactory.decodeResource(getResources(), R.drawable.fire);
		robot = BitmapFactory.decodeResource(getResources(), R.drawable.exo);
		exo = new Exo(this, robot);
		sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		explosion = sp.load(getContext(), R.raw.mp, 1);

	}

	private Sprite createSprite(int resouce) {
		// gets blood shed fo each sprint all the time
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
		bmpBlood = BitmapFactory.decodeResource(getResources(),
				R.drawable.blood1);
		return new Sprite(this, bmp,resouce);

	}

	@Override
	// function which draws all the element on to the screen
	protected void onDraw(Canvas canvas) {
		try {
			// Draw background
			canvas.drawBitmap(background, 0, 0, null);
			// canvas.drawColor(Color.BLACK);

			// temp sprints
			for (int i = temps.size() - 1; i >= 0; i--) {

				temps.get(i).onDraw(canvas);

			}

			// exo is our main hero
			exo.onDraw(canvas);

			// firing is the one which our exo fires
			for (int i = firing.size() - 1; i >= 0; i--) {
				firing.get(i).onDraw(canvas);
			}

			// these are the devil ow wilen whome we have to kill
			for (Sprite sprite : sprites) {

				sprite.onDraw(canvas);

			}
		}
		catch (Exception e) {}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (System.currentTimeMillis() - lastClick > 300) {

			lastClick = System.currentTimeMillis();
		}

		// take x any on touch
		float x = event.getX();
		float y = event.getY();

		synchronized (getHolder()) {
			for (int i = sprites.size() - 1; i >= 0; i--) {

				Sprite activesprite = sprites.get(i);

				// fire and explod sound
				sp.play(explosion, 1, 1, 0, 0, 1);
				firing.add(new Firing(firing, this, exo, x, y, fire));

				// to check collision detection
				// if collision has happened and it's in the range
				if (activesprite.isCollition(x, y) && exo.isRange(x, y)) {
					robot1 = BitmapFactory.decodeResource(getResources(),
							R.drawable.exo1);
					// now the exo will fire
					exo.change(robot1);
					
					// we shall remove those sprints and place blood insted of
					// it
					sprites.remove(activesprite);
					temps.add(new TempSprite(temps, this, x, y, bmpBlood));

					// now the exo will fire
					// exo.change(robot1);
					if(activesprite.bmpid== R.drawable.sprint) {
						scorecount++;
						sprites.add(createSprite(R.drawable.sprint));
						break;
					}else
					{
						scorecount--;
						sprites.add(createSprite(R.drawable.girl));
						break;

					}

				}
			}
		}

		return super.onTouchEvent(event);
	}

	public void getsensordata(int direction) {
		if (exo != null)// started now
			exo.updateposition(direction);

	}

}