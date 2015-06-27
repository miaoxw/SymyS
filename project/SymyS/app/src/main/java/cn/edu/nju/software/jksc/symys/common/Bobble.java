package cn.edu.nju.software.jksc.symys.common;

import java.util.Random;

import cn.edu.nju.software.jksc.symys.R;
import cn.edu.nju.software.jksc.symys.common.exception.CannotMixException;
import cn.edu.nju.software.jksc.symys.common.exception.CannotSwapException;

/**
 * Created by 缪晓伟 on 2015/6/16.
 */
public class Bobble implements Cloneable
{
	private int color;

	private static final Random random=new Random();

	public static final int RED_INDEX=1;
	public static final int YELLOW_INDEX=2;
	public static final int BLUE_INDEX=4;
	public static final int NEW_COLOR_INDEX=8;
	public static final int ORANGE_INDEX=3;
	public static final int PURPLE_INDEX=5;
	public static final int GREEN_INDEX=6;
	public static final int BLOCK_INDEX=-1;
	public static final int BLANK_INDEX=0;
	private Bobble(int color)
	{
		this.color=color;
	}

	/**
	 * This method generates a new bobble for the UI.
	 * Notice that blank bobble or blocked bobble will not be generated by this method.
	 *
	 * @return A random new bobble instance
	 */
	public static Bobble getRandomBobble()
	{
		return new Bobble(random.nextInt(6)+1);
	}

	public static Bobble getBlockedBobble()
	{
		return new Bobble(BLOCK_INDEX);
	}

	public static Bobble getRandomPrimaryBobble()
	{
		return new Bobble(1<<random.nextInt(3));
	}

	public static Bobble getPrimaryBobbleByID(int primaryColorID)
	{
		return new Bobble(1<<(primaryColorID-1));
	}

	public static Bobble getRandomPrimaryBobble(int colorCount)
	{
		return new Bobble(1<<random.nextInt(colorCount));
	}

	private String getColorName()
	{
		switch(color)
		{
			case BLOCK_INDEX:
				return "Blocked";
			case BLANK_INDEX:
				return "Black";
			case RED_INDEX:
				return "Red";
			case YELLOW_INDEX:
				return "Yellow";
			case ORANGE_INDEX:
				return "Orange";
			case BLUE_INDEX:
				return "Blue";
			case PURPLE_INDEX:
				return "Purple";
			case GREEN_INDEX:
				return "Green";
			case NEW_COLOR_INDEX:
				return "New color";
			default:
				return "Unknown";
		}
	}

	public int getColorID()
	{
		return color;
	}

	public int getPicID()
	{
		switch(color)
		{
			case BLOCK_INDEX:
				return 0;
			case BLANK_INDEX:
				return 0;
			case RED_INDEX:
				return R.drawable.red;
			case YELLOW_INDEX:
				return R.drawable.yellow;
			case ORANGE_INDEX:
				return R.drawable.orange;
			case BLUE_INDEX:
				return R.drawable.blue;
			case PURPLE_INDEX:
				return R.drawable.purple;
			case GREEN_INDEX:
				return R.drawable.green;
			case NEW_COLOR_INDEX:
				return R.drawable.new_color;
			default:
				return 0;
		}
	}

	public boolean isPrimary()
	{
		return color==RED_INDEX||color==YELLOW_INDEX||color==BLUE_INDEX;
	}

	public boolean isBobble()
	{
		return color>0&&color<=6;
	}

	public boolean swapWith(Bobble anotherBobble)// throws CannotSwapException
	{
		if(isBobble()&&anotherBobble.isBobble())
		{
			int color=this.color;
			this.color=anotherBobble.color;
			anotherBobble.color=color;
			return true;
		}
		else
			return false;
	}

	/**
	 * Notice: This method will cause some side effects to the parameter.
	 *
	 * @param anotherBobble Another bobble that participates in the mixing.
	 * @return true if the mix succeeds.
	 * @throws CannotMixException When any one of the bobbles cannot participate in the mixing, this exception is thrown.
	 */
	public boolean mixWith(Bobble anotherBobble)// throws CannotMixException
	{
		if(isPrimary()&&anotherBobble.isPrimary())
		{
			this.color|=anotherBobble.color;
			anotherBobble.color=BLANK_INDEX;
			return true;
		}
		else
			return false;
	}

	@Override
	public String toString()
	{
		return super.toString()+" state:"+getColorName();
	}

	@Override
	public boolean equals(Object o)
	{
		if(o==null)
			return false;
		if(o instanceof Bobble&&((Bobble)o).color==this.color)
			return true;
		else
			return false;
	}

	@Override
	public Bobble clone()
	{
		return new Bobble(this.color);
	}
}
