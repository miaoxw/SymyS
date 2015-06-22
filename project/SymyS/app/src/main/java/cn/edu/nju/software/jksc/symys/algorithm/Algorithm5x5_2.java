package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Hashtable;

import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
final class Algorithm5x5_2 extends GeneratingAlgorithm
{
	public Algorithm5x5_2()
	{
		super(9);
		base=new int[][]{{1,3,5,3,1},{2,4,8,4,2},{6,7,9,7,6},{2,4,8,4,2},{1,3,5,3,1}};
		maxMix=12;
	}

	@Override
	public Bobble[][] generate(int mixCount) throws ParameterInvalidException
	{
		if(mixCount<0||mixCount>maxMix)
			throw new ParameterInvalidException("Invalid number of mix");

		Bobble[][] ret=new Bobble[base.length][base[0].length];

		Hashtable<Integer,Bobble> colorMapping=new Hashtable<>();
		for(int i=1;i<=colorCount;i++)
			colorMapping.put(i,Bobble.getRandomPrimaryBobble());

		//No solution for odd mixCount
		if(mixCount%2==1)
			throw new ParameterInvalidException("Invalid number of mix");
		//mixCount=2,4,6,8,10,12
		if(mixCount!=0)
		{
			while(colorMapping.get(1).equals(colorMapping.get(2)))
			{
				colorMapping.put(2,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(3).equals(colorMapping.get(4)))
			{
				colorMapping.put(4,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(6).equals(colorMapping.get(7)))
			{
				colorMapping.put(7,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(5).equals(colorMapping.get(8)))
			{
				colorMapping.put(8,Bobble.getRandomPrimaryBobble());
			}
		}

		for(int i=0;i<ret.length;i++)
			for(int j=0;j<ret[i].length;j++)
				ret[i][j]=colorMapping.get(base[i][j]);

		return ret;
	}
}
