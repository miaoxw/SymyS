package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Hashtable;
import java.util.Random;

import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
final class Algorithm4x4_1 extends GeneratingAlgorithm
{
	public Algorithm4x4_1()
	{
		super(8);
		base=new int[][]{{1,5,5,1},{2,6,6,2},{3,7,7,3},{4,8,8,4}};
		maxMix=8;
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

		//No solution for odd number
		if(mixCount%2==1)
			throw new ParameterInvalidException("Invalid number of mix");
		else
		{
			while(colorMapping.get(1).equals(colorMapping.get(2)))
			{
<<<<<<< HEAD
				colorMapping.put(2,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(3).equals(colorMapping.get(4)))
			{
				colorMapping.put(4,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(5).equals(colorMapping.get(6)))
			{
				colorMapping.put(6,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(7).equals(colorMapping.get(8)))
			{
				colorMapping.put(8,Bobble.getRandomPrimaryBobble());
=======
				colorMapping.put(2,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(3).equals(colorMapping.get(4)))
			{
				colorMapping.put(4,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(5).equals(colorMapping.get(6)))
			{
				colorMapping.put(6,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(7).equals(colorMapping.get(8)))
			{
				colorMapping.put(8,Bobble.getRandomPrimaryBobble(colorTypeCount));
>>>>>>> 4-Color
			}
		}

		for(int i=0;i<ret.length;i++)
			for(int j=0;j<ret[i].length;j++)
				ret[i][j]=colorMapping.get(base[i][j]).clone();

		return ret;
	}
}
