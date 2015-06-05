package cn.edu.nju.software.miaoxw.swapdemo;

import java.util.Random;

public class Colour
{
	private int colour;
	
	public static final Colour RED=new Colour(0);
	public static final Colour YELLOW=new Colour(1);
	public static final Colour BLUE=new Colour(2);
	public static final Colour ORANGE=new Colour(3);
	public static final Colour GREEN=new Colour(4);
	public static final Colour PURPLE=new Colour(5);
	
	private static final Random RANDOM=new Random();
	
	private Colour(int colourID)
	{
		colour=colourID;
	}
	
	public static Colour getRandomColour()
	{
		return new Colour(RANDOM.nextInt(6));
	}
	
	public boolean isPrimaryColour()
	{
		return this.equals(RED)||this.equals(YELLOW)||this.equals(BLUE);
	}

	@Override public boolean equals(Object o)
	{
		if(o==null)
			return false;
		if(o instanceof Colour&&((Colour)o).colour==this.colour)
			return true;
		else
			return false;
	}
}
