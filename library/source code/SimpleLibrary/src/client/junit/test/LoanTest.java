/*
 * LoanTest class
 * Mathieu Comeau Oct 10 2017
 * 
 * This is a UNIT TEST class that tests the functionalities related to librarians dealing with loaning/returning books
 */


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
	public String t_user_notbook = "yu@carleton.ca,9781611687910,1";
	
	@Parameter(4)
	public String t_user_borrowing2 = "zhibo@carleton.ca,9781442616899,1";
	
	//testing a user returning a book who does not have a loan outstanding
	@Test
	public void testReturnNoLoan() 
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The Loan Does Not Exist!", OutputHandler.USER);
		Output result = testOH.returnBook(t_user_noloan);		
		
		assertEquals(expectedOut, result);
	}
	
	//testing a user who successfully returns a borrowed book
	@Test
	public void testReturnBorrowing() 
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Success!", OutputHandler.USER);
		Output preResult = testOH.borrow("kevin@carleton.ca,9781442667181,1");		
		Output result = testOH.returnBook("kevin@carleton.ca,9781442667181,1");	
		
		assertEquals(expectedOut, result);
	}
	
	//testing a user trying to borrow a book that's already been lent out
	@Test
	public void testBorrowNotAvailable()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The Item is Not Available!", OutputHandler.USER);
		Output result = testOH.borrow(t_user_notbook);	
		
		assertEquals(expectedOut, result);
	}
	
	//testing a user who tries to borrow but has outstanding fees
	@Test
	public void testBorrowAOutstandingFees()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Outstanding Fee Exists!", OutputHandler.USER);
		//Output preresult = testOH.borrow(t_user_borrowing2);	
		Output result = testOH.borrow("zhibo@carleton.ca,9781317594277,1");	
		//Output postresult = testOH.returnBook(t_user_borrowing2);
		
		assertEquals(expectedOut, result);
	}
	
	//testing a user trying to borrow from a user who does not exist
	@Test
	public void testBorrowUserNotExists()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The User Does Not Exist!", OutputHandler.BORROW);
		Output result = testOH.borrow(t_user_notexists);	
		
		assertEquals(expectedOut, result);
	}
	
	//testing a user successfully borrowing a book
	@Test
	public void testBorrowSuccess()
	{
		String t_user_borrow_success1 = "sun@carleton.ca,9781442616899,1";
		
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Success!", OutputHandler.USER);
		Output result = testOH.borrow(t_user_borrow_success1);	
		
		assertEquals(expectedOut, result);
		
	}
	
	//testing a user trying to borrow more books than the limit allows
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
				
		String t_user_borrow_success3 = "sun@carleton.ca,9781317594277,1";
		Output result2 = testOH.borrow(t_user_borrow_success3);	
		
		Output postresult = testOH.returnBook(t_user_borrow_success3);		
		
		Output expectedOut = new Output("The Maximun Number of Items is Reached!", OutputHandler.USER);		
		
		assertEquals(expectedOut, result2);
	}
	
	//testing the renewal of a loan that does not exist
	@Test
	public void testRenewLoanNotExist()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The loan does not exist!", OutputHandler.USER);
		Output result = testOH.renew(t_user_noloan);		
		
		assertEquals(expectedOut, result);
	}
	
	//testing a user trying to borrow over the limit of books
	@Test
	public void testRenewBookLimit()
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
		
		String t_user_borrow_success5 = "sun@carleton.ca,9781611687910,1";
		Output result5 = testOH.renew(t_user_borrow_success4);				
		
		
		Output expectedOut = new Output("The Maximun Number of Items is Reached!", OutputHandler.USER);		
		
		assertEquals(expectedOut, result5);
	}
	
	//testing a user trying to renew a book more than once
	@Test
	public void testRenewMoreThanOnce()
	{
		OutputHandler testOH = new OutputHandler();
		String t_user_borrow_success1 = "sun@carleton.ca,9781442616899,1";
		Output preResult = testOH.returnBook(t_user_borrow_success1);
		String t_user_borrow_success4 = "yu@carleton.ca,9781442616899,1";
		Output result3 = testOH.borrow(t_user_borrow_success4);	
		Output result5 = testOH.renew(t_user_borrow_success4);	
		Output result6 = testOH.renew(t_user_borrow_success4);	
		
		Output expectedOut = new Output("Renewed Item More Than Once for the Same Loan!", OutputHandler.USER);		
		
		assertEquals(expectedOut, result6);
	}
	
	//testing a user who has outstanding fees trying to renew
	@Test
	public void testRenewOutstandingFees()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Outstanding Fee Exists!", OutputHandler.USER);
		Output result = testOH.renew(t_user_borrowing);		
		
		assertEquals(expectedOut, result);
	}
	
	//testing the successful renewal of a book
	@Test
	public void testRenewSuccessful()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Success!", OutputHandler.USER);
		String t_user_borrow_success4 = "sun@carleton.ca,9781442667181,1";
		String t_user_borrow_success3 = "sun@carleton.ca,9781611687910,1";
		Output preResult = testOH.returnBook(t_user_borrow_success3);
		Output result3 = testOH.renew(t_user_borrow_success4);	
		
		assertEquals(expectedOut, result3);
	}

}
