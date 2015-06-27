package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Hashtable;

import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
final class Algorithm6x6_1 extends GeneratingAlgorithm
{
	public Algorithm6x6_1()
	{
		super(18);
		base=new int[][]{{1,7,13,13,7,1},{2,8,14,14,8,2},{3,9,15,15,9,3},{4,10,16,16,10,4},{5,11,17,17,11,5},{6,12,18,18,12,6}};
		maxMix=18;
	}

	@Override
	public Bobble[][] generate(int mixCount,int colorTypeCount) throws ParameterInvalidException
	{
		if(mixCount<0||mixCount>maxMix)
			throw new ParameterInvalidException("Invalid number of mix");

		Bobble[][] ret=new Bobble[base.length][base[0].length];

		Hashtable<Integer,Bobble> colorMapping=new Hashtable<>();
		for(int i=1;i<=colorCount;i++)
			colorMapping.put(i,Bobble.getRandomPrimaryBobble(colorTypeCount));

		//No solution for odd number
		if(mixCount%2==1)
			throw new ParameterInvalidException("Invalid number of mix");
		else
		{
			while(colorMapping.get(1).equals(colorMapping.get(2)))
			{
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
			}
			while(colorMapping.get(9).equals(colorMapping.get(10)))
			{
				colorMapping.put(10,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(11).equals(colorMapping.get(12)))
			{
				colorMapping.put(12,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(13).equals(colorMapping.get(14)))
			{
				colorMapping.put(14,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(15).equals(colorMapping.get(16)))
			{
				colorMapping.put(16,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(17).equals(colorMapping.get(18)))
			{
				colorMapping.put(18,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
		}

		for(int i=0;i<ret.length;i++)
			for(int j=0;j<ret[i].length;j++)
				ret[i][j]=colorMapping.get(base[i][j]).clone();

		return ret;
	}
}
