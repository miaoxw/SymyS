package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Random;

import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;

/**
 * Created by 缪晓伟 on 2015/6/27.
 */
final class AlgorithmPointing extends GeneratingAlgorithm
{
	public AlgorithmPointing()
	{
		super(-1);
		base=null;
		maxMix=Integer.MAX_VALUE;
	}

	@Override
	public Bobble[][] generate(int mixCount,int colorTypeCount) throws ParameterInvalidException
	{
		Bobble[][] ret=new Bobble[6][6];

		for(int i=0;i<6;i++)
			for(int j=0;j<6;j++)
				ret[i][j]=Bobble.getRandomPrimaryBobble(colorTypeCount);

		Random random=new Random();
		int x=random.nextInt(6);
		int y=random.nextInt(6);
		Bobble newBobble=null;
		do
		{
			newBobble=Bobble.getRandomPrimaryBobble(colorTypeCount);
		}while(ret[x][y].equals(newBobble));
		ret[x][y].mixWith(newBobble);

		return ret;
	}
}
