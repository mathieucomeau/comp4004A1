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

}
