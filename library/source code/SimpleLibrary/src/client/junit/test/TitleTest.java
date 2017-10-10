package client.junit.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class TitleTest {

	@Parameter(0)
	public String t_ISBN_success = "3453453456789, Failing Test Case For Dummies";
	@Parameter(1)	
	public String t_ISBN_fail = "9781442616899, Dante's lyric poetry";
	@Parameter(2)
	public String t_ISBN_missing = "8888888888888";	//missing title
	@Parameter(3)
	public String t_ISBN_short = "888888888888, This ISBN is too short";	//12 numbers instead of 13
	
	public TitleTest()
	{
		
	}
	
	@Test
	public void testISBNExists()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The Title Already Exists!", 2);
		Output result = testOH.createTitle(t_ISBN_fail);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testISBNMissingInfo()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number", 5);
		Output result = testOH.createTitle(t_ISBN_missing);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testISBNTooShort()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number", 5);
		Output result = testOH.createTitle(t_ISBN_short);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testISBNAddSuccess()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Success!", 2);
		Output result = testOH.createTitle(t_ISBN_success);
		
		assertEquals(expectedOut, result);
	}

}
