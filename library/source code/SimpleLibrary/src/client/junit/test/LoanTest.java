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
	public String t_user_noloan = "michelle@carleton.ca,9781442668584,1";
	
	@Parameter(2)
	public String t_user_notexists = "user@not.exists,9781442668584,1";
	
	@Parameter(3)
	public String t_user_notbook = "yu@carleton.ca,9781442668584,1";
	
	
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
	
	@Test
	public void testBorrowNotAvailable()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The Item is Not Available!", OutputHandler.USER);
		Output result = testOH.borrow(t_user_notbook);	
		Output result2 = testOH.borrow(t_user_notbook);	
		
		assertEquals(expectedOut, result2);
	}
	
	@Test
	public void testBorrowAOutstandingFees()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Outstanding Fee Exists!", OutputHandler.USER);
		Output result = testOH.borrow(t_user_borrowing);	
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testBorrowUserNotExists()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The User Does Not Exist!", OutputHandler.BORROW);
		Output result = testOH.borrow(t_user_notexists);	
		
		assertEquals(expectedOut, result);
	}
	
	
	
	@Test
	public void testBorrowSuccess()
	{
		String t_user_borrow_success1 = "sun@carleton.ca,9781442616899,1";
		
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Success!", OutputHandler.USER);
		Output result = testOH.borrow(t_user_borrow_success1);	
		
		assertEquals(expectedOut, result);
		
	}
	
	@Test
	public void testBorrowLimit()
	{
		OutputHandler testOH = new OutputHandler();
		
		//Output resultRet = testOH.returnBook(t_user_borrowing);
		
		String t_user_borrow_success1 = "sun@carleton.ca,9781442616899,1";
		Output result = testOH.borrow(t_user_borrow_success1);
		
		String t_user_borrow_success2 = "sun@carleton.ca,9781442667181,1";
		Output result1 = testOH.borrow(t_user_borrow_success2);			
		
		String t_user_borrow_success4 = "sun@carleton.ca,9781611687910,1";
		Output result3 = testOH.borrow(t_user_borrow_success4);		
		
		String t_user_borrow_success3 = "sun@carleton.ca,9781442668584,1";
		Output result2 = testOH.borrow(t_user_borrow_success3);	
		
		
		
		Output expectedOut = new Output("The Maximun Number of Items is Reached!", OutputHandler.USER);		
		
		assertEquals(expectedOut, result2);
	}

}
