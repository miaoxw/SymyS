package cn.edu.nju.software.jksc.symys.algorithm;

import java.util.Random;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
abstract class GeneratingAlgorithm
{
	int[][] base;
	int maxMix;

	private Random random=new Random();

	public abstract int[][] generate();

	private int[][] swap(int step)
	{
		int swapIndex=base[0].length;
		for(int i=1;i<=step*1.5;i++)
		{
			int swapX=random.nextInt(swapIndex);
			int swapY=random.nextInt(swapIndex);
			if(random.nextFloat()>0.5f)
			{
				int temp=base[swapX][swapY];
				base[swapX][swapY]=base[swapX+1][swapY];
				base[swapX+1][swapY]=temp;
			}
			else
			{
				int temp=base[swapX][swapY];
				base[swapX][swapY]=base[swapX][swapY+1];
				base[swapX][swapY+1]=temp;
			}
		}
		return base.clone();
	}
}
