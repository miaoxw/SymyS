package cn.edu.nju.software.jksc.symys.algorithm;

import cn.edu.nju.software.jksc.symys.common.Bobble;

/**
 * Created by 缪晓伟 on 2015/6/20.
 */
public class AxisChecker
{
	public static int countAxis(Bobble[][] src)
	{
		int ret=0;

		if(checkVertical(src))
			ret++;
		if(checkHorizontal(src))
			ret++;
		if(checkLean1(src))
			ret++;
		if(checkLean2(src))
			ret++;

		return ret;
	}

	public static boolean checkVertical(Bobble[][] src)
	{
		int gridSize=src.length;
		for(int i=0;i<gridSize;i++)
			for(int j=0;j<gridSize/2;j++)
				if(!src[i][j].equals(src[i][gridSize-j]))
					return false;
		return true;
	}

	public static boolean checkHorizontal(Bobble[][] src)
	{
		int gridSize=src.length;
		for(int i=0;i<gridSize/2;i++)
			for(int j=0;j<gridSize;j++)
				if(!src[i][j].equals(src[gridSize-i][j]))
					return false;
		return true;
	}

	//Lean-leftup->rightdown
	public static boolean checkLean1(Bobble[][] src)
	{
		int gridSize=src.length;
		for(int i=0;i<gridSize;i++)
			for(int j=i;j<gridSize;j++)
				if(!src[i][j].equals(src[j][i]))
					return false;
		return true;
	}

	//Lean-leftdown->rightup
	public static boolean checkLean2(Bobble[][] src)
	{
		int gridSize=src.length;
		for(int i=0;i<gridSize;i++)
			for(int j=0;j<i;j++)
				if(!src[i][j].equals(src[gridSize-j-1][gridSize-i-1]))
					return false;
		return true;
	}
}
