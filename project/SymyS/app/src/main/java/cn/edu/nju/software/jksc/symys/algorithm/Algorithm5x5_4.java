package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Hashtable;

import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
final class Algorithm5x5_4 extends GeneratingAlgorithm
{
	public Algorithm5x5_4()
	{
		super(6);
		base=new int[][]{{1,2,3,2,1},{2,4,5,4,2},{3,5,6,5,3},{2,4,5,4,2},{1,2,3,2,1}};
		maxMix=12;
	}

	@Override
	public int[][] generate(int mixCount) throws ParameterInvalidException
	{
		if(mixCount<0||mixCount>maxMix)
			throw new ParameterInvalidException("Invalid number of mix");

		int[][] ret=base.clone();

		Hashtable<Integer,Integer> colorMapping=new Hashtable<>();
		for(int i=1;i<=colorCount;i++)
			colorMapping.put(i,Bobble.getRandomPrimaryBobble().getColorID());

		//No solution for non-4-multiples
		if(mixCount%4!=0)
			throw new ParameterInvalidException("Invalid number of mix");
		if(mixCount!=0)
		{
			if(mixCount>4)
				colorMapping.put(3,colorMapping.get(1));

			while(colorMapping.get(1).equals(colorMapping.get(2)))
			{
				colorMapping.put(2,Bobble.getRandomPrimaryBobble().getColorID());
			}
			while(colorMapping.get(3).equals(colorMapping.get(5)))
			{
				colorMapping.put(5,Bobble.getRandomPrimaryBobble().getColorID());
			}
		}

		for(int i=0;i<ret.length;i++)
			for(int j=0;j<ret[i].length;j++)
				ret[i][j]=colorMapping.get(ret[i][j]);

		return ret;
	}
}
