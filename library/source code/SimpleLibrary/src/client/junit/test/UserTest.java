package client.junit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.model.User;
import server.logic.tables.UserTable;

public class UserTest {

	@Parameter(0)
	public String t_user_exists = "zhibo@carleton.ca, zhibo";	
	
	@Parameter(1)
	public String t_user_notexist = "mathieu@carleton.ca, mathieu";
	
	@Parameter(2)
	public String t_user_invalid = "invalid_input@email.com";
	
	@Parameter(3)
	public String t_user_not_email = "invalid_input, password";
	
	@Parameter(5)
	public String t_user_delete_fees = "zhibo@carleton.ca";	
	
	@Parameter(5)
	public String t_user_delete_notexist = "mathieu@carleton.ca";
	
	@Parameter(6)
	public String t_user_delete_success = "yu@carleton.ca";
	
	@Test
	public void testUserExists() 
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The User Already Exists!", OutputHandler.CLERK);
		Output result = testOH.createUser(t_user_exists);		
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testUserNotExists() 
	{
		OutputHandler testOH = new OutputHandler();
		//Output expectedOut = new Output("Success!", OutputHandler.CLERK);
		Output result = testOH.createUser(t_user_notexist);
		List<User> t_table = UserTable.getInstance().getUserTable();
		
		//test the user ID to see if it's correct
		assertEquals("mathieu@carleton.ca", t_table.get(t_table.size() - 1).getUsername());
		
	}
	
	@Test
	public void testUserInvalid() 
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Your input should in this format:'username,password'", OutputHandler.CREATEUSER);
		Output result = testOH.createUser(t_user_invalid);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testUserNotEmail() 
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Your input should in this format:'username,password'", OutputHandler.CREATEUSER);
		Output result = testOH.createUser(t_user_not_email);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testZRemoveNonExistant()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The User Does Not Exist!", OutputHandler.DELETEUSER);
		Output result = testOH.deleteUser(t_user_delete_notexist);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testZRemoveActiveLoan()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Active Loan Exists!", OutputHandler.CLERK);
		Output preResult = testOH.payFine("zhibo@carleton.ca");
		Output preResult2 = testOH.borrow("zhibo@carleton.ca,9781442668584,1");
		Output result = testOH.deleteUser(t_user_delete_fees);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testZRemoveOutstandingFees()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Outstanding Fee Exists!", OutputHandler.CLERK);
		String t_user_borrowing = "zhibo@carleton.ca,9781442668584,1";
		Output preResult = testOH.returnBook(t_user_borrowing);
		Output result = testOH.deleteUser(t_user_delete_fees);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testZRemoveSuccessful()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Success!", OutputHandler.CLERK);
		Output result = testOH.deleteUser(t_user_delete_success);
		
		assertEquals(expectedOut, result);
	}

}
