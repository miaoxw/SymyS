package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Hashtable;

import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
final class Algorithm6x6_4 extends GeneratingAlgorithm
{
	public Algorithm6x6_4()
	{
		super(6);
		base=new int[][]{{1,2,3,3,2,1},{2,4,5,5,4,2},{3,5,6,6,5,3},{3,5,6,6,5,3},{2,4,5,5,4,2},{1,2,3,3,2,1}};
		maxMix=8;
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

		//No solution for num-4-multiples
		if(mixCount>=1&&mixCount<=7)
			throw new ParameterInvalidException("Invalid number of mix");
		if(mixCount==8)
		{
			while(colorMapping.get(3).equals(colorMapping.get(5)))
			{
				colorMapping.put(5,Bobble.getRandomPrimaryBobble().getColorID());
			}
		}

		for(int i=0;i<ret.length;i++)
			for(int j=0;j<ret[i].length;i++)
				ret[i][j]=colorMapping.get(ret[i][j]);

		return ret;
	}
}
