package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Hashtable;
import java.util.Random;

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
	public Bobble[][] generate(int mixCount,int colorTypeCount) throws ParameterInvalidException
	{
		if(mixCount<0||mixCount>maxMix)
			throw new ParameterInvalidException("Invalid number of mix");

		Bobble[][] ret=new Bobble[base.length][base[0].length];

		Hashtable<Integer,Bobble> colorMapping=new Hashtable<>();
		for(int i=1;i<=colorCount;i++)
			if(i<=colorTypeCount)
				colorMapping.put(i,Bobble.getPrimaryBobbleByID(i));
			else
				colorMapping.put(i,Bobble.getRandomPrimaryBobble(colorTypeCount));

		//Make some changes
		Random random=new Random();
		for(int i=1;i<=100;i++)
		{
			int index1=random.nextInt(colorMapping.size()+1);
			int index2=random.nextInt(colorMapping.size()+1);
			Bobble tempColor=colorMapping.get(index1);
			colorMapping.put(index1,colorMapping.get(index2));
			colorMapping.put(index2,tempColor);
		}

		//No solution for mixCount=1,2,3,5,6,7
		if(mixCount>=1&&mixCount<=3)
			throw new ParameterInvalidException("Invalid number of mix");
		if(mixCount>=5&&mixCount<=7)
			throw new ParameterInvalidException("Invalid number of mix");
		if(mixCount==4||mixCount==8)
		{
			while(colorMapping.get(1).equals(colorMapping.get(3)))
			{
				colorMapping.put(3,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
			while(colorMapping.get(2).equals(colorMapping.get(4)))
			{
				colorMapping.put(4,Bobble.getRandomPrimaryBobble(colorTypeCount));
			}
		}
		
		for(int i=0;i<ret.length;i++)
			for(int j=0;j<ret[i].length;j++)
				ret[i][j]=colorMapping.get(base[i][j]).clone();
		
		return ret;
	}
}
