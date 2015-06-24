package cn.edu.nju.software.jksc.symys.algorithm;

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

	public static long calculateScore(int axisCount,int remainStep,int numberOfMix,int elapsedTime)
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
