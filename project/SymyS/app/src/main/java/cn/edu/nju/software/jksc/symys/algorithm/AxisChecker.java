package cn.edu.nju.software.jksc.symys.algorithm;

import cn.edu.nju.software.jksc.symys.common.Bobble;

/**
 * Created by 缪晓伟 on 2015/6/20.
 */
public class AxisChecker
{
	public static int check(Bobble[][] src)
	{
		int ret=0;
		boolean checkFlag;

		int gridSize=src.length;
		//Horizontal
		checkFlag=true;
horizontalCheck:
		for(int i=0;i<gridSize/2;i++)
			for(int j=0;j<gridSize;j++)
				if(!src[i][j].equals(src[gridSize-i][j]))
				{
					checkFlag=false;
					break horizontalCheck;
				}
		if(checkFlag)
			ret++;

		//Vertical
		checkFlag=true;
verticalCheck:
		for(int i=0;i<gridSize;i++)
			for(int j=0;j<gridSize/2;j++)
				if(!src[i][j].equals(src[i][gridSize-j]))
				{
					checkFlag=false;
					break verticalCheck;
				}
		if(checkFlag)
			ret++;

		//Lean-leftup->rightdown
		checkFlag=true;
leanCheck1:
		for(int i=0;i<gridSize;i++)
			for(int j=i;j<gridSize;j++)
				if(!src[i][j].equals(src[j][i]))
				{
					checkFlag=false;
					break leanCheck1;
				}
		if(checkFlag)
			ret++;

		//Lean-leftdown->rightup
		checkFlag=true;
leanCheck2:
		for(int i=0;i<gridSize;i++)
			for(int j=0;j<i;j++)
				if(!src[i][j].equals(src[gridSize-j-1][gridSize-i-1]))
				{
					checkFlag=false;
					break leanCheck2;
				}
		if(checkFlag)
			ret++;

		return ret;
	}
}
