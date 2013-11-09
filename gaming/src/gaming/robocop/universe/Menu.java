package gaming.robocop.universe;



import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;


public class Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		LinearLayout mMailTab=(LinearLayout) findViewById(R.id.robomenu);
		
		//mMailTab.setImageBitmap(null);
		mMailTab.setBackgroundResource( R.anim.earthanimation );

		final AnimationDrawable mailAnimation = (AnimationDrawable) mMailTab.getBackground();
		mMailTab.post(new Runnable() {
		    public void run() {
		        if ( mailAnimation != null ) mailAnimation.start();
		      }
		});

		
		Button b1=(Button) findViewById(R.id.button2);
		b1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameView.scorecount=0;
				if(!Main.activitypaused) {
				Toast.makeText(getApplicationContext(), "Kill the enemies in range ,Dont kill the girl .", Toast.LENGTH_LONG).show();
				Toast.makeText(getApplicationContext(), "Tilt your phone to move", Toast.LENGTH_LONG).show();
				Toast.makeText(getApplicationContext(), "You have 1 minute time", Toast.LENGTH_LONG).show();
				}
				final Handler handler = new Handler();
		        handler.postDelayed(new Runnable() {
		          public void run() {
		        	  if(!Main.activitypaused) {
		        	  Toast.makeText(getApplicationContext() , "game over in 5", Toast.LENGTH_SHORT).show();
				       Toast.makeText(getApplicationContext() , "game over in 4", Toast.LENGTH_SHORT).show();
				       Toast.makeText(getApplicationContext() , "game over in 3", Toast.LENGTH_SHORT).show();
				       Toast.makeText(getApplicationContext() , "game over in 2", Toast.LENGTH_SHORT).show();
				       Toast.makeText(getApplicationContext() , "game over in 1", Toast.LENGTH_SHORT).show();
		        	  }
				      
		          }
		        }, 60000);
		        handler.postDelayed(new Runnable() {
			          public void run() {
			        	  
			        	  if(!Main.activitypaused) {
					       Toast.makeText(getApplicationContext() , "Your Total Kill  "+ GameView.scorecount, Toast.LENGTH_LONG).show();
					       
			        	  }
			          }
			        }, 65000);
		       
				startActivity(new Intent(getApplicationContext(), Main.class));
			}
		});
	}

	

}
