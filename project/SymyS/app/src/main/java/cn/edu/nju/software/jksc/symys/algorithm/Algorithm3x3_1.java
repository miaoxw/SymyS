package cn.edu.nju.software.jksc.symys.algorithm;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
final class Algorithm3x3_1 extends GeneratingAlgorithm
{

	public Algorithm3x3_1()
	{
		base=new int[][]{{1,4,1},{2,5,2},{3,6,3}};
		maxMix=3;
	}

	@Override
	public int[][] generate()
	{
		return base.clone();
	}
}
