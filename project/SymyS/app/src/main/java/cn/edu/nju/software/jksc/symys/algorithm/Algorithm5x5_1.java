package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Hashtable;
import java.util.Random;

import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
final class Algorithm5x5_1 extends GeneratingAlgorithm
{
	public Algorithm5x5_1()
	{
		super(15);
		base=new int[][]{{1,6,11,6,1},{2,7,12,7,2},{3,8,13,8,3},{4,9,14,9,4},{5,10,15,10,5}};
		maxMix=12;
	}

	@Override
<<<<<<< HEAD
	public Bobble[][] generate(int mixCount) throws ParameterInvalidException
=======
	public Bobble[][] generate(int mixCount,int colorTypeCount) throws ParameterInvalidException
>>>>>>> 4-Color
	{
		if(mixCount<0||mixCount>maxMix)
			throw new ParameterInvalidException("Invalid number of mix");

		Bobble[][] ret=new Bobble[base.length][base[0].length];

		Hashtable<Integer,Bobble> colorMapping=new Hashtable<>();
		for(int i=1;i<=colorCount;i++)
<<<<<<< HEAD
			colorMapping.put(i,Bobble.getRandomPrimaryBobble());
=======
			if(i<=colorTypeCount)
				colorMapping.put(i,Bobble.getPrimaryBobbleByID(i));
			else
				colorMapping.put(i,Bobble.getRandomPrimaryBobble(colorTypeCount));

		//Make some changes
		Random random=new Random();
		for(int i=1;i<=100;i++)
		{
			int index1=random.nextInt(colorCount)+1;
			int index2=random.nextInt(colorCount)+1;
			Bobble tempColor=colorMapping.get(index1);
			colorMapping.put(index1,colorMapping.get(index2));
			colorMapping.put(index2,tempColor);
		}
>>>>>>> 4-Color

		if(mixCount>=1&&mixCount<=12)
		{
			while(colorMapping.get(1).equals(colorMapping.get(2)))
			{
<<<<<<< HEAD
				colorMapping.put(2,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(6).equals(colorMapping.get(7)))
			{
				colorMapping.put(7,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(4).equals(colorMapping.get(5)))
			{
				colorMapping.put(5,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(9).equals(colorMapping.get(10)))
			{
				colorMapping.put(10,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(3).equals(colorMapping.get(8)))
			{
				colorMapping.put(8,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(11).equals(colorMapping.get(12)))
			{
				colorMapping.put(12,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(14).equals(colorMapping.get(15)))
			{
				colorMapping.put(15,Bobble.getRandomPrimaryBobble());
=======
				colorMapping.put(2,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(6).equals(colorMapping.get(7)))
			{
				colorMapping.put(7,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(4).equals(colorMapping.get(5)))
			{
				colorMapping.put(5,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(9).equals(colorMapping.get(10)))
			{
				colorMapping.put(10,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(3).equals(colorMapping.get(8)))
			{
				colorMapping.put(8,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(11).equals(colorMapping.get(12)))
			{
				colorMapping.put(12,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(14).equals(colorMapping.get(15)))
			{
				colorMapping.put(15,Bobble.getRandomPrimaryBobble(colorTypeCount));
>>>>>>> 4-Color
			}
		}

		for(int i=0;i<ret.length;i++)
			for(int j=0;j<ret[i].length;j++)
				ret[i][j]=colorMapping.get(base[i][j]).clone();

		return ret;
	}
}
