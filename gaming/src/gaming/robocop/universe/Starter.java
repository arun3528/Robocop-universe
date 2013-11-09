package gaming.robocop.universe;





import android.app.Activity;
import android.content.Intent;



import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
 
public class Starter extends Activity {
     public void onAttachedToWindow() {
            super.onAttachedToWindow();
            Window window = getWindow();
            window.setFormat(PixelFormat.RGBA_8888);
        }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        StartAnimations();
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
                public void run() {
              	  startMenu();
                        finish();
                }
        }, secondsDelayed * 5000);
        
 	  

    }
    public void startMenu(){    	
    	 startActivity(new Intent(this, Menu.class));
    }
    private void StartAnimations() {
    	// first animation file
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        //first animation for layout file
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        
        
        // second  animation 
        anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        
        ImageView im= (ImageView) findViewById(R.id.robocop);
        // ImageView iv = (ImageView) findViewById(R.drawable.exo1);
         im.clearAnimation();
         im.startAnimation(anim);
         
         
         // third animation
         anim = AnimationUtils.loadAnimation(this, R.anim.translate);
         anim.reset();
         
        ImageView imv= (ImageView) findViewById(R.id.logo);
       // ImageView iv = (ImageView) findViewById(R.drawable.exo1);
        imv.clearAnimation();
        imv.startAnimation(anim);
        
        
       
 
    }
 
}
