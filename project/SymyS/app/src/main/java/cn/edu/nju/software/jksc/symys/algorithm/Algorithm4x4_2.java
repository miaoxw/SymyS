package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Hashtable;

import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
final class Algorithm4x4_2 extends GeneratingAlgorithm
{
	public Algorithm4x4_2()
	{
		super(4);
		base=new int[][]{{1,3,3,1},{2,4,4,2},{2,4,4,2},{1,3,3,1}};
		maxMix=8;
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

		//No solution for mixCount=1,2,3,5,6,7
		if(mixCount>=1&&mixCount<=3)
			throw new ParameterInvalidException("Invalid number of mix");
		if(mixCount>=5&&mixCount<=7)
			throw new ParameterInvalidException("Invalid number of mix");
		if(mixCount==4||mixCount==8)
		{
			while(colorMapping.get(1).equals(colorMapping.get(3)))
			{
				colorMapping.put(3,Bobble.getRandomPrimaryBobble());
			}
			while(colorMapping.get(2).equals(colorMapping.get(4)))
			{
				colorMapping.put(4,Bobble.getRandomPrimaryBobble());
			}
		}
		
		for(int i=0;i<ret.length;i++)
			for(int j=0;j<ret[i].length;j++)
				ret[i][j]=colorMapping.get(base[i][j]);
		
		return ret;
	}
}
