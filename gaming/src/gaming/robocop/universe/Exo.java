package gaming.robocop.universe;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Exo {

    // direction = 0 up, 1 left, 2 down, 3 right,

    // animation = 3 back, 1 left, 0 front, 2 right



    private static final int BMP_ROWS =1;

    private static final int BMP_COLUMNS =2;

    public int x=100;

    public int y=100 ;


    private int xSpeed = 30;

    private GameView gameView;

    private Bitmap bmp;

    private int currentFrame = 0;

    public int width;

    public int height;

    private int ySpeed=30;

	private int life=5;

	private int flag=0;

	private Bitmap bmp1;
	private Bitmap temp;



    public Exo(GameView gameView, Bitmap bmp) {

    	this.width = bmp.getWidth() / BMP_COLUMNS;

          this.height = bmp.getHeight() / BMP_ROWS;

          this.gameView = gameView;

          this.bmp = bmp;

   
        
      /* x = gameView.getWidth()/2;

       y = gameView.getHeight();
*/

    }

public void change(Bitmap bmp) {
	
this.bmp1=bmp;
flag=1;
temp=this.bmp;
this.bmp=bmp1;
}

    private void update() {
    	
   
       		
         
    		if(currentFrame==0) {
    			currentFrame=1;
    			//x=x+3;
    			//y=y+3;
    			
    		}else
    		{
    			currentFrame=0;
    			//x=x+3;
    			//y=y+3;
    			
    		}
    		if(flag==1){
    			life--;
    			if (life < 1 ) {

    				this.bmp=temp;
    				flag=0;
    				life=5;

    			}
    		}
         

    }

   public void  updateposition( int flag) {
	// TODO Auto-generated method stub
	   
 
		 /*  if( (x >= gameView.getWidth()-width/2) || (x <0) || (y >= gameView.getHeight() - height/2) || (y <0)) {		   
			   System.out.println("Out of Range==================");
			   System.out.println(x+" "+y+" "+gameView.getWidth()+" "+gameView.getHeight()+" "+width+" "+height);
	   }*/
	   
		   switch(flag) {

		   case 1: 
			   	y=y-ySpeed;
			   	break;
		
		   case 2: //if (!(y >= gameView.getHeight() - height - ySpeed || y + ySpeed <= 0))
			   	y=y+ySpeed;
			   	break;

		   case 3:  //if (x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0)
			   x=x-xSpeed;
		
			   	break;

		   case 4: // if (!(x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0))
			   x=x+xSpeed;
		
			   break;

		   }

		   if(x<0 ) {   x=0; }
		   if(y<0 ) {   y=0; }
		   if(x >= gameView.getWidth()-width/2 ) { 
			   x=gameView.getWidth()-width/2; 
			   }
		   if((y >= gameView.getHeight() - height/2) ) {
			   y=gameView.getHeight() - height/2;
			   }
		   
		   if(x < (gameView.getWidth()-width/2) || y < (gameView.getHeight() - height/2) )
		   update();
		   
		   
		   }
		  
	   
		   

  

    public void onDraw(Canvas canvas) {

    	// update();
    	//x=100;
    //	y=100;

          int srcX = currentFrame * width;

          Rect src = new Rect(srcX, 0, srcX + width,0 + height);

          Rect dst = new Rect(x, y, x + width/2,y + height/2);

          canvas.drawBitmap(bmp, src, dst, null);

    }
    public boolean isRange(float x2, float y2) {
    	
    	double distance = Math.sqrt((x2-(x))*(x2-(x2)) + (y2-(y))*(y2-(y)));
    	
    	System.out.println("hit at distance "+distance+" and points "+ x2+" "+y2+"--- "+x+" "+y);
    	if(distance<100 ){
    		System.out.println("hit");
    		return true;
    	}else
    	{
    		return false;
    	}
		
		//distance = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)); 
		
	}



    


}
