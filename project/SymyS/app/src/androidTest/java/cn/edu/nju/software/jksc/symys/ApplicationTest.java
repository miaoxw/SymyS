package cn.edu.nju.software.jksc.symys;

import android.app.Application;
import android.test.ApplicationTestCase;

import cn.edu.nju.software.jksc.symys.algorithm.MapGenerator;
import cn.edu.nju.software.jksc.symys.common.Bobble;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application>
{
	public ApplicationTest()
	{
		super(Application.class);

        Bobble[][] bobbles = MapGenerator.generate(3, 1, 1, 4);
        assert bobbles.length !=0;
	}
}