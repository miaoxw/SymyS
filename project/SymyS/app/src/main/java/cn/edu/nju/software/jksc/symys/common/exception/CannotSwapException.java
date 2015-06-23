package cn.edu.nju.software.jksc.symys.common.exception;

import cn.edu.nju.software.jksc.symys.common.Bobble;

/**
 * Created by 缪晓伟 on 2015/6/23.
 */
public class CannotSwapException extends GameLogicException
{
	private Bobble bobble1,bobble2;

	public CannotSwapException(Bobble bobble1,Bobble bobble2)
	{
		this.bobble1=bobble1;
		this.bobble2=bobble2;
	}

	@Override
	public String toString()
	{
		return bobble1+" and "+bobble2+" cannot swap!";
	}
}
