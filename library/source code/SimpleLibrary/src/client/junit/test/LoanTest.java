package client.junit.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class LoanTest {

	@Parameter(0)
	public String t_user_borrowing = "zhibo@carleton.ca,9781442668584,1";
	
	@Parameter(1)
	public String t_user_noloan = "yu@carleton.ca,9781442668584,1";
	
	@Parameter(2)
	public String t_user_overdue = "";
	
	@Test
	public void testReturnNoLoan() 
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The Loan Does Not Exist!", OutputHandler.USER);
		Output result = testOH.returnBook(t_user_noloan);		
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testReturnBorrowing() 
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Success!", OutputHandler.USER);
		Output result = testOH.returnBook(t_user_borrowing);	
		
		assertEquals(expectedOut, result);
	}

}
