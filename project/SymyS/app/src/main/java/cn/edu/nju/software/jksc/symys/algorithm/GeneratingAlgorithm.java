package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Random;

import cn.edu.nju.software.jksc.symys.common.Bobble;
import cn.edu.nju.software.jksc.symys.common.exception.ParameterInvalidException;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
abstract class GeneratingAlgorithm
{
	protected int[][] base;
	protected int maxMix;

	protected int colorCount;

	private static Random random=new Random();

	public abstract Bobble[][] generate(int mixCount) throws ParameterInvalidException;

	public GeneratingAlgorithm(int colorCount)
	{
		this.colorCount=colorCount;
	}

	public static Bobble[][] swap(Bobble[][] src,int step)
	{
		int swapIndex=src[0].length;
		for(int i=1;i<=step*1.5;i++)
		{
			int swapX=random.nextInt(swapIndex);
			int swapY=random.nextInt(swapIndex);
			if(random.nextFloat()>0.5f)
			{
				Bobble temp=src[swapX][swapY];
				src[swapX][swapY]=src[swapX+1][swapY];
				src[swapX+1][swapY]=temp;
			}
			else
			{
				Bobble temp=src[swapX][swapY];
				src[swapX][swapY]=src[swapX][swapY+1];
				src[swapX][swapY+1]=temp;
			}
		}
		return src;
	}
}
