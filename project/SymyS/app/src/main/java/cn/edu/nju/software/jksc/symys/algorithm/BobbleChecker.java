package cn.edu.nju.software.jksc.symys.algorithm;

import cn.edu.nju.software.jksc.symys.common.Bobble;

/**
 * Created by 缪晓伟 on 2015/6/29.
 */
public class BobbleChecker
{
	public static int countMixBobble(Bobble[][] src)
	{
		int ret=0;
		for(int i=0;i<src.length;i++)
			for(int j=0;j<src[i].length;j++)
				if(src[i][j].isMixed())
					ret++;

		return ret;
	}
}
