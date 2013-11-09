package gaming.robocop.universe;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Path.FillType;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import static android.widget.LinearLayout.LayoutParams;

// Please Comment all the lines 
public class Main extends Activity implements SensorEventListener{

    private PowerManager mPowerManager;
	private WakeLock mWakeLock;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	public static  GameView gameview ;
	private float mSensorX;
	private float mSensorY;
	private float mSensorZ;
	private int direction;
    private  LinearLayout ll;
    private LinearLayout l2;
    LayoutParams fpfp;
    public static boolean activitypaused=false;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameview = new GameView(this);
        android.widget.FrameLayout.LayoutParams params = new android.widget.FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,  LayoutParams.MATCH_PARENT);
        gameview.setLayoutParams(params);
     
        setContentView(gameview);
        
        
       
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       
        
        // Get an instance of the SensorManager
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get an instance of the PowerManager
        mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // Create a bright wake lock
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, getClass()
                .getName());
        
        final Handler handler = new Handler();
        
        handler.postDelayed(new Runnable() {
	          public void run() {
	        	  
	        	  Main.gameview.stopgame();
			       
			       finish();
			      
	          }
	        }, 69000);
       
    }
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	activitypaused=false;
    	 mWakeLock.acquire();
    	 mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	activitypaused=true;
    	 mWakeLock.release();
    	   mSensorManager.unregisterListener(this);
    }
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		mSensorX = event.values[0];
        mSensorY = event.values[1];
        mSensorZ = event.values[2];
      
     
        direction=0;
        if(mSensorX>2 && (mSensorY>-1||mSensorY<1)  && mSensorZ>0){ 
        	System.out.println("bottom "+mSensorX+" "+mSensorY); 
        	direction=3;
        	gameview.getsensordata(direction );
        	}            	
        else if(mSensorX<-2 && (mSensorY>-1||mSensorY<1)  && mSensorZ>0) {
        	System.out.println("top "+mSensorX+" "+mSensorY);
        	direction=4;
        	gameview.getsensordata(direction );
        	}
        else  if((mSensorX>-1||mSensorX<1) && mSensorY<2  && mSensorZ>0) { 
        	System.out.println("left "+mSensorX+" "+mSensorY); 
        	direction=1;
        	gameview.getsensordata(direction );
        	}
        else  if((mSensorX>-1||mSensorX<1) && mSensorY>4  && mSensorZ>0) { 
        	System.out.println("right "+mSensorX+" "+mSensorY); 
        	direction=2;
        	gameview.getsensordata(direction );
        	}
     
        
		
		
		
	}

    public  void closemainactivity() {
    	this.finish();
    	
    }
}
