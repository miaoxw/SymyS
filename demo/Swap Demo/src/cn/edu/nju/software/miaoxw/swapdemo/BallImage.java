package cn.edu.nju.software.miaoxw.swapdemo;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class BallImage extends ImageView
{
	private int lineNo;
	private int rowNo;
	private Colour colour;
	private MainActivity parent;
	
	private BallImage upBallImage;
	private BallImage downBallImage;
	private BallImage leftBallImage;
	private BallImage rightBallImage;
	
	private float startTouchX;
	private float startTouchY;
	private float moveTouchX;
	private float moveTouchY;
	
	public BallImage(Context context,int lineNo,int rowNo)
	{
		super(context);
		
		this.lineNo=lineNo;
		this.rowNo=rowNo;
		parent=(MainActivity)context;
		
		setScaleType(ScaleType.FIT_XY);
		
		this.setColour(Colour.RED);
	}
	
	public BallImage(Context context,int lineNo,int rowNo,Colour colour)
	{
		super(context);
		
		this.lineNo=lineNo;
		this.rowNo=rowNo;
		parent=(MainActivity)context;
		
		setScaleType(ScaleType.FIT_XY);
		
		this.setColour(colour);
	}
	
	@Override public boolean onTouchEvent(MotionEvent event)
	{
		switch(event.getActionMasked())
		{
			case MotionEvent.ACTION_DOWN:
				this.zoom();
				Log.v("flip","ACTION_DOWN!");
				
				startTouchX=event.getX();
				startTouchY=event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				Log.v("flip","ACTION_MOVE!");
				
				moveTouchX=event.getX();
				moveTouchY=event.getY();
				
				if(upBallImage!=null)
					upBallImage.resetSize();
				if(downBallImage!=null)
					downBallImage.resetSize();
				if(leftBallImage!=null)
					leftBallImage.resetSize();
				if(rightBallImage!=null)
					rightBallImage.resetSize();
				
				float deltaX=moveTouchX-startTouchX;
				float deltaY=moveTouchY-startTouchY;
				
				if(Math.abs(deltaX)>Math.abs(deltaY)&&Math.abs(deltaX)>32)
				{
					if(Math.signum(deltaX)>0)// right
					{
						if(rightBallImage!=null)
							rightBallImage.zoom();
					}
					else
					{
						if(leftBallImage!=null)
							leftBallImage.zoom();
					}
				}
				else
					if(Math.abs(deltaX)<Math.abs(deltaY)&&Math.abs(deltaY)>32)
					{
						if(Math.signum(deltaY)>0)// down
						{
							if(downBallImage!=null)
								downBallImage.zoom();
						}
						else
						{
							if(upBallImage!=null)
								upBallImage.zoom();
						}
					}
				break;
			case MotionEvent.ACTION_UP:
				this.resetSize();
				if(upBallImage!=null)
					upBallImage.resetSize();
				if(downBallImage!=null)
					downBallImage.resetSize();
				if(leftBallImage!=null)
					leftBallImage.resetSize();
				if(rightBallImage!=null)
					rightBallImage.resetSize();
				
				Log.v("flip","ACTION_UP!");
				
				processAction(startTouchX,startTouchY,moveTouchX,moveTouchY);
				break;
			// 这个case不会到达，因为在放开手指之前永远不会触发到OUTSIDE的条件
			case MotionEvent.ACTION_OUTSIDE:
				this.setScaleX(1.0f);
				this.setScaleY(1.0f);
				Log.v("flip","ACTION_OUTSIDE!");
				break;
			default:
				break;
		}
		return true;
	}
	
	public boolean setCoordinate(Pair<Integer,Integer> newCoordinate)
	{
		lineNo=newCoordinate.first;
		rowNo=newCoordinate.second;
		return true;
	}
	
	public Pair<Integer,Integer> getCoordinate()
	{
		return new Pair<Integer,Integer>(lineNo,rowNo);
	}
	
	public Colour getColour()
	{
		return colour;
	}
	
	public void setNeighbours()
	{
		if(lineNo-1>=0)// up
			upBallImage=parent.getSingleBallImage(5*(lineNo-1)+rowNo);
		if(lineNo+1<=4)// down
			downBallImage=parent.getSingleBallImage(5*(lineNo+1)+rowNo);
		if(rowNo-1>=0)// left
			leftBallImage=parent.getSingleBallImage(5*lineNo+rowNo-1);
		if(rowNo+1<=4)// right
			rightBallImage=parent.getSingleBallImage(5*lineNo+rowNo+1);
	}
	
	public void setColour(Colour colour)
	{
		this.colour=colour;
		if(this.colour.equals(Colour.RED))
			this.setImageDrawable(getResources().getDrawable(R.drawable.red));
		if(this.colour.equals(Colour.YELLOW))
			this.setImageDrawable(getResources().getDrawable(R.drawable.yellow));
		if(this.colour.equals(Colour.BLUE))
			this.setImageDrawable(getResources().getDrawable(R.drawable.blue));
		if(this.colour.equals(Colour.ORANGE))
			this.setImageDrawable(getResources().getDrawable(R.drawable.orange));
		if(this.colour.equals(Colour.GREEN))
			this.setImageDrawable(getResources().getDrawable(R.drawable.green));
		if(this.colour.equals(Colour.PURPLE))
			this.setImageDrawable(getResources().getDrawable(R.drawable.purple));
	}
	
	public void resetSize()
	{
		this.setScaleX(1.0f);
		this.setScaleY(1.0f);
	}
	
	public void zoom()
	{
		this.setScaleX(1.2f);
		this.setScaleY(1.2f);
	}
	
	private void processAction(float startX,float startY,float endX,float endY)
	{
		float deltaX=endX-startX;
		float deltaY=endY-startY;
		
		if(Math.abs(deltaX)>32||Math.abs(deltaY)>32)
			// Move clear enough
			if(Math.abs(deltaX)>Math.abs(deltaY))
			{
				if(Math.signum(deltaX)>0)// right
				{
					if(rightBallImage!=null)
						this.swapBallWith(rightBallImage);
				}
				else
					// left
					if(leftBallImage!=null)
						this.swapBallWith(leftBallImage);
			}
			else
			{
				if(Math.signum(deltaY)>0)// down
				{
					if(downBallImage!=null)
						this.swapBallWith(downBallImage);
				}
				else
					// up
					if(upBallImage!=null)
						this.swapBallWith(upBallImage);
			}
	}
	
	private void swapBallWith(final BallImage anotherBallImage)
	{
		float originalX=this.getX();
		float originalY=this.getY();
		float destinationX=anotherBallImage.getX();
		float destinationY=anotherBallImage.getY();
		
		// Set animation
		Animation translateAnimation1=new TranslateAnimation(0,destinationX-originalX,0,destinationY-originalY);
		translateAnimation1.setDuration(300);
		Animation translateAnimation2=new TranslateAnimation(0,originalX-destinationX,0,originalY-destinationY);
		translateAnimation2.setDuration(300);
		this.startAnimation(translateAnimation1);
		anotherBallImage.startAnimation(translateAnimation2);
		
		// Swap balls
		translateAnimation1.setAnimationListener(new AnimationListener()
		{
			
			@Override public void onAnimationStart(Animation animation)
			{
			}
			
			@Override public void onAnimationRepeat(Animation animation)
			{
			}
			
			@Override public void onAnimationEnd(Animation animation)
			{
				BallImage other=anotherBallImage;
				
				BallImage.this.clearAnimation();
				other.clearAnimation();
				
				Colour meColour=colour;
				BallImage.this.setColour(other.colour);
				other.setColour(meColour);
			}
		});
	}
}
