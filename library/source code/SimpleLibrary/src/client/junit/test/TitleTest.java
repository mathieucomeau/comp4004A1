package client.junit.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class TitleTest {

	@Parameter(0)
	public String t_ISBN_success = "66667772288554, Failing Test Case For Dummies";
	@Parameter(1)	
	public String t_ISBN_fail = "9781442616899, Dante's lyric poetry";
	
	public TitleTest()
	{
		
	}
	
	@Test
	public void testISBNExists() {
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The Title Already Exists!", 2);
		Output result = testOH.createTitle(t_ISBN_fail);
		
		assertEquals(expectedOut, result);
	}

	@Test
	public void testLookup() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTitleTable() {
		fail("Not yet implemented");
	}

}
