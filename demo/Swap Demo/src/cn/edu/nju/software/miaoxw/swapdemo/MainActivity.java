package cn.edu.nju.software.miaoxw.swapdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity
{
	private BallImage[] balls;
	
	public MainActivity()
	{
		balls=new BallImage[25];
	}
	
	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		int startX=20;
		int startY=20;
		int currentX,currentY;
		BallImage newBallImage=null;
		
		FrameLayout layout=(FrameLayout)findViewById(R.id.frameLayout);
		for(int i=0;i<5;i++)
		{
			currentX=startX;
			currentY=startY+i*70;
			
			newBallImage=new BallImage(this,i,0,Colour.getRandomColour());
			Log.v("create","id:"+(5*i));
			newBallImage.setLayoutParams(new ViewGroup.LayoutParams(64,64));
			
			newBallImage.setX(currentX);
			newBallImage.setY(currentY);
			newBallImage.setId(5*i);
			
			balls[5*i]=newBallImage;
			layout.addView(newBallImage);
			for(int j=1;j<5;j++)
			{
				currentX=startX+j*70;
				currentY=startY+i*70;
				
				newBallImage=new BallImage(this,i,j,Colour.getRandomColour());
				Log.v("create","id:"+(5*i+j));
				newBallImage.setLayoutParams(new ViewGroup.LayoutParams(64,64));
				
				newBallImage.setX(currentX);
				newBallImage.setY(currentY);
				newBallImage.setId(i*5+j+1);
				
				balls[5*i+j]=newBallImage;
				layout.addView(newBallImage);
			}
		}
		
		for(BallImage i:balls)
			//To avoid null, must do this here
			i.setNeighbours();
	}
	
	@Override public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}
	
	@Override public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id=item.getItemId();
		if(id==R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public BallImage getSingleBallImage(int id)
	{
		return balls[id];
	}
}
