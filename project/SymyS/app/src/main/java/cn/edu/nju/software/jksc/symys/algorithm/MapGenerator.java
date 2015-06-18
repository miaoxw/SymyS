package cn.edu.nju.software.jksc.symys.algorithm;

import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
public class MapGenerator
{
	private static GeneratingAlgorithm[][] algorithmMatrix;

	static
	{
		algorithmMatrix=new GeneratingAlgorithm[7][5];
		algorithmMatrix[3][1]=(GeneratingAlgorithm)new Algorithm3x3_1();
	}

	public static int[][] generate(int gridSize,int numOfAxis,int numOfMix,int step) throws ParameterInvalidException
	{
		if(gridSize<3||gridSize>6||numOfAxis<1||numOfAxis>4)
			throw new ParameterInvalidException("grid size: "+gridSize+", number of axis: "+numOfAxis);

		if(numOfAxis==3)
			numOfAxis=4;

		return null;
	}
}
