package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Hashtable;

import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
final class Algorithm3x3_4 extends GeneratingAlgorithm
{
	public Algorithm3x3_4()
	{
		super(3);
		base=new int[][]{{1,2,1},{2,3,2},{1,2,1}};
		maxMix=4;
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

		//No solution for 1<=mixCount<=3
		if(mixCount>=1&&mixCount<=3)
			throw new ParameterInvalidException("Invalid number of mix");
		if(mixCount==4)
		{
			while(colorMapping.get(1).equals(colorMapping.get(2)))
			{
				colorMapping.put(2,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
		}

		for(int i=0;i<ret.length;i++)
			for(int j=0;j<ret[i].length;j++)
				ret[i][j]=colorMapping.get(base[i][j]).clone();

		return ret;
	}
}
