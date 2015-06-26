package cn.edu.nju.software.jksc.symys.algorithm;

import cn.edu.nju.software.jksc.symys.common.Bobble;
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
		algorithmMatrix[3][1]=new Algorithm3x3_1();
		algorithmMatrix[3][2]=new Algorithm3x3_2();
		algorithmMatrix[3][4]=new Algorithm3x3_4();
		algorithmMatrix[4][1]=new Algorithm4x4_1();
		algorithmMatrix[4][2]=new Algorithm4x4_2();
		algorithmMatrix[4][4]=new Algorithm4x4_4();
		algorithmMatrix[5][1]=new Algorithm5x5_1();
		algorithmMatrix[5][2]=new Algorithm5x5_2();
		algorithmMatrix[5][4]=new Algorithm5x5_4();
		algorithmMatrix[6][1]=new Algorithm6x6_1();
		algorithmMatrix[6][2]=new Algorithm6x6_2();
		algorithmMatrix[6][4]=new Algorithm6x6_4();
	}

	public static Bobble[][] generate(int gridSize,int numOfAxis,int numOfMix,int step)
	{
		if(gridSize<3||gridSize>6||numOfAxis<1||numOfAxis>4)
			return null;

		if(numOfAxis==3)
			numOfAxis=4;

		Bobble[][] ret;
		do
		{
			try
			{
				ret=algorithmMatrix[gridSize][numOfAxis].generate(numOfMix);
			}
			catch(ParameterInvalidException e)
			{
				e.printStackTrace();
				return null;
			}
		//In case of initial symmetrical situation
		}while(AxisChecker.countAxis(ret)==numOfAxis);

		//For redundancy
		algorithmMatrix[gridSize][numOfAxis].swap(ret,(int)(step/1.5f));

		return ret;
	}
}
