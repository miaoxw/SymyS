package cn.edu.nju.software.jksc.symys.common.exception;

/**
 * Created by 缪晓伟 on 2015/6/18.
 */
public class ParameterInvalidException extends GameLogicException
{
	private String errorMessage;

	public ParameterInvalidException(String errorMessage)
	{
		this.errorMessage=errorMessage;
	}

	@Override
	public String toString()
	{
		return super.toString()+" "+errorMessage;
	}
}
