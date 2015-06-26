package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Hashtable;

import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
final class Algorithm6x6_2 extends GeneratingAlgorithm
{
	public Algorithm6x6_2()
	{
		super(10);
		base=new int[][]{{1,2,3,4,5,1},{2,6,7,8,6,5},{3,7,9,10,8,4},{4,8,10,9,7,3},{5,6,8,7,6,2},{1,5,4,3,2,1}};
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

		//No solution for num-4-multiples
		if(mixCount%4!=0)
			throw new ParameterInvalidException("Invalid number of mix");
		if(mixCount>0)
		{
			while(colorMapping.get(2).equals(colorMapping.get(3)))
			{
				colorMapping.put(3,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(4).equals(colorMapping.get(5)))
			{
				colorMapping.put(5,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(7).equals(colorMapping.get(8)))
			{
				colorMapping.put(8,Bobble.getRandomPrimaryBobble());
			}
		}

		for(int i=0;i<ret.length;i++)
			for(int j=0;j<ret[i].length;j++)
				ret[i][j]=colorMapping.get(base[i][j]).clone();

		return ret;
	}
}
