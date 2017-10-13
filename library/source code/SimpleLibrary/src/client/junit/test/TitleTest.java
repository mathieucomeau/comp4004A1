/*
 * TitleTest class
 * Mathieu Comeau Oct 10 2017
 * 
 * This is a UNIT TEST class that tests the functionalities related to librarians dealing with titles(books)
 */


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
	@Parameter(4)
	public String t_ISBN_loaned = "9781442668584, By the grace of God";		//loaned by Zhibo
	@Parameter(5)	
	public String t_ISBN_delete_fail = "9781611687910";
	@Parameter(6)
	public String t_ISBN_delete_loaned = "9781442616899";		//loaned by Sun
	@Parameter(7)
	public String t_ISBN_delete_success = "3662233554434";
	
	//testing adding a title that already exists
	@Test
	public void testAISBNExists()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The Title Already Exists!", OutputHandler.CLERK);
		Output result = testOH.createTitle(t_ISBN_fail);
		
		assertEquals(expectedOut, result);
	}
	
	//testing improper format
	@Test
	public void testISBNMissingInfo()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number", OutputHandler.CREATETITLE);
		Output result = testOH.createTitle(t_ISBN_missing);
		
		assertEquals(expectedOut, result);
	}
	
	//testing improper format
	@Test
	public void testISBNTooShort()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number", OutputHandler.CREATETITLE);
		Output result = testOH.createTitle(t_ISBN_short);
		
		assertEquals(expectedOut, result);
	}
	
	//testing successful title add
	@Test
	public void testISBNAddSuccess()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Success!", OutputHandler.CLERK);
		Output result = testOH.createTitle(t_ISBN_success);
		
		assertEquals(expectedOut, result);
	}
	
	//testing removal of non-existing title
	@Test
	public void testRemoveNonExistant()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The Title Does Not Exist!", OutputHandler.CLERK);
		Output result = testOH.deleteTitle(t_ISBN_delete_success);
		
		assertEquals(expectedOut, result);
	}
	
	//testing removing a book that's been loaned
	@Test
	public void testRemoveLoaned()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Active Loan Exists!", OutputHandler.CLERK);
		Output result = testOH.deleteTitle(t_ISBN_delete_loaned);
		
		assertEquals(expectedOut, result);
	}
	
	//testing successfully removing a book
	@Test
	public void testRemoveSuccessful()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Success!", OutputHandler.CLERK);
		Output result = testOH.deleteTitle(t_ISBN_delete_fail);
		
		assertEquals(expectedOut, result);
	}

}
