package cn.edu.nju.software.jksc.symys.algorithm;

import cn.edu.nju.software.jksc.symys.common.Bobble;

/**
 * Created by 缪晓伟 on 2015/6/24.
 */
public class ScoreCalculator
{
	private static final int STEP_BONUS=5000;
	private static final int AXIS_BONUS=10000;
	private static final int TIME_BONUS=215874;
	private static final int MIX_BONUS=3000;

	private static final double STEP_ALPHA=1.15;
	private static final double MIX_ALPHA=2.0/3;
	private static final double TIME_ALPHA=0.85;

	private static final int POINTING_MODE_PRIMARY_BOBBLE=1;
	private static final int POINTING_MODE_MIX_BOBBLE=2;

	public static long calculatePointingModeScore(Bobble[][] src)
	{
		int gridSize=src.length;
		long ret=0;

		//Vertical
		for(int i=0;i<gridSize;i++)
			for(int j=0;j<gridSize/2;j++)
				if(src[i][j].equals(src[i][gridSize-j-1]))
					if(src[i][j].isPrimary())
						ret+=POINTING_MODE_PRIMARY_BOBBLE;
					else
						ret+=POINTING_MODE_MIX_BOBBLE;

		//Horizontal
		for(int i=0;i<gridSize/2;i++)
			for(int j=0;j<gridSize;j++)
				if(src[i][j].equals(src[gridSize-i-1][j]))
					if(src[i][j].isPrimary())
						ret+=POINTING_MODE_PRIMARY_BOBBLE;
					else
						ret+=POINTING_MODE_MIX_BOBBLE;

		//Lean-leftup->rightdown
		for(int i=0;i<gridSize;i++)
			for(int j=i;j<gridSize;j++)
				if(src[i][j].equals(src[j][i]))
					if(src[i][j].isPrimary())
						ret+=POINTING_MODE_PRIMARY_BOBBLE;
					else
						ret+=POINTING_MODE_MIX_BOBBLE;

		//Lean-leftdown->rightup
		for(int i=0;i<gridSize;i++)
			for(int j=0;j<gridSize-i;j++)
				if(src[i][j].equals(src[gridSize-j-1][gridSize-i-1]))
					if(src[i][j].isPrimary())
						ret+=POINTING_MODE_PRIMARY_BOBBLE;
					else
						ret+=POINTING_MODE_MIX_BOBBLE;

		return ret;
	}

	public static long calculateNormalModeScore(int axisCount,int remainStep,int numberOfMix,int elapsedTime)
	{
		long axisScore=0,stepScore=0,mixScore=0,timeScore=0;
		for(int i=1;i<=axisCount;i++)
			axisScore+=i*i*AXIS_BONUS;

		if(remainStep>0)
			stepScore=(long)(Math.pow(STEP_ALPHA,remainStep-1)*STEP_BONUS);
		else
			stepScore=0;

		mixScore=(long)((1-Math.pow(MIX_ALPHA,numberOfMix))/(1-MIX_ALPHA)*MIX_BONUS);

		timeScore=(long)(Math.pow(TIME_ALPHA,elapsedTime)*TIME_BONUS);

		return axisScore+stepScore+mixScore+timeScore;
	}
}
