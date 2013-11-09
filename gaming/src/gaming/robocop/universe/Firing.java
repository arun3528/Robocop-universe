package gaming.robocop.universe;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Firing {

	
	

	    private float x;

	    private float y;

	    private Bitmap bmp;

	    private int life = 15;

	    private List<Firing> temps;

		private Exo exo;



	    public Firing(List<Firing> temps, GameView gameView,Exo exo, float x,

	                 float y, Bitmap bmp) {

	          this.x = Math.min(Math.max(x - bmp.getWidth() / 2, 0),

	                        gameView.getWidth() - bmp.getWidth());

	          this.y = Math.min(Math.max(y - bmp.getHeight() / 2, 0),

	                        gameView.getHeight() - bmp.getHeight());

	          this.bmp = bmp;

	          this.temps = temps;
	          this.exo=exo;

	    }



	    public void onDraw(Canvas canvas) {

	          update();

	          canvas.drawBitmap(bmp, (exo.x-10), (exo.y+12+(exo.width/4)), null);

	    }



	    private void update() {

	          if (--life < 1) {

	                 temps.remove(this);

	          }

	    }

	}

